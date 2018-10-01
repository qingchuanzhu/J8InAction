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

		// Composing Functions
		Function<Integer, Integer> f = (i) -> i + 1;
		Function<Integer, Integer> g = (i) -> i * 2;
		Function<Integer, Integer> h = f.andThen(g);
		int res = h.apply(1);
		out.println("Applying 1 to andThen fg() is " + res);
		h = f.compose(g);
		out.println("Applying 1 to compose fg() is " + h.apply(1));

		Function<String, String> addHeader = Letter::addHeader;
		Function<String, String> transformationPipeline = addHeader.andThen(Letter::checkSpelling)
																	.andThen(Letter::addFooter);
		out.println("=== String transformation pipeline ===");
		out.println("Input = Apple, output = " + transformationPipeline.apply("Apple"));
	}
}

class Letter {
	static String addHeader(String text) {
		return "From Raoul, Mario and Alan: " + text;
	}

	static String addFooter(String text) {
		return text + " Kind regards";
	}

	static String checkSpelling(String text) {
		return text.replaceAll("labda", "lambda");
	}
}