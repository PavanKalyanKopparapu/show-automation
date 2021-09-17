#Author: Parth Moradiya
@login
Feature: Login Scenarios Automation tests

  # LoginNegative1
  @negative
  Scenario: User will try to login with wrong password
    Given User is on the SRK home page
    When User enters username and password
      | negative_test_case_01 |
    And User click the 'Login' button
    Then User gets validation message
      | wrong_password |

  # LoginNegative2
  @negative
  Scenario: User will try to login with wrong username
    When User enters username and password
      | negative_test_case_02 |
    And User click the 'Login' button
    Then User gets validation message
      | no_user_exist |

  # LoginNegative3
  @negative
  Scenario: User will try to login with wrong username and password
    When User enters username and password
      | negative_test_case_03 |
    And User click the 'Login' button
    Then User gets validation message
      | no_user_exist |

  # LoginNegative4
  @negative
  Scenario: User will try to login without entering of username and password
    When User enters username and password
      | negative_test_case_04 |
    And User click the 'Login' button

  # LoginNegative5
  @negative
  Scenario: User will try to login with entering password with capital alphabet
    When User enters username and password
      | negative_test_case_05 |
    And User click the 'Login' button
    Then User gets validation message
      | wrong_password |

  # LoginNegative41
  @negative
  Scenario: User will try to login without entering and password
    When User enters username and password
      | negative_test_case_04 |
    And User click the 'Login' button

  # LoginNegative51
  @negative
  Scenario: User will try to login with entering password with alphabet
    When User enters username and password
      | negative_test_case_05 |
    And User click the 'Login' button
    Then User gets validation message
      | wrong_password |

  # LoginPositive1
  @positive
  Scenario: User try to login to the SRK homepage with an valid ID and password
    When User enters username and password
      | positive_test_case_06 |
    And User click the 'Login' button
