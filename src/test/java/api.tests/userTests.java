package api.tests;

import api.endpoints.userEndpoint2;
import api.payload.User;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;



public class userTests {

    Faker faker ;
    User userPayload;
    public Logger logger;
    @BeforeClass
    public void setupData(){
        faker =new Faker();
         userPayload = new User();

         userPayload.setId(faker.idNumber().hashCode());
         userPayload.setUsername(faker.name().username());
         userPayload.setFirstname(faker.name().firstName());
         userPayload.setLastname(faker.name().lastName());
         userPayload.setEmail(faker.internet().safeEmailAddress());
         userPayload.setPassword(faker.internet().password());
         userPayload.setPhone(faker.phoneNumber().phoneNumber());

         logger = LogManager.getLogger(this.getClass());

    }

    @Test(priority=1)
    public void testPOSTUser(){
        logger.info("*****************Creating User*****************");
       Response response= userEndpoint2.createUser(userPayload);
       response.then().log().all();
       Assert.assertEquals(response.getStatusCode(),200);
    }

    @Test(priority=2)
    public void testGETUser(){
        Response response=userEndpoint2.readUser(this.userPayload.getUsername());
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);
    }

    @Test(priority=3)
    public void testUpdateUser(){

        userPayload.setFirstname(faker.name().firstName());
        userPayload.setLastname(faker.name().lastName());
        userPayload.setEmail(faker.internet().safeEmailAddress());
        Response response= userEndpoint2.updateUser(userPayload,this.userPayload.getUsername());
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);
        Response responseAfter=userEndpoint2.readUser(this.userPayload.getUsername());
        response.then().log().all();
    }

    @Test(priority=4)
    public void testDeleteUser(){
        Response response=userEndpoint2.deleteUser(this.userPayload.getUsername());
        response.then().log().all();
        response.then().statusCode(200);
    }
}
