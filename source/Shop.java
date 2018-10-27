import java.util.*;

class Shop {
	double getPrice(String product) {
		return calculatePrice(product);
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