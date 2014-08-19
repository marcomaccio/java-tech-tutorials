Feature: Create Customer
  As Provisioning Operator
  I want to create a Customer

  Scenario: Successfully Create a Customer (HTTP STATUS CODE 201)
    Given I have a user with this info:
    | firstname | lastname  | customerId |
    | Marco     | Maccio    | 123456     |
  When I call the customer provisioning service
  Then I should retrieve the http response 201