package uno;

import java.util.Random;// this for shuffle generatore that we use for shuffle methode 

public class Deck {
  //les attributs de la class
 private Card[] cards;
 private int totalCards = 108 ;


  // constucture 
   public Deck(){
     this.cards = new Card[totalCards];
     initializeDeck();
     shuffle();
   } 

   // the methode initializeDeck
  public void initializeDeck() {
    //initialize the deck of Uno cards in arrays
    String[] colors = {"red", "green", "blue", "yellow"};
    String[] actions = {"SKIP", "REVERSE", "DRAW TWO"};
    int index = 0;
    //add numbers cards each card have 1 card of 0 number and 2 cards of (1-9)number
    //length is a property that tells you the number of elements in an array
    // i for colors and j for numbers 


    for (int i = 0; i < colors.length; i++) {
      String color = colors[i];
      cards[index] = new Card(color,0,"number");//one card of '0'for each color
      index++;// preparing it for the next card 

      for (int j = 1; j <= 9; j++) {
        cards[index] = new Card(color,j,"number"); // Two cards for each number
        index++;
        cards[index] = new Card(color,j,"number");
        index++;
      }
    }

    // Initialize action cards
    for (int i = 0; i < colors.length; i++) {
      String color = colors[i];
      for (int j = 0; j < actions.length; j++) {
        String action = actions[j];
        cards[index] = new Card(color,-1, action); // Two cards for each action
        index++;

        cards[index] = new Card(color,-1, action);
        index++;
      }
    }


    // initialize wild but without color and without number 
    // 4 cards for wild and 4 cords for wild draw four
    for (int i = 0; i < 4; i++) {
      cards[index] = new Card("any",-3, "WILD"); 
      index++;
      cards[index] = new Card("any",-4,"WILD DRAW FOUR"); 
      index++;
    }
    //Ensures that the deck is initialized with exactly 108 cards
    if (index != totalCards) {
      throw new IllegalStateException("Deck initialization error: Incorrect number of cards!");
  }
  }


  // methode shuffle is to randomly rearrange the order of the 108 cards in the array
  public void shuffle(){
   
   Random random = new Random(); // random generator for pick random number

   for(int i=cards.length-1;i>0;i--){

   int j = random.nextInt(i+1);// pick randomly between 0 and i
   // Swap cards[i] and cards[j]
   Card temp = cards[i];
    cards[i] = cards[j];
    cards[j] = temp;
    }
    // this for Verify Shuffle Logic
    //System.out.println("Deck after shuffle:");
    //  for (Card card : cards) {
    //  System.out.println(card);
    //}

  }


  //the deal methode distribute the shuffled cards to the players so that each player starts with an equal number of cards(7 cards)
  public Card[][] deal(int numberOfPlayers,int numberOfCards ){
   int c = 0;
   Card[][] cardsInHand = new Card[4][50];
     //Check if there are enough cards to deal
   if (numberOfPlayers * numberOfCards > cards.length) {
      System.out.println("Not enough cards in the deck to deal!");
      return null;
    }

    //distribute cards
   // i represent the number of players and j represent the number of cards
   for (int i = 0; i < numberOfPlayers; i++) {
     //System.out.println("Dealing cards to Player " + (i + 1));// using (i+1) for the number of player start with the player number 1 not 0
     for (int j = 0; j < numberOfCards; j++) {
      if (c < cards.length && cards[c] != null) {
       //System.out.println("Player " + (i + 1) + " gets: " + cards[c]);
       cardsInHand[i][j] = cards[c];
       cards[c] = null; // Remove dealt card from the deck
       c++;
        } else {
        System.out.println("Not enough cards left to deal!");
        return null;
      }
      }
    }
  return cardsInHand;
  }
  // draw methode  
  //This method removes the top card from the deck and returns it.
  //It is used when a player (or system) draws a card, meaning the deck is modified by removing a card.
  public Card draw() {
  for (int i = 0; i < cards.length; i++) {
   if (cards[i] != null) {
      Card drawnCard = cards[i];
      cards[i] = null; // Remove the card from the deck
      return drawnCard;
    }
  }
  System.out.println("No cards left in the deck!");
  return null;
  }
  
}
