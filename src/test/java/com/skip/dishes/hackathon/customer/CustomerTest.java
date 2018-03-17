package com.skip.dishes.hackathon.customer;

import com.mysql.jdbc.StringUtils;
import com.skip.dishes.hackathon.TestUtils;
import com.skip.dishes.hackathon.model.Customer;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.util.StringUtil;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static io.restassured.RestAssured.given;

@RunWith(SpringJUnit4ClassRunner.class)
public class CustomerTest extends TestUtils {

    @Test
    public void loginTest() throws Exception {
        Response api = RestAssured.given()
                .that().contentType("application/json")
                .when()
                .post(API_URL + "/Customer/auth?email=admin@admin.com&password=admin");

        System.out.println(api.getBody());

        Assert.assertTrue("Login failed!", api.getStatusCode() == 200);
    }

    @Test
    public void createTest() throws Exception {
        String email = RandomStringUtils.randomAlphabetic(20).toLowerCase();
        Customer customer = new Customer();
        customer.setName("Test");
        customer.setEmail(email + "@gmail.com");
        customer.setPassword("pass");
        customer.setAddress("Address Test");

        Response api = RestAssured.given()
                .that().contentType("application/json")
                .body(customer)
                .when()
                .post(API_URL + "/Customer");

        Assert.assertTrue("Create Customer failed!", api.getStatusCode() == 200);
    }

    @After
    public void eraseDB() throws Exception {

    }
}
