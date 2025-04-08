package tests;

import helpers.BaseRequests;
import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import models.Entity;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class GetEntityTest {

    private RequestSpecification requestSpecification;

    @BeforeClass
    public void setup() {
        requestSpecification = BaseRequests.initRequestSpecification();
    }

    @BeforeMethod
    public void createEntity() {
        BaseRequests.createEntity();
    }

    @AfterMethod
    public void deleteEntity() {
        BaseRequests.deleteLastEntity();
    }

    @Test
    @Description("Checking the get last entity")
    public void testGetEntity() {
        Entity responseEntity = RestAssured
                .given()
                .spec(requestSpecification)
                .when()
                .get("api/get/%s".formatted(BaseRequests.getLastEntityId()))
                .then()
                .statusCode(200)
                .extract().as(Entity.class);

        Assert.assertEquals(responseEntity.getId(), BaseRequests.getLastEntityId());
    }

    @Test
    @Description("Checking the get all entities")
    public void testGetAllEntities() {
        List<Entity> entityList = RestAssured
                .given()
                .spec(requestSpecification)
                .when()
                .get("api/getAll")
                .then()
                .statusCode(200)
                .extract().body().jsonPath().getList("entity", Entity.class);

        Assert.assertEquals(entityList.size(), BaseRequests.getCountEntities());
    }

    @Test
    @Description("Checking the get all entities with all parameters")
    public void testGetAllEntitiesParameterized() {
        List<Entity> entityList = RestAssured
                .given()
                .spec(requestSpecification)
                .when()
                .get("api/getAll%s".formatted("?title=" + BaseRequests.title + "&verified="
                        + BaseRequests.verified + "&page=" + BaseRequests.page + "&perPage=" + BaseRequests.perPage))
                .then()
                .statusCode(200)
                .extract().body().jsonPath().getList("entity", Entity.class);

        entityList.forEach(obj -> Assert.assertEquals(obj.getTitle(), BaseRequests.title));
        entityList.forEach(obj -> Assert.assertEquals(obj.getVerified(), BaseRequests.verified));
    }
}
