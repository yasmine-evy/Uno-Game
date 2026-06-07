package uno;

public interface GameOperations {
	abstract Player start();
	abstract Card getTopCard();
	abstract Player playerTurn(Player player);
}
