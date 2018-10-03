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
	}
}