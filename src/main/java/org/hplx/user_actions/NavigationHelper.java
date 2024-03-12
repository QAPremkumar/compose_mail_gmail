package org.hplx.user_actions;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.PlaywrightException;
import com.microsoft.playwright.options.WaitUntilState;
import org.hplx.constants.FrameworkConstants;

import java.util.function.Predicate;

public class NavigationHelper {
    private NavigationHelper() {
    }

    public boolean waitForNavigationTo(Page page, Predicate<String> condition) {
        try {
            page.waitForURL(condition);
            page.waitForLoadState();
            return true;
        } catch (PlaywrightException e) {
            //ExtentLogger.fail(e.getMessage());
            return false;
        }
    }

    public static void navigateToURL(Page page, String url) {
        boolean status = false;
        try {
            Page.NavigateOptions navigateOptions = new Page.NavigateOptions()
                    .setTimeout(FrameworkConstants.DEFAULT_TIMEOUT)
                    .setWaitUntil(WaitUntilState.NETWORKIDLE)
                    .setWaitUntil(WaitUntilState.LOAD)
                    .setWaitUntil(WaitUntilState.DOMCONTENTLOADED);

            page.navigate(url, navigateOptions);
            page.waitForLoadState();
            page.waitForSelector("body");

            if (!url.contains("hadmin")) {
                Page.WaitForURLOptions waitForURLOptions = new Page.WaitForURLOptions().setTimeout(FrameworkConstants.DEFAULT_TIMEOUT);
                page.waitForURL(url, waitForURLOptions);
            }
            //ExtentLogger.pass("User has successfully navigated to :=> <b><i>" + url + "</i></b>");
            System.out.println("User has successfully navigated to :=> " + url);
            status = true;
        } catch (Exception e) {
            //ExtentLogger.fail(ICON_BUG + "Navigation has some issue, Kindly check :=> <br>Issue is:=><b><i>" + e.getLocalizedMessage() + "</i></b>");
            System.out.println("Navigation has some issue, Kindly check :=> " + page.url());
            e.getStackTrace();
        }
    }

    public static boolean navigateBack(Page page) {
        return navigate(page, "back");
    }

    public static boolean navigateForward(Page page) {
        return navigate(page, "forward");
    }

    public static boolean reloadPage(Page page) {
        return navigate(page, "reload");
    }

    private static boolean navigate(Page page, String action) {
        boolean status = false;
        try {
            String currentUrl = page.url();
            switch (action) {
                case "back":
                    page.goBack();
                    break;
                case "forward":
                    page.goForward();
                    break;
                case "reload":
                    page.reload();
                    break;
            }
            page.waitForLoadState();
            //ExtentLogger.pass("User has successfully navigated " + action + " to :=><b><i>" + page.url() + "</i></b>");
            System.out.println("User has successfully navigated " + action + " to :=> " + page.url());
            status = true;
        } catch (Exception e) {
            //ExtentLogger.fail(ICON_BUG + "Navigation has some issue, Kindly check :=> <br>Issue is:=><b><i>" + e.getLocalizedMessage() + "</i></b>");
            System.out.println("Navigation has some issue, Kindly check :=> " + page.url());
            e.getStackTrace();
        }
        return status;
    }
}