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
		manager.printKillRing();
		manager.printGraveyard();
		System.out.println();
		System.out.println(manager.graveyardContains("Name"));
//		System.out.println(manager.killRingContains("Vinny"));
		manager.kill("name");

		manager.printKillRing();
		manager.printGraveyard();
		System.out.println(manager.graveyardContains("Name"));
		System.out.println(manager.winner());
		System.out.println();
		manager.kill("sam");
		manager.printKillRing();
		manager.printGraveyard();
		System.out.println(manager.graveyardContains("name"));
		System.out.println(manager.graveyardContains("nam"));
		System.out.println(manager.graveyardContains("vinny"));
		System.out.println(manager.winner());
	}

}
