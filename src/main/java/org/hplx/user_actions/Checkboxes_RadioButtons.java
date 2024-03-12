package org.hplx.user_actions;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import org.hplx.config_manager.ReadLocatorUtils;

public class Checkboxes_RadioButtons {

    private Checkboxes_RadioButtons() {
    }

    public static boolean checkCheckbox(Page page, String locator) {
        return performCheckboxAction(page, locator, () -> {
            Locator l = ReadLocatorUtils.getObjectLocator(page, locator);
            l.check();
            System.out.println("User checked the checkbox :=> " + locator);
        });
    }

    public static boolean selectRadioButton(Page page, String locator) {
        return performRadioButtonAction(page, locator, () -> {
            Locator l = ReadLocatorUtils.getObjectLocator(page, locator);
            l.check();
            System.out.println("User selected the radiobutton :=> " + locator);
        });
    }

    private static boolean performCheckboxAction(Page page, String locator, CheckboxAction checkboxAction) {
        return performAction(page, locator, checkboxAction);
    }

    private static boolean performRadioButtonAction(Page page, String locator, RadioButtonAction radioButtonAction) {
        return performAction(page, locator, radioButtonAction);
    }

    private static boolean performAction(Page page, String locator, CommonAction action) {
        boolean status = false;
        try {
            action.perform();
            status = true;
        } catch (Exception e) {
            System.out.println("User facing issue in clicking on :=> " + locator);
            e.printStackTrace();
        }
        return status;
    }

    private interface CheckboxAction extends CommonAction {
        void perform();
    }

    private interface RadioButtonAction extends CommonAction {
        void perform();
    }

    private interface CommonAction {
        void perform();
    }
}
