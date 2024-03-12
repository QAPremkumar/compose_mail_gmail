package org.hplx.user_actions;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import org.hplx.config_manager.ReadLocatorUtils;

public class InputHelper {

    private InputHelper() {
    }

    public static boolean typeCustom(Page page, String locator, String inputValue) {
        return performInputAction(page, locator, inputValue, (l, input) -> {
            l.scrollIntoViewIfNeeded();
            l.type(input);
        });
    }

    public static boolean fillCustom(Page page, String locator, String inputValue) {
        return performInputAction(page, locator, inputValue, (l, input) -> {
            l.scrollIntoViewIfNeeded();
            l.fill(input);
        });
    }

    public boolean typeAndEnter(Page page, String locator, String value) {
        return typeCustom(page, locator, value) && ClickHelper.pressEnter(page, locator);
    }

    public void inputViaKeyboard(Page page, String locator, String value) {
        page.keyboard().type(value);
    }

    public static String getText(Page page, String locator) {
        Locator l = ReadLocatorUtils.getObjectLocator(page, locator);
        String text = l.inputValue();
        System.out.println("User successfully input value: " + text);
        return text;
    }

    public static boolean typeByXpathText(Page page, String locator, String text, String inputValue) {
        return performInputAction(page, locator, inputValue, (l, input) -> {
            Locator xpathLocator = ReadLocatorUtils.getXpathByText(page, locator, text);
            xpathLocator.waitFor();
            xpathLocator.scrollIntoViewIfNeeded();
            xpathLocator.clear();
            xpathLocator.type(input);
        });
    }

    private static boolean performInputAction(Page page, String locator, String inputValue, InputAction inputAction) {
        boolean status = false;
        try {
            Locator l = ReadLocatorUtils.getObjectLocator(page, locator);
            inputAction.perform(l, inputValue.trim());
            status = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

    private interface InputAction {
        void perform(Locator l, String input);
    }
}
