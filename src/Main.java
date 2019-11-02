import java.util.*;

public class Main {

	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		list.add("Sam");
		list.add("Name");
		list.add("Vinny");
		list.add("Drew Queue");
		list.add("String");
		list.add("Strings");
//		list.add("Sam");
//		list.add("Name");
//		list.add("Vinny");
//		list.add("Drew Queue");
//		list.add("String");
//		list.add("Strings");
		
		AssassinManager manager = new AssassinManager(list);
		manager.printKillRing();
		manager.printGraveyard();
		System.out.println(manager.killRingContains("Vinny"));
		manager.kill("Vinny");
		manager.printKillRing();
		manager.printGraveyard();
	}

}
