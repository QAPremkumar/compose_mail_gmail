package org.hplx.constants;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import static org.hplx.config_manager.ConfigFactory.getConfig;

public class FrameworkConstants {

    // Project Paths
    static String PROJECT_PATH = System.getProperty("user.dir");
    public static final String REPORTS_TRACE_LOCATION = "./target/traces";

    // Timeout Constants (in milliseconds)
    public static final double DEFAULT_NAVIGATION_TIMEOUT = 300000; // 5 minutes
    public static final double DEFAULT_TIMEOUT = 60000; // 1 minute
    public static final double LONG_TIMEOUT = 180000; // 3 minutes
    public static final double SHORT_TIMEOUT = 30000; // 30 seconds
    public static final double MEDIUM_TIMEOUT = 120000; // 2 minutes

    // Viewport Dimensions
    public static final int VIEWPORT_WIDTH = 1800;
    public static final int VIEWPORT_HEIGHT = 963;

    // Locator Configuration
    public static final String LOCATOR_CONFIG_PATH = "/src/test/resources/Locators.properties";

    public static final String SAMPLE_PDF_PATH = "/src/test/resources/TestData/pin.pdf";

    // Project Information
    public static final String PROJECT_NAME = "Automation Framework - HealthPlix Web EMR";

    // Report Information
    public static final String REPORT_TITLE = "HealthPlix Web EMR Automation Execution Summary";
    public static final String REPORT_SUBJECT = "UI Automation || Execution Summary Report [Automated Email] :: " +
            LocalDateTime.now(ZoneId.of("Asia/Kolkata"))
                    .format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));


    // Browser Configuration
    public static final String DEFAULT_BROWSER_NAME = "chrome";
    public static final boolean DEFAULT_IS_HEADLESS = true;

    // Test Configuration
    public static final int DEFAULT_MAX_RETRY_COUNT = 3;
    public static final boolean DEFAULT_ENABLE_RECORD_VIDEO = false;
    public static final String DEFAULT_ENABLE_TRACING = "no";

    // Environment Configuration
    public static final String DEFAULT_ENVIRONMENT = "testprod";

    // URLs
    public static final String BASE_URL = getConfig().url();

    // Credentials
    public static final String USER_ID = getConfig().user_id();
    public static final String PASSWORD = getConfig().password();

    // Patient Information
    public static final String PATIENT_PHONE = getConfig().patient_phone();
    public static final String PATIENT_ID = getConfig().patient_id();
    public static final String PATIENT_NAME = getConfig().patient_name();

    public static final String PATIENT_ID_1 = getConfig().patient_id1();
    public static final String PATIENT_ID_2 = getConfig().patient_id2();

    // Private constructor to prevent instantiation
    private FrameworkConstants() {
    }
}

