package org.hplx.user_actions;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.assertions.LocatorAssertions;
import org.hamcrest.MatcherAssert;
import org.hplx.config_manager.ReadLocatorUtils;
import org.testng.Assert;

import java.util.List;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.CoreMatchers.is;

public class AssertHelper {

    private AssertHelper() {
    }



    // Assert the checked state
    public static void is_checkbox_checked(Page page, String locator) {
        assertThat(ReadLocatorUtils.getObjectLocator(page, locator)).isChecked();
    }
    // Assert the selected state
    public static void is_radiobutton_selected(Page page, String locator) {
        assertThat(ReadLocatorUtils.getObjectLocator(page, locator)).isChecked();
    }

    // Assert the is visible state
    public static void isVisible(Page page, String locator) throws InterruptedException {
        try{
            ReadLocatorUtils.getObjectLocator(page, locator).wait(2000);
            assertThat( ReadLocatorUtils.getObjectLocator(page, locator)).isVisible();
        }catch (Exception e){

        }
    }
    // Assert the is enable state
    public static void isEnabled(Page page, String locator) {
        assertThat(ReadLocatorUtils.getObjectLocator(page, locator)).isEnabled();
    }
    // Assert the  isAttached state
    public static void isAttached(Page page, String locator) {
        assertThat(ReadLocatorUtils.getObjectLocator(page, locator)).isAttached();
    }
    // Assert the isChecked state
    public static void isChecked(Page page, String locator) {
        assertThat(ReadLocatorUtils.getObjectLocator(page, locator)).isChecked();
    }
    // Assert the isDisabled state
    public static void isDisabled(Page page, String locator) {
        assertThat(ReadLocatorUtils.getObjectLocator(page, locator)).isDisabled();
    }
    // Assert the isEditable state
    public static void isEditable(Page page, String locator) {
        assertThat(ReadLocatorUtils.getObjectLocator(page, locator)).isEditable();
    }
    // Assert the isDisabled state
    public static void isEmpty(Page page, String locator) {
        assertThat(ReadLocatorUtils.getObjectLocator(page, locator)).isDisabled();
    }
    // Assert the if error state
    public static void isErrorPage(Page page, String locator) {
        assertThat(ReadLocatorUtils.getObjectLocator(page, locator)).not().containsText("error");
    }

    // Assert the if containsText state
    public static void containsText(Page page, String locator,String  text) {
        assertThat(ReadLocatorUtils.getObjectLocator(page, locator)).containsText(text);
    }

    // Assert the hasAttributestate
    public static void hasAttribute(Page page, String locator, String attribute_type) {
        assertThat(ReadLocatorUtils.getObjectLocator(page, locator)).hasAttribute("type",attribute_type);
    }


    // Assert the hasClass
    public static void hasClass(Page page, String locator,String class_name) {
        assertThat(ReadLocatorUtils.getObjectLocator(page, locator)).hasClass(class_name);
    }

    // Assert the hasCount
    public static void hasCount(Page page, String locator,int number) {
        assertThat(ReadLocatorUtils.getObjectLocator(page, locator)).hasCount(number);
    }
    // Assert the hasCSS
    public static void hasCount(Page page, String locator,String css_name) {
        assertThat(ReadLocatorUtils.getObjectLocator(page, locator)).hasCSS("display",css_name);
    }
    // Assert the hasText
    public static void hasText(Page page, String locator,String text) {
        assertThat(ReadLocatorUtils.getObjectLocator(page, locator)).hasText(text);
    }
    public static void hasText1(Page page,String locator, String expectedText) {
        assertThat(ReadLocatorUtils.getObjectLocator(page, locator)).hasText(expectedText, new LocatorAssertions.HasTextOptions().setIgnoreCase(true));
    }
    public static void assertAttribute(Page page,String locator, String attributeName, String attributeValue) {
        assertThat(ReadLocatorUtils.getObjectLocator(page, locator)).hasAttribute(attributeName, attributeValue);
    }

    public static <T> void assertEquals(T actual, T expected, String message) {
        try {
            MatcherAssert.assertThat(message, actual, equalTo(expected));
            //ExtentLogger.pass(message + "<b><i>Actual: </i> </b>" + actual + " and <b><i> Expected: equal To </i> </b>" + expected);
        } catch (AssertionError assertionError) {
            //ExtentLogger.fail("<b><i>" + assertionError.getMessage() + "</i></b>");
            Assert.fail(assertionError.getMessage());
        }
    }

    public static void assertContains(String actual, String expected, String message) {
        try {
            MatcherAssert.assertThat(message, actual, containsString(expected));
            //ExtentLogger.pass(message + "<b><i>Actual: </i> </b>" + actual + " and <b><i> Expected: Contains String </i> </b>" + expected);
        } catch (AssertionError assertionError) {
            //ExtentLogger.fail("<b><i>" + assertionError.getMessage() + "</i></b>");
            Assert.fail(assertionError.getMessage());
        }
    }

    public static void assertContainsIgnoreCase(String actual, String expected, String message) {
        try {
            MatcherAssert.assertThat(message, actual, containsStringIgnoringCase(expected));
            //ExtentLogger.pass(message + "<b><i>Actual: </i> </b>" + actual + " and <b><i> Contains String IgnoringCase: </i> </b>" + expected);
        } catch (AssertionError assertionError) {
            //ExtentLogger.fail("<b><i>" + assertionError.getMessage() + "</i></b>");
            Assert.fail(assertionError.getMessage());
        }
    }

    public static <T> void assertHasItem(List<T> actual, T expected, String message) {
        try {
            MatcherAssert.assertThat(message, actual, hasItem(expected));
            //ExtentLogger.pass(message + "<b><i>Actual: </i> </b>" + actual + " and <b><i> Expected: Has Item </i> </b>" + expected);
        } catch (AssertionError assertionError) {
            //ExtentLogger.fail("<b><i>" + assertionError.getMessage() + "</i></b>");
            Assert.fail(assertionError.getMessage());
        }
    }
    public static <T> void assertHasItems(List<T> actual, T... expected) {
        try {
            MatcherAssert.assertThat(actual, hasItems(expected));
            //ExtentLogger.pass("<b><i>Actual: </i> </b>" + actual + " and <b><i> Has Items: </i> </b>" + Arrays.toString(expected));
        } catch (AssertionError assertionError) {
            //ExtentLogger.fail("<b><i>" + assertionError.getMessage() + "</i></b>");
            Assert.fail(assertionError.getMessage());
        }
    }

    public static void assertTrue(boolean result, String message) {
        try {
            MatcherAssert.assertThat(message, result, is(true));
            //ExtentLogger.pass("<b><i>" + message + "</b></i>");
        } catch (AssertionError assertionError) {
            //ExtentLogger.fail("<b><i>" + assertionError.getMessage() + "</i></b>");
            Assert.fail(assertionError.getMessage());
        }
    }

    public static void assertFalse(boolean result, String message) {
        try {
            MatcherAssert.assertThat(message, result, is(false));
            //ExtentLogger.pass("<b><i>" + message + "</b></i>");
        } catch (AssertionError assertionError) {
            //ExtentLogger.fail("<b><i>" + assertionError.getMessage() + "</i></b>");
            Assert.fail(assertionError.getMessage());

        }
    }


}
