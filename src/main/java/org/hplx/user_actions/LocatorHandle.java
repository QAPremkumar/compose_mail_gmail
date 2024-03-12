package org.hplx.user_actions;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import org.hplx.config_manager.ReadLocatorUtils;

public class LocatorHandle {

    LocatorHandle(){

    }
    public static Locator page(Page page, String locator_name){
        return ReadLocatorUtils.getObjectLocator(page,locator_name);
    }
}
