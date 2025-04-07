package tests;

import helpers.BaseRequests;
import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.Assert;
import org.testng.annotations.Test;
import pojo.Request.Addition;
import pojo.Request.Entity;

import java.util.List;

public class UpdateEntityTest {

    @Test
    @Description("Checking the update entity")
    public void testUpdateEntity() {
        Entity entity = new Entity(new Addition("Дополнительные сведения изменились", 100500),
                List.of(900, 800, 700), "Заголовок сущности, который изменился", false);

        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(entity)
                .when()
                .patch("http://localhost:8080/api/patch/%s".formatted(BaseRequests.getLastEntityId()))
                .then()
                .log().all()
                .statusCode(204);

        Assert.assertEquals(BaseRequests.getLastEntity().getId(), BaseRequests.expectedLastId);
        Assert.assertEquals(BaseRequests.getLastEntity().getTitle(), entity.getTitle());
        Assert.assertEquals(BaseRequests.getLastEntity().getVerified(), entity.getVerified());
        Assert.assertEquals(BaseRequests.getLastEntity().getImportant_numbers(), entity.getImportant_numbers());
    }
}
