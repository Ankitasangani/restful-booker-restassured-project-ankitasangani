package com.restful.booker.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class BookingExtraction {

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
    public void test001() {
        String fName = response.extract().path("firstname");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("First name is : " + fName);
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    public void test002() {
        String lName = response.extract().path("lastname");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Last name is : " + lName);
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    public void test003() {
        int totalPrice = response.extract().path("totalprice");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Total price is : " + totalPrice);
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    public void test004() {
        Boolean depositPaid = response.extract().path("depositpaid");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Deposite paid : " + depositPaid);
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    public void test005() {
        String date = response.extract().path("bookingdates.checkin");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("checkIn date  : " + date);
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    public void test006() {
        String date = response.extract().path("bookingdates.checkout");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("checkout date  : " + date);
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    public void test007() {
        String data = response.extract().path("additionalneeds");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("additionalneeds  : " + data);
        System.out.println("------------------End of Test---------------------------");
    }
}
