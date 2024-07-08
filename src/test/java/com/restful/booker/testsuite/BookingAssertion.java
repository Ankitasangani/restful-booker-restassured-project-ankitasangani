package com.restful.booker.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasKey;

public class BookingAssertion {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
        response = given()
                .when()
                .get("/booking/10")
                .then().statusCode(200);
    }

    @Test
    public void test01() {
        response.body("", hasKey("firstname"));
    }

    @Test
    public void test02() {
        response.body("", hasKey("lastname"));
    }

    @Test
    public void test03() {
        response.body("", hasKey("totalprice"));
    }

    @Test
    public void test04() {
        response.body("", hasKey("depositpaid"));
    }

    @Test
    public void test05() {
        response.body("bookingdates", hasKey("checkin"));
    }

    @Test
    public void test06() {
        response.body("bookingdates", hasKey("checkout"));
    }
}
