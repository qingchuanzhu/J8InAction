import java.util.*;

class Apple {
	private String color = "";

	Apple () {
		color = "";
	}

	Apple (String col){
		color = col;
	}

	String getColor() {
		return color;
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

	public static void main(String[] args) {
		ArrayList<Apple> apples = new ArrayList<>();
		apples.add(new Apple("green"));
		apples.add(new Apple("red"));
		apples.add(new Apple("blue"));

		List<Apple> result = CodeRefTest.fileterGreenApples(apples);
		System.out.println(result);
	}
}