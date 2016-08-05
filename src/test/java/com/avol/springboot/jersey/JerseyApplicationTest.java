package com.avol.springboot.jersey;

import com.avol.springboot.jersey.api.JerseyResponse;
import com.avol.springboot.jersey.api.PostalCode;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.hateoas.Link;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by Durga on 7/13/2015.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = JerseyApplication.class)
@WebIntegrationTest(value = "server.address=localhost", randomPort = true)
public class JerseyApplicationTest {

    private RestTemplate restTemplate = null;

    @Value("${local.server.port}")  //boot injects the port used for bootstart the application.
    private int port;

    private String baseURL = null;

    @Before
    public void setUp(){
        restTemplate = new TestRestTemplate();
        baseURL = "http://localhost:"+ port + "/avol/postal";
    }

    @Test
    public void testCreate() {

        JerseyResponse response = restTemplate.postForObject(baseURL, postalCode("560027", "WilsonGarden"),
                JerseyResponse.class);
        assertEquals(Response.Status.CREATED.getStatusCode(), response.getStatusCode());
        assertNotNull(response.getLinks());
    }

    @Test
    public void testGet() {
        PostalCode postalCode = postalCode("560027", "WilsonGarden");
        //Create.
        JerseyResponse response = restTemplate.postForObject(baseURL, postalCode,
                JerseyResponse.class, new Object[]{});

        //Get.
        response = restTemplate.getForObject(response.getLink(Link.REL_SELF).getHref(),
                JerseyResponse.class, new Object[]{});

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatusCode());
        assertNotNull(response.getPostalCodes());
        assertEquals(postalCode.getCode(), response.getPostalCodes().get(0).getCode());
    }

    @Test
    public void testListGet() {
        PostalCode postalCode = postalCode("560027", "WilsonGarden");
        //Create.
        JerseyResponse response = restTemplate.postForObject(baseURL, postalCode,
                JerseyResponse.class, new Object[]{});
        assertEquals(Response.Status.CREATED.getStatusCode(), response.getStatusCode());
        //Get.
        response = restTemplate.getForObject(baseURL + "/list",
                JerseyResponse.class, new Object[]{});

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatusCode());
        assertNotNull(response.getPostalCodes());
        assertTrue(response.getPostalCodes().size() > 0);
    }

    private PostalCode postalCode(String code, String areaName) {
        PostalCode postalCode = new PostalCode();
        postalCode.setCode(code);
        postalCode.setAreaName(areaName);
        return postalCode;
    }

    @Test
    public void testRequestParams() {
        Map<String , Object> map = new HashMap<String, Object>(){
            {
                put("Name", "Lova");
                put("Age", 28);
            }
        };
        restTemplate.setMessageConverters(Arrays.asList(new FormHttpMessageConverter()));
        JerseyResponse response = restTemplate.postForObject(baseURL + "/testRequestParams",
                map,JerseyResponse.class);
        System.out.println("JerseyApplicationTest.testRequestParams" + response.getStatusCode());
        assertEquals(response.getStatusCode(), Response.Status.OK.getStatusCode());
    }
}
