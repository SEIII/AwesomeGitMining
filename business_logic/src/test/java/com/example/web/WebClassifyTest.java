package com.example.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.sun.org.apache.xerces.internal.jaxp.validation.WrappedSAXException;

import Application.GitMiningServer;
import Application.business_logic.bl.stat.callback.StatTotalCallbackImpl;
import Application.common.StarRelatedItem;
import Application.common.DTO.GitUserTotalInfo;
import Application.common.blService.statService.callback.StatTotalCallback;
import Application.common.blService.userService.GitUserTotalInfoLogicService;
import Application.common.blService.verifyService.LoginService;
import Application.common.info.GitMiningUserInfo;
import Application.webService.WebClassifyController;
import Application.webService.WebSearchController;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import java.util.HashMap;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {GitMiningServer.class})
@WebIntegrationTest
public class WebClassifyTest extends BaseTestClass{

    String repoKey = "rubinius";
    String userKey = "8";
    
    @Autowired
    WebClassifyController webClassifyController;
    
    @Autowired
    GitUserTotalInfoLogicService totalInfoService;
    

    @Before
    public void Before() {
    }
    
    @Test
    public void testLogin() throws Exception{
        GitMiningUserInfo info = loginService.verifyLogin("admin", "admin");
        assertEquals(info.getAccount(), "admin");
    }
    
    @Test
    public void testRepoLanguage() throws Exception {
        
        mockMvc.perform(get("/classify/repoLanguage").param("key", key)
                .sessionAttrs(sessionMap))
        .andExpect(status().isOk());
    }

    @Test
    public void testUserLanguage() throws Exception {
        mockMvc.perform(get("/classify/userLanguage").param("key", key)
                .sessionAttrs(sessionMap))
        .andExpect(status().isOk());
    }

    @Test
    public void testUserType() throws Exception {
        mockMvc.perform(get("/classify/userType").param("key", key)
                .sessionAttrs(sessionMap))
        .andExpect(status().isOk());
    }

    @Test
    public void testRepoDate() throws Exception{
        mockMvc.perform(get("/classify/repoDate").param("key", key)
                .sessionAttrs(sessionMap))
        .andExpect(status().isOk());
    }

    @Test
    public void testUserDate() throws Exception{
        mockMvc.perform(get("/classify/userDate").param("key", key)
                .sessionAttrs(sessionMap))
        .andExpect(status().isOk());
    }

    @Test
    public void testRepoExp() throws Exception{
        mockMvc.perform(get("/classify/repoExp").param("key", key)
                .sessionAttrs(sessionMap))
        .andExpect(status().isOk());
    }

    @Test
    public void testUserExp() throws Exception{
        mockMvc.perform(get("/classify/userExp").param("key", key)
                .sessionAttrs(sessionMap))
        .andExpect(status().isOk()).andDo(print());
    }
    
    @Test
    public void testSearchRepos() throws Exception {
        mockMvc.perform(get("/repoSearch").param("key", repoKey)
                .sessionAttrs(sessionMap))
            .andExpect(status().isOk());

        mockMvc.perform(get("/repoSearch")
                .param("key", repoKey)
                .param("page", "1")
                .sessionAttrs(sessionMap))
        .andExpect(status().isOk());

        mockMvc.perform(get("/repoSearch")
                .param("key", repoKey)
                .param("page", "1")
                .param("sort", "star")
                .sessionAttrs(sessionMap))
        .andExpect(status().isOk());
    }

    @Test
    public void testSearchUser() throws Exception {
        mockMvc.perform(get("/userSearch").param("key", userKey)
                .sessionAttrs(sessionMap))
            .andExpect(status().isOk());
    }

    
    @Test
    public void testTotalReposStat() throws Exception{
        String statTotal = "/statTotalRepos/";
        
        mockMvc.perform(get(statTotal+"language")).andExpect(status().isOk());
        mockMvc.perform(get(statTotal+"fork")).andExpect(status().isOk());
        mockMvc.perform(get(statTotal+"createTime")).andExpect(status().isOk());
        mockMvc.perform(get(statTotal+"star")).andExpect(status().isOk());
        mockMvc.perform(get(statTotal+"contributor")).andExpect(status().isOk());
        mockMvc.perform(get(statTotal+"starRelated")
                .param("item", StarRelatedItem.contributor.toString()))
        .andExpect(status().isOk());
        
        mockMvc.perform(get(statTotal+"starRelated")
                .param("item", StarRelatedItem.fork.toString()))
        .andExpect(status().isOk());
   
    }
    
    @Test
    public void testTotalUserStat() throws Exception{
        String statTotal = "/statTotalUser/";
        
        mockMvc.perform(get(statTotal+"createTime")).andExpect(status().isOk());
        mockMvc.perform(get(statTotal+"createRepo")).andExpect(status().isOk());
    }
    
    @Test
    public void testDetailInfo() throws Exception{
        mockMvc.perform(get("/userDetail")
                .param("login", userKey))
            .andExpect(status().isOk());
        
        mockMvc.perform(get("/repoDetail")
                .param("owner", "NJUShenbin")
                .param("repoName", "EL-game"))
            .andExpect(status().isOk());
    }
    
    @Test
    public void testStatUserTotalInfo() throws Exception {
        String login = "NJUShenbin";
        
        StatTotalCallback callback = new StatTotalCallbackImpl(null, login);
        GitUserTotalInfo info = totalInfoService.cacheUser(login, callback);
        
        assertEquals(info.getUser().getLogin(), login);
        
        boolean isHit = totalInfoService.isHit(login);
        assertTrue(isHit);
        
        mockMvc.perform(get("/createRepos").param("login", login))
            .andExpect(status().isOk());
        
        mockMvc.perform(get("/contributeRepos").param("login", login))
        .andExpect(status().isOk());
        
    }
    
    @Test
    public void testSingleUser() throws Exception {
        String login = "NJUShenbin";
        String statSingle = "/statSingleUser";
        StatTotalCallback callback = new StatTotalCallbackImpl(null, login);
        GitUserTotalInfo info = totalInfoService.cacheUser(login, callback);
        
        mockMvc.perform(get(statSingle+"/radar").param("login", login))
        .andExpect(status().isOk());
        
        mockMvc.perform(get(statSingle+"/productive").param("login", login))
        .andExpect(status().isOk());
        
        mockMvc.perform(get(statSingle+"/contributionPie").param("login", login))
        .andExpect(status().isOk());
        
        mockMvc.perform(get(statSingle+"/value").param("login", login))
        .andExpect(status().isOk());
        
        mockMvc.perform(get(statSingle+"/code").param("login", login))
        .andExpect(status().isOk());
        
        mockMvc.perform(get(statSingle+"/codeValue").param("login", login))
        .andExpect(status().isOk());
        
        mockMvc.perform(get(statSingle+"/age").param("login", login))
        .andExpect(status().isOk());
        
    }
    
    @Test
    public void testSingleRepo() throws Exception {
        String owner = "NJUShenbin";
        String repoName = "EL-game";
        String statSingle = "/statSingleRepo";
        
        mockMvc.perform(get(statSingle+"/radar")
                .param("owner", owner)
                .param("repoName",repoName))
        .andExpect(status().isOk());
        
        mockMvc.perform(get(statSingle+"/languages")
                .param("owner", owner)
                .param("repoName",repoName))
        .andExpect(status().isOk());
        
        mockMvc.perform(get(statSingle+"/codeFrequency")
                .param("owner", owner)
                .param("repoName",repoName))
        .andExpect(status().isOk());
        
        
    }
    
    
}
