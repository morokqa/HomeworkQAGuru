package data;

public enum Sections {
    HOME("Главное", "[data-test-id='HEADER_BOOKS_LINK']"),
    AUDIO("Аудио", "[data-test-id='HEADER_AUDIOBOOK_LINK']"),
    COMICS("Комиксы", "[data-test-id='HEADER_COMICBOOK_LINK']"),
    KIDS("Детям", "[data-test-id='HEADER_MIXED_LINK']");

    private final String sectionName;
    private final String selector;


    Sections(String sectionName, String selector) {
        this.sectionName = sectionName;
        this.selector = selector;
    }
    public String getSectionName() {
        return sectionName;
    }

    public String getSelector() {
        return selector;
    }
}
