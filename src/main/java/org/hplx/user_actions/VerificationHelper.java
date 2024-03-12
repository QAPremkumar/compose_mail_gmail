package org.hplx.user_actions;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.PlaywrightException;
import com.microsoft.playwright.options.LoadState;
import org.hplx.config_manager.ReadLocatorUtils;

import java.util.regex.Pattern;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.testng.Assert.assertTrue;

public class VerificationHelper {

    private VerificationHelper() {
    }

    public static void verifyPageURL(Page page, String pageName) {
        page.onLoad(p -> System.out.println("Page loaded!"));
        System.out.println("Page navigated should be :=> " + pageName);
        page.waitForURL("Pattern.compile(\".*/\" + pageName + \"+");
        String regexPattern = pageName.contains(".php") ? ".*/" + pageName + "/" : ".*/" + pageName + ".php/";
        assertURLMatchesPattern(page, regexPattern);
    }
    public static void assertURLMatchesPattern(Page page, String regexPattern) {
        try {
            page.waitForURL(Pattern.compile(regexPattern));
        } catch (Exception e) {
            System.out.println("URL did not match the expected pattern: " + regexPattern);
            throw e; // Re-throw the exception to indicate failure
        }
    }
    public static void verifyPageTitle(Page page, String title) {
        System.out.println("Page navigated should be :->" + page.url());
        String pageTitle = page.title();
        if (pageTitle.contains(title)) {
            assertThat(page).hasTitle(title);
            System.out.println("Partial Page Title Present, Actual Page Title : " + pageTitle);
        }
    }

    public static void verifyPageHasAttribute(Page page, String locator, String attributeType) {
        assertThat(ReadLocatorUtils.getObjectLocator(page, locator)).hasAttribute("type", attributeType);
    }

    public static boolean isExist(Page page, String locator) {
        try {
            System.out.println("User is supposed to find :=><b><i>" + locator + "</i></b>");
           Locator l= ReadLocatorUtils.getObjectLocator(page, locator);
                    l.scrollIntoViewIfNeeded();
                    l.isVisible();
            System.out.println("User successfully found expected field :=><b><i>" + locator + "</i></b>");
            return true;
        } catch (Exception e) {
            System.out.println("User facing issue in finding :=> " + locator + "<br>ERROR IS:=><b><i>" + e.getLocalizedMessage() + "</i></b>");
            return false;
        }
    }

    public static boolean isVisible(Page page, String locator) {
        try {
            System.out.println("User is supposed to find :=><b><i>" + locator + "</i></b>");
            AssertHelper.isVisible(page, locator);
            System.out.println("User successfully found expected field :=><b><i>" + locator + "</i></b>");
            return true;
        } catch (Exception e) {
            System.out.println("User facing issue in finding :=> " + locator + "<br>ERROR IS:=><b><i>" + e.getLocalizedMessage() + "</i></b>");
            return false;
        }
    }

    public static boolean isChecked(Page page, String locator) {
        try {
            System.out.println("User is supposed to find :=><b><i>" + locator + "</i></b>");
            AssertHelper.isChecked(page, locator);
            System.out.println("Element is checked as expected!!!");
            return true;
        } catch (Exception e) {
            System.out.println("User facing issue in finding :=> " + locator + "<br>ERROR IS:=><b><i>" + e.getLocalizedMessage() + "</i></b>");
            return false;
        }
    }

    public static boolean isEnabled(Page page, String locator) {
        try {
            System.out.println("User is supposed to find :=><b><i>" + locator + "</i></b>");
            AssertHelper.isEnabled(page, locator);
            System.out.println("Element is enabled as expected!!!");
            return true;
        } catch (Exception e) {
            System.out.println("User facing issue in finding :=> " + locator + "<br>ERROR IS:=><b><i>" + e.getLocalizedMessage() + "</i></b>");
            return false;
        }
    }

    public static boolean isDisabled(Page page, String locator) {
        try {
            System.out.println("User is supposed to find if :=><b><i>" + locator + " is disabled</i></b>");
            ReadLocatorUtils.getObjectLocator(page, locator).highlight();
            assertThat(ReadLocatorUtils.getObjectLocator(page, locator)).isDisabled();
            System.out.println("Element is disabled as expected!!!");
            return true;
        } catch (PlaywrightException p) {
            System.out.println("Element is not disabled as expected!!! " + p);
            return false;
        }
    }

    public static void isEditable(Page page, String locator) {
        try {
            System.out.println("User is supposed to find  :->  " + ReadLocatorUtils.getObjectLocator(page, locator).innerText());
            ReadLocatorUtils.getObjectLocator(page, locator).highlight();
            assertThat(ReadLocatorUtils.getObjectLocator(page, locator)).isEditable();
            System.out.println("Element is editable as expected!!!");
        } catch (PlaywrightException p) {
            System.out.println("Element is not editable as expected!!! " + p);
        }
    }

    public static void isHidden(Page page, String locator) {
        try {
            System.out.println("User is supposed to find  :->  " + ReadLocatorUtils.getObjectLocator(page, locator).innerText());
            ReadLocatorUtils.getObjectLocator(page, locator).highlight();
            assertThat(ReadLocatorUtils.getObjectLocator(page, locator)).isHidden();
            System.out.println("Element is hidden as expected!!!");
        } catch (PlaywrightException p) {
            System.out.println("Element is not hidden as expected!!! " + p);
        }
    }

    public static void isEmpty(Page page, String locator) {
        System.out.println("User is supposed to find :-> " + ReadLocatorUtils.getObjectLocator(page, locator).innerText());
        ReadLocatorUtils.getObjectLocator(page, locator).highlight();
        assertThat(ReadLocatorUtils.getObjectLocator(page, locator)).isEmpty();
        System.out.println("Element is empty as expected!!!");
    }

    public static void containsText(Page page, String locator, String expectedText) {
        try {
            assertThat(ReadLocatorUtils.getObjectLocator(page, locator)).containsText(expectedText);
            System.out.println("Page has expected text :-> " + expectedText);
        } catch (PlaywrightException p) {
            System.out.println(expectedText + " text is not present as expected!!! " + p);
        }
    }

    public static boolean hasText(Page page, String locator, String expectedText) {
        try {
            assertThat(ReadLocatorUtils.getObjectLocator(page, locator)).hasText(expectedText);
            System.out.println("Page has expected text :-> " + expectedText);
            return true;
        } catch (PlaywrightException p) {
            System.out.println(expectedText + " text is not present as expected!!! " + p);
            return false;
        }
    }

    public static boolean hasAttribute(Page page, String locator, String type, String value) {
        try {
            assertThat(ReadLocatorUtils.getObjectLocator(page, locator)).hasAttribute(type, value);
            System.out.println("Page has expected attribute :-> " + value);
            return true;
        } catch (Exception e) {
            System.out.println("User facing issue in finding :=> " + value + "<br>ERROR IS:=><b><i>" + e.getLocalizedMessage() + "</i></b>");
            return false;
        }
    }

    public static void isErrorPage(Page page) {
        assertThat(page).not().hasURL("error");
    }

    public static boolean hasTitle(Page page, String title) {
        try {
            System.out.println("User is supposed to find title :->  " + title);
            assertThat(page).hasTitle(title);
            if (page.title().contains(title)) {
                System.out.println("User found title as :=><b><i>" + page.title() + "</i></b>");
                return true;
            }
        } catch (Exception e) {
            System.out.println("User facing issue in finding title :=> " + title + "<br>ERROR IS:=><b><i>" + e.getLocalizedMessage() + "</i></b>");
        }
        return false;
    }

    public static boolean hasURL(Page page, String url) {
        try {
            page.waitForLoadState(LoadState.LOAD);
            page.waitForLoadState(LoadState.NETWORKIDLE);
            page.waitForLoadState(LoadState.DOMCONTENTLOADED);
            page.waitForURL("**/" + url);
            assertThat(page).hasURL(url);
            System.out.println("Current URL is as expected :->  <b><i>" + page.url() + "</i></b>");
            return true;
        } catch (Exception e) {
            System.out.println("User facing issue in finding url :=> " + url + "<br>ERROR IS:=><b><i>" + e.getLocalizedMessage() + "</i></b>");
            return false;
        }
    }

    public static boolean hasCount(Page page, String locator, String elementsCount) {
        try {
            assertThat(ReadLocatorUtils.getObjectLocator(page, locator)).hasCount(Integer.parseInt(elementsCount));
            System.out.println("Page has expected count :-> " + elementsCount);
            return true;
        } catch (PlaywrightException p) {
            System.out.println(elementsCount + " not present as expected!!! " + p);
        }
        return false;
    }

    public static boolean isExistByText(Page page, String locator, String text) {
        try {
            System.out.println("User is supposed to find :=><b><i>" + locator + "</i></b>");
            Locator l = ReadLocatorUtils.getXpathByText(page, locator, text);
            l.scrollIntoViewIfNeeded();
            l.highlight();
            assertTrue(l.isVisible());
            System.out.println("User successfully found expected field :=><b><i>" + locator + "</i></b>");
            return true;
        } catch (Exception e) {
            System.out.println("User facing issue in finding :=> " + locator + "<br>ERROR IS:=><b><i>" + e.getLocalizedMessage() + "</i></b>");
        }
        return false;
    }

    public static boolean hasCountByText(Page page, String locator, String elementsCount, String text) {
        try {
            assertThat(ReadLocatorUtils.getXpathByText(page, locator, text)).hasCount(Integer.parseInt(elementsCount));
            System.out.println("Page has expected count :-> " + elementsCount);
            return true;
        } catch (PlaywrightException p) {
            System.out.println(elementsCount + " not present as expected!!! " + p);
        }
        return false;
    }

    public static void containsFrameText(Page page, String locator, String frameLocator, String expectedText) {
        try {
            assertThat(ReadLocatorUtils.getFrameLocator(page, locator, frameLocator)).containsText(expectedText);
            System.out.println("Page has expected text :-> " + expectedText);
        } catch (PlaywrightException p) {
            System.out.println(expectedText + " text is not present as expected!!! " + p);
        }
    }

    public static void waitForDisappear(Page page,int maxAttempts, int interval, String locator){
        System.out.println("Waiting for element to visible");
        boolean isElementDisappeared = false;
        for (int attempt = 0; attempt < maxAttempts; attempt++) {
            if (!page.isVisible(locator)) {
                isElementDisappeared = true;
            }
            page.waitForTimeout(interval);
        }
    }
}