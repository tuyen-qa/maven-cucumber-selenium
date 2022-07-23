@register
Feature: NopCommerce Register Page

  @TC_01_Register_Empty_Data
  Scenario: Register to system with empty data
    # Open Url -> Hooks
    Given Get User home page url
    When Open Register page
    And Click to register button
    Then Validate error message at firstname textbox
    And Validate error message at lastname textbox
    And Validate error message at email textbox
    And Validate error message at password textbox
    And Validate error message at confirm password textbox

  @TC_02_Register_Invalid_Email
  Scenario Outline: Register to system with invalid data
    # Open Url -> Hooks
    Given Get User home page url
    When Open Register page
    And Input to firstname textbox
    And Input to lastname textbox
    And Input to email textbox as <Email>
    And Input to password textbox
    And Input to confirm password textbox
    And Click to register button
    Then Validate error message at email textbox as <Validation>

    Examples:
      | Email                     | Validation  |
      | abcgd                     | Wrong email |
      | email@example@example.com | Wrong email |
      | abc.def@mail#archive.com  | Wrong email |

#  @TC_03_Register_Successfully_Option_1
#  Scenario: Register to system with invalid data
#    # Open Url -> Hooks
#    Given Get User home page url
#    When Click to "Register" button
#    And Click to "Female" radio button
#    And Input to "FirstName" textbox
#    And Input to "LastName" textbox
#    And Select "DateOfBirthDay" dropdown
#    And Select "DateOfBirthMonth" dropdown
#    And Select "DateOfBirthYear" dropdown
#    And Input to "Email" textbox
#    And Input to "Password" textbox
#    And Input to "ConfirmPassword" textbox
#    Then Get user and password info
#    When Click to "Register" button
#    Then Message is displayed with "Your registration completed"
#    When Click to "My account" button
#    Then Valid text in "FirstName" textbox
#    And Valid text in "LastName" textbox
#    And Valid "Female" Radio button  is selected
#    And Valid text in "DateOfBirthDay" dropdown
#    And Valid text in "DateOfBirthMonth" dropdown
#    And Valid text in "DateOfBirthYear" dropdown

  @TC_03_Register_Successfully_Option_2
  Scenario Outline: Register to system with valid data
    # Open Url -> Hooks
    Given Get User home page url
    When Click to "Register" link
    And Open Register page
    And Click to "Female" radio button
    And Input to "FirstName" textbox with value "<FirstName>"
    And Input to "LastName" textbox with value "<LastName>"
    And Select "DateOfBirthDay" dropdown with value "<DateOfBirthDay>"
    And Select "DateOfBirthMonth" dropdown with value "<DateOfBirthMonth>"
    And Select "DateOfBirthYear" dropdown with value "<DateOfBirthYear>"
    And Input to "Email" textbox
    And Input to "Password" textbox with value "<Password>"
    And Input to "ConfirmPassword" textbox with value "<ConfirmPassword>"
    And Click to register button
    Then Register Success Message is displayed with "Your registration completed"
    When Click to "My account" link
    And Open My Account page
    Then Valid text in "FirstName" textbox with value "<FirstName>"
    And Valid text in "LastName" textbox with value "<LastName>"
    And Radio button "Female" is selected
    And Valid text in "DateOfBirthDay" dropdown with value "<DateOfBirthDay>"
    And Valid text in "DateOfBirthMonth" dropdown with value "<DateOfBirthMonth>"
    And Valid text in "DateOfBirthYear" dropdown with value "<DateOfBirthYear>"

    Examples:
      | FirstName | LastName | DateOfBirthDay | DateOfBirthMonth | DateOfBirthYear | Password | ConfirmPassword |
      | John      | Wicked   | 2              | November         | 1993            | 123456   | 123456          |