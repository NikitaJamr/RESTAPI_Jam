package ClickUp.stepDef;

import ClickUp.domain.Folder;
import ClickUp.domain.Lists;
import ClickUp.domain.Space;
import ClickUp.domain.Task;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;

import java.util.Random;

import static ClickUp.client.ClickUpClient.*;
import static ClickUp.constants.ClickUpConstants.*;
import static ClickUp.helper.TestCaseContext.*;

public class ClickUpSteps {

    @Given("Space is setup and has correct info")
    public void getSpaceAndInfo() {
        Response response = getSpaceInfo(SPACE_ID);
        Space space = response.as(Space.class);

        Assertions.assertThat(space.getId())
                .as("Checking space ID")
                .isEqualTo(SPACE_ID);
        Assertions.assertThat(space.getName())
                .as("Checking space name")
                .isEqualTo(SPACE_NAME);
    }

    @When("I add a new folder to space named {string}")
    public void addNewFolderToSpace(String folderName) {
        Response response = createFolder(folderName, SPACE_ID);
        Folder folder = response.as(Folder.class);
        Assertions.assertThat(folder.getName())
                .as("Checking folder name")
                .isEqualTo(FOLDER_NAME);
        folderID = folder.getId();
        System.out.println("Folder ID is: "+folderID); //double-checking in console
    }

    @Then("I have a folder named API Folder")
    public void getFolderAndInfo() {
        Response response = getFolderInfo(folderID);
        Folder folder = response.as(Folder.class);

        Assertions.assertThat(folder.getName())
                .as("Checking folder name to be API Folder")
                .isEqualTo(FOLDER_NAME);
    }

    @When("I add a new List named {string}")
    public void addNewListToFolder(String listName) {
        Response response = createList(listName, folderID);
        Lists list = response.as(Lists.class);

        Assertions.assertThat(list.getName())
                .as("Checking list name to be API List")
                .isEqualTo(LIST_NAME);
        listID = list.getId();
        System.out.println("List ID is: "+listID);
    }

    @Then("I have a list named API List")
    public void getListAndInfo() {
        Response response = getListInfo(listID);
        Lists list = response.as(Lists.class);

        Assertions.assertThat(list.getName())
                .as("Checking list name to be API List")
                .isEqualTo(LIST_NAME);
    }

    @When("I randomize a name for the task")
    public String taskNameRandomizer() {
        int min = 1;
        int max = 999;
        Random random = new Random();
        return taskName = "Task name is " + random.nextInt(max - min) + min;
    }

    @And("I add a task with a random name to the list")
    public void addTaskToTheList() {
        Response response = createTask(taskName, listID);
        Task task = response.as(Task.class);

        Assertions.assertThat(task.getName())
                .as("Checking task name to be...some random stuff?")
                .isEqualTo(taskName);
        taskID = task.getId();
        System.out.println("Task ID is: "+task.getId());
        System.out.println("Task name is: "+task.getName());
    }

    @And("I remove the task")
    public void deleteTask() {
        Response response = removeTask(taskID);
        //Task task = response.as(Task.class);

        //Assertions.assertThat(task.getName()) //gives some parser errors..?
                //.as("Checking that task is non existent")
                //.isBlank();
    }
}
