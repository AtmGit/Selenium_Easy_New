Narrative:
In order to test pracice features of Selenium Demo site
As a user
I want to open the web site and test different features

Scenario: Validate Table pagination
Meta:
@fuctional
@issue A3_tablepage
@id A3_tablepage

Given Select Table pgination link from Table menu
Then User finds total number of columns their names and rows from first page
Then Get the value of specific rows and columns
Then User moves to all the pages and get the text of first columns


Scenario: Validate Table Data Seach
Meta:
@fuctional
@issue A3_tabledatasearch
@id A3_tabledatasearch

Given Select Table Data Search link from Table menu
Then User verifies search functionality:
|SearchText  |
|progress |
Then User clicks on filter button and verifies fiter criteria:
|Username  |
|jacob     |

Scenario: Validate Filter data
Meta:
@fuctional
@issue A3_tablefiltersearch
@id A3_tablefiltersearch
Given Select Table Filter link from Table menu
Then User verifies filter functionality


Scenario: Validate Table Sort
Meta:
@fuctional
@issue A3_tablesort
@id A3_tablesort
Given Select Table Sort link from Table menu
Then User verifies Sort functionality
Then User verifies the search functionality
Then User verifies sorting 











