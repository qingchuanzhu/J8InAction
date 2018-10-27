import java.util.concurrent.*;
import java.util.*;

class BestPriceFinder {
	public static void main(String[] args) {
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
}