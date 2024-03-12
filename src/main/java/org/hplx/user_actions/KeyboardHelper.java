package org.hplx.user_actions;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.PlaywrightException;
import org.hplx.config_manager.ReadLocatorUtils;

public class KeyboardHelper {
    private KeyboardHelper() {
    }

    public static boolean pressEnter(Page page, String locator) {
        return performKeyboardAction(page, locator, (l) -> {
            l.scrollIntoViewIfNeeded();
            l.press("Enter");
        });
    }

    public boolean sendKeyboardKeys(Page page, String key) {
        try {
            page.keyboard().press(key);
            return true;
        } catch (PlaywrightException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean clear(Page page, String locator) {
        try {
            ReadLocatorUtils.getObjectLocator(page, locator).clear();
            return true;
        } catch (PlaywrightException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean clear(Page page, String locator, Locator.ClearOptions options) {
        try {
            ReadLocatorUtils.getObjectLocator(page, locator).clear(options);
            return true;
        } catch (PlaywrightException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean clearAndSendKeys(Page page, String locator, String value) {
        return performKeyboardAction(page, locator, (l) -> {
            clear(page, locator);
            InputHelper.fillCustom(page, locator, value);
        });
    }

    public void dispatchControlRight(Page page, String locator, String value) {
        try {
            ReadLocatorUtils.getObjectLocator(page, locator).press("Control+ArrowRight");
        } catch (PlaywrightException e) {
            e.printStackTrace();
        }
    }

    private static boolean performKeyboardAction(Page page, String locator, KeyboardAction keyboardAction) {
        try {
            Locator l = ReadLocatorUtils.getObjectLocator(page, locator);
            keyboardAction.perform(l);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private interface KeyboardAction {
        void perform(Locator locator);
    }
}
