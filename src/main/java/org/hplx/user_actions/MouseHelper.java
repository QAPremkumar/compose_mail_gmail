package org.hplx.user_actions;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.PlaywrightException;
import com.microsoft.playwright.options.KeyboardModifier;
import com.microsoft.playwright.options.MouseButton;
import org.hplx.config_manager.ReadLocatorUtils;

import java.util.Arrays;
import java.util.Collections;

public class MouseHelper {
    private MouseHelper() {
    }

    public static boolean mouseOver(Page page, String locator) {
        return performMouseAction(page, locator, (l) -> {
            l.hover();
        });
    }

    public static boolean mouseOver(Page page, String locator, Locator.HoverOptions hoverOptions) {
        return performMouseAction(page, locator, (l) -> {
            l.hover(hoverOptions);
        });
    }

    public static boolean clickButton(Page page, String locator) {
        return performMouseAction(page, locator, (l) -> {
            l.scrollIntoViewIfNeeded();
            l.click();
        });
    }

    public static boolean doubleClickButton(Page page, String locator) {
        return performMouseAction(page, locator, (l) -> {
            l.scrollIntoViewIfNeeded();
            l.dblclick();
        });
    }

    public static boolean clickProgrammatically(Page page, String locator) {
        return performMouseAction(page, locator, (l) -> {
            l.dispatchEvent("click");
        });
    }

    public static boolean clickForcefully(Page page, String locator) {
        return performMouseAction(page, locator, (l) -> {
            l.scrollIntoViewIfNeeded();
            l.click(new Locator.ClickOptions().setForce(true));
        });
    }

    public static boolean rightClick(Page page, String locator) {
        return performMouseAction(page, locator, (l) -> {
            l.click(new Locator.ClickOptions().setButton(MouseButton.RIGHT));
        });
    }

    public static boolean shiftClick(Page page, String locator) {
        return performMouseAction(page, locator, (l) -> {
            l.click(new Locator.ClickOptions().setModifiers(Collections.singletonList(KeyboardModifier.SHIFT)));
        });
    }

    public static boolean hoverOverElement(Page page, String locator) {
        return performMouseAction(page, locator, (l) -> {
            l.hover();
        });
    }

    public static boolean clickTopLeftCorner(Page page, String locator) {
        return performMouseAction(page, locator, (l) -> {
            l.click(new Locator.ClickOptions().setPosition(0, 0));
        });
    }

    public static boolean singleClickByForce(Page page, String locator) {
        return performMouseAction(page, locator, (l) -> {
            l.click(new Locator.ClickOptions().setForce(true));
        });
    }

    public static boolean singleClickByForceProgrammatically(Page page, String locator) {
        return performMouseAction(page, locator, (l) -> {
            l.dispatchEvent("click");
        });
    }

    private static boolean performMouseAction(Page page, String locator, MouseAction mouseAction) {
        try {
            Locator l = ReadLocatorUtils.getObjectLocator(page, locator);
            mouseAction.perform(l);
            System.out.println("User performed a mouse action on :=> " + locator);
            return true;
        } catch (Exception e) {
            System.out.println("User facing issue while performing a mouse action on :=> " + locator);
            e.printStackTrace();
            return false;
        }
    }

    private interface MouseAction {
        void perform(Locator locator);
    }
}