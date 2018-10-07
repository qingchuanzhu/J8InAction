class Trader {
	private final String name;
	private final String city;

	Trader(String n, String c) {
		this.name = n;
		this.city = c;
	}

	String getName() {
		return this.name;
	}

	String getCity() {
		return this.city;
	}

	public String toString() {
		return "Trader:" + this.name + " in " + this.city;
	}
}

class Transaction {
	private final Trader trader;
	private final int year;
	private final int value;

	Transaction(Trader trader, int year, int value) {
		this.trader = trader;
		this.year = year;
		this.value = value;
	}

	Trader getTrader() {
		return this.trader;
	}

	int getYear() {
		return this.year;
	}

	int getValue() {
		return this.value;
	}

	public String toString() {
		return "{" + this.trader + ", " +
				"year: " + this.year + ", " +
				"value: " + this.value + "}"; 
	}
}