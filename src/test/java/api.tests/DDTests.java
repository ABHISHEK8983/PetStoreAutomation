package api.tests;

import api.endpoints.userEndpoint;
import api.payload.User;
import api.utilities.dataProviders;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DDTests {

    @Test(dataProvider = "Data",dataProviderClass = dataProviders.class,priority = 1)
    public void testPOSTUser(String userid,String username,String fname,String lname,String useremail,String passwd,String ph){

        User userPayload= new User();
        userPayload.setId(Integer.parseInt(userid));
        userPayload.setUsername(username);
        userPayload.setFirstname(fname);
        userPayload.setLastname(lname);
        userPayload.setEmail(useremail);
        userPayload.setPassword(passwd);
        userPayload.setPhone(ph);

        Response response= userEndpoint.createUser(userPayload);
        response.prettyPrint();
        Assert.assertEquals(response.getStatusCode(),200);
    }
    @Test(dataProvider = "userNames",dataProviderClass = dataProviders.class,priority = 2)
    public void testDeleteUser(String username){
        Response response= userEndpoint.deleteUser(username);
        response.prettyPrint();
        Assert.assertEquals(response.getStatusCode(),200);
    }
}