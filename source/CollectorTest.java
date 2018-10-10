import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.*;

class CollectorTest {
	public static void main(String[] args) {
		// 6.2.0 count
		long howManyDishes = Dish.menu.stream().collect(Collectors.counting());
		System.out.println("Number of dishes: " + howManyDishes);

		// 6.2.1 get max min
		Optional<Dish> mostCalorieDish = Dish.menu.stream()
												  .collect(maxBy(Comparator.comparing(Dish::getCalories)));
		System.out.println("Most Calorie Dish: " + mostCalorieDish);

		// 6.2.2 Summarization
		int totalCalories = Dish.menu.stream()
									 .collect(summingInt(Dish::getCalories));
		double avgCalories = Dish.menu.stream()
									  .collect(averagingInt(Dish::getCalories));
		System.out.println("Total Calorie is " + totalCalories + ", Average Calorie is " + avgCalories);

		// Joining strings
		String shortMenu = Dish.menu.stream()
									.map(Dish::getName).collect(joining(", "));
		System.out.println("Short Menu is: " + shortMenu);
	}
}