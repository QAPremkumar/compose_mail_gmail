package org.hplx.user_actions;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.SelectOption;
import org.hplx.config_manager.ReadLocatorUtils;

import java.util.Arrays;
import java.util.List;

public class SelectHelper {

    private SelectHelper(){

    }

    public static boolean select_by_label(Page page, String label, String input_value) {
        boolean status = false;
        try {
            System.out.println("Please select text : [" + input_value + "] in " + label);
           ReadLocatorUtils.getObjectLocator(page, label).waitFor();
           ReadLocatorUtils.getObjectLocator(page, label).scrollIntoViewIfNeeded();
           ReadLocatorUtils.getObjectLocator(page, label).selectOption(input_value);
            System.out.println("User successfully selected value :=>" + input_value);
            //ExtentLogger.pass("User successfully selected value :=> <b><i>" + input_value + "</i></b>");
            status = true;
        } catch (Exception e) {
            //ExtentLogger.fail(ICON_BUG + "User facing issue in selecting :=> " + input_value + "<br>ERROR IS:=><b><i>" + e.getLocalizedMessage() + "</i></b>");
            System.out.println("User facing issue in selecting :=> " + label + "<br>ERROR IS:=>" + e.getLocalizedMessage());
        }
        return status;
    }

    public static boolean select_by_locator_with_matching_value(Page page, String locator, String input_value) {
        boolean status = false;
        try {
            System.out.println("Please enter text : [" + input_value + "] in " + locator);
           ReadLocatorUtils.getObjectLocator(page, locator).waitFor();
           ReadLocatorUtils.getObjectLocator(page, locator).scrollIntoViewIfNeeded();
           ReadLocatorUtils.getObjectLocator(page, locator).selectOption(input_value);
            System.out.println("User successfully selected value :=>" + input_value);
            //ExtentLogger.pass("User successfully selected value :=> <b><i>" + input_value + "</i></b>");
            status = true;
        } catch (Exception e) {
            //ExtentLogger.fail(ICON_BUG + "User facing issue in selecting :=> " + input_value + "<br>ERROR IS:=><b><i>" + e.getLocalizedMessage() + "</i></b>");
            System.out.println("User facing issue in selecting :=> " + locator + "<br>ERROR IS:=>" + e.getLocalizedMessage());
        }
        return status;
    }

    public static boolean select_by_locator_with_matching_label(Page page, String locator, String input_value) {
        boolean status = false;
        try {
            System.out.println("Please enter text : [" + input_value + "] in " + locator);
           ReadLocatorUtils.getObjectLocator(page, locator).waitFor();
           ReadLocatorUtils.getObjectLocator(page, locator).scrollIntoViewIfNeeded();
           ReadLocatorUtils.getObjectLocator(page, locator).selectOption(new SelectOption().setLabel(input_value));
            System.out.println("User successfully selected value :=>" + input_value);
            //ExtentLogger.pass("User successfully selected value :=> <b><i>" + input_value + "</i></b>");
            status = true;
        } catch (Exception e) {
            //ExtentLogger.fail(ICON_BUG + "User facing issue in selecting :=> " + input_value + "<br>ERROR IS:=><b><i>" + e.getLocalizedMessage() + "</i></b>");
            System.out.println("User facing issue in selecting :=> " + locator + "<br>ERROR IS:=>" + e.getLocalizedMessage());
        }
        return status;
    }

    public static List<String> select_multiple_value(Page page, String locator, String[] input_value) {
        try {
            System.out.println("Please select :=>" + locator);
           ReadLocatorUtils.getObjectLocator(page, locator).waitFor();
           ReadLocatorUtils.getObjectLocator(page, locator).scrollIntoViewIfNeeded();
           ReadLocatorUtils.getObjectLocator(page, locator).selectOption(input_value);
            System.out.println("User successfully selected value :=>" + Arrays.toString(input_value));
            //ExtentLogger.pass("User successfully selected value :=> <b><i>" + Arrays.toString(input_value) + "</i></b>");
        } catch (Exception e) {
            //ExtentLogger.fail(ICON_BUG + "User facing issue in selecting :=> " + Arrays.toString(input_value) + "<br>ERROR IS:=><b><i>" + e.getLocalizedMessage() + "</i></b>");
            System.out.println("User facing issue in selecting :=> " + locator + "<br>ERROR IS:=>" + e.getLocalizedMessage());
        }
        return List.of();
    }

    public static boolean click_and_choose_in_dropdown(Page page, String select_locator, String option_locator) {
        boolean status = false;
        try {
            System.out.println("Please select :=> " + option_locator);
           ReadLocatorUtils.getObjectLocator(page, select_locator).waitFor();
           ReadLocatorUtils.getObjectLocator(page, select_locator).scrollIntoViewIfNeeded();

           ReadLocatorUtils.getObjectLocator(page, select_locator).click();
           ReadLocatorUtils.getObjectLocator(page, option_locator).waitFor();
           ReadLocatorUtils.getObjectLocator(page, option_locator).scrollIntoViewIfNeeded();
           ReadLocatorUtils.getObjectLocator(page, option_locator).click();
            System.out.println("User successfully selected value :=>" + option_locator);
            //ExtentLogger.pass("User successfully selected value :=> <b><i>" + option_locator + "</i></b>");
            status = true;
        } catch (Exception e) {
            //ExtentLogger.fail(ICON_BUG + "User facing issue in selecting :=> " + option_locator + "<br>ERROR IS:=><b><i>" + e.getLocalizedMessage() + "</i></b>");
            System.out.println("User facing issue in selecting :=> " + option_locator + "<br>ERROR IS:=>" + e.getLocalizedMessage());
        }
        return status;
    }


}

