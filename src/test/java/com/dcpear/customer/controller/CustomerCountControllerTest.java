package com.dcpear.customer.controller;

import com.dcpear.customer.domain.Customer;
import com.dcpear.customer.domain.Level;
import com.dcpear.customer.service.CustomerService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class CustomerCountControllerTest {
    private static final String COMMON_URL = "/count";

    //These Customers do not already exist in the db
    private static final Level CUSTOMER_LEVEL = Level.Platinum;
    private static final Long VALUE_RETURNED = 909L;

    @MockBean
    private CustomerService customerServiceMock;

    @Mock
    private Customer customerMock;

    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void setupReturnValuesOfMockMethods() {
        when(customerMock.getLevel()).thenReturn(CUSTOMER_LEVEL);
    }
    /**
     * HTTP /getCustomersByLevel
     */
    @Test
    public void getCustomersByLevel(){
        when(customerServiceMock.getLevelCount(CUSTOMER_LEVEL)).thenReturn(VALUE_RETURNED);

        String CALL_URL = COMMON_URL+"/"+ "getNumberOf/"+CUSTOMER_LEVEL;
        ResponseEntity<String> response = restTemplate.getForEntity(CALL_URL ,String.class );
        assertEquals((HttpStatus.OK),response.getStatusCode());
        assertEquals(VALUE_RETURNED.toString(), response.getBody());
    }
}