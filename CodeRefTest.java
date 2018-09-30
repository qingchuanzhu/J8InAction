import java.util.*;
import java.util.function.*;

class Apple {
	private String color = "";
	private double weight = 0;

	Apple () {
		color = "";
		weight = 0;
	}

	Apple (String col, double w){
		color = col;
		weight = w;
	}

	String getColor() {
		return color;
	}

	Double getWeight() {
		return weight;
	}

	void setWeight(double w) {
		this.weight = w;
	}

	void setColor(String c) {
		this.color = c;
	}

	public String toString() {
		return "Apple{" + 
			   "color='" + color + '\'' + 
			   ", weight=" + weight +
			   '}';
	}

	public static List<Apple> fileterApplesByColor(List<Apple> inventory, String color) {
		List<Apple> result = new ArrayList<>();
		for (Apple apple : inventory) {
			if (color.equals(apple.getColor())) {
				result.add(apple);
			}
		}
		return result;
	}

	public static List<Apple> filterApplesByWeight(List<Apple> inventory, double weight) {
		List<Apple> result = new ArrayList<>();
		for (Apple apple : inventory) {
			if (apple.getWeight() > weight) {
				result.add(apple);
			}
		}
		return result;	
	}

	public static boolean isGreenApple(Apple apple) {
		return "green".equals(apple.getColor());
	}

	public static boolean isHeavyApple(Apple apple) {
		return apple.getWeight() > 150;
	}

	public static List<Apple> filterApples(List<Apple> inventory, Predicate<Apple> p) {
		List<Apple> result = new ArrayList<>();
		for (Apple apple : inventory) {
			if (p.test(apple)) {
				result.add(apple);
			}
		}
		return result;
	}
}

class CodeRefTest {
	public static void main(String[] args) {
		ArrayList<Apple> apples = new ArrayList<>();
		apples.add(new Apple("green", 134));
		apples.add(new Apple("red", 149));
		apples.add(new Apple("blue", 187));

		// List<Apple> result = fileterGreenApples(apples);
		// List<Apple> result2 = filterHeavyApples(apples);
		List result = Apple.filterApples(apples, Apple::isGreenApple);
		List result2 = Apple.filterApples(apples, Apple::isHeavyApple);
		System.out.println(result);
		System.out.println(result2);
		System.out.println("Results by using Lambda");
		result = Apple.filterApples(apples, (Apple a) -> "green".equals(a.getColor()));
		result2 = Apple.filterApples(apples, (Apple a) -> a.getWeight() > 150);
		System.out.println(result);
		System.out.println(result2);
	}
}