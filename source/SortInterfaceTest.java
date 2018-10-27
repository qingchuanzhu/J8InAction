import java.util.*;

interface MyInterface<T> {
	static <T extends Comparable<? super T>> Comparator<T> naturalOrder() {
		return (o1, o2) -> o1.compareTo(o2);
	}
}

class SortInterfaceTest {
	public static void main(String[] args) {
		List<Integer> numbers = Arrays.asList(3,5,1,2,6);
		System.out.println("Before sort:");
		System.out.println(numbers);
		numbers.sort(MyInterface.naturalOrder());
		System.out.println("After sort:");
		System.out.println(numbers);
	}
}