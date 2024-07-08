package com.restful.booker.crudtest;

import com.restful.booker.model.BookingPojo;
import com.restful.booker.testbase.TestBase;
import com.restful.booker.utils.TestUtils;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class BookingCRUDTest extends TestBase {
    static String id;

    @Test
    public void verifyBookingCreatedSuccessfully() {
        String fName = "Jim" + TestUtils.getRandomValue();
        String lName = "Brown" + TestUtils.getRandomValue();
        int totalPrice = 111;
        boolean depositPaid = true;
        HashMap<String, String> bookingDates = new HashMap<>();
        String checkIn = "2018-01-01";
        String checkOut = "2019-01-01";
        bookingDates.put("checkIn", checkIn);
        bookingDates.put("checkout", checkOut);
        String additionalNeeds = "Breakfast";

        BookingPojo bookingPojo = new BookingPojo();
        bookingPojo.setFirstname(fName);
        bookingPojo.setLastname(lName);
        bookingPojo.setTotalprice(totalPrice);
        bookingPojo.setDepositpaid(depositPaid);
        bookingPojo.setBookingdates(bookingDates);
        bookingPojo.setAdditionalneeds(additionalNeeds);

        Response response = given().log().ifValidationFails()
                .header("Content-Type", "application/json")
                .when()
                .body(bookingPojo)
                .post("/booking");

        id = response.jsonPath().getString("bookingid");

        response.prettyPrint();
        response.then().log().ifValidationFails().statusCode(200);
    }

    @Test
    public void verifyBookingReadSuccessfully() {
        Response response = given()
                .pathParam("id",id)
                .when()
                .get("/booking/{id}");
        response.prettyPrint();
        response.then().statusCode(200);
    }


    @Test
    public void verifyBookingUpdateSuccessfully(){
        String fName = "Jim" + "Updated";
        String lName = "Brown" + "Updated";
        int totalPrice = 111;
        boolean depositPaid = true;

        HashMap<String, String> bookingDates = new HashMap<>();
        String checkIn = "2018-01-01";
        String checkOut = "2019-01-01";
        bookingDates.put("checkin", checkIn);
        bookingDates.put("checkout", checkOut);
        String additionalNeeds = "Breakfast";

        BookingPojo bookingPojo = new BookingPojo();
        bookingPojo.setFirstname(fName);
        bookingPojo.setLastname(lName);
        bookingPojo.setTotalprice(totalPrice);
        bookingPojo.setDepositpaid(depositPaid);
        bookingPojo.setBookingdates(bookingDates);
        bookingPojo.setAdditionalneeds(additionalNeeds);

        Response response =
                given().log().all()
                        .header("Content-Type", "application/json")
                        .header("Authorization", "Bearer 2633d0058d2069545c8073896cbd4e12533b9b68bab8bb0ef0b969588bd7f7af")
                        .header("Connection", "keep-alive")
                        .pathParam("id",id)
                        .body(bookingPojo)
                        .when()
                        .put("/booking/{id}");
        response.then().statusCode(200);
        response.prettyPrint();
    }

    @Test
    public void verifyBookingDeleteSuccessfully() {
        Response response = given().log().all()
                .pathParam("id",id)
                .header("Authorization", "Bearer 2633d0058d2069545c8073896cbd4e12533b9b68bab8bb0ef0b969588bd7f7af")
                .when()
                .delete("booking/{id}");
        response.then().statusCode(201);
        response.prettyPrint();
    }
}
