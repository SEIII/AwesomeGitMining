//package com.example.web;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.SpringApplicationConfiguration;
//import org.springframework.boot.test.WebIntegrationTest;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//
//import Application.GitMiningServer;
//import Application.business_logic.BlPackageInfo;
//import Application.data.DataPackageInfo;
//import Application.webService.WebClassifyController;
//import Application.webService.WebSearchController;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes = {GitMiningServer.class})
//@WebIntegrationTest
//public class WebSearchTest {
//
//    @Autowired
//    WebApplicationContext wac;
//
//    @Autowired
//    WebSearchController webSearhController;
//
//    @Autowired
//    WebClassifyController webClassifyController;
//
//    MockMvc mockMvc;
//
//    String repoKey = "rubinius";
//
//    String userKey = "8";
//
//    @Before
//    public void setUp() {
//        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
//    }
//
//    @Test
//    public void testSearchRepos() throws Exception {
//        mockMvc.perform(get("/repoSearch").param("key", repoKey))
//            .andExpect(status().isOk());
//
//        mockMvc.perform(get("/repoSearch")
//                .param("key", repoKey)
//                .param("page", "1"))
//        .andExpect(status().isOk());
//
//        mockMvc.perform(get("/repoSearch")
//                .param("key", repoKey)
//                .param("page", "1")
//                .param("sort", "star"))
//        .andExpect(status().isOk());
//    }
//
//    @Test
//    public void testSearchUser() throws Exception {
//        mockMvc.perform(get("/userSearch").param("key", userKey))
//            .andExpect(status().isOk());
//    }
//
//    @Test
//    public void testSearchUserCreateRepos() throws Exception {
//        mockMvc.perform(get("/createRepos").param("login", "rubinius"))
//            .andExpect(status().is2xxSuccessful());
//        mockMvc.perform(get("/createRepos").param("login", "8"))
//        .andExpect(status().is2xxSuccessful());
//    }
//
//    @Test
//    public void testSearchReposCollaborators() throws Exception {
//        mockMvc.perform(get("/reposCollaborators")
//                .param("owner", "rubinius")
//                .param("repoName", "rubinius")
//                )
//            .andExpect(status().isOk());
//    }
//
//}
