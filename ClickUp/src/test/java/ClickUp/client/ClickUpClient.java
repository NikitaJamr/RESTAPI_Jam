package ClickUp.client;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static ClickUp.constants.ClickUpConstants.API_KEY;

public class ClickUpClient {
    public static Response getSpaceInfo(String spaceID) {
        return RestAssured
                .given().log().all()
                .contentType(ContentType.JSON)
                .header("Authorization", API_KEY)
                .when()
                .get("https://api.clickup.com/api/v2/space/"+spaceID)
                .then().log().all()
                .statusCode(200)
                .extract().response();
    }
    public static Response createFolder(String folderName, String spaceID) {
        return RestAssured
                .given().log().all()
                .contentType(ContentType.JSON)
                .body("{\"name\": \"" + folderName + "\"}")
                .header("Authorization", API_KEY)
                .when()
                .post("https://api.clickup.com/api/v2/space/"+spaceID+"/folder")
                .then().log().all()
                .statusCode(200)
                .extract().response();
    }

    public static Response getFolderInfo(String folderID) {
        return RestAssured
                .given().log().all()
                .contentType(ContentType.JSON)
                .header("Authorization", API_KEY)
                .when()
                .get("https://api.clickup.com/api/v2/folder/"+folderID)
                .then().log().all()
                .statusCode(200)
                .extract().response();
    }

    public static Response createList(String listName, String folderID) {
        return RestAssured
                .given().log().all()
                .contentType(ContentType.JSON)
                .header("Authorization", API_KEY)
                .body("{\"name\": \"" + listName + "\"}")
                .when()
                .post("https://api.clickup.com/api/v2/folder/"+folderID+"/list")
                .then().log().all()
                .statusCode(200)
                .extract().response();
    }

    public static Response getListInfo(String listID) {
        return RestAssured
                .given()
                .contentType(ContentType.JSON)
                .header("Authorization", API_KEY)
                .when()
                .get("https://api.clickup.com/api/v2/list/"+listID)
                .then().log().all()
                .statusCode(200)
                .extract().response();
    }

    public static Response createTask(String taskName, String listID) {
        return RestAssured
                .given()
                .contentType(ContentType.JSON)
                .header("Authorization", API_KEY)
                .body("{\"name\": \"" + taskName + "\"}")
                .when()
                .post("https://api.clickup.com/api/v2/list/"+listID+"/task")
                .then().log().all()
                .statusCode(200)
                .extract().response();
    }

    public static Response removeTask(String taskID) {
        return RestAssured
                .given()
                .contentType(ContentType.JSON)
                .header("Authorization", API_KEY)
                .when()
                .delete("https://api.clickup.com/api/v2/task/"+taskID)
                .then().log().all()
                .statusCode(204)
                .extract().response();
    }

    public static Response deleteFolder(String folderID) {
        return RestAssured
                .given()
                .contentType(ContentType.JSON)
                .header("Authorization", API_KEY)
                .when()
                .delete("https://api.clickup.com/api/v2/folder/"+folderID)
                .then().log().all()
                .statusCode(200)
                .extract().response();
    }
}
