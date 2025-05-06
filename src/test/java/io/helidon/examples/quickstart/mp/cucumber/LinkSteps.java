package io.helidon.examples.quickstart.mp.cucumber;

import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class LinkSteps {

    private Response response;

    @Given("the link service is running")
    public void the_link_service_is_running() {
//        RestAssured.baseURI = "http://localhost:8080";
//        RequestSpecification request = RestAssured.given();
//        latestResponse = request.get("/links");
    }

    @When("I request the list of dynamic links")
    public void i_request_the_list_of_dynamic_links() {
        response = RestAssured.get("/links");
    }

    @Then("I should receive a successful response")
    public void i_should_receive_a_successful_response() {
        response.then().statusCode(200);
    }

    @And("the response should include links")
    public void the_response_should_include_links() {
        System.out.println(response.toString());
        assertThat(response.jsonPath().getList("$"), is(not(empty())));
    }
}
