import java.util.concurrent.*;
import java.util.stream.*;
import static java.util.stream.Collectors.*;
import java.util.*;

class BestPriceFinder {

	List<Shop> shops = Arrays.asList(new Shop("BestPrice"),
									 new Shop("LetsSaveBig"),
									 new Shop("MyFavoriteShop"),
									 new Shop("BuyItAll"),
									 new Shop("LetsSaveBig2"),
									 new Shop("MyFavoriteShop2"),
									 new Shop("BuyItAll2")
									);

	// create an Executor
	private final Executor executor = Executors.newFixedThreadPool(Math.min(shops.size(), 100),
							new ThreadFactory() {
								public Thread newThread(Runnable r) {
									Thread t = new Thread(r);
									t.setDaemon(true);
									return t;
								}
							});

	List<String> findPrices(String product) {
		
		/*
		//Without Future
		return shops.parallelStream()
					 .map(shop -> shop.getPrice(product))
					 .map(Quote::parse)
					 .map(Discount::applyDiscount)
					 .collect(toList());
		*/						

 		
		List<CompletableFuture<String>> priceFutures = shops.stream()
				.map(shop -> CompletableFuture.supplyAsync(
						() -> shop.getPrice(product), executor
					))
				.map(future -> future.thenApply(Quote::parse))
				.map(future -> future.thenCompose(quote -> 
					CompletableFuture.supplyAsync(
						() -> Discount.applyDiscount(quote), executor)))
				.collect(toList());

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