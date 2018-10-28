import java.util.*;
import java.util.concurrent.*;

class Shop {
	private final String shopName;
	private static final Random random = new Random();

	Shop(String name) {
		shopName = name;
	}

	Shop() {
		shopName = "Anonymous Shop";
	}

	String getPrice(String product) {
		double price = calculatePrice(product);
		Discount.Code code = Discount.Code.values()[random.nextInt(Discount.Code.values().length)];
		return String.format("%s:%.2f:%s", shopName, price, code);
	}

	String getName() {
		return shopName;
	}

	Future<Double> getPriceAsync(String product) {
		CompletableFuture<Double> futurePrice = new CompletableFuture<>();
		new Thread(
			() -> {
				double price = calculatePrice(product);
				futurePrice.complete(price);
			}
		).start();
		return futurePrice;
	}

	// simulate 1-s delay
	static void delay() {
		try {
			Thread.sleep(1000L);
		} catch(InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	static void randomDelay() {
		int delay = 500 + random.nextInt(2000);
		try {
			Thread.sleep(delay);
		} catch(InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	private double calculatePrice(String product) {
		randomDelay();
		return random.nextDouble() * product.charAt(0) + product.charAt(1);
	}
}