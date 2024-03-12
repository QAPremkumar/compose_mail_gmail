package org.hplx.config_manager;

import org.aeonbits.owner.Config;

/**
 * Configuration interface for the framework.
 */
@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "system:env",
        "file:${user.dir}/src/main/resources/framework_config/config.properties",
        "file:${user.dir}/src/main/resources/environments/staging.properties",
        "file:${user.dir}/src/main/resources/environments/production-blue.properties",
        "file:${user.dir}/src/main/resources/environments/production-green.properties",
        "file:${user.dir}/src/main/resources/environments/testprod.properties",
        "file:${user.dir}/src/main/resources/environments/testprod2.properties",
        "file:${user.dir}/src/main/resources/environments/md_beta.properties"
})
public interface FrameworkConfig extends Config {
    /**
     * Gets the name of the browser.
     *
     * @return The browser name.
     */
    @DefaultValue("chrome")
    String browser_name();

    @DefaultValue("testprod")
    String environment();

    @Key("${environment}.url")
    String url();

    @Key("${environment}.userid")
    String user_id();

    @Key("${environment}.password")
    String password();

    @Key("${environment}.patient_phone")
    String patient_phone();

    @Key("${environment}.patient_id")
    String patient_id();

    @Key("${environment}.patient_name")
    String patient_name();


    @Key("${environment}.patient_id1")
    String patient_id1();

    @Key("${environment}.patient_id2")
    String patient_id2();


    boolean is_headless();

    boolean enableRecordVideo();

    String enableTracing();

    String send_email_to_users();

    String[] email_user_to();

    int MAX_RETRY_COUNT();


}