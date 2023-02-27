#@Author: Parth Moradiya
@show_master
Feature: Test cases for show master feature

  # LoginPositive1
  @positive
  Scenario: User try to login to the SRK homepage with valid ID and password
    Given User is on the SRK home page
    When User enters username and password
      | positive_test_case_06 |
    And User click the 'Login' button
    Then User should navigate to Show Event Master
   
   # AddNewShowEventNegative
   @negative
   Scenario: User try to add new show event with empty fields
   Given User click on show radio button
   When User click on save button