package org.hplx.config_manager;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import org.hplx.custom_exceptions.PropertyFileUsageException;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

import static org.hplx.constants.FrameworkConstants.LOCATOR_CONFIG_PATH;


public class ReadLocatorUtils {

    private static Properties property = new Properties();
    private static final Map<String, String> CONFIGMAP = new HashMap<>();

    private ReadLocatorUtils() {
    }

    static {
        try {
            FileInputStream file = new FileInputStream(System.getProperty("user.dir") + LOCATOR_CONFIG_PATH);
            property.load(file);
            for (Map.Entry<Object, Object> entry : property.entrySet()) {
                CONFIGMAP.put(String.valueOf(entry.getKey()), String.valueOf(entry.getValue()).trim()); //remove the trailing and leading spaces
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    public static String get(String key) {
        if (Objects.isNull(key) || Objects.isNull(CONFIGMAP.get(key.toLowerCase()))) {
            //throw new PropertyFileUsageException("Property name " + key + " is not found. Please check config.properties");
        }
        return CONFIGMAP.get(key);
    }

    public static Locator getObjectLocator(Page page, String locatorName) {
        // System.out.println("Locator name : " + locatorName);
        String locatorProperty = ReadLocatorUtils.get(locatorName);
        // System.out.println("locatorProperty : " + locatorProperty);
        String locatorType = locatorProperty.split(":", 2)[0];
        String locatorValue = locatorProperty.split(":", 2)[1];
        //  System.out.println("locatorType : " + locatorType);
        //  System.out.println("locatorValue: " + locatorValue);
        Locator locator = null;
        switch (locatorType.toUpperCase()) {
            case "ID":
                locator = page.locator("id=" + locatorValue);
                break;
            case "NAME":
                locator = page.locator("name=" + locatorValue);
                break;
            case "CSS":
                locator = page.locator(locatorValue);
                break;
            case "TEXT":
//                <span>Welcome, John</span>
                locator = page.getByText(locatorValue, new Page.GetByTextOptions().setExact(true));
                break;
            case "PLACEHOLDER":
//                <input type="email" placeholder="name@example.com" />
                locator = page.getByPlaceholder(locatorValue);
                break;
            case "LABEL":
//                <label>Password <input type="password" /></label>
                locator = page.getByLabel(locatorValue);
                break;
            case "XPATH":
                locator = page.locator(locatorValue);
                break;
            case "ALTTEXT":
//                <img alt="playwright logo" src="/img/playwright-logo.svg" width="100" />
                locator = page.getByAltText(locatorValue);
                break;
            case "TITLE":
//                <span title='Issues count'>25 issues</span>
                locator = page.getByTitle(locatorValue);
                break;
            case "FIRSTELEMENT":
//                <span title='Issues count'>25 issues</span>
                locator = page.locator(locatorValue).first();
                break;
            case "LINK":
                locator = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(locatorValue));
                break;
            case "CHECKBOX":
//                <h3>Sign up</h3>
//                    <label>
//                      <input type="checkbox" /> Subscribe
//                    </label>
//                    <br/>
//                <button>Submit</button>
                locator = page.getByRole(AriaRole.CHECKBOX, new Page.GetByRoleOptions().setName(locatorValue));
                break;
            case "TEXTBOX":
                locator = page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName(locatorValue));
                break;
            case "BUTTON":
                locator = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName(locatorValue));
                break;
            case "HEADING":
                locator = page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName(locatorValue));
                break;
            case "COMBOBOX":
                locator = page.getByRole(AriaRole.COMBOBOX, new Page.GetByRoleOptions().setName(locatorValue));
                break;
            case "DATATESTID":
                locator = page.getByTestId(locatorValue);
                break;

        }
        return locator;
    }


    public static Locator getLocator_List(Page page) {
        Locator rows = page.getByRole(AriaRole.LISTITEM);
        return rows;
    }
    public static Locator getXpathByText(Page page, String locatorName, String text) {
        //System.out.println("Locator name : " + locatorName);
        String locatorProperty = ReadLocatorUtils.get(locatorName);
        //System.out.println("locatorProperty : " + locatorProperty);
        String locatorValue = locatorProperty.split(":", 2)[1];
        String locator = String.format(locatorValue, text);
        return page.locator(locator);
    }
    public static Locator getFrameLocator (Page page, String locatorName, String frameLocator) {
        //System.out.println("Locator name : " + frameLocator);
        String frameLocatorProperty = ReadLocatorUtils.get(frameLocator);
        //System.out.println("locatorProperty : " + frameLocatorProperty);
        String frameLocatorValue = frameLocatorProperty.split(":", 2)[1];

        System.out.println("Locator name : " + locatorName);
        String locatorProperty = ReadLocatorUtils.get(locatorName);
        System.out.println("locatorProperty : " + locatorProperty);
        String locatorValue = locatorProperty.split(":", 2)[1];


        return page.frameLocator(frameLocatorValue).locator(locatorValue);
    }
}
