package ClickUp.stepDef;

import ClickUp.helper.TestCaseContext;
import io.cucumber.java.After;
import io.cucumber.java.Before;

import static ClickUp.client.ClickUpClient.deleteFolder;

public class Hooks {
    @Before
    public void beforeHook() {

    }
    @After
    public void afterHook() {
        deleteFolder(TestCaseContext.folderID);
    }
}
