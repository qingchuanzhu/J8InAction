import java.util.*;

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

	double getWeight() {
		return weight;
	}

	public String toString() {
		return color +" apple";
	}
}

class CodeRefTest {
	public static List<Apple> fileterGreenApples(List<Apple> inventory) {
		List<Apple> result = new ArrayList<>();
		for (Apple apple : inventory) {
			if ("green".equals(apple.getColor())) {
				result.add(apple);
			}
		}
		return result;
	}

	public static List<Apple> filterHeavyApples(List<Apple> inventory) {
		List<Apple> result = new ArrayList<>();
		for (Apple apple : inventory) {
			if (apple.getWeight() > 150) {
				result.add(apple);
			}
		}
		return result;	
	}

	public static void main(String[] args) {
		ArrayList<Apple> apples = new ArrayList<>();
		apples.add(new Apple("green", 134));
		apples.add(new Apple("red", 149));
		apples.add(new Apple("blue", 187));

		List<Apple> result = fileterGreenApples(apples);
		List<Apple> result2 = filterHeavyApples(apples);
		System.out.println(result);
		System.out.println(result2);
	}
}