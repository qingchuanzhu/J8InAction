import java.util.*;
import java.util.concurrent.*;

class Shop {
	double getPrice(String product) {
		return calculatePrice(product);
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

	private double calculatePrice(String product) {
		delay();
		Random random = new Random();
		return random.nextDouble() * product.charAt(0) + product.charAt(1);
	}
}