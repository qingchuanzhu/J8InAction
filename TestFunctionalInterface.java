import java.util.function.*;
import java.util.*;

class TestFunctionalInterface {
	static <T> List<T> filter(List<T> list, Predicate<T> p) {
		List<T> result = new ArrayList<>();
		for (T s : list) {
		 	if (p.test(s)) {
		 		result.add(s);
		 	}
		 }
		 return result;
	}

	static <T> void forEach(List<T> list, Consumer<T> c) {
		for (T s : list) {
			c.accept(s);
		}
	}

	public static void main(String[] args) {
		List<String> listOfStrings = Arrays.asList("", "Hello world!", "Peter", "Apple and Google");
		
		System.out.println("===== Predicate Lambda =====");
		Predicate<String> p = (String s) -> !s.isEmpty();
		List<String> res = filter(listOfStrings, p);
		System.out.println(res);
		System.out.println("\n\n===== Consumer Lambad =====");
		forEach(listOfStrings, (String s) -> System.out.println(s));
	}
}