import java.util.*;
import static java.util.Comparator.comparing;

class MethodRefTest {
	public static void main(String[] args) {
		List<Apple> apples = Arrays.asList(new Apple("green", 184), new Apple("red", 149), new Apple("blue", 187));
		System.out.println("=== Before sorting ===");
		System.out.println(apples);

		// sort using pure object
		// apples.sort(new AppleComparator());

		// sort using anonymous class
		// apples.sort(new Comparator<Apple>(){
		// 	public int compare(Apple a1, Apple a2) {
		// 		return a1.getWeight().compareTo(a2.getWeight());
		// 	}
		// });

		// sort using lambda
		// apples.sort((a1, a2) -> a1.getWeight().compareTo(a2.getWeight()));

		// sort using Function<Apple, Double>
		// apples.sort(comparing((a) -> a.getWeight()));

		// sort using method references
		apples.sort(Comparator.comparing(Apple::getWeight));

		System.out.println("=== After sorting ===");
		System.out.println(apples);

/*
		List<String> str = Arrays.asList("a", "b", "A", "B");
		System.out.println("=== Sorting using Lambda ===");
		str.sort((s1, s2) -> s1.compareToIgnoreCase(s2));
		System.out.println(str);
		str = Arrays.asList("a", "b", "A", "B");
		System.out.println("=== Sorting using MR ===");
		str.sort(String::compareToIgnoreCase);
		System.out.println(str);
	*/
	}
}

class AppleComparator implements Comparator<Apple> {
	public int compare(Apple a1, Apple a2) {
		return a1.getWeight().compareTo(a2.getWeight());
	}
 }