package com.skip.dishes.hackathon.cousine;

import com.skip.dishes.hackathon.TestUtils;
import com.skip.dishes.hackathon.model.Cousine;
import com.skip.dishes.hackathon.model.Store;
import com.skip.dishes.hackathon.repository.StoreRepository;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static com.skip.dishes.hackathon.TestUtils.API_URL;

@RunWith(SpringJUnit4ClassRunner.class)
public class CousineTest extends TestUtils {

    @Test
    public void findAllTest() throws Exception {

        String access_token = login("admin@admin.com", "admin");

        Response api = RestAssured.given()
                .that().contentType("application/json")
                .header("Authorization", "Bearer " + access_token)
                .when()
                .get(API_URL + "/Cousine");

        List<Cousine> cousineList = Arrays.asList(api.getBody().as(Cousine[].class));

        Assert.assertTrue("FindAll Cousine failed!", cousineList.size() > 0);
    }

    @Test
    public void findBySearch() throws Exception {

        String access_token = login("admin@admin.com", "admin");

        Response api = RestAssured.given()
                .that().contentType("application/json")
                .header("Authorization", "Bearer " + access_token)
                .when()
                .get(API_URL + "/Cousine/search/Japanese");

        List<Cousine> cousineList = Arrays.asList(api.getBody().as(Cousine[].class));

        Assert.assertTrue("FindAll Cousine failed!", cousineList.size() > 0);
    }

    @Test
    public void findByCousineId() throws Exception {

        String access_token = login("admin@admin.com", "admin");

        Response api = RestAssured.given()
                .that().contentType("application/json")
                .header("Authorization", "Bearer " + access_token)
                .when()
                .get(API_URL + "/Cousine/search/1/stores");

        List<Store> stores = Arrays.asList(api.getBody().as(Store[].class));

        Assert.assertTrue("FindAll Cousine failed!", stores.size() > 0);
    }
}
