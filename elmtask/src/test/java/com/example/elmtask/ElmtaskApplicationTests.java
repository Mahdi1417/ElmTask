package com.example.elmtask;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;

@AutoConfigureMockMvc
@SpringBootTest
@WebAppConfiguration
class ElmtaskApplicationTests {

    @Autowired
    protected MockMvc mvc;
    @Autowired
    WebApplicationContext webApplicationContext;

    @Test
    public void getAllCustomers() throws Exception {
        String uri = "/customer/getall";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .with(httpBasic("user", "P@ssw0rd"))
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
    }

    @Test
    public void getCustomer() throws Exception {
        String uri = "/customer/get?id=1";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .with(httpBasic("user", "P@ssw0rd"))
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
    }

    @Test
    public void getCustomerForAnonymous() throws Exception {
        String uri = "/customer/get?id=1";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(401, status);
        String content = mvcResult.getResponse().getContentAsString();
    }

    @Test
    public void getAllServicesForAdmin() throws Exception {
        String uri = "/service/getall";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .with(httpBasic("admin", "P@ssw0rd"))
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
    }

    @Test
    public void getAllServicesForUser() throws Exception {
        String uri = "/service/getall";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .with(httpBasic("user", "P@ssw0rd"))
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(403, status);
        String content = mvcResult.getResponse().getContentAsString();
    }

}
