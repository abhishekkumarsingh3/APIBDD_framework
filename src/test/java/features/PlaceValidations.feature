Feature: Validating Place API's.
@AddPlace
  Scenario Outline: verify if the place is being successfully added using AddPlaceAPI

    Given Add Place Payload with "<name>" "<language>" "<address>"
    When User calls "AddPlaceAPI" with "post" http request
    Then the API call success with status code 200
    And "status" in response "OK"
    And "scope" in response "APP"
    And verify place_Id created maps to "<name>" using "getPlaceAPI"


    Examples:
      | name     | language | address           |
      | Abhishek | English  | Dhanbad,Jharkhand |
      |chandan   |Hindi     |BB house           |
  @DeletePlace @Regression
  Scenario: Verify if Delete Place functionality is working

    Given DeletePlace Payload
    When User calls "deletePlaceAPI" with "POST" http request
    Then the API call success with status code 200
    And "status" in response "OK"
