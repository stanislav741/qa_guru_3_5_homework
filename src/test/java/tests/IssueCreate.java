package tests;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class IssueCreate {

    String varUsername = Files.readAllLines(Paths.get("src/test/resources/credentials.txt")).get(0),
            varPassword = Files.readAllLines(Paths.get("src/test/resources/credentials.txt")).get(1),
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
        $("#labels-select-menu").$(".select-menu-list").$(byText("documentation")).click();
        $("#labels-select-menu").$(".select-menu-list").$(byText("duplicate")).click();
        $("#labels-select-menu").$(".select-menu-list").$(byText("question")).click();
        $("#labels-select-menu").click();

        $("#new_issue .js-slash-command-surface").$(byText("Submit new issue")).click();

        $(".js-issue-title").shouldHave(text(varIssueTitle));
        $("#assignees-select-menu").sibling(0).shouldHave(text(varUsername));
        $("#labels-select-menu").sibling(0).shouldHave(text("documentation"));
        $("#labels-select-menu").sibling(0).shouldHave(text("duplicate"));
        $("#labels-select-menu").sibling(0).shouldHave(text("question"));
    }
}
