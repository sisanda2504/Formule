package za.ac.cput.controller;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import za.ac.cput.domain.Customer;
import za.ac.cput.factory.CustomerFactory;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.MethodName.class)
class CustomerControllerTest {

    private static Customer customer;

    @Autowired
    private TestRestTemplate restTemplate;
    private  static final String BASE_URL = "http://localhost:8080/formule/customer";

    @BeforeAll
    static void setUp() throws Exception {
        customer = CustomerFactory.createCustomer(
                "Agnes",
                "Mabusela",
                "0763728303",
                "agnes@gmail.com",
                "password123",
                123
        );
    }

    @Test
    void a_create() {
        String url = BASE_URL + "/create";
        ResponseEntity<Customer> postResponse = restTemplate.postForEntity(url, customer, Customer.class);
        assertNotNull(postResponse);
        Customer customerSaved = postResponse.getBody();
        assertEquals(customer.getId(), customerSaved.getId());
        System.out.println("Created: "+ customerSaved);
    }

    @Test
    void b_read(){
        String url = BASE_URL + "/read" + customer.getId();
        ResponseEntity<Customer> response = this.restTemplate.getForEntity(url, Customer.class);
        assertNotEquals(customer.getId(), response.getBody().getId());
        System.out.println("Read: "+ response.getBody());
    }

    @Test
    void c_update(){
        Customer updatedCustomer = new Customer.Builder().copy(customer).setPhoneNumber("073873939").build();
        String url = BASE_URL + "/update";
        ResponseEntity<Customer> response = this.restTemplate.postForEntity(url, updatedCustomer, Customer.class);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(updatedCustomer.getPhoneNumber(), response.getBody().getPhoneNumber());
        System.out.println("Updated: "+ response.getBody());
    }

    @Test
    void d_delete(){
        String url = BASE_URL + "/delete" + customer.getId();
        this.restTemplate.delete(url);

        ResponseEntity<Customer> response = this.restTemplate.getForEntity(BASE_URL+"/read/"+customer.getId(), Customer.class);
        assertNull(response.getBody());
        System.out.println("Deleted: "+ response.getBody());
    }

    @Test
    void d_getAll(){
        String url = BASE_URL + "/getAll";
        ResponseEntity<Customer[]> response = this.restTemplate.getForEntity(url, Customer[].class);
        assertNotNull(response.getBody());
        assertTrue(response.getBody().length>0);
        System.out.println("GetAll: ");
        for (Customer customer : response.getBody()) {
            System.out.println(customer);
        }


    }


}