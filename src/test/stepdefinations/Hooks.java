package org.hplx.stepdefinations;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.Tracing;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import io.cucumber.java.*;
import io.qameta.allure.Allure;

import org.hplx.driver.PlaywrightFactory;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.hplx.config_manager.ConfigFactory.getConfig;
import static org.hplx.constants.FrameworkConstants.MEDIUM_TIMEOUT;
import static org.hplx.driver.PlaywrightFactory.*;

public class Hooks {

    @BeforeAll
    public static void beforeAll() {
        System.out.println("================ BEFORE ALL ================");
    }

    @Before("@login")
    public void initialize(Scenario s) {
        initPlaywrightAndBrowser(s);
    }

    @Before("not @login")
    public void initializeWithLoginState(Scenario s) {
        initPlaywrightAndBrowser(s);
        if ("yes".equalsIgnoreCase(getConfig().enableTracing())) {
            startTracing(s);
        }
    }

    @After(order = 0)
    public void closure(Scenario s) throws IOException {
        System.out.println("Completed execution for the scenario: " + s.getName());
        if (s.isFailed()) {
            attachScreenshot(s);
            if ("yes".equalsIgnoreCase(getConfig().enableTracing())) {
                stopAndAttachTracing(s);
            }
        }
        closeBrowser();
    }

    @AfterAll
    public static void shutdown() {
        closeDriver();
        System.out.println("================================Testing Done!!!===============================");
    }

    private void initPlaywrightAndBrowser(Scenario s) {
        PlaywrightFactory playwrightFactory = PlaywrightFactory.getInstance();
        playwrightFactory.initBrowser();
        configureTracing(s);
        PlaywrightAssertions.setDefaultAssertionTimeout(MEDIUM_TIMEOUT);
        System.out.println("Current Thread info = " + Thread.currentThread().getId() + ", Driver = " + getPage());
        System.out.println("Started execution for the scenario: " + s.getName());
    }

    private void configureTracing(Scenario s) {
        PlaywrightFactory.getBrowserContext().tracing().start(new Tracing.StartOptions()
                .setScreenshots(true)
                .setSnapshots(true)
                .setSources(true)
                .setTitle(s.getName()));
    }

    private void startTracing(Scenario s) {
            configureTracing(s);
    }

    private void attachScreenshot(Scenario s) {
        byte[] screenshot = getPage().screenshot(new Page.ScreenshotOptions()
                .setPath(Paths.get("screenshot.png"))
                .setFullPage(true));
        s.attach(screenshot, "image/png", s.getName());
    }

    private void stopAndAttachTracing(Scenario s) throws IOException {
        final Path tracePath = Paths.get("target/traces/trace-" + s.getName() + ".zip");
        PlaywrightFactory.getBrowserContext().tracing().stop(new Tracing.StopOptions().setPath(tracePath));
        try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(Files.readAllBytes(tracePath))) {
            Allure.addAttachment("My Trace", "application/zip", byteArrayInputStream, ".zip");
        }
    }
}
