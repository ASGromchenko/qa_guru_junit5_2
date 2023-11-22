package data;

public enum LanguageOfProgramming {
    JAVA("Java-разработчик"),
    PHP("PHP-разработчик с нуля до PRO");
    public final String description;

    LanguageOfProgramming(String description) {
        this.description = description;
    }
}
