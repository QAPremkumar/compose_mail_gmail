package org.hplx.user_actions;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import org.hplx.config_manager.ReadLocatorUtils;

public class ClickHelper {
    private static final int MAX_RETRIES = 3;

    private ClickHelper() {
    }

    public static boolean pressEnter(Page page, String locator) {
        return performClickAction(page, locator, () -> {
            Locator l = ReadLocatorUtils.getObjectLocator(page, locator);
            l.scrollIntoViewIfNeeded();
            l.press("Enter");
        });
    }

    public static boolean clickAnyway(Page page, String locator) {
        return performClickAction(page, locator, () -> {
            Locator l = ReadLocatorUtils.getObjectLocator(page, locator);
            l.scrollIntoViewIfNeeded();
            l.click(new Locator.ClickOptions().setForce(true));
        });
    }

    public static boolean clickButton(Page page, String locator) {
        return performClickAction(page, locator, () -> {
            Locator l = ReadLocatorUtils.getObjectLocator(page, locator);
            l.scrollIntoViewIfNeeded();
            l.click();
        });
    }

    public static boolean clickByCoordinate(Page page, String locator, int xCoord, int yCoord) {
        return performClickAction(page, locator, () -> {
            Locator l = ReadLocatorUtils.getObjectLocator(page, locator);
            l.scrollIntoViewIfNeeded();
            l.click(new Locator.ClickOptions().setPosition(xCoord, yCoord));
        });
    }

    public static boolean doubleClick(Page page, String locator) {
        return performClickAction(page, locator, () -> {
            Locator l = ReadLocatorUtils.getObjectLocator(page, locator);
            l.scrollIntoViewIfNeeded();
            l.dblclick();
        });
    }

    public static boolean clickProgrammatically(Page page, String locator) {
        return performClickAction(page, locator, () -> {
            Locator l = ReadLocatorUtils.getObjectLocator(page, locator);
            l.scrollIntoViewIfNeeded();
            l.dispatchEvent("click");
        });
    }

    public static boolean clickForcefully(Page page, String locator) {
        return performClickAction(page, locator, () -> {
            Locator l = ReadLocatorUtils.getObjectLocator(page, locator);
            l.scrollIntoViewIfNeeded();
            l.click(new Locator.ClickOptions().setForce(true));
        });
    }

    public static boolean jsClick(Page page, String locator) {
        return performClickAction(page, locator, () -> {
            Locator l = ReadLocatorUtils.getObjectLocator(page, locator);
            l.scrollIntoViewIfNeeded();
            l.evaluate("node => node.click();");
        });
    }

    public static boolean clickElementByText(Page page, String locatorName, String text) {
        return performClickAction(page, locatorName, () -> {
            Locator l = ReadLocatorUtils.getXpathByText(page, locatorName, text);
            l.click();
            page.waitForLoadState();
        });
    }

    private static boolean performClickAction(Page page, String locator, ClickAction clickAction) {
        int retries = 0;
        boolean clicked = false;

        while (!clicked && retries < MAX_RETRIES) {
            try {
                clickAction.perform();
                clicked = true;
            } catch (Exception e) {
                // Handle the exception or log it
                retries++;
            }
        }

        return clicked;
    }

    private interface ClickAction {
        void perform();
    }
}


