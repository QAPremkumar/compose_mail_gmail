package org.hplx.user_actions;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import org.hplx.config_manager.ReadLocatorUtils;

import java.util.List;

public class OutputHelper {

    private OutputHelper() {
    }

    public String getPageTitle(Page page) {
        return page.title();
    }

    public String getCurrentUrl(Page page) {
        return page.url();
    }

    public String getInnerHtml(Page page, String locator) {
        return getObjectLocator(page, locator).innerHTML();
    }

    public String getInnerText(Page page, String locator) {
        return getObjectLocator(page, locator).innerText();
    }

    public String getAttribute(Page page, String locator, String attributeValue) {
        return getObjectLocator(page, locator).getAttribute(attributeValue);
    }

    public static String getInputValue(Page page, String locator) {
        return getObjectLocator(page, locator).inputValue();
    }

    public static String getTextContent(Page page, String locator) {
        return getObjectLocator(page, locator).textContent();
    }

    public static List<String> getAllTextContent(Page page, String locator) {
        return getObjectLocator(page, locator).allTextContents();
    }

    public List<String> getAllInnerText(Page page, String locator) {
        return getObjectLocator(page, locator).allInnerTexts();
    }

    public void getAllLink(Page page, String locator) {
        List<String> linksList = getObjectLocator(page, locator).allInnerTexts();
        linksList.forEach(System.out::println);
    }

    public String getFirstTextFound(Page page, String locator) {
        Locator firstLocator = getObjectLocator(page, locator).first();
        if (firstLocator != null) {
            return firstLocator.textContent();
        }
        return null;
    }

    private static Locator getObjectLocator(Page page, String locator) {
        return ReadLocatorUtils.getObjectLocator(page, locator);
    }
}
