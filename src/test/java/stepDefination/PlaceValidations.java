package stepDefination;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static org.junit.Assert.*;
import pojo.AddPlace;
import pojo.Location;
import resources.APIResources;
import resources.MapAPITest;
import resources.Utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class PlaceValidations extends Utils {
    RequestSpecification res;
    ResponseSpecification resspec;
    Response response;
    MapAPITest data=new MapAPITest();
    static String place_id;

    @Given("Add Place Payload with {string} {string} {string}")
    public void add_Place_Payload_with(String name, String language, String address) throws IOException {

        res=given().spec(requestSpecification())
                .body(data.AddPlacePayload(name,language,address));
    }
    @When("User calls {string} with {string} http request")
    public void user_calls_with_http_request(String resource, String method) {

//constructor will be called with value of resource which you pass
        APIResources resourceAPI=APIResources.valueOf(resource);
        System.out.println(resourceAPI.getResource());


        resspec =new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

        if(method.equalsIgnoreCase("POST"))
            response =res.when().post(resourceAPI.getResource());
        else if(method.equalsIgnoreCase("GET"))
            response =res.when().get(resourceAPI.getResource());

    }
    @Then("the API call success with status code {int}")
    public void the_api_call_success_with_status_code(Integer int1) {
        assertEquals(response.getStatusCode(),200);
    }
    @Then("{string} in response {string}")
    public void in_response(String keyValue,String expectedvalue) {
        assertEquals(getJsonPath(response,keyValue),expectedvalue);

    }
    @Then("verify place_Id created maps to {string} using {string}")
    public void verify_place_Id_created_maps_to_using(String expectedName, String resource) throws IOException {

        // requestSpec
        place_id=getJsonPath(response,"place_id");
        res=given().spec(requestSpecification()).queryParam("place_id",place_id);
        user_calls_with_http_request(resource,"GET");
        String actualName=getJsonPath(response,"name");
        assertEquals(actualName,expectedName);


    }
    @Given("DeletePlace Payload")
    public void deleteplace_Payload() throws IOException {
        // Write code here that turns the phrase above into concrete actions

        res =given().spec(requestSpecification()).body(data.deletePlacePayload(place_id));
    }


}
