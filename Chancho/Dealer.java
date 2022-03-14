import java.util.Set;

/**
 * The class Dealer encapsulates the actions of a Chancho game-dealer. The game
 * dealer is responsible for dealing cards from the provided game-deck to each
 * player, and scheduling rounds of the game until a player has won the game.
 * All players who have declared themselves as a winner should be congratulated.
 * 
 * Developers should provide the constructor,
 * 
 * public Dealer(Set<Player> players, Deck gameDeck);
 * 
 */
public final class Dealer {

  private Set<Player> players;
  private Deck gameDeck;

	public Dealer(Set<Player> players, Deck gameDeck) {
	  this.players = players;
	  this.gameDeck = gameDeck;
  }

  private boolean hasWinner() {
    for (Player player : players) {
      if (player.hasWon()) {
        return true;
      }
    }
    return false;
  }

  public void playGame() {
	  for (int i = 0; i < 4; i++) {
	    for (Player player : players) {
        player.addToHand(gameDeck.removeFromTop());
      }
    }

	  while(!hasWinner()) {
	    for (Player player : players) {
	      player.discard();
      }
      for (Player player : players) {
        player.pickup();
      }
    }

	  congratulateWinners();
  }

  private void congratulateWinners() {
    System.out.println("The game has been won! Congratulations to:");
	  for (Player player : players) {
	    if (player.hasWon()) {
        System.out.println(player);
      }
    }
  }
}