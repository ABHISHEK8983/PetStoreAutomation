package api.endpoints;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class userEndpoint {

   public static Response createUser(User payload){
      Response response=given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
      .when()
                .post(Routes.postURL);

              return response;
    }

    public static Response readUser(String username){
        Response response=given()
                .pathParam("username",username)
                .when()
                .get(Routes.getURL);

        return response;
    }

    public static Response updateUser(User payload,String username){
        Response response=given()
                                .contentType(ContentType.JSON)
                                .accept(ContentType.JSON)
                                .body(payload)
                                .pathParam("username",username)
                          .when()
                                .put(Routes.updateURL);

        return response;
    }

    public static Response deleteUser(String username){
        Response response=given()
                .pathParam("username",username)
                .when()
                .get(Routes.deleteURL);

        return response;
    }
}
