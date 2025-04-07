package helpers;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import pojo.Request.Addition;
import pojo.Request.Entity;
import pojo.Response.ResponseEntity;

import java.util.List;
import java.util.Optional;

public class BaseRequests {

    public static Integer expectedLastId;
    public static Integer expectedFirstId;

    public static String title = "Заголовок сущности";
    public static boolean verified = true;
    public static int page = 1;
    public static int perPage = 10;

    public static void createEntity() {
        Entity entity = new Entity(new Addition("Дополнительные сведения", 123),
                List.of(42, 87, 15), "Заголовок сущности", true);

        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(entity)
                .when()
                .post("http://localhost:8080/api/create");
    }

    public static Integer getCountEntities() {
        return RestAssured
                .given()
                .accept(ContentType.JSON)
                .when()
                .get("http://localhost:8080/api/getAll")
                .then()
                .extract().body().jsonPath().getList("entity", ResponseEntity.class).size();
    }

    public static Integer getLastEntityId() {
        List<ResponseEntity> entities = RestAssured
                .given()
                .accept(ContentType.JSON)
                .when()
                .get("http://localhost:8080/api/getAll")
                .then()
                .extract().body().jsonPath().getList("entity", ResponseEntity.class);

        expectedLastId = entities.get(entities.size() - 1).getId();
        return expectedLastId;
    }

    public static Integer getFirstEntityId() {
        List<ResponseEntity> entities = RestAssured
                .given()
                .accept(ContentType.JSON)
                .when()
                .get("http://localhost:8080/api/getAll")
                .then()
                .extract().body().jsonPath().getList("entity", ResponseEntity.class);

        expectedFirstId = entities.get(0).getId();
        return expectedFirstId;
    }

    public static List<ResponseEntity> getEntityList() {
        return RestAssured
                .given()
                .accept(ContentType.JSON)
                .when()
                .get("http://localhost:8080/api/getAll")
                .then()
                .extract().body().jsonPath().getList("entity", ResponseEntity.class);
    }

    public static Optional<ResponseEntity> getEntityById(Integer id) {
        List<ResponseEntity> responseEntity = RestAssured
                .given()
                .accept(ContentType.JSON)
                .when()
                .get("http://localhost:8080/api/getAll")
                .then()
                .extract().body().jsonPath().getList("entity", ResponseEntity.class);

        return responseEntity
                .stream()
                .filter(obj -> obj.getId().equals(id))
                .findFirst();
    }

    public static ResponseEntity getLastEntity() {
        return RestAssured
                .given()
                .accept(ContentType.JSON)
                .when()
                .get("http://localhost:8080/api/get/%s".formatted(BaseRequests.getLastEntityId()))
                .then()
                .extract().as(ResponseEntity.class);
    }
}
