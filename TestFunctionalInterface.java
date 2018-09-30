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

	static <T, R> List<R> map(List<T> list, Function<T, R> f) {
		List<R> result = new ArrayList<>();
		for (T  s: list ) {
			result.add(f.apply(s));
		}
		return result;
	}

	public static void main(String[] args) {
		List<String> listOfStrings = Arrays.asList("", "Hello world!", "Peter", "Apple and Google");
		
		System.out.println("===== Predicate Lambda =====");
		Predicate<String> p = (String s) -> !s.isEmpty();
		List<String> res = filter(listOfStrings, p);
		System.out.println(res);
		System.out.println("\n\n===== Consumer Lambda =====");
		forEach(listOfStrings, (String s) -> System.out.println(s));
		System.out.println("\n\n===== Function Lambda =====");
		System.out.println(map(listOfStrings, (String s) -> s.length()));
	}
}