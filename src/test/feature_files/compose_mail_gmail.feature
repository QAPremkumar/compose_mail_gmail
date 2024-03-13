Feature: Gmail Email Composing Functionality
  As a user
  I want to compose and send emails
  So that I can communicate with others effectively

  @gmail
  Scenario: Compose and send an email
    Given I am logged in to Gmail
    When I click on the "Compose" button
    And I enter the recipient email address "premkumaranbu051995.com"
    And I enter the subject "Incubyte"
    And I type the email body "Automation QA test for Incubyte"
    And I click on the "Send" button
    Then the email should be sent successfully

  @gmail
  Scenario: Verify sent email in the "Sent" folder
    Given I am logged in to Gmail
    When I navigate to the "Sent" folder
    Then I should see the sent email with the subject "Incubyte" and recipient "premkumaranbu051995.com"

  @gmail
  Scenario: Verify email in the "Drafts" folder
    Given I am logged in to Gmail
    When I click on the "Compose" button
    And I enter the recipient email address "premkumaranbu051995.com"
    And I enter the subject "Incubyte"
    And I type the email body "This is a draft email."
    And I click on the "Save Draft" button
    Then the email should be saved in the "Drafts" folder
    And I should see the draft email with the subject "Incubyte" and recipient "premkumaranbu051995.com" in the "Drafts" folder
