package helpers;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import models.Entity;

import java.util.List;
import java.util.Optional;

public class BaseRequests {

    public static Integer expectedLastId;
    public static Integer expectedFirstId;

    public static String title = "Заголовок сущности";
    public static boolean verified = true;
    public static int page = 1;
    public static int perPage = 10;

    public static RequestSpecification initRequestSpecification() {
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder
                .setContentType(ContentType.JSON)
                .addFilter(new AllureRestAssured())
                .setBaseUri("http://localhost:8080/")
                .setAccept(ContentType.JSON);
        RequestSpecification requestSpecification;
        return requestSpecification = requestSpecBuilder.build();
    }

    public static void createEntity() {
        Entity entity = Entity.builder().build();

        RestAssured
                .given()
                .spec(initRequestSpecification())
                .body(entity)
                .when()
                .post("api/create");
    }

    public static void deleteLastEntity() {
        RestAssured
                .given()
                .spec(initRequestSpecification())
                .when()
                .delete("api/delete/%s".formatted(BaseRequests.getLastEntityId()));
    }

    public static Integer getCountEntities() {
        return RestAssured
                .given()
                .spec(initRequestSpecification())
                .when()
                .get("api/getAll")
                .then()
                .extract().body().jsonPath().getList("entity", Entity.class).size();
    }

    public static Integer getLastEntityId() {
        List<Entity> entities = RestAssured
                .given()
                .spec(initRequestSpecification())
                .when()
                .get("api/getAll")
                .then()
                .extract().body().jsonPath().getList("entity", Entity.class);

        expectedLastId = entities.get(entities.size() - 1).getId();
        return expectedLastId;
    }

    public static Integer getFirstEntityId() {
        List<Entity> entities = RestAssured
                .given()
                .spec(initRequestSpecification())
                .when()
                .get("api/getAll")
                .then()
                .extract().body().jsonPath().getList("entity", Entity.class);

        expectedFirstId = entities.get(0).getId();
        return expectedFirstId;
    }

    public static List<Entity> getEntityList() {
        return RestAssured
                .given()
                .spec(initRequestSpecification())
                .when()
                .get("api/getAll")
                .then()
                .extract().body().jsonPath().getList("entity", Entity.class);
    }

    public static Entity getLastEntity() {
        return RestAssured
                .given()
                .spec(initRequestSpecification())
                .when()
                .get("api/get/%s".formatted(BaseRequests.getLastEntityId()))
                .then()
                .extract().as(Entity.class);
    }

    public static Optional<Entity> getEntityById(Integer id) {
        List<Entity> entity = RestAssured
                .given()
                .spec(initRequestSpecification())
                .when()
                .get("api/getAll")
                .then()
                .extract().body().jsonPath().getList("entity", Entity.class);

        return entity
                .stream()
                .filter(obj -> obj.getId().equals(id))
                .findFirst();
    }
}
