@login
Feature: Facebook login page

  @no_param
  Scenario: Verify login page
    Given Open facebook application
    When Input to Username textbox
    And Input to Password textbox
    And Click to Submit button
    # Then Verify...
    And Close application

  @param_mark
  Scenario: Verify login page
    Given Open facebook application
    When Input to Username textbox "automationtest@gmail.com"
    And Input to Password textbox "123456"
    And Click to Submit button
    # Then Verify...
    And Close application

  @param_no_mark
  Scenario: Verify login page
    Given Open facebook application
    When Input to Username textbox automationtest@gmail.com
    And Input to Password textbox 123456
    And Click to Submit button
    # Then Verify...
    And Close application

  @multiple_param
  Scenario: Verify login page
    Given Open facebook application
    When Input to Username with "automationtest@gmail.com" and Password "123456"
    And Click to Submit button
    # Then Verify...
    And Close application

  @datatable_step_example
  Scenario Outline: Create new customer with email <Username>
    Given Open facebook application
    When Input to Username and Password
      | Username   | Password   | Addresss  |
      | <Username> | <Password> | <Address> |
    And Click to Submit button
    Then Verify submitted info displayed
      | Username   | Adresss   |
      | <Username> | <Address> |
    When Open Customter page
    And Input to Customer Name textbox <Customer Name>
    And Input to City textbox <City>
    And Input to other required fields
      | Phone | Email |
      | 098888888 | automationfc@gmail.com |
    And Close application

    Examples:
      | Username                 | Password | City   | Address | Phone     |
      | automationtest@gmail.com | 123456   | Ha Noi | Le Loi  | 098888888 |

  @datatable_step
  Scenario Outline: Create new customer with email <Username>
    Given Open facebook application
    When Input to Username and Password
      | Username   | Password   |
      | <Username> | <Password>   |

    And Click to Submit button
    And Close application

    Examples:
      | Username                 | Password |
      | automationtest01@gmail.com | 123456   |

  @datatable_scenario @data_driven_testing
  Scenario Outline: Data Table in Scenario
    Given Open facebook application
    When Input to Username textbox with <Username>
    And Input to Password textbox with <Password>
    And Click to Submit button
    And Close application

    Examples:
      | Username                 | Password |
      | automation01@gmail.com   | 123456 	|
      | automation02@gmail.com   | 123456 	|
      | automation03@gmail.com   | 123456 	|
      | automation04@gmail.com   | 123456 	|
