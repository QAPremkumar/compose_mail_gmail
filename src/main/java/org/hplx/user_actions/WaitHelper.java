package org.hplx.user_actions;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.PlaywrightException;
import com.microsoft.playwright.options.WaitForSelectorState;
import org.hplx.config_manager.ConfigFactory;
import org.hplx.config_manager.ReadLocatorUtils;

import static org.hplx.constants.FrameworkConstants.DEFAULT_TIMEOUT;

public class WaitHelper {
    private static final int DEFAULT_TIMEOUT = 10; // Default timeout in seconds

    private WaitHelper() {
    }

    public static void wait(Page page, int timeInSeconds) {
        page.waitForTimeout(timeInSeconds * 1000L);
    }

    public static boolean pageWait(Page page, double timeoutInSeconds) {
        try {
            page.waitForTimeout((int) (timeoutInSeconds * 1000L));
            return true;
        } catch (PlaywrightException e) {
            return false;
        }
    }

    public static void waitForURL(Page page, String relativeURL) {
        page.waitForURL(ConfigFactory.getConfig().url() + relativeURL);
    }

    public static boolean waitForDisappearance(Page page, String locator) {
        try {
            ReadLocatorUtils.getObjectLocator(page, locator)
                    .waitFor(new Locator.WaitForOptions().setTimeout(DEFAULT_TIMEOUT)
                            .setState(WaitForSelectorState.HIDDEN));
            return true;
        } catch (PlaywrightException e) {
            return false;
        }
    }

    public static boolean waitForAppearance(Locator locator) {
        try {
            locator.waitFor(new Locator.WaitForOptions().setTimeout(DEFAULT_TIMEOUT)
                    .setState(WaitForSelectorState.VISIBLE));
            return true;
        } catch (PlaywrightException e) {
            return false;
        }
    }

    public static boolean waitFor(Locator locator, Locator.WaitForOptions waitForOptions) {
        try {
            locator.waitFor(waitForOptions);
            return true;
        } catch (PlaywrightException e) {
            return false;
        }
    }
}