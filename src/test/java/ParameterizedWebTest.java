
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;


public class KinopoiskTest extends TestBase {
    @BeforeEach
    void SetUp() {
        open("https://hd.kinopoisk.ru/");
    }
    @ValueSource(strings = {
            "Леди Баг",
            "Дети леса",
            "Мушкетёры"
    })
    @ParameterizedTest(name = "Для поискового запроса {0} должен отдавать не пустой список фильмов")
    void searchResultsShouldNotBeEmpty(String searchQuery) {
        $("button[data-tid='SearchButton']").click();
        $("div[data-tid='Input'] input").setValue(searchQuery).pressEnter();
        $$("div[data-tid='SuggestLists'] li[data-tid='SuggestListItem']")
                .shouldBe(sizeGreaterThan(0));
    }

    @CsvSource(value = {
            "Metallica , Лучшее: Metallica",
            "Linkin Park , Лучшее: Linkin Park"
    })
    @ParameterizedTest(name = "Для поискового запроса {0} на странице должен отображаться раздел с лучшими треками {1}")
    void searchResultsShouldContainExpectedSection(String searchQuery, String displaySection) {
        $("#searchbox_input").setValue(searchQuery).pressEnter();
        $("[data-testid='mainline'] li[data-layout='organic']")
                .shouldHave(text(displaySection));
    }

}

