package com.skip.dishes.hackathon;

import com.skip.dishes.hackathon.model.Cousine;
import com.skip.dishes.hackathon.model.Customer;
import com.skip.dishes.hackathon.model.Store;
import com.skip.dishes.hackathon.oauth.service.UserService;
import com.skip.dishes.hackathon.service.CousineService;
import com.skip.dishes.hackathon.service.CustomerService;
import com.skip.dishes.hackathon.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
@EnableResourceServer
@EnableAuthorizationServer
@SpringBootApplication(scanBasePackages = "com.skip.dishes.hackathon")
public class HackathonApplication extends ResourceServerConfigurerAdapter {

    @Autowired
    private TokenStore tokenStore;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CousineService cousineService;

    @Autowired
    private StoreService storeService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    public TokenStore tokenStore() {
        return new InMemoryTokenStore();
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.httpBasic().disable();
        http.csrf().disable();
        http.anonymous().and().authorizeRequests()
                .antMatchers("/api/v1/Customer/auth/", "/api/v1/oauth/token**").permitAll();
    }

    public static void main(String[] args) {
        SpringApplication.run(HackathonApplication.class, args);
    }

    @PostConstruct
    public void postConstruct() throws Exception {
        Customer customer = null;
        customer = customerService.loadUserByUsername("admin@admin.com");

        if (customer == null) {
            customer = new Customer();
            customer.setName("User ADMIN");
            customer.setAddress("Address Admin");
            customer.setEmail("admin@admin.com");
            customer.setPassword("admin");
            customerService.save(customer);
        }

        if (cousineService.search("Japanese").size() == 0) {
            Cousine c1 = new Cousine();
            c1.setName("Japanese");
            c1 = cousineService.save(c1);

            Store store = new Store();
            store.setName("Store 1");
            store.setAddress("Address 1");
            store.setCousineId(c1.getId());

            storeService.save(store);
        }

        if (cousineService.search("Mexican").size() == 0) {
            Cousine c2 = new Cousine();
            c2.setName("Mexican");
            c2 = cousineService.save(c2);

            Store store = new Store();
            store.setName("Store 2");
            store.setAddress("Address 2");
            store.setCousineId(c2.getId());

            storeService.save(store);
        }
    }
}
