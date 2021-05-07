package test;

import data.DataHelper;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import lombok.val;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ApiTest {
    @Test
    public void shouldAPIPaymentCardWithValidCard() {
        val validCardInformation = DataHelper.getValidCardInformation();
        RequestSpecification requestSpec = new RequestSpecBuilder()
                .setBaseUri("http://localhost:8080")
                .setAccept(ContentType.JSON)
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();
        String response = given()
                .spec(requestSpec)
                .body(validCardInformation)
                .when()
                .post("/api/v1/pay")
                .then().log().all()
                .statusCode(200)
                .extract()
                .asString();
        assertTrue(response.contains("APPROVED"));
    }

    @Test
    public void shouldAPIPaymentCardWithNotValidCard() {
        val notvalidCardInformation = DataHelper.getNotvalidCardInformation();
        RequestSpecification requestSpec = new RequestSpecBuilder()
                .setBaseUri("http://localhost:8080")
                .setAccept(ContentType.JSON)
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();
        String response = given()
                .spec(requestSpec)
                .body(notvalidCardInformation)
                .when()
                .post("/api/v1/pay")
                .then().log().all()
                .statusCode(200)
                .extract()
                .asString();
        assertTrue(response.contains("DECLINED"));
    }

    @Test
    public void shouldAPICreditdWithValidCard() {
        val validCardInformation = DataHelper.getValidCardInformation();
        RequestSpecification requestSpec = new RequestSpecBuilder()
                .setBaseUri("http://localhost:8080")
                .setAccept(ContentType.JSON)
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();
        String response = given()
                .spec(requestSpec)
                .body(validCardInformation)
                .when()
                .post("/api/v1/credit")
                .then().log().all()
                .statusCode(200)
                .extract()
                .asString();
        assertTrue(response.contains("APPROVED"));
    }

    @Test
    public void shouldAPICreditWithNotValidCard() {
        val notvalidCardInformation = DataHelper.getNotvalidCardInformation();
        RequestSpecification requestSpec = new RequestSpecBuilder()
                .setBaseUri("http://localhost:8080")
                .setAccept(ContentType.JSON)
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();
        String response = given()
                .spec(requestSpec)
                .body(notvalidCardInformation)
                .when()
                .post("/api/v1/credit")
                .then().log().all()
                .statusCode(200)
                .extract()
                .asString();
        assertTrue(response.contains("DECLINED"));
    }


}
