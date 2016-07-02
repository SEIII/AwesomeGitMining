package com.example.dao;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Application.data.DAO.impl.GitHubAPIRepoAndUserDAO;

public class TestGitHubDao {

    GitHubAPIRepoAndUserDAO dao;
    @Before
    public void setUp() throws Exception {
        dao = new GitHubAPIRepoAndUserDAO();
    }

    @After
    public void tearDown() throws Exception {
    }



    @Test
    public void testGetSingleBasicRepoInfo() {
        System.out.println(dao.getSingleBasicRepoInfo("NJUShenbin", "EL-game"));
        System.out.println(dao.getSingleBasicRepoInfo("rubinius", "rubinius"));
    }

    @Test
    public void testGetSingleBasicUserInfo() {
        System.out.println(dao.getSingleBasicUserInfo("rubinius"));
        System.out.println(dao.getSingleBasicUserInfo("8"));
        System.out.println(dao.getSingleBasicUserInfo("NJUShenbin"));
    }

    @Test
    public void testGetRepositoryForkList() {
        
    }

    @Test
    public void testGetRepositoryContributorList() {
        
    }

    @Test
    public void testGetRepositoryCollaboratorList() {
        
    }

    @Test
    public void testGetUserCreatedRepoList() {
        
    }

    @Test
    public void testGetUserContributedRepoList() {
        
    }


    @Test
    public void testGetAllRepoInfo() {
        fail("Not yet implemented");
    }

    @Test
    public void testGetAllUserInfo() {
        fail("Not yet implemented");
    }

    @Test
    public void testGetUserIcon() {
        fail("Not yet implemented");
    }

    @Test
    public void testGetAllLanguageLine() {
        fail("Not yet implemented");
    }

    @Test
    public void testGetUserEvent() {
        fail("Not yet implemented");
    }

    @Test
    public void testGetMostPopularRepos() {
        fail("Not yet implemented");
    }

    @Test
    public void testGetMostLanguageRepos() {
        fail("Not yet implemented");
    }

    @Test
    public void testGetMostContributedUsers() {
        fail("Not yet implemented");
    }

    @Test
    public void testGetRepoListByKey() {
        fail("Not yet implemented");
    }

    @Test
    public void testGetCreatedRepoFromGit() {
        fail("Not yet implemented");
    }

    @Test
    public void testGetSearchRepoFromGit() {
        fail("Not yet implemented");
    }

}
