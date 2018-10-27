import java.util.concurrent.*;
import java.util.stream.*;
import static java.util.stream.Collectors.*;
import java.util.*;

class BestPriceFinder {

	List<Shop> shops = Arrays.asList(new Shop("BestPrice"),
									 new Shop("LetsSaveBig"),
									 new Shop("MyFavoriteShop"),
									 new Shop("BuyItAll")
									);

	List<String> findPrices(String product) {
		/**
		Without Future
		return shops.parallelStream().map(
								shop -> String.format("%s price is %.2f", shop.getName(), shop.getPrice(product))
							  ).collect(toList());
		*/

		List<CompletableFuture<String>> priceFutures = shops.stream().map(
			shop -> CompletableFuture.supplyAsync(
					() -> shop.getName() + " price is " + shop.getPrice(product)
				)
			).collect(toList());

		return priceFutures.stream().map(CompletableFuture::join).collect(toList());
	}	

	// The API used on Shop is a sync/blocking API
	void goSync() {
		long start = System.nanoTime();
		System.out.println(findPrices("myPhone27S"));
		long duration = (System.nanoTime() - start) / 1_000_000;
		System.out.println("Done in " + duration + " msec");
	}

	// The API used on Shop is a async/non-blocking API
	void goAsync() {
		Shop shop = new Shop();
		long start = System.nanoTime();
		Future<Double> futurePrice = shop.getPriceAsync("My favoriate product");
		long invocationTime = (System.nanoTime() - start) / 1_000_000;
		System.out.println("Invocation returned after " + invocationTime + " msecs");

		try {
			Thread.sleep(1000L);
		} catch(InterruptedException e) {
			throw new RuntimeException(e);
		}

		try {
			double price = futurePrice.get();
			System.out.printf("Price is %.2f%n", price);
		} catch(Exception e) {
			throw new RuntimeException(e);
		}

		long retrivelTime = (System.nanoTime() - start) / 1_000_000;
		System.out.println("Price returned after " + retrivelTime + " msecs");
	}

	public static void main(String[] args) {
		new BestPriceFinder().goSync();
	}
}