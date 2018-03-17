package com.skip.dishes.hackathon.resource;

import com.skip.dishes.hackathon.model.Customer;
import com.skip.dishes.hackathon.repository.CustomerRepository;
import com.skip.dishes.hackathon.service.CustomerService;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.security.auth.message.callback.SecretKeyCallback;
import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/Customer")
public class CustomerResource {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    CustomerService customerService;

    @PostMapping(value = "/auth", params = {"email", "password"})
    public ResponseEntity<?> login(@PathParam("email") String email, @PathParam("password") String password, HttpServletRequest request) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "application/x-www-form-urlencoded");
            headers.add("Accept", "application/json");
            headers.add("Authorization", "Basic c2tpcDpza2lwLXNlY3JldA==");

            String url = request.getScheme() + "://" +
                    request.getServerName() + ":" +
                    request.getLocalPort() +
                    request.getContextPath() + "/oauth/token?grant_type=password";

            Map<String, String> params = new HashMap<>();
            params.put("email", email);
            params.put("password", password);

//            ResponseEntity<Object> responseEntity = restTemplate.postForEntity(url, headers, Object.class, params);

            HttpClient client = HttpClientBuilder.create().build();
            HttpPost post = new HttpPost(url);

            // add header
            post.setHeader("Content-Type", "application/x-www-form-urlencoded");
            post.setHeader("Accept", "application/json");
            post.setHeader("Authorization", "Basic c2tpcDpza2lwLXNlY3JldA==");

            List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
            urlParameters.add(new BasicNameValuePair("username", email));
            urlParameters.add(new BasicNameValuePair("password", password));

            post.setEntity(new UrlEncodedFormEntity(urlParameters));

            HttpResponse response = client.execute(post);

            String json = EntityUtils.toString(response.getEntity());

            return new ResponseEntity<>(json, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Username or password invalid", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping()
    public ResponseEntity<?> save(@RequestBody Customer customer) {
        try {
            return new ResponseEntity<>(customerService.save(customer), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("An error occurred while updating the entries. See the inner exception for details.", HttpStatus.BAD_REQUEST);
        }
    }
}
