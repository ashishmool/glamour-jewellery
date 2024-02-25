package com.glamourjewellery.glamour_jewellery;

import com.glamourjewellery.glamour_jewellery.entity.SystemUser;
import com.glamourjewellery.glamour_jewellery.repo.SystemUserRepo;
import com.glamourjewellery.glamour_jewellery.service.SystemUserService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import lombok.extern.log4j.Log4j2;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@Log4j2
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class SystemUserStepDefinitions {

    @Autowired
    private SystemUserService systemUserService;

    @Autowired
    private SystemUserRepo systemUserRepo;

    @Given("getAll SystemUser")
    public void getall_SystemUser() {
        List<SystemUser> allUsers = systemUserService.getAll();
        log.info(allUsers);
        Assert.assertTrue(!allUsers.isEmpty());
    }

    @Given("getById SystemUser")
    public void getbyid_SystemUser() {
        systemUserService.getById(2L);
        System.out.println("User Fetched Successfully!");
    }

    @Given("save SystemUser")
    public void save_SystemUser() {
        // Logic to Save User
    }

    @Given("Verify Id")
    public void verify_Id() {
        // Logic to verify UserById
    }
    @Then("Verify Email")
    public void verify_Email() {
        // Logic to Verify SystemUser by Email
    }


}
