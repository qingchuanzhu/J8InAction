import java.util.*;
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
	}
}