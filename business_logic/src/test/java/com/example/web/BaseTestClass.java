package com.example.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import Application.GitMiningServer;
import Application.common.blService.verifyService.LoginService;
import Application.common.info.GitMiningUserInfo;
import Application.webService.WebClassifyController;
import Application.webService.WebSearchController;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {GitMiningServer.class})
@WebIntegrationTest
public class BaseTestClass {

    @Autowired
    WebApplicationContext wac;

    @Autowired
    WebSearchController webSearhController;
    
    @Autowired
    LoginService loginService;
    
    HashMap<String, Object> sessionMap = new HashMap<>();
    GitMiningUserInfo info;
    MockMvc mockMvc;
    String key = "rubinius";
    
    @Before
    public void init() throws Exception{
        info = loginService.verifyLogin("admin", "admin");
        sessionMap.put("user", info);
        
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
        
        mockMvc.perform(get("/repoSearch").param("key", key).sessionAttrs(sessionMap))
        .andExpect(status().isOk());

        mockMvc.perform(get("/userSearch").param("key", key).sessionAttrs(sessionMap))
        .andExpect(status().isOk());
    }
    
    protected MockHttpServletRequestBuilder get(String url) {
        return MockMvcRequestBuilders.get(url).sessionAttrs(sessionMap);
    }
    
}
