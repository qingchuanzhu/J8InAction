import java.util.*;
import java.util.stream.*;
import java.util.function.*;
import static java.util.stream.Collectors.toList;

class StreamTaste {
	public static void main(String[] args) {
		List<String> threeHighCaloricDishName = 
			Dish.menu.stream()
					.filter(d -> d.getCalories() > 300)
					.map(Dish::getName)
					.limit(3)
					.collect(toList());
		System.out.println(threeHighCaloricDishName);

		List<String> names = new ArrayList<>();
		Iterator<Dish> iterator = Dish.menu.iterator();
		while(iterator.hasNext()){
			Dish d = iterator.next();
			names.add(d.getName());
		}

		List<String> names_s = Dish.menu.stream()
										.filter(d -> {
											System.out.println("filtering " + d.getName());
											return d.getCalories() > 300;
										})
										.map(d -> {
											System.out.println("mapping " + d.getName());
											return d.getName();
										})
										.limit(3)
										.collect(toList());
		System.out.println(names_s);

		//5.1.1 Filtering with Predicate
		List<Dish> vegetarianMenu = Dish.menu.stream()
											.filter(Dish::isVegetarian)
											.collect(toList());
		System.out.println(vegetarianMenu);

		// 5.1.2 Filtering unique elements
		List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
		numbers.stream()
				.filter((a) -> a%2 == 0)
				.distinct()
				.forEach(System.out::println);

		// 5.1.4 Skipping elements
		List<Dish> skip2HighCaloricDishName = Dish.menu.stream()
													.filter(d -> d.getCalories() > 300)
													.skip(2)
													.collect(toList());
		System.out.println(skip2HighCaloricDishName);

		// Quiz 5.1
		List<Dish> first2MeatDishName = Dish.menu.stream()
													.filter(d -> d.getType() == Dish.Type.MEAT)
													.limit(2)
													.collect(toList());
		System.out.println("Quiz 5.1 answer " + first2MeatDishName);

		// 5.2.1 map
		List<String> words = Arrays.asList("Java8", "Lambdas", "In", "Action");
		List<Integer> numberOfChar = words.stream()
											.map(String::length)
											.collect(toList());
		System.out.println("5.2.1 map " + numberOfChar);

		// 5.2.2 flap map
		List<String> distinctChar = words.stream()                             // Stream<String>
											.map(word -> word.split(""))       // Stream<String[]>
											.flatMap(Arrays::stream)           // Stream<String> instead of Stream<Stream<String>>
											.distinct()
											.collect(toList());
		System.out.println("5.2.2 flat map " + distinctChar);

		// Quiz 5.2.1
		List<Integer> squaredNum = Arrays.asList(1, 2, 3, 4, 5).stream()
																.map(a -> a * a)
																.collect(toList());
		System.out.println("Quiz 5.2.1 answer = " + squaredNum);

		//Quiz 5.2.2
		System.out.println("Quiz 5.2.2 answer = ");
		Arrays.asList(1, 2, 3).stream()
							  .map(a -> new int[][]{{a, 3}, {a, 4}})  // Stream<Integer[][]>
							  .flatMap(Arrays::stream)  // Stream<Integer[]>
							  .forEach(pair -> System.out.println("[" + pair[0] + "," + pair[1] + "]"));
		// Quiz 5.2.2 using two map
		List<Integer> numbers1 = Arrays.asList(1,2,3);
		List<Integer> numbers2 = Arrays.asList(3,4);
		List<int[]> permutation = numbers1.stream()
											.flatMap(a -> numbers2.stream()
															  .map(b -> new int[]{a, b})) // Stream<Int[]>
											.collect(toList());
		System.out.println("Quiz 5.2.2 answer = ");
		for (int[] pair : permutation) {
			System.out.println("[" + pair[0] + "," + pair[1] + "]");
		}

		// Quiz 5.2.3
		List<int[]> permutationBy3 = numbers1.stream()
											 .flatMap(a -> numbers2.stream()
											 						.map(b -> new int[]{a, b}))
											 .filter(pair -> (pair[0] + pair[1])%3 == 0)
											 .collect(toList());
		System.out.println("Quiz 5.2.3 answer = ");
		for (int[] pair : permutationBy3) {
			System.out.println("[" + pair[0] + "," + pair[1] + "]");
		}

		// 5.3.1 anyMatch
		if (Dish.menu.stream().anyMatch(Dish::isVegetarian)) {
			System.out.println("The menu is (somewhat) vegetarian friendly!!");
		}

		// 5.3.2 allMatch
		Optional<Dish> dishOfVege = Dish.menu.stream()
											.filter(Dish::isVegetarian)
											.findAny();
		dishOfVege.ifPresent(d -> System.out.println(d));

		// 5.3.4 findFirst
		Optional<Integer> firstSquareDivisbleByThree = Arrays.asList(1,2,3,4,5).stream()
																				.map(x -> x*x)
																				.filter(d -> d%3 == 0)
																				.findFirst();
		firstSquareDivisbleByThree.ifPresent(a -> System.out.println(a));

		// 5.4.1 Summing the elements
		int sum = numbers.stream().reduce(0, (a, b) -> a + b);
		sum = numbers.stream().reduce(0, Integer::sum);
		System.out.println("The sum of numbers is = " + sum);

		// 5.4.2 Maximum and minimum
		Optional<Integer> max = numbers.stream().reduce(Integer::max);
		System.out.println("The max is: " + max);

		// Quiz 5.3
		Optional<Integer> numberOfDishes = Dish.menu.stream()
													.map(d -> 1)
													.reduce(Integer::sum);
		System.out.println("Quiz 5.3: number of dishes is " + numberOfDishes);

		// 5.6.1 Primitive Stream
		int calories = Dish.menu.stream()
								.mapToInt(Dish::getCalories)
								.sum();
		// 5.7.4 Infinite Stream
		Stream.iterate(0, n -> n + 3)
			  .limit(10)
			  .forEach(System.out::println);

		// Quiz 5.4 Fibonacci tuples series

		Stream.iterate(new int[]{0, 1}, t -> new int[]{t[1], t[0] + t[1]})
			  .limit(20)
			  .forEach(t -> System.out.println("(" + t[0] + "," + t[1] + ")"));

		// 5.7.4 Using Generate
		IntStream.generate(new IntSupplier(){
					int prev1 = 0;
					int prev2 = 1;
					public int getAsInt() {
						int current = prev1 + prev2;
						prev1 = prev2;
						prev2 = current;
						return current;
					}
				})
			  .limit(20)
			  .forEach(System.out::println);
	}
}