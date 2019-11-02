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
		while(current.next != null) {
			if(current.name.equals(name)) {
				return true;
			}
			current = current.next;
		}
		return false;
	}
	
	public boolean graveyardContains(String name) {

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
		if(!killRingContains(name)) {
			throw new IllegalArgumentException();
		}
		boolean found = false;
		AssassinNode current = killRing;
		while(!found) {
			if(current.next.name.equals(name)) {
				found = true;
			} else {
				current = current.next;
			}
		}
		AssassinNode temp = graveyard;
		graveyard = current.next;
		current.next = current.next.next;
		graveyard.next = temp;
	}
	
}