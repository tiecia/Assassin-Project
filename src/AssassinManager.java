import java.util.List;

public class AssassinManager {
	
	private AssassinNode killRing;
	private AssassinNode graveyard;
	
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
	
	public void printKillRing() {
		System.out.println("Current Kill Ring: ");
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
	
	public void printGraveyard() {
		System.out.println("Current graveyard:");
		if(graveyard != null) {
			AssassinNode current = graveyard;
			while(current.next != null) {
				System.out.println("    " + current.name + " was killed by " + current.killer);
				current = current.next;
			}
			System.out.println("    " + current.name + " was killed by " + current.killer);
		}
	}
	
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
	
	public boolean graveyardContains(String name) {
		AssassinNode current = graveyard;
		if(current == null) {
			return false;
		}
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
	
	public boolean gameOver() {
		return this.killRing.next == null;
	}
	
	public String winner() {
		if(gameOver()) 
			return killRing.name;
		return null;
	}
	
	public void kill(String name) {
		if(!killRingContains(name)) { //Check for non existent target
			throw new IllegalArgumentException();
		} else if(gameOver()) { //Check for game over
			throw new IllegalStateException();
		}
		
		AssassinNode current = killRing;
		if(current.name.toLowerCase().equals(name.toLowerCase())) { //Current = Victim //if first element is victim
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
			while(!found) { //Current.next = Victim, Current = killer //if all other elements is victim
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