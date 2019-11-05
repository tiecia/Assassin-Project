/*
* @author Tyler CH
* @Assignment_Info AssassinManager class for Assassin project
*/
import java.util.List;

public class AssassinManager {
	
	private AssassinNode killRing;
	private AssassinNode graveyard;
	
	//Pre: A List<String> with non empty strings and no duplicate names
	//Post: Creates an AssassinManager object with a kill ring in the exact order as the the input list names.
	//Throws: IllegalArgumentException if precondition list is empty.
	public AssassinManager(List<String> names) {
		if(names.isEmpty()) {
			throw new IllegalArgumentException();
		}
		killRing = new AssassinNode(names.get(0));
		AssassinNode insertPos = killRing; 
		for(int i = 1; i<names.size(); i++) {
			insertPos.next = new AssassinNode(names.get(i));
			insertPos = insertPos.next;
		}
	}
	
	//Pre: An object has been instantiated
	//Post: 
	public void printKillRing() {
		if(killRing == null) { //Empty List
			System.out.println(0);
		} if(killRing.next == null){ //List with only one element
			System.out.println("   " + killRing.name + " is stalking " + killRing.name);
		} else { //All other lists
			AssassinNode current = killRing;
			while(current.next.next != null) {
				System.out.println("    " + current.name + " is staking " + current.next.name);
				current = current.next;
			}
			System.out.println("    " + current.name + " is stalking "+ current.next.name);
			System.out.println("    " + current.next.name + " is stalking "+ killRing.name);
		}
	}
	
	//Pre: ?
	//Post: ?
	public void printGraveyard() {
		if(graveyard != null) {
			AssassinNode current = graveyard;
			while(current.next != null) {
				System.out.println("    " + current.name + " was killed by " + current.killer);
				current = current.next;
			}
			System.out.println("    " + current.name + " was killed by " + current.killer);
		}
	}
	
	//Pre: A string to test if AssassinManager killRing contains it.
	//Post: Returns true if AssassinManager killRing contains the string (ignoring case); Returns false if the killRing does not contain the string given.
	public boolean killRingContains(String name) {  //Is ok to assume killRing will never be empty
		AssassinNode current = killRing;
		if(current.next == null && current.name.toLowerCase().equals(name.toLowerCase())) { //Single Element List
			return true;
		}
		while(current.next != null) { //All other sizes
			if(current.name.toLowerCase().equals(name.toLowerCase())) {
				return true;
			}
			current = current.next;
		}
		return current.name.toLowerCase().equals(name.toLowerCase()); //Checks the last element
	}
	
	//Pre: A string to test if AssassinManager graveyard contains it.
	//Post: Returns true if AssassinManager graveyard contains the string (ignoring case); Returns false if the graveyard does not contain the string given.
	public boolean graveyardContains(String name) {
		AssassinNode current = graveyard;
		if(current == null) { //Empty graveyard test
			return false;
		}
		if(current.next == null && current.name.toLowerCase().equals(name.toLowerCase())) { //Single Element List test
			return true;
		}
		while(current.next != null) { //All other sizes
			if(current.name.toLowerCase().equals(name.toLowerCase())) {
				return true;
			}
			current = current.next;
		}
		return current.name.toLowerCase().equals(name.toLowerCase()); //Checks the last element
	}
	
	//Pre: N/A
	//Post: Returns true if killRing only contains one player, simulating the game being over; Returns false if the killRing contains more than one player, simulating the game still in session.
	public boolean gameOver() {
		return this.killRing.next == null;
	}
	
	//Pre: N/A
	//Post: Returns the name of the player if the game is over; Returns null if game is not over; Uses the gameOver() method to determine if the game is over.
	public String winner() {
		if(gameOver()) 
			return killRing.name;
		return null;
	}
	
	//Pre: A string if the person to kill.
	//Post: Removes that person from the killRing, records teh killer, and moves that person to the front of the graveyard. (Ignores case in precondition)
	//Throws: IllegalArgumentException if precondition does not match any name in the killRing; IllegalStateException if method is called when there is only one person left in the killRing; IllegalAgrumentException if both invalid input and game over.
	public void kill(String name) {
		if(!killRingContains(name)) { //Check for non existent target
			throw new IllegalArgumentException();
		} else if(gameOver()) { //Check for game over
			throw new IllegalStateException();
		}
		
		AssassinNode current = killRing;
		if(current.name.toLowerCase().equals(name.toLowerCase())) { //Current = Victim //if first element ok killRing is victim
			AssassinNode killer = current;
			while(killer.next != null) {
				killer = killer.next;
			}
			current.killer = killer.name;
			AssassinNode temp = graveyard;
			graveyard = current;
			current = current.next;
			graveyard.next = temp;
			killRing = current;
			
		} else {
			boolean found = false;
			while(!found) { //Current.next = Victim, Current = killer //if all other elements of killRing is victim
				if(current.next.name.toLowerCase().equals(name.toLowerCase())) {
					found = true;
				} else {
					current = current.next;
				}
			}
			current.next.killer = current.name;
			AssassinNode temp = graveyard;
			graveyard = current.next;
			current.next = current.next.next;
			graveyard.next = temp;
		}
	}	
}