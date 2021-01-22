package com.dcpear.customer.service;

import com.dcpear.customer.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.Assert.assertNotEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserServiceIntegrationTest {

    @Autowired
    private UserService service;

    @Test
    public void signup() {
        Optional<User> user = service.signup("dummyUsername", "dummypassword",
                "john", "doe");
        assertNotEquals("dummypassword", user.get().getPassword());
        System.out.println("Encoded Password = " + user.get().getPassword());

    }
}