package org.stepdefinations;

import com.microsoft.playwright.*;
import com.microsoft.playwright.assertions.LocatorAssertions;
import com.microsoft.playwright.options.LoadState;
import com.microsoft.playwright.options.WaitUntilState;
import com.microsoft.playwright.Download;
import io.cucumber.java.en.*;
import org.apache.pdfbox.Loader;
import org.hplx.config_manager.ReadLocatorUtils;
import org.hplx.driver.PlaywrightFactory;
import org.hplx.user_actions.*;
import org.hplx.utils.*;
import org.testng.Assert;

import java.io.*;
import java.net.URL;
import java.nio.file.Paths;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Map;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.hplx.config_manager.ConfigFactory.getConfig;

import static org.hplx.constants.PageURL.*;
import static org.testng.Assert.assertTrue;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.text.PDFTextStripper;
import org.testng.annotations.AfterClass;
import org.apache.pdfbox.pdmodel.PDDocument;

public class TestSteps {
    static Map<String, Map<String, String>> excelData;

    static {
        ExcelReader excelReader = new ExcelReader();
        try {
            excelData = excelReader.getExcelAsMap();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public TestSteps() {
    }


}