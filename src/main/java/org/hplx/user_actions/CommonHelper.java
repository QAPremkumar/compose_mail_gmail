package org.hplx.user_actions;

import com.microsoft.playwright.FileChooser;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.PlaywrightException;
import org.hplx.config_manager.ReadLocatorUtils;

import java.awt.*;
import java.nio.file.Paths;

import static org.hplx.driver.PlaywrightFactory.getPage;


public class CommonHelper {

    private CommonHelper(){

    }

    public boolean focus(Page page, String locator_name) {
        return this.focus((ReadLocatorUtils.getObjectLocator(page,locator_name)));
    }
    public static boolean focus(Locator locator) {
        try {
            locator.focus();
            return true;
        } catch (PlaywrightException e) {
            //ExtentLogger.fail(e.getMessage());
            return false;
        }
    }
    public void setViewport(int width, int height) {
        getPage().setViewportSize(width, height);
    }
    public void maximize() {
        try {
            GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
            getPage().setViewportSize(gd.getDisplayMode().getWidth(), gd.getDisplayMode().getHeight());
        } catch (HeadlessException e) {
            //ExtentLogger.warn(e.getMessage());
        }
    }
    public boolean scrollIntoViewIfNeeded(Page page,String locator_name) {
        try {
            ReadLocatorUtils.getObjectLocator(page,locator_name).scrollIntoViewIfNeeded();
            return true;
        } catch (PlaywrightException e) {
            //ExtentLogger.warn(e.getMessage());
            return false;
        }
    }

    public boolean jsScrollIntoView(Locator locator) {
        try {
            locator.evaluate("node => node.scrollIntoView();");
            return true;
        } catch (PlaywrightException e) {
            //ExtentLogger.warn(e.getMessage());
            return false;
        }
    }
    public static boolean isDialogPopupTextDisplayed(Page page, String message) {
        try {
            page.onDialog(dialog -> {
                String text = dialog.message();
                AssertHelper.assertEquals(text, message, "Dialog message displayed is wrong");
                dialog.accept();
            });
            return true;
        } catch (PlaywrightException p) {
          //  ExtentLogger.warn(p.getMessage());
            return false;
        }
    }

    public static String getColorOfElement(Page page, String locator_name){
        String color = "";
        try {
            Locator locator = ReadLocatorUtils.getObjectLocator(page,locator_name);
            color = locator.evaluate("element => getComputedStyle(element)['color']").toString();
//            colors = (String) locator.evaluate("(ele) => { return window.getComputedStyle(ele).getPropertyValue('color')}");
        } catch (PlaywrightException e) {
//            ExtentLogger.warn(e.getMessage());

        }
        return color;
    }

    public static String getColorOfElementByText(Page page, String locator_name, String text){
        String color = "";
        try {
            Locator locator = ReadLocatorUtils.getXpathByText(page, locator_name, text);
            color = locator.evaluate("element => getComputedStyle(element)['color']").toString();
        } catch (PlaywrightException e) {

        }
        return color;
    }

    public static boolean isFileUploaded(Page page, String locator_name){

        try {
            Locator locator = ReadLocatorUtils.getObjectLocator(page,locator_name);
            FileChooser fileChooser = page.waitForFileChooser(() -> locator.click());
            fileChooser.setFiles(Paths.get("screenshot.png"));
            return true;
        } catch (PlaywrightException e) {
            return false;
        }
    }


}
