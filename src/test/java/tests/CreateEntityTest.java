package tests;

import helpers.BaseRequests;
import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import models.Entity;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CreateEntityTest {

    private RequestSpecification requestSpecification;

    @BeforeClass
    public void setup() {
        requestSpecification = BaseRequests.initRequestSpecification();
    }

    @Test
    @Description("Checking the create entity")
    public void testCreateEntity() {
        Entity entity = Entity.builder().build();

        String entityId = RestAssured
                .given()
                .spec(requestSpecification)
                .body(entity)
                .when()
                .post("api/create")
                .then()
                .statusCode(200)
                .extract()
                .asString();

        Assert.assertEquals(Integer.parseInt(entityId), BaseRequests.getLastEntityId());
        Assert.assertEquals(BaseRequests.getLastEntity().getTitle(), entity.getTitle());
        Assert.assertEquals(BaseRequests.getLastEntity().getImportant_numbers(), entity.getImportant_numbers());
        Assert.assertEquals(BaseRequests.getLastEntity().getVerified(), entity.getVerified());
    }
}
