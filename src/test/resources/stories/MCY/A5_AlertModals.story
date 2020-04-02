Narrative:
In order to test pracice features of Selenium Demo site
As a user
I want to open the web site and test different features

Scenario: Validate Alerts 
Meta:
@fuctional
@issue A5_AlertsModals
@id A5_AlertsModals

Given Select Bootstrap Alers link from Alerts & Modals Menu
Then User validates bootstrap alert messages


Scenario: Validate Modals
Meta:
@fuctional
@issue A5_Modals
@id A5_AlertsModals

Given Select Bootstrap Modals link from Alerts & Modals Menu
Then User validates Single Modal bootstrap alert 
Then User validates Multiple Modal bootstrap alert 

Scenario: Validate Window Popup
Meta:
@fuctional
@issue A5_WindowPopup
@id A5_WindowPopup

Given Select Window Popup from Alert Menu
Then User validates Single Window Popup 
Then User validates Multiple Window popup

Scenario: Validate Progress Bar Modal 
Meta:
@fuctional
@issue A5_ProgressModal
@id A5_ProgressModal

Given Select Progress Bar Modal from Alert Menu
Then User validates modal dialog with progress bar

Scenario: Validate Java Alerts
Meta:
@fuctional
@issue A5_JavaAlerts
@id A5_JavaAlerts

Given Select JavaScript Alert from Alert Menu
Then User validates Java Alert popups


Scenario: Validate File Download
Meta:
@fuctional
@issue A5_FileDownload
@id A5_FileDownload

Given Select file Download from Alert Menu
Then User validates File download functionality

