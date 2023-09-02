package trivia;

import java.util.ArrayList;
import java.util.LinkedList;

// REFACTOR ME
public class GameBetter implements IGame {
   private final ArrayList<Player> players = new ArrayList<>();
   private int currentPlayerIndex = 0;
   private Player currentPlayer = null;
   int[] places = new int[6];
   boolean[] inPenaltyBox = new boolean[6];

   LinkedList popQuestions = new LinkedList();
   LinkedList scienceQuestions = new LinkedList();
   LinkedList sportsQuestions = new LinkedList();
   LinkedList rockQuestions = new LinkedList();
   boolean isGettingOutOfPenaltyBox;

   public GameBetter() {
      for (int i = 0; i < 50; i++) {
         popQuestions.addLast("Pop Question " + i);
         scienceQuestions.addLast(("Science Question " + i));
         sportsQuestions.addLast(("Sports Question " + i));
         rockQuestions.addLast(createRockQuestion(i));
      }
   }

   public String createRockQuestion(int index) {
      return "Rock Question " + index;
   }

   public boolean add(String playerName) {
      players.add(new Player(playerName));
      places[howManyPlayers()] = 0;
      inPenaltyBox[howManyPlayers()] = false;

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

      if (inPenaltyBox[currentPlayerIndex]) {
         if (roll % 2 != 0) {
            isGettingOutOfPenaltyBox = true;

            System.out.println(currentPlayer.getName() + " is getting out of the penalty box");
            places[currentPlayerIndex] = places[currentPlayerIndex] + roll;
            if (places[currentPlayerIndex] > 11) places[currentPlayerIndex] = places[currentPlayerIndex] - 12;

            System.out.println(currentPlayer.getName()
                               + "'s new location is "
                               + places[currentPlayerIndex]);
            System.out.println("The category is " + currentCategory());
            askQuestion();
         } else {
            System.out.println(currentPlayer.getName() + " is not getting out of the penalty box");
            isGettingOutOfPenaltyBox = false;
         }

      } else {

         places[currentPlayerIndex] = places[currentPlayerIndex] + roll;
         if (places[currentPlayerIndex] > 11) places[currentPlayerIndex] = places[currentPlayerIndex] - 12;

         System.out.println(currentPlayer.getName()
                            + "'s new location is "
                            + places[currentPlayerIndex]);
         System.out.println("The category is " + currentCategory());
         askQuestion();
      }

   }

   private void askQuestion() {
      if (currentCategory() == "Pop")
         System.out.println(popQuestions.removeFirst());
      if (currentCategory() == "Science")
         System.out.println(scienceQuestions.removeFirst());
      if (currentCategory() == "Sports")
         System.out.println(sportsQuestions.removeFirst());
      if (currentCategory() == "Rock")
         System.out.println(rockQuestions.removeFirst());
   }


   private String currentCategory() {
      if (places[currentPlayerIndex] == 0) return "Pop";
      if (places[currentPlayerIndex] == 4) return "Pop";
      if (places[currentPlayerIndex] == 8) return "Pop";
      if (places[currentPlayerIndex] == 1) return "Science";
      if (places[currentPlayerIndex] == 5) return "Science";
      if (places[currentPlayerIndex] == 9) return "Science";
      if (places[currentPlayerIndex] == 2) return "Sports";
      if (places[currentPlayerIndex] == 6) return "Sports";
      if (places[currentPlayerIndex] == 10) return "Sports";
      return "Rock";
   }

   public boolean wasCorrectlyAnswered() {
      if (inPenaltyBox[currentPlayerIndex]) {
         if (isGettingOutOfPenaltyBox) {
            System.out.println("Answer was correct!!!!");
            currentPlayer.addCoin();
            System.out.println(currentPlayer.getName()
                               + " now has "
                               + currentPlayer.getCoins()
                               + " Gold Coins.");

            boolean winner = didPlayerWin();
            currentPlayerIndex++;
            if (currentPlayerIndex == players.size()) currentPlayerIndex = 0;
            currentPlayer = players.get(currentPlayerIndex);

            return winner;
         } else {
            currentPlayerIndex++;
            if (currentPlayerIndex == players.size()) currentPlayerIndex = 0;
            currentPlayer = players.get(currentPlayerIndex);
            return true;
         }


      } else {

         System.out.println("Answer was corrent!!!!");
         currentPlayer.addCoin();
         System.out.println(currentPlayer.getName()
                            + " now has "
                            + currentPlayer.getCoins()
                            + " Gold Coins.");

         boolean winner = didPlayerWin();
         currentPlayerIndex++;
         if (currentPlayerIndex == players.size()) currentPlayerIndex = 0;
         currentPlayer = players.get(currentPlayerIndex);

         return winner;
      }
   }

   public boolean wrongAnswer() {
      System.out.println("Question was incorrectly answered");
      System.out.println(currentPlayer.getName() + " was sent to the penalty box");
      inPenaltyBox[currentPlayerIndex] = true;

      currentPlayerIndex++;
      if (currentPlayerIndex == players.size()) currentPlayerIndex = 0;
      currentPlayer = players.get(currentPlayerIndex);
      return true;
   }


   private boolean didPlayerWin() {
      return !(currentPlayer.getCoins() == 6);
   }
}
