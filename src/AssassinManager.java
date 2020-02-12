/**
* @author Tyler CH
* @Assignment_Info AssassinManager class for Assassin project
* @version 1.1
*/
import java.util.List;

public class AssassinManager {
	
	private AssassinNode killRing;
	private AssassinNode graveyard;

	/**
	 * Creates a new instance of {@link AssassinManager}
	 *
	 * @param names a list with non empty strings and no duplicate names that contains names of all players
	 *
	 * @throws IllegalArgumentException if names is empty
	 *
	 * @return a new instance of {@link AssassinManager} that is populated with all names in names list
	 * @since 1.0
	 */
	public AssassinManager(List<String> names) {
		if(names.isEmpty()) {
			throw new IllegalArgumentException();
		}
		this.killRing = new AssassinNode(names.get(0));
		AssassinNode insertPos = this.killRing;
		for(int i = 1; i < names.size(); i++) {
			insertPos.next = new AssassinNode(names.get(i));
			insertPos = insertPos.next;
		}
	}

	/**
	 * Prints all the players who have not been killed and who is stalking them
	 *
	 * @apiNote Game must still be in play for valid call
	 * @since 1.0
	 */
	public void printKillRing() {
		//Empty List
		if(this.killRing == null) {
			System.out.println(0);
		//List with only one element
		} if(this.killRing.next == null){
			System.out.println("   " + this.killRing.name + " is stalking " + this.killRing.name);
		//All other lists
		} else {
			AssassinNode current = this.killRing;
			while(current.next.next != null) {
				System.out.println("    " + current.name + " is staking " + current.next.name);
				current = current.next;
			}
			System.out.println("    " + current.name + " is stalking " + current.next.name);
			System.out.println("    " + current.next.name + " is stalking "+ this.killRing.name);
		}
	}
	
	/**
	 * Prints all the players who died and who killed them to the console
	 *
	 * @apiNote At least one person must have been killed for anything to print
	 * @since 1.0
	 */
	public void printGraveyard() {
		if(this.graveyard != null) {
			AssassinNode current = this.graveyard;
			while(current.next != null) {
				System.out.println("    " + current.name + " was killed by " + current.killer);
				current = current.next;
			}
			System.out.println("    " + current.name + " was killed by " + current.killer);
		}
	}
	
	/**
	 * Verifies if the given player (Ignoring case) is still in the game
	 *
	 * @param name the name of the player to verify
	 *
	 * @return true if the player is in the game; false if the player is either in the graveyard or not in the game
	 * @since 1.0
	 */
	public boolean killRingContains(String name) {
		//Is ok to assume killRing will never be empty
		AssassinNode current = this.killRing;
		return linkedListContains(name, current);
	}

	/**
	 * Verifies if the given player (Ignoring case) has been killed
	 *
	 * @param name the name of the player to verify
	 *
	 * @return true if the player has been killed; false if the player has not been killed
	 * @since 1.0
	 */
	public boolean graveyardContains(String name) {
		AssassinNode current = this.graveyard;
		//Empty graveyard test
		if(current == null) {
			return false;
		}
		return linkedListContains(name, current);
	}

	/**
	 * Helper method to reduce redundancy for contains methods
	 *
	 * @since 1.1
	 */
	private boolean linkedListContains(String name, AssassinNode current) {
		//Single Element List
		if(current.next == null && current.name.toLowerCase().equals(name.toLowerCase())) {
			return true;
		}
		//All other sizes
		while(current.next != null) {
			if(current.name.toLowerCase().equals(name.toLowerCase())) {
				return true;
			}
			current = current.next;
		}
		//Checks the last element
		return current.name.toLowerCase().equals(name.toLowerCase());
	}

	/**
	 * Checks if the game is over
	 * @return true true if game is over; false if game is still in play
	 * @since 1.0
	 */
	public boolean gameOver() {
		return this.killRing.next == null;
	}
	
	/**
	 * Gets the winner of the game
	 * @return name of the player that won the game; null if the game is still in play
	 * @since 1.0
	 */
	public String winner() {
		if(gameOver()) 
			return this.killRing.name;
		return null;
	}
	
	/**
	 * Completes one complete kill operation. Moves the person from the kill ring
	 * to the graveyard and updates game state.
	 * @param name the name of the person to kill (Ignores Case)
	 *
	 * @throws IllegalArgumentException if the kill ring does not contain the name
	 * @throws IllegalStateException if the game is over when kill is called
	 * @throws IllegalArgumentException if both kill ring does not contains the name and game is over
	 * @since 1.0
	 */
	public void kill(String name) {
		//Check for non existent target
		if(!killRingContains(name)) {
			throw new IllegalArgumentException();
		//Check for game over
		} else if(gameOver()) {
			throw new IllegalStateException();
		}
		
		AssassinNode current = this.killRing;
		//Current = Victim
		//If first element ok killRing is victim
		if(current.name.toLowerCase().equals(name.toLowerCase())) {
			AssassinNode killer = current;
			while(killer.next != null) {
				killer = killer.next;
			}
			current.killer = killer.name;
			AssassinNode temp = this.graveyard;
			this.graveyard = current;
			current = current.next;
			this.graveyard.next = temp;
			this.killRing = current;
			
		} else {
			boolean found = false;
			//Current.next = Victim, Current = killer
			//If all other elements of killRing is victim
			while(!found) {
				if(current.next.name.toLowerCase().equals(name.toLowerCase())) {
					found = true;
				} else {
					current = current.next;
				}
			}
			current.next.killer = current.name;
			AssassinNode temp = this.graveyard;
			this.graveyard = current.next;
			current.next = current.next.next;
			this.graveyard.next = temp;
		}
	}	
}