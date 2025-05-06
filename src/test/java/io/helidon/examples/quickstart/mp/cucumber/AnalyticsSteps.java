package io.helidon.examples.quickstart.mp.cucumber;

import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class AnalyticsSteps {

    private Response response;

    @Given("the analytics service is running")
    public void the_analytics_service_is_running() {
//        RestAssured.baseURI = "http://localhost:8080";
    }

    @When("I request the list of click analytics")
    public void i_request_the_list_of_click_analytics() {
        response = RestAssured.get("/analytics");
    }

    @Then("the analytics response should be successful")
    public void the_analytics_response_should_be_successful() {
        response.then().statusCode(200);
    }

    @And("the analytics response should include records")
    public void the_analytics_response_should_include_records() {
        assertThat(response.jsonPath().getList("$"), is(not(empty())));
    }
}
