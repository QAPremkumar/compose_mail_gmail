Feature: Verify all scenario of product update

  Background:
    When User navigate to "Login URL"
    And User login and navigate to default home page
    Given User is on "viewRemoteAppointments.php" page
  
  @Sanity_Test  @EMR_2
    Scenario: Verify major update in product update
      Given User is on "viewRemoteAppointments.php" page
      When User click on "app_icon"
      Then User should be able to view "major_updates"
    Then User should be able to view "minor_updates"
    Then User should be able to view "product_updates_container"
    Then User should be able to view "product_updates_card"
    When User click on "view_all_major"
    Then User should be able to view "healthPlix_product_updates_label"
    Then User should be able to view "major_updates_label"
    Then User should be able to view "minor_updates_label"
    Then User should be able to view "view_product_updates_container"
    Then User should be able to view "view_product_updates_container_left"
    Then User should be able to view "view_product_updates_container_right"
    Then User should be able to view "product_update_header"
    Then User should be able to view "product_update_announcement" if visible
    Then User should be able to view "product_update_announcement_banner_1" if visible
    Then User should be able to view "product_update_announcement_banner_2" if visible
    Then User should be able to view "product_update_announcement_banner_3" if visible
  
  @Sanity_Test  @ignore
  Scenario: Verify minor update in product update
    Given User is on "viewRemoteAppointments.php" page
    When User click on "app_icon"
    Then User should be able to view "major_updates"
    Then User should be able to view "minor_updates"
    Then User should be able to view "product_updates_container"
    Then User should be able to view "product_updates_card"
    When User click on "view_all_minor" if visible
    Then User should be able to view "healthPlix_product_updates_label" if visible
    Then User should be able to view "major_updates_label" if visible
    Then User should be able to view "minor_updates_label" if visible
    Then User should be able to view "view_product_updates_container" if visible
    Then User should be able to view "view_product_updates_container_left" if visible
    Then User should be able to view "view_product_updates_container_right" if visible
    Then User should be able to view "product_update_header" if visible
    Then User should be able to view "product_update_announcement" if visible
    Then User should be able to view "product_update_announcement_1" if visible
    Then User should be able to view "product_update_announcement_2" if visible
    Then User should be able to view "product_update_announcement_3" if visible
  
