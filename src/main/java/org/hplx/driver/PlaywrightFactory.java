package org.hplx.driver;

import com.microsoft.playwright.*;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static org.hplx.config_manager.ConfigFactory.getConfig;

public class PlaywrightFactory {

    private static PlaywrightFactory instance = null;
    private final Playwright playwright;
    private final Browser browser;
    private final BrowserContext browserContext;
    private final Page page;

    private static ThreadLocal<Browser> tlBrowser = ThreadLocal.withInitial(() -> null);
    private static ThreadLocal<BrowserContext> tlBrowserContext = ThreadLocal.withInitial(() -> null);
    private static ThreadLocal<Page> tlPage = ThreadLocal.withInitial(() -> null);
    private static ThreadLocal<Playwright> tlPlaywright = ThreadLocal.withInitial(() -> null);

    private PlaywrightFactory() {
        playwright = Playwright.create();
        tlPlaywright.set(playwright);
        browser = null;
        browserContext = null;
        page = null;
    }

    public static PlaywrightFactory getInstance() {
        if (instance == null) {
            instance = new PlaywrightFactory();
        }
        return instance;
    }

    public static Playwright getPlaywright() {
        return tlPlaywright.get();
    }

    public static Browser getBrowser() {
        return tlBrowser.get();
    }

    public static BrowserContext getBrowserContext() {
        return tlBrowserContext.get();
    }

    public static Page getPage() {
        return tlPage.get();
    }

    public final void initBrowser() {
        String browserName = getConfig().browser_name().trim();
        System.out.println("Browser name is: " + browserName);

        // Set Playwright Java source directory
        final String SRC_DIRS = System.getenv("PLAYWRIGHT_JAVA_SRC") == null ? "src/test/java" : System.getenv("PLAYWRIGHT_JAVA_SRC");
        Map<String, String> env = new HashMap<>();
        env.put("PLAYWRIGHT_JAVA_SRC", SRC_DIRS);

        tlPlaywright.set(Playwright.create());

        if (browserName.equalsIgnoreCase("nonIncognitoChrome")) {
            launchNonIncognitoChrome();
        } else {
            launchOtherBrowsers(browserName);
        }

        getPage().setDefaultNavigationTimeout(120000);
    }

    private void launchNonIncognitoChrome() {
        BrowserContext context = getPlaywright().chromium().launchPersistentContext(
                Path.of("./Library/Application Support/Google/Chrome/profile " + new Random().nextInt(9)),
                new BrowserType.LaunchPersistentContextOptions().setHeadless(getConfig().is_headless())
                        .setJavaScriptEnabled(true)
                        .setTimeout(100000)
        );
        tlBrowserContext.set(context);
        tlPage.set(getBrowserContext().pages().get(0));
    }

    private void launchOtherBrowsers(String browserName) {
        switch (browserName.toLowerCase()) {
            case "chromium":
                tlBrowser.set(getPlaywright().chromium().launch(
                        new BrowserType.LaunchOptions()
                                .setHeadless(getConfig().is_headless())
                                .setArgs(List.of("--start-maximized"))
                ));
                break;
            case "firefox":
                tlBrowser.set(getPlaywright().firefox().launch(
                        new BrowserType.LaunchOptions()
                                .setHeadless(getConfig().is_headless())
                                .setArgs(List.of("--start-maximized"))
                ));
                break;
            case "safari":
                tlBrowser.set(getPlaywright().webkit().launch(
                        new BrowserType.LaunchOptions()
                                .setHeadless(getConfig().is_headless())
                                .setArgs(List.of("--start-maximized"))
                ));
                break;
            case "chrome":
                tlBrowser.set(getPlaywright().chromium().launch(
                        new BrowserType.LaunchOptions()
                                .setChannel("chrome")
                                .setHeadless(getConfig().is_headless())
                                .setArgs(List.of("--start-maximized"))
                ));
                break;
            case "edge":
                tlBrowser.set(getPlaywright().chromium().launch(
                        new BrowserType.LaunchOptions()
                                .setChannel("msedge")
                                .setHeadless(getConfig().is_headless())
                                .setArgs(List.of("--start-maximized"))
                ));
                break;
            default:
                System.out.println("Please pass the right browser name...");
                break;
        }
        tlBrowserContext.set(getBrowser().newContext(
                new Browser.NewContextOptions().setViewportSize(null)
        ));
        tlPage.set(getBrowserContext().newPage());
    }

    public static void closeBrowser() {
        Page page = tlPage.get();
        BrowserContext context = tlBrowserContext.get();
        Browser browser=tlBrowser.get();
        if (page != null) {
            page.close();
            tlPage.remove();
        }
        if (context != null) {
            context.close();
            tlBrowserContext.remove();
        }
           if (browser != null) {
            browser.close();
            tlBrowser.remove();
        }
    }

    public static void closeDriver() {
        Playwright playwright = tlPlaywright.get();
        if (playwright != null) {
            playwright.close();
            tlPlaywright.remove();
        }
    }


}