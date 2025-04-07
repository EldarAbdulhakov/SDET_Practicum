package tests;

import helpers.BaseRequests;
import io.qameta.allure.Description;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import pojo.Request.Addition;
import pojo.Request.Entity;

import java.util.List;

public class CreateEntityTest {

    @BeforeSuite
    public static void setup() {
        RestAssured.filters(new AllureRestAssured());
    }

    @Test
    @Description("Checking the create entity")
    public void testCreateEntity() {
        Entity entity = new Entity(new Addition("Дополнительные сведения", 123),
                List.of(42, 87, 15), "Заголовок сущности", true);

        String entityId = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(entity)
                .when()
                .post("http://localhost:8080/api/create")
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .asString();

        Assert.assertEquals(Integer.parseInt(entityId), BaseRequests.getLastEntityId());
        Assert.assertEquals(BaseRequests.getLastEntity().getTitle(), entity.getTitle());
        Assert.assertEquals(BaseRequests.getLastEntity().getImportant_numbers(), entity.getImportant_numbers());
        Assert.assertEquals(BaseRequests.getLastEntity().getVerified(), entity.getVerified());
    }
}
