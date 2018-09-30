import java.util.*;

class MethodRefTest {
	public static void main(String[] args) {
		List<Apple> apples = Arrays.asList(new Apple("green", 184), new Apple("red", 149), new Apple("blue", 187));
		System.out.println("=== Before sorting ===");
		System.out.println(apples);
		apples.sort(Comparator.comparing(Apple::getWeight));
		System.out.println("=== After sorting ===");
		System.out.println(apples);

		List<String> str = Arrays.asList("a", "b", "A", "B");
		System.out.println("=== Sorting using Lambda ===");
		str.sort((s1, s2) -> s1.compareToIgnoreCase(s2));
		System.out.println(str);
		str = Arrays.asList("a", "b", "A", "B");
		System.out.println("=== Sorting using MR ===");
		str.sort(String::compareToIgnoreCase);
		System.out.println(str);
	}
}