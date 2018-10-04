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
		System.out.println(first2MeatDishName);

	}
}