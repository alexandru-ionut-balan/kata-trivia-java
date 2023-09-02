package trivia;

import java.util.List;

public enum Category {
    POP("Pop"),
    SCIENCE("Science"),
    SPORTS("Sports"),
    ROCK("Rock");

    private final String name;

    private static final List<Category> categories = List.of(
            POP, SCIENCE, SPORTS, ROCK
    );

    Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Category getCategoryForPosition(int position) {
        position = position % GameBetter.NUMBER_OF_CATEGORIES;
        return categories.get(position);
    }
}
