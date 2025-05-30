import data.Sections;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;


public class ParameterizedWebTest extends TestBase {
    @ValueSource(strings = {
            "Леди Баг",
            "Дети леса",
            "Мушкетёры"
    })
    @ParameterizedTest(name = "Для поискового запроса {0} должен отдавать не пустой список фильмов")
    @Tag("REGRESS")
    @Disabled("TASK-123")
    void searchResultsShouldNotBeEmpty(String searchQuery) {
        open("https://hd.kinopoisk.ru/");
        $("button[data-tid='SearchButton']").click();
        $("div[data-tid='Input'] input").setValue(searchQuery).pressEnter();
        $$("div[data-tid='SuggestLists'] li[data-tid='SuggestListItem']")
                .shouldBe(sizeGreaterThan(0));
    }
    @CsvSource(value = {
            "Эрарта , «Эрарта» — это частный музей современного искусства",
            "Эрмитаж , Государственный Эрмитаж — это крупнейший в России"
    })
    @ParameterizedTest(name = "У карточки {0} есть описание {1}...")
    void searchResultsShouldContainExpectedUrl(String searchQuery, String expectedDescription) {
        open("https://yandex.ru/maps/");
        $("input.input__control[placeholder='Поиск и выбор мест']").setValue(searchQuery).pressEnter();
        $(".business-story-entry-view__description").shouldHave(text(expectedDescription));
    }
    @EnumSource(Sections.class)
    @DisplayName("Наличие и кликабельность всех разделов на главной странице")
    @ParameterizedTest
    @Tag("SMOKE")
    void presenceOfSectionsOnThePage(Sections section) {
        open("https://books.yandex.ru/");
        $(section.getSelector()).shouldHave(text(section.getSectionName()))
                .shouldBe(interactable);
    }
}