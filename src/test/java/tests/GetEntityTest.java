package tests;

import helpers.BaseRequests;
import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.Assert;
import org.testng.annotations.Test;
import pojo.Response.ResponseEntity;

import java.util.List;

public class GetEntityTest {

    @Test
    @Description("Checking the get last entity")
    public void testGetEntity() {
        ResponseEntity responseEntity = RestAssured
                .given()
                .accept(ContentType.JSON)
                .when()
                .get("http://localhost:8080/api/get/%s".formatted(BaseRequests.getLastEntityId()))
                .then()
                .log().all()
                .statusCode(200)
                .extract().as(ResponseEntity.class);

        Assert.assertEquals(responseEntity.getId(), BaseRequests.getLastEntityId());
    }

    @Test
    @Description("Checking the get all entities")
    public void testGetAllEntities() {
        List<ResponseEntity> responseEntityList = RestAssured
                .given()
                .accept(ContentType.JSON)
                .when()
                .get("http://localhost:8080/api/getAll")
                .then()
                .log().all()
                .statusCode(200)
                .extract().body().jsonPath().getList("entity", ResponseEntity.class);

        Assert.assertEquals(responseEntityList.size(), BaseRequests.getCountEntities());
    }

    @Test
    @Description("Checking the get all entities with all parameters")
    public void testGetAllEntitiesParameterized() {
        List<ResponseEntity> responseEntityList = RestAssured
                .given()
                .accept(ContentType.JSON)
                .when()
                .get("http://localhost:8080/api/getAll%s".formatted("?title=" + BaseRequests.title + "&verified="
                        + BaseRequests.verified + "&page=" + BaseRequests.page + "&perPage=" + BaseRequests.perPage))
                .then()
                .log().all()
                .statusCode(200)
                .extract().body().jsonPath().getList("entity", ResponseEntity.class);

        responseEntityList.forEach(obj -> Assert.assertEquals(obj.getTitle(), BaseRequests.title));
        responseEntityList.forEach(obj -> Assert.assertEquals(obj.getVerified(), BaseRequests.verified));
    }
}
