import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.*;

enum CaloricLevel {DIET, NORMAL, FAT}

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

		//6.2.3 Joining strings
		String shortMenu = Dish.menu.stream()
									.map(Dish::getName).collect(joining(", "));
		System.out.println("Short Menu is: " + shortMenu);

		// 6.3.0 grouping
		Map<Dish.Type, List<Dish>> dishesByType = Dish.menu.stream()
														   .collect(groupingBy(Dish::getType));
		System.out.println(dishesByType);

		Map<Dish.Type, Map<CaloricLevel, List<Dish>>> dishesByTypeCaloricLevel
				= Dish.menu.stream()
						   .collect(groupingBy(Dish::getType, groupingBy(dish -> {
						   	if (dish.getCalories() <= 400) {
						   		return CaloricLevel.DIET;
						   	} else if (dish.getCalories() <= 700){
						   		return CaloricLevel.NORMAL;
							} else return CaloricLevel.FAT;
						   })));
		System.out.println(dishesByTypeCaloricLevel);

		// 6.3.2 collecting in subgroups
		Map<Dish.Type, Long> numberOfDishesInType = Dish.menu.stream()
												.collect(groupingBy(Dish::getType, counting()));
		System.out.println("Number of dishes in each type:");
		System.out.println(numberOfDishesInType);

		Map<Dish.Type, Dish> mostCaloricByType = Dish.menu.stream()
															.collect(groupingBy(Dish::getType,
															collectingAndThen(
																maxBy(Comparator.comparing(Dish::getCalories)),
																Optional::get
																)));
		System.out.println("Most Caloric Dish in each type:");
		System.out.println(mostCaloricByType);

		Map<Dish.Type, Integer> totalCaloriesByType	= Dish.menu.stream()
													.collect(groupingBy(Dish::getType,
													summingInt(Dish::getCalories)
														));
		System.out.println("Total Calories in each type:");
		System.out.println(totalCaloriesByType);

		Map<Dish.Type, Set<CaloricLevel>> caloricLevelsByType = Dish.menu.stream()
															.collect(groupingBy(Dish::getType,
															mapping(dish -> {
						   										if (dish.getCalories() <= 400) {
						   											return CaloricLevel.DIET;
						   										} else if (dish.getCalories() <= 700){
						   											return CaloricLevel.NORMAL;
																} else return CaloricLevel.FAT;
						   									}, toSet())	
															));
		System.out.println("Caloric Level in each type:");
		System.out.println(caloricLevelsByType);

		// 6.4 Partitioning
		Map<Boolean, List<Dish>> partitionedMenu = Dish.menu.stream()
													.collect(partitioningBy(Dish::isVegetarian));
		System.out.println("Partitioning Menu:");
		System.out.println(partitionedMenu);

		// 6.5 Collector Interface
		List<Dish> myDish = Dish.menu.stream()
									 .collect(new ToListCollector<Dish>());
		System.out.println("List of Dishes collected by custom Collector:");
		System.out.println(myDish);
	}
}