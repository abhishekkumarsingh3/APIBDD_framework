Feature: Validating Place API's.

  Scenario Outline: verify if the place is being successfully added using AddPlaceAPI
    Given Add Place Payload with "<name>" "<language>" "<address>"
    When User calls "AddPlaceAPI" with "post" http request
    Then the API call success with status code 200
    And "status" in response "OK"
    And "scope" in response "APP"

    Examples:
      | name     | language | address           |
      | Abhishek | English  | Dhanbad,Jharkhand |
