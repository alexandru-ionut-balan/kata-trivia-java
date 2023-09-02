package trivia;

public class Question {

    private final String questionContent;

    public Question(Category category, int questionNumber) {
        this.questionContent = category.getName() + " Question " + questionNumber;
    }

    public String getQuestionContent() {
        return questionContent;
    }
}
