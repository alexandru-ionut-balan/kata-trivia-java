package trivia;

import java.util.ArrayList;
import java.util.LinkedList;

// REFACTOR ME
public class GameBetter implements IGame {
    private final ArrayList<Player> players = new ArrayList<>();
    private int currentPlayerIndex = 0;
    private Player currentPlayer = null;
    int[] places = new int[6];
    LinkedList<String> popQuestions = new LinkedList<>();
    LinkedList<String> scienceQuestions = new LinkedList<>();
    LinkedList<String> sportsQuestions = new LinkedList<>();
    LinkedList<String> rockQuestions = new LinkedList<>();

    public static final int NUMBER_OF_CATEGORIES = 4;

    public GameBetter() {

        for (int i = 0; i < 50; i++) {
            popQuestions.add("Pop Question " + i);
            scienceQuestions.add("Science Question " + i);
            sportsQuestions.add("Sports Question " + i);
            rockQuestions.add("Rock Question " + i);
        }
    }

    public boolean add(String playerName) {
        players.add(new Player(playerName));

        System.out.println(playerName + " was added");
        System.out.println("They are player number " + players.size());

        if (currentPlayer == null) {
            currentPlayer = players.get(0);
        }

        return true;
    }

    public int howManyPlayers() {
        return players.size();
    }

    public void roll(int roll) {
        System.out.println(currentPlayer.getName() + " is the current player");
        System.out.println("They have rolled a " + roll);

        if (currentPlayer.isInJail()) {
            if (roll % 2 != 0) {
                currentPlayer.freeFromJail();

                System.out.println(currentPlayer.getName() + " is getting out of the penalty box");
                currentPlayer.moveForward(roll);

                System.out.println(currentPlayer.getName()
                        + "'s new location is "
                        + currentPlayer.getPosition());
                System.out.println("The category is " + Category.getCategoryForPosition(currentPlayer.getPosition()).getName());
                askQuestion();
            } else {
                System.out.println(currentPlayer.getName() + " is not getting out of the penalty box");
            }
        } else {
            currentPlayer.moveForward(roll);

            System.out.println(currentPlayer.getName()
                    + "'s new location is "
                    + currentPlayer.getPosition());
            System.out.println("The category is " + Category.getCategoryForPosition(currentPlayer.getPosition()).getName());
            askQuestion();
        }
    }

    private void askQuestion() {
        switch (Category.getCategoryForPosition(currentPlayer.getPosition())) {
            case POP -> System.out.println(popQuestions.removeFirst());
            case SCIENCE -> System.out.println(scienceQuestions.removeFirst());
            case SPORTS -> System.out.println(sportsQuestions.removeFirst());
            case ROCK -> System.out.println(rockQuestions.removeFirst());
        }
    }

    public boolean wasCorrectlyAnswered() {
        if (currentPlayer.isInJail()) {
            currentPlayerIndex++;
            if (currentPlayerIndex == players.size()) currentPlayerIndex = 0;
            currentPlayer = players.get(currentPlayerIndex);
            return true;
        }

        System.out.println("Answer was correct!!!!");
        currentPlayer.addCoin();
        System.out.println(currentPlayer.getName()
                + " now has "
                + currentPlayer.getCoins()
                + " Gold Coins.");

        boolean winner = currentPlayer.hasNotWonYet();
        currentPlayerIndex++;
        if (currentPlayerIndex == players.size()) currentPlayerIndex = 0;
        currentPlayer = players.get(currentPlayerIndex);

        return winner;
    }

    public boolean wrongAnswer() {
        System.out.println("Question was incorrectly answered");
        System.out.println(currentPlayer.getName() + " was sent to the penalty box");
        currentPlayer.putInJail();

        currentPlayerIndex++;
        if (currentPlayerIndex == players.size()) currentPlayerIndex = 0;
        currentPlayer = players.get(currentPlayerIndex);
        return true;
    }
}
