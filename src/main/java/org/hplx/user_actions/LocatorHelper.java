package org.hplx.user_actions;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

import java.util.regex.Pattern;

public class LocatorHelper {

    private LocatorHelper(){

    }
    public Locator getLocator(Page page, String locator) {
        return page.locator(locator);
    }

    public Locator getFirst(Locator locator) {
        return locator.first();
    }

    public Locator getParentLocator(Locator locator) {
        return locator.locator("..");
    }

    public Locator getIndexedLocator(Locator locator, int nth) {
        return locator.locator("nth=" + nth);
    }

    public Locator getLastLocator(Locator locator) {
        return locator.locator("nth=-1");
    }

    public Locator getVisibleLocator(Locator locator) {
        return locator.locator("visible=true");
    }

    public Locator getLocatorByRole(Page page, AriaRole role, Page.GetByRoleOptions roleOptions) {
        return page.getByRole(role, roleOptions);
    }

    public Locator getLocatorByText(Page page, String text) {
        return page.getByText(text);
    }

    public Locator getLocatorByText(Page page, Pattern text) {
        return page.getByText(text);
    }

    public Locator getLocatorByLabel(Page page, String text) {
        return page.getByLabel(text);
    }

    public Locator getLocatorByLabel(Page page, Pattern text) {
        return page.getByLabel(text);
    }

    public Locator getLocatorByPlaceholder(Page page, String text) {
        return page.getByPlaceholder(text);
    }

    public Locator getLocatorByPlaceholder(Page page, Pattern text) {
        return page.getByPlaceholder(text);
    }

    public Locator getLocatorByAltText(Page page, String text) {
        return page.getByAltText(text);
    }

    public Locator getLocatorByAltText(Page page, Pattern text) {
        return page.getByAltText(text);
    }

    public Locator getLocatorByTitle(Page page, String text) {
        return page.getByTitle(text);
    }

    public Locator getLocatorByTitle(Page page, Pattern text) {
        return page.getByTitle(text);
    }

}
