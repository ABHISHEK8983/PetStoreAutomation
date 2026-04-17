package api.endpoints;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import java.util.ResourceBundle;
import static io.restassured.RestAssured.given;

public class userEndpoint2 {

    static ResourceBundle getUrl(){
        ResourceBundle routes= ResourceBundle.getBundle("routes") ;
        return routes;
    }

   public static Response createUser(User payload){
       String postURL= getUrl().getString("postURL");
      Response response=given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
      .when()
                .post(postURL);

              return response;
    }

    public static Response readUser(String username){
        String getURL= getUrl().getString("getURL");
        Response response=given()
                .pathParam("username",username)
                .when()
                .get(getURL);

        return response;
    }

    public static Response updateUser(User payload,String username){
        String updateURL= getUrl().getString("updateURL");
        Response response=given()
                                .contentType(ContentType.JSON)
                                .accept(ContentType.JSON)
                                .body(payload)
                                .pathParam("username",username)
                          .when()
                                .put(updateURL);

        return response;
    }

    public static Response deleteUser(String username){
        String deleteURL= getUrl().getString("deleteURL");
        Response response=given()
                .pathParam("username",username)
                .when()
                .get(deleteURL);

        return response;
    }
}
