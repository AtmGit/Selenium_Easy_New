Narrative:
In order to test pracice features of Selenium Demo site
As a user
I want to open the web site and test different features

Scenario: Validate Simple Form Demo
Meta:
@fuctional
@issue A1_CheckBox
@id A1_SeleniumHome

Given Select Simple Form Demo link from Input Form Menu
When User enters and verifies the message in Single Input Field
Then User enters and verifies the sum of two numbers:
|ValueA|ValueB|
|26    |90    |

Scenario: Validate Checkbox functionality
Meta:
@functional
@issue A1_SeleniumCheckbox
@id A1_SeleniumCheckbox

When User selects Checkbox Demo from Input Form Menu
Then User selects Single Checkbox and verifies the message
Then User selects Multiple Checkboxes and verifies the functionality


Scenario: Validate Radio button functionality
Meta:
@functional
@issue A1_SeleniumRadiobutton
@id A1_SeleniumRadiobutton

When User selects Radio Button Demo from Input Form Menu
Then User selects radio and group radio buttons and verifies the functionality


Scenario: Validate Dropdown functionality
Meta:
@functional
@issue A1_SeleniumDropdown
@id A1_SeleniumDropdown

When User selects Dropdown List from Input Form Menu
Then Select a day from the dropdown
Then Select multiple States from the dropdown
Examples:
|SelectADay |
|Friday     |

Scenario: Validate Ajax Form functionality
Meta:
@functional
@issue A1_SeleniumAjaxForm
@id A1_SeleniumAjaxForm

When User selects Ajex Form from Input Form Menu
Then User validates Ajex functionality
Examples:
|AjaxName  |AjexComment            |
|jQuery    |This is test comments  |

Scenario: Validate Input Form functionality
Meta:
@functional
@issue A1_SeleniumInputForm
@id A1_SeleniumInputForm
When User selects Input Forms from the Input Form Menu
Then User validates Input form functionality
Examples:
|Fname   |Lname   |FEmail        |FPh            |FAddress |FCity     |FState   |Fzip    |Fwebsite    |Fdescrition  |
|John    |Test    |12@yahoo.com  |(999)999-9999  |123 St   |tampa     |Colorado |30040   |Yahoo.com   |test desc    |

Scenario: Validate Jquery Form functionality
Meta:
@functional
@issue A1_SeleniumJqueryForm
@id A1_SeleniumJqueryForm
When User selects Jquery Link from the Input Form Menu
Then User validates Jquery form functionality
Examples:
|FromFile  |
|Java      |


