package uno;
import java.util.Scanner;

public class Main {
    public static void main (String[] args){

        Scanner scanner  = new Scanner(System.in);

        System.out.println("\n======================== Welcome to Uno game ========================\n");
        System.out.println("1. Play with friends.");
        System.out.println("2. Play with computer.\n");
        System.out.print("=> Choose an option (1 or 2): ");
        
        int mode = scanner.nextInt();

        while( mode != 1 && mode != 2){
            System.out.print("!!> Please enter 1 or 2 as an option: ");
            mode = scanner.nextInt();
        }

        boolean isHuman;
        if(mode == 2) {
            isHuman = false;
        } else {
            isHuman = true;
        }

        System.out.print("\n=> Enter the number of players (2, 3 or 4): ");
        int numPlayer = scanner.nextInt();

        while( numPlayer > 4 || numPlayer < 2){
            System.out.print("!!> The number of players must be between 2 and 4: ");
            numPlayer = scanner.nextInt();
        }
        System.out.print("\n");
        // create array for players 
        Player[] players = new Player[numPlayer];

         
        Deck deck = new Deck();
        deck.initializeDeck();
        deck.shuffle();
        
        Card[][] cardH = new Card[numPlayer][50];
        cardH = deck.deal(numPlayer, 7);
        // assign a name and cards for players 
        for ( int i = 0; i< numPlayer; i++) {
            
            if(mode == 2 && i!=0){
                players[i] = new botPlayer("Bot " + (i+1), cardH[i]);

            } else {
                System.out.println("=> Enter the name of the player "+ (i+1) + " : ");
                String name = scanner.next();
                players[i] = new Player(name, cardH[i]);
            }
        }
        // start the game 
        Game game = new Game(deck, players, isHuman);
        Player player = game.start();
        boolean gameOver = false;
        while(gameOver == false) {
        	player = game.playerTurn(player);
        	if(player.cardsNum() == 0) {
        		System.out.println("\n\t\t*!*!*!*!* " + player.getName()+" wins the game !! *!*!*!*!*");
        		break;
        	}
    		//Let the next player play his turn
			game.moveToNextPlayer();
        }
        System.out.println("\n======================== Game over. Thank you for playing ========================\n");
        scanner.close();
}        
}       
