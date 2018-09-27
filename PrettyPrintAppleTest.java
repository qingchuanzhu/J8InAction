// This for Quiz 2.1 for J8InAction
import java.util.*;

interface PrettyPrintApple {
	String prettyPrint(Apple a);
}

class AppleWeightPrinter implements PrettyPrintApple {
	public String prettyPrint(Apple a) {
		return "Apple weights " + a.getWeight();
	}
}

class AppleWeightPrinter2 implements PrettyPrintApple {
	public String prettyPrint(Apple a) {
		return a.getWeight() > 150 ? "Heavy Apple" : "Light Apple";
	}
}

class PrettyPrintAppleTest {
	static void prettyPrintApple(List<Apple> inventory, PrettyPrintApple p) {
		for (Apple apple : inventory) {
			String output = p.prettyPrint(apple);
			System.out.println(output);
		}
	}

	public static void main(String[] args) {
		ArrayList<Apple> apples = new ArrayList<>();
		apples.add(new Apple("green", 134));
		apples.add(new Apple("red", 149));
		apples.add(new Apple("blue", 187));

		System.out.println("Printing first style...");
		prettyPrintApple(apples, new PrettyPrintApple(){
			public String prettyPrint(Apple a) {
				return "Apple weights " + a.getWeight();
			}
		});
		System.out.println("Printing second style...");
		prettyPrintApple(apples, new PrettyPrintApple(){
			public String prettyPrint(Apple a) {
				return a.getWeight() > 150 ? "Heavy Apple" : "Light Apple";
			}	
		});
	}
}