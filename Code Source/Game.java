package uno;
//Import library of Scanner class to enter user's inputs
import java.util.Scanner;

public class Game implements GameOperations{
	
	//Attributes of the class 
	private Deck deck;
	private Player players[];
	private int currentPlayerIndex;
	private Card topCard,chosenCard;
	private boolean isReversed;
	private boolean isHuman;

	//Initialize a Scanner object
	Scanner in = new Scanner(System.in);

	//Parameterized constructor that accept deck, Player objects and isHuman variable from Main class
	public Game(Deck deck,Player players[],boolean isHuman) {
		this.deck = deck;
		this.players = players;	
		this.currentPlayerIndex = 0;
		this.topCard = null;
		this.isReversed = false;
		this.isHuman = isHuman;
		this.chosenCard = null;
	}
	
		//Method that handle the first actions at the beginning (dealing, first card, first player turn)
		@Override
		public Player start() {
	        System.out.println("\n======================== Game Start ========================");
			//Draw the first card from the deck
			do {
				topCard = deck.draw();
				topCard.setType("REVERSE");
			//This action is repeated if the first card was a wild or wild draw four cards which need a player to choose a color	
			}while((topCard.getType()).equalsIgnoreCase("wild") || (topCard.getType()).equalsIgnoreCase("wild draw four"));
			
			//If the first card was a draw two card (skip first player turn)
			if(topCard.getType().equalsIgnoreCase("draw two")){
				//handleSpecialCards that is specific for the start() method
				//Force the first player to draw two cards from the deck 
				players[0].addCard(deck.draw());
				players[0].addCard(deck.draw());
				System.out.println("\n<^o^> " + players[0].getName()+" draws two cards!!"); 
				//Skip the first player turn and move to the second one
				moveToNextPlayer();
				System.out.println("\n<^o^> Skipping to the next player!!");
			}else {
				//First turn for the player who enter his name first (or skip his turn by skip card)
				handleSpecialCards(topCard, players[currentPlayerIndex]);
			}
			return players[currentPlayerIndex];
		}
		
		//A method that return the current card on the table (Top card)
		@Override
		public Card getTopCard() {
			return topCard;
		}
		
		//Method that handle the player turn (whether was a human or a computer) 
		@Override
		public Player playerTurn(Player player) {
			//Fill the object player with the current player (after filling it in Main class with the first player)
			player = players[currentPlayerIndex];
			int chosenCardIndex;
			Card drawnCard;
			//Human case
			if(currentPlayerIndex == 0 || isHuman == true) {
				//Number of cards in hand before drawing or playing a card
				int n = player.cardsNum();
				do {
					//Displaying operation
					System.out.println("\n<*> Top card : " + getTopCard().toString());
		            System.out.println("\n~~> " + player.getName() + "'s turn !");
					player.displayCardsInHand();
					System.out.print("\n=> Choose a card by its index (or draw by -1): ");
					//Enter the index of the card chosen by user
					chosenCardIndex = in.nextInt();
					//If the player chosen an existed card index 
					if(chosenCardIndex >= 0 && chosenCardIndex < player.cardsNum()) {
						chosenCard = player.cardh[chosenCardIndex];
						//If the card is playable
						if(chosenCard.isPlayable(topCard)) {
								topCard = player.playCard(chosenCardIndex);
								System.out.println("\n~~> " + player.getName()+" played: "+topCard.toString());
								handleSpecialCards(topCard,player);
								//Then exist the loop
								break;
						//If not then it's not playable 		
						}else {
								System.out.println("\n!!> " + chosenCard.toString()+" is not playable. Please choose a playable card or draw by pressing -1.");
						}
					//If the player want to draw a card and it's his/her first draw at this turn	
					}else if(chosenCardIndex == -1 && player.cardsNum() == n ){
						//If the player has a playable card before drawing
						boolean playableCardsBeforeDrawing = player.canPlay(topCard);
						//Drawing operation
						drawnCard = deck.draw();
						System.out.println("\n~~> " + player.getName()+" draws a card.");
						player.addCard(drawnCard);
						//If the player has a playable card (after drawing)
						if(player.canPlay(topCard) == false) {
							player.displayCardsInHand();							
							System.out.println("\n!!> " + player.getName()+" doesn't have a playable card.");
							//Exit the loop
							break;
						//If the drawing card is playable and all it's cards except the drawing one aren't playable
						}else if(drawnCard.isPlayable(topCard) && playableCardsBeforeDrawing == false) {
							//Let the player play again
							continue;
						//If the player draws a card even if he/she has a playable card	
						}else {
							break;
						}
					//If the player tries to draw again 	
					}else if(chosenCardIndex == -1){
						System.out.println("\n!!> You can draw only once.");
					//If the player enter invalid key or an index that doesn't existed 
					}else {
						System.out.println("\n!!> There is no card with this index. Please enter one of the existed indices.");
					}
				}while(true);
				System.out.println("/\\/\\/\\===> Your Turn ends here <===/\\/\\/\\");
			//Computer case	(Bot_player)
			}else {				  
				System.out.print("\n------ "+ player.getName() +"'s Turn -----------------------");
				System.out.println("\n<*> Top card : " + getTopCard().toString());
				//If the Bot_player can play a card
				if(player.canPlay(topCard) == true) {
					//Play the card (Pop it from its cards to the table)
					topCard = ((botPlayer) player).playCard(topCard);
					System.out.println("\n~~>" + player.getName()+" played: "+topCard.toString());
					//Handle the effect of the card chosen 
					handleSpecialCards(topCard,player);
				//If the Bot_player doesn't have a playable card
				}else{
					//It is forced to draw a card
					drawnCard = deck.draw();
					System.out.println("\n~~> " + player.getName()+" draws a card.");
					//Add the drawn card to its cards
					player.addCard(drawnCard);
					//If the drawn card is playable
					if(drawnCard.isPlayable(topCard) == true) {
						//player play the drawn card
						topCard = ((botPlayer) player).playCard(drawnCard);
						System.out.println("\n~~> " + player.getName()+" played: "+topCard.toString());
						//Handle the effect of that card
						handleSpecialCards(topCard,player);
					}
				}				  	
				System.out.println("------ End of "+ player.getName() +"'s Turn ----------------");
		}
			//Return the player that played this turn for others methods
			return player;
		}		
		
		//A method that return the next player who will be forced to draw when "wild draw four" are dropped
		private Player getNextPlayer() {
			//Reserve the current player index in a another variable to use the last one and keep the same current index 
			int nextPlayerIndex = currentPlayerIndex;
			//If the top card is "reverse"
			if(isReversed) {
				//The previous player will be the next (counterclockwise direction)
				nextPlayerIndex = (nextPlayerIndex - 1 + players.length) % players.length;
			}else {
				//The next player stays the next (clockwise direction)
				nextPlayerIndex = (nextPlayerIndex + 1) % players.length;
			}
			return players[nextPlayerIndex];
		}
		
		//A method that change the current player index to the next current player index with handling the case of reverse
		public void moveToNextPlayer() {
			//If the top card is "reverse"
			if(isReversed) {
				currentPlayerIndex = (currentPlayerIndex - 1 + players.length) % players.length;
			}else {
				currentPlayerIndex = (currentPlayerIndex + 1) % players.length;
			}
		}
		
		//A Method that accept the top card and the player as a parameters and handling the effect of special cards with displaying the action 
		private void handleSpecialCards(Card topCard, Player player) {
			String choosedColor;
			//The type of the top card will be treated
			switch(topCard.getType()) {
			case "DRAW TWO":
				//Force the next player to draw two cards from the deck 
				getNextPlayer().addCard(deck.draw());
				getNextPlayer().addCard(deck.draw());
				System.out.println("\n<^o^> " + getNextPlayer().getName()+" draws two cards!!");
				//No break because always we skip after the player forced to draw two card 
			case "SKIP":
				//Just skip the current player and move to the next one
				moveToNextPlayer();
				System.out.println("\n<^o^> Skipping to the next player!!");
				break;
			case "REVERSE":
				//Change the direction
				isReversed = !isReversed;
				System.out.println("\n<^o^> Reversing turn order!!");
				break;
			case "WILD":
				//Let the player choose a color
				if(currentPlayerIndex == 0 || isHuman == true) {
					wildChooseColor();
					System.out.println("\n<^o^> " + player.getName()+" changed the color to "+topCard.getColor()+".");
				}else {
					choosedColor = ((botPlayer) player).wildChooseColor(topCard);
					topCard.setColor(choosedColor);
					System.out.println("\n<^o^> " + player.getName()+" changed the color to "+choosedColor+".");
				}
				break;
			case "WILD DRAW FOUR":	
				//Let the player choose a color 
				if(currentPlayerIndex == 0 || isHuman == true) {
					wildChooseColor();
					System.out.println("\n<^o^> " + player.getName()+" changed the color to "+topCard.getColor()+".");
				}else {
					choosedColor = ((botPlayer) player).wildChooseColor(topCard);
					topCard.setColor(choosedColor);
					System.out.println("\n<^o^> " + player.getName()+" changed the color to "+choosedColor+".");
				}
				System.out.println("<^o^> " + getNextPlayer().getName()+" draws four cards!!");
				//Force the next player to draw four cards
				for(int i=0;i<4;i++) {
					getNextPlayer().addCard(deck.draw());
				}
				break;
			}
		}
		
		//A method that let the player choose a color in wild or wild draw four cases
		private void wildChooseColor() { 
			do {
				System.out.println("\n=> " + players[currentPlayerIndex].getName() + ", choose any color [red, green, blue, yellow]: ");
				String chosenColor = in.next();
				//If the player wrote the color's word correctly 
				if(chosenColor.equalsIgnoreCase("red") || chosenColor.equalsIgnoreCase("green") || chosenColor.equalsIgnoreCase("blue") || chosenColor.equalsIgnoreCase("yellow")) {
					//Change top card color
					topCard.setColor(chosenColor.toLowerCase());
				}else {
					//Ask him to write it correctly
					System.out.println("!!> Please enter one of the color's name of the list.");
				}
				
			//Repeat until the the color of the top card change from "any" to a specific color	
			}while(topCard.getColor().equals("any") == true);
		}	
}





