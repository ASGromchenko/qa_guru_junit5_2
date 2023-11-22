package tests;

import com.codeborne.selenide.Condition;
import data.LanguageOfProgramming;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Selenide.*;

public class SkillTests extends TestBase {

    @BeforeEach
    void setup() {
        open("https://skillbox.ru/");
    }

    @ValueSource(strings = {
            "TeamLead", "Developer"
    })

    @ParameterizedTest(name = "��������� ������ {0} ������ �������� �� ������ ������ �����������")
    @Tags({@Tag("WEB"), @Tag("SMOKE")})
    @DisplayName("�������� �������� ������� �� ���������� � ������ ��������� ����������")
    void searchProfessionQueryShouldNotHasEmptyResultTest(String query) {
        $("[name=search]").setValue(query).pressEnter();
        $$(".card-list").shouldBe(sizeGreaterThan(0));
    }

    @CsvSource(value = {
            "TeamLead, TeamLead",
            "Data science, Data Analyst"
    })

    @ParameterizedTest(name = "��� ���������� ������� {0} ������ ���� ���������� ������������ {1}")
    @Tag("REST")
    @DisplayName("�������� ������� � �������� ����������� ������ �����")
    void searchResultsShouldContainExpectedUrlTest(String query, String expectedUrl) {
        $("[name=search]").setValue(query).pressEnter();
        $(".courses-block").shouldHave(Condition.text(expectedUrl));
    }

    @Tag("REGRESS")
    @EnumSource(LanguageOfProgramming.class)
    @ParameterizedTest(name = "�������� ������������ ����������� ������������ �����")

    void correctNameOfTheCourseTest(LanguageOfProgramming language) {
        $("[name=search]").setValue(language.name()).pressEnter();
        $(".courses-block").shouldHave(Condition.text(language.description));
    }
}
