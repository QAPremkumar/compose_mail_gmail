package org.stepdefinations;

import io.cucumber.java.en.Given;
        import io.cucumber.java.en.When;
        import io.cucumber.java.en.Then;

public class GmailSteps {

    @Given("I am logged in to Gmail")
    public void loginToGmail() {
        // Implement login steps
    }

    @When("I click on the {string} button")
    public void clickButton(String button) {
        // Implement click button steps
    }

    @And("I enter the recipient email address {string}")
    public void enterRecipient(String recipient) {
        // Implement enter recipient steps
    }

    @And("I enter the subject {string}")
    public void enterSubject(String subject) {
        // Implement enter subject steps
    }

    @And("I type the email body {string}")
    public void typeEmailBody(String body) {
        // Implement type email body steps
    }

    @And("I click on the {string} button")
    public void clickSendButton(String sendButton) {
        // Implement click send button steps
    }

    @Then("the email should be sent successfully")
    public void verifyEmailSent() {
        // Implement verification steps
    }

    @When("I navigate to the {string} folder")
    public void navigateToFolder(String folder) {
        // Implement navigation steps
    }

    @Then("I should see the sent email with the subject {string} and recipient {string}")
    public void verifySentEmail(String subject, String recipient) {
        // Implement verification steps
    }

    // Add more step definitions for other scenarios as needed
}
