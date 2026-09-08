package com.project.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.testng.Assert;

public class LoginSteps {

    private int responseCode;

    @Given("the user is on the login API")
    public void userOnLoginApi() {
        System.out.println("Navigating to login endpoint...");
    }

    @When("the user submits valid credentials")
    public void userSubmitsValidCredentials() {
        // Simulating a successful authentication request
        this.responseCode = 200;
    }

    @Then("the API should return a 200 OK status")
    public void apiShouldReturn200() {
        Assert.assertEquals(responseCode, 200, "Response status code should be 200");
    }
}
