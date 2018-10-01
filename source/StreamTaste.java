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
	}
}