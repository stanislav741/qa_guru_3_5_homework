package tests;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class IssueCreate {

    String varUsername = Files.readAllLines(Paths.get("src/test/resources/credentials.txt")).get(0),    // username in the file is empty by default
    varPassword = Files.readAllLines(Paths.get("src/test/resources/credentials.txt")).get(1),           // password in the file is empty by default
    varIssueTitle = "New issue";

    public IssueCreate() throws IOException {
    }

    @Test
    void selenideSteps() {
        open("https://github.com/");

        $(byText("Sign in")).click();
        $("#login_field").val(varUsername);
        $("#password").val(varPassword);
        $("input[value='Sign in']").shouldBe(visible);
        $("input[value='Sign in']").click();

        $("#repos-container").shouldBe(visible);
        $("input[placeholder='Find a repository…']").val("qa_guru_3_5_homework").pressEnter();
        $(byText("qa_guru_3_5_homework")).click();

        $(byText("Code")).closest("li").sibling(0).click();
        $("#filters-select-menu").parent().sibling(0).shouldHave(text("New issue")).click();

        $("#issue_title").val(varIssueTitle);

        $("#assignees-select-menu").click();
        $("#assignees-select-menu").$(".select-menu-list").$(byText(varUsername)).click();
        $("#assignees-select-menu").click();

        $("#labels-select-menu").click();
//        $("#labels-select-menu").$(".select-menu-list").$(byText("documentation")).click();
        $("#labels-select-menu").$(".select-menu-list").$(byText("duplicate")).click();
        $("#labels-select-menu").$(".select-menu-list").$(byText("question")).click();
        $("#labels-select-menu").click();

        $("#new_issue .js-slash-command-surface").$(byText("Submit new issue")).click();

        $(".js-issue-title").shouldHave(text(varIssueTitle));
        $("#assignees-select-menu").sibling(0).shouldHave(text(varUsername));
//        $("#labels-select-menu").sibling(0).shouldHave(text("documentation"));
        $("#labels-select-menu").sibling(0).shouldHave(text("duplicate"));
        $("#labels-select-menu").sibling(0).shouldHave(text("question"));
    }

    @Test
    void lambdaSteps() {
        step("Open the website", () -> {
            open("https://github.com/");
        });

        step("Sign in", () -> {
            $(byText("Sign in")).click();
            $("#login_field").val(varUsername);
            $("#password").val(varPassword);
            $("input[value='Sign in']").shouldBe(visible);
            $("input[value='Sign in']").click();
        });

        step("Find & open the repo", () -> {
            $("#repos-container").shouldBe(visible);
            $("input[placeholder='Find a repository…']").val("qa_guru_3_5_homework").pressEnter();
            $(byText("qa_guru_3_5_homework")).click();
        });

        step("Open new issue form", () -> {
            $(byText("Code")).closest("li").sibling(0).click();
            $("#filters-select-menu").parent().sibling(0).shouldHave(text("New issue")).click();
        });

        step("Enter issue title", () -> {
            $("#issue_title").val(varIssueTitle);
        });

        step("Select assignee user", () -> {
            $("#assignees-select-menu").click();
            $("#assignees-select-menu").$(".select-menu-list").$(byText(varUsername)).click();
            $("#assignees-select-menu").click();
        });

        step("Select labels", () -> {
            $("#labels-select-menu").click();
//            $("#labels-select-menu").$(".select-menu-list").$(byText("documentation")).click();
            $("#labels-select-menu").$(".select-menu-list").$(byText("duplicate")).click();
            $("#labels-select-menu").$(".select-menu-list").$(byText("question")).click();
            $("#labels-select-menu").click();
        });

        step("Submit new issue", () -> {
            $("#new_issue .js-slash-command-surface").$(byText("Submit new issue")).click();
        });

        step("Data assert for the created issue", () -> {
            $(".js-issue-title").shouldHave(text(varIssueTitle));
            $("#assignees-select-menu").sibling(0).shouldHave(text(varUsername));
//            $("#labels-select-menu").sibling(0).shouldHave(text("documentation"));
            $("#labels-select-menu").sibling(0).shouldHave(text("duplicate"));
            $("#labels-select-menu").sibling(0).shouldHave(text("question"));
        });
    }

    @Test
    void annotationSteps() {
        final StepsDetails steps = new StepsDetails();

        steps.openMainPage();
        steps.signIn();
        steps.findRepo();
        steps.openNewIssueForm();
        steps.enterIssueTitle();
        steps.selectAssigneeUser();
        steps.selectLables();
        steps.submitNewIssue();
        steps.dataAssert();
    }

    public class StepsDetails {

        @Step("Open the website")
        public void openMainPage() {
            open("https://github.com/");
        }

        @Step("Sign in")
        public void signIn() {
            $(byText("Sign in")).click();
            $("#login_field").val(varUsername);
            $("#password").val(varPassword);
            $("input[value='Sign in']").shouldBe(visible);
            $("input[value='Sign in']").click();
        }

        @Step("Find & open the repo")
        public void findRepo() {
            $("#repos-container").shouldBe(visible);
            $("input[placeholder='Find a repository…']").val("qa_guru_3_5_homework").pressEnter();
            $(byText("qa_guru_3_5_homework")).click();
        }

        @Step("Open new issue form")
        public void openNewIssueForm() {
            $(byText("Code")).closest("li").sibling(0).click();
            $("#filters-select-menu").parent().sibling(0).shouldHave(text("New issue")).click();
        }

        @Step("Enter issue title")
        public void enterIssueTitle() {
            $("#issue_title").val(varIssueTitle);
        }

        @Step("Select assignee user")
        public void selectAssigneeUser() {
            $("#assignees-select-menu").click();
            $("#assignees-select-menu").$(".select-menu-list").$(byText(varUsername)).click();
            $("#assignees-select-menu").click();
        }

        @Step("Select labels")
        public void selectLables() {
            $("#labels-select-menu").click();
//            $("#labels-select-menu").$(".select-menu-list").$(byText("documentation")).click();
            $("#labels-select-menu").$(".select-menu-list").$(byText("duplicate")).click();
            $("#labels-select-menu").$(".select-menu-list").$(byText("question")).click();
            $("#labels-select-menu").click();
        }

        @Step("Submit new issue")
        public void submitNewIssue() {
            $("#new_issue .js-slash-command-surface").$(byText("Submit new issue")).click();
        }

        @Step("Data assert for the created issue")
        public void dataAssert() {
            $(".js-issue-title").shouldHave(text(varIssueTitle));
            $("#assignees-select-menu").sibling(0).shouldHave(text(varUsername));
//            $("#labels-select-menu").sibling(0).shouldHave(text("documentation"));
            $("#labels-select-menu").sibling(0).shouldHave(text("duplicate"));
            $("#labels-select-menu").sibling(0).shouldHave(text("question"));
        }
    }
}
