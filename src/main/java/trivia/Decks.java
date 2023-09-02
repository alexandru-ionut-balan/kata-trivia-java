package trivia;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Decks {
    private static final Map<Category, LinkedList<Question>> questionDecks = new HashMap<>();

    private Decks(){}

    public static void generateDeck(Category category, int numberOfQuestions) {
        var newDeck = new LinkedList<Question>();

        for (int i = 0; i < numberOfQuestions; i++) {
            newDeck.add(new Question(category, i));
        }

        questionDecks.put(category, newDeck);
    }

    public static Question removeQuestionFromDeck(Category category) {
        return questionDecks.get(category).removeFirst();
    }
}
