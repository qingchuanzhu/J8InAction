import java.util.*;
import java.util.function.*;
import static java.lang.System.out;

class ComposeLambdaTest {
	public static void main(String[] args) {
		List<Apple> apples = Arrays.asList(new Apple("green", 184), 
										   new Apple("red", 149), 
										   new Apple("blue", 187),
										   new Apple("green", 133));

		out.println("=== Origianl Apples ===");
		out.println(apples);

		//Composing Predicates
		List<Apple> resIsGreen = Apple.filterApples(apples, Apple::isGreenApple);
		out.println("=== After filtering green ===");
		out.println(resIsGreen);

		Predicate<Apple> isGreen = Apple::isGreenApple; //(a) -> Apple.isGreenApple(a);
		Predicate<Apple> heavyApple = Apple::isHeavyApple; 
		List<Apple> resNotGreen = Apple.filterApples(apples, isGreen.negate());
		out.println("=== After filtering not green ===");
		out.println(resNotGreen);
		List<Apple> isGreenAndHeavy = Apple.filterApples(apples, isGreen.and(heavyApple));
		out.println("=== After filtering green and heavy ===");
		out.println(isGreenAndHeavy);

		List<Apple> isGreenAndHeavyOrRed = Apple.filterApples(apples, isGreen.and(heavyApple)
																			 .or((a) -> a.getColor().equals("red")));
		out.println("=== After filtering green and heavy or red ===");
		out.println(isGreenAndHeavyOrRed);
	}
}