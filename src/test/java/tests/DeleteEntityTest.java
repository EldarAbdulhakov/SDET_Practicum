package tests;

import helpers.BaseRequests;
import io.qameta.allure.Description;
import io.restassured.RestAssured;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DeleteEntityTest {

    @Test
    @Description("Checking the delete first entity")
    public void testDeleteEntity() {
        BaseRequests.createEntity();

        RestAssured
                .given()
                .when()
                .delete("http://localhost:8080/api/delete/%s".formatted(BaseRequests.getFirstEntityId()))
                .then()
                .log().all()
                .statusCode(204);

        Assert.assertFalse(BaseRequests.getEntityList()
                .stream()
                .anyMatch(obj -> obj.getId().equals(BaseRequests.expectedFirstId)));
    }
}
