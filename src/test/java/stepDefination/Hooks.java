package stepDefination;

import io.cucumber.java.Before;

import java.io.IOException;

public class Hooks {

    @Before("@DeletePlace")
    public void beforeScenario() throws IOException, IOException {		//execute this code only when place id is null
        //write a code that will give you place id

        PlaceValidations m =new PlaceValidations();
        if(PlaceValidations.place_id==null)
        {
            m.add_Place_Payload_with("Shetty", "French", "Asia");
            m.user_calls_with_http_request("AddPlaceAPI", "POST");
            m.verify_place_Id_created_maps_to_using("Shetty", "getPlaceAPI");
        }



    }
}
