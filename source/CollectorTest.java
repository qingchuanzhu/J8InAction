import java.util.stream.*;
import java.util.stream.Collectors.*;

class CollectorTest {
	public static void main(String[] args) {
		// 6.2.0 count
		long howManyDishes = Dish.menu.stream().collect(Collectors.counting());
		System.out.println("Number of dishes: " + howManyDishes);
	}
}