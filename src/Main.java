import java.util.*;

public class Main {

	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		list.add("Sam");
		list.add("Name");
		list.add("Vinny");
//		list.add("Drew Queue");
//		list.add("String");
//		list.add("Strings");
//		list.add("Sam");
//		list.add("Name");
//		list.add("Vinny");
//		list.add("Drew Queue");
//		list.add("String");
//		list.add("Strings");
		
		AssassinManager manager = new AssassinManager(list);
		System.out.println("Kill Ring Contains:");
		manager.printKillRing();
		System.out.println("Graveyard Contains:");
		manager.printGraveyard();
		System.out.println("GraveyardContains: Name");
		System.out.println(manager.graveyardContains("Name"));
		System.out.println("killRingContains: Name");
		System.out.println(manager.killRingContains("Vinny"));
		System.out.println();
		
		System.out.println("Kill Name");
		manager.kill("name");
		System.out.println("Kill Ring Contains:");
		manager.printKillRing();
		System.out.println("Graveyard Contains:");
		manager.printGraveyard();
		System.out.println("Graveyard Contains: Name");
		System.out.println(manager.graveyardContains("Name"));
		System.out.println("Winner: ");
		System.out.println(manager.winner());
		System.out.println();
		
		System.out.println("Kill: Sam");
		manager.kill("sam");
		System.out.println("Kill Ring Contains:");
		manager.printKillRing();
		System.out.println("Graveyard Contains:");
		manager.printGraveyard();
		System.out.println("Graveyard Contains: name");
		System.out.println(manager.graveyardContains("name"));
		System.out.println("Graveyard Contains: nam");
		System.out.println(manager.graveyardContains("nam"));
		System.out.println("Graveyard Contains: vinny");
		System.out.println(manager.graveyardContains("vinny"));
		System.out.println("Kill Ring Contains: Vinny");
		System.out.println(manager.killRingContains("Vinny"));
		System.out.println("Kill Ring Contains: sam");
		System.out.println(manager.killRingContains("sam"));
		System.out.println("Winner:");
		System.out.println(manager.winner());
		
//		manager.kill("sam");
//		manager.kill("vinny");
	}

}
