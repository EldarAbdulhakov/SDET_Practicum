package tests;

import helpers.BaseRequests;
import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DeleteEntityTest {

    private RequestSpecification requestSpecification;

    @BeforeClass
    public void setup() {
        requestSpecification = BaseRequests.initRequestSpecification();
    }

    @BeforeMethod
    public void createEntity() {
        BaseRequests.createEntity();
    }

    @Test
    @Description("Checking the delete first entity")
    public void testDeleteEntity() {

        RestAssured
                .given()
                .spec(requestSpecification)
                .when()
                .delete("api/delete/%s".formatted(BaseRequests.getFirstEntityId()))
                .then()
                .statusCode(204);

        Assert.assertFalse(BaseRequests.getEntityList()
                .stream()
                .anyMatch(obj -> obj.getId().equals(BaseRequests.expectedFirstId)));
    }
}
