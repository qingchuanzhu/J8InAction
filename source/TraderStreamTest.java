import java.util.*;
import static java.util.stream.Collectors.toList;
import static java.util.Comparator.comparing;

class TraderStreamTest {

	Trader raoul = new Trader("Raoul", "Cambridge");
	Trader mario = new Trader("Mario", "Milan");
	Trader alan = new Trader("Alan", "Cambridge");
	Trader brian = new Trader("Brian", "Cambridge");

	List<Transaction> transactions = Arrays.asList(
		new Transaction(brian, 2011, 300),
		new Transaction(raoul, 2012, 1000),
		new Transaction(raoul, 2011, 400),
		new Transaction(mario, 2012, 710),
		new Transaction(mario, 2012, 700),
		new Transaction(alan, 2012, 950)
		);

	void go() {
		// Find all transactions in year 2011 and sort them by value
		List<Transaction> ans1 = transactions.stream()
											.filter(t -> t.getYear() == 2011)
											.sorted(comparing(Transaction::getValue))
											.collect(toList());
		System.out.println(ans1);

		// What are all the unique cities where the traders work
		List<String> ans2 = transactions.stream()
										.map(t -> t.getTrader().getCity())
										.distinct()
										.collect(toList());
		System.out.println("Unique cities are " + ans2);

		// Find all traders from Cambridge and sort them by name
		List<Trader> ans3 = transactions.stream()
										.map(Transaction::getTrader)
										.distinct()
										.filter(t -> t.getCity().equals("Cambridge"))
										.sorted(comparing(Trader::getName))
										.collect(toList());
		System.out.println("Traders from Cambridge are: " + ans3);

		// Return a string of all traders' names sorted alphabetically
		String ans4 = transactions.stream()
								  .map(Transaction::getTrader)
								  .distinct()
								  .map(Trader::getName)
								  .sorted(comparing(a -> a))  // or directly using sorted()
								  .reduce("", (a, b) -> a + b);
		System.out.println("All Traders names is: " + ans4);

		// Are any traders based in Milan?
		Optional<Trader> ans5 = transactions.stream()
											.map(Transaction::getTrader)
											.distinct()
											.filter(t -> t.getCity().equals("Milan"))
											.findAny(); // or use anyMatch
		ans5.ifPresent(System.out::println);

		// Print all transactions' values from the traders living in Cambridge
		List<Integer> ans6 = transactions.stream()
										 .filter(t -> t.getTrader().getCity().equals("Cambridge"))
										 .map(Transaction::getValue)
										 .collect(toList());
		System.out.println("Trans Values from Cambridge: " + ans6);

		// What is the highest value of all the transactions
		Optional<Integer> highestValue = transactions.stream()
									   .map(Transaction::getValue)
									   .reduce(Integer::max);
		highestValue.ifPresent(v -> System.out.println("Highest transaction value is " + v));

		//Find the transaction with the smallest value
		Optional<Transaction> ans8 = transactions.stream()
									   .reduce((a, b) -> a.getValue() < b.getValue() ? a : b); // or use Stream's min, max method
		ans8.ifPresent(t -> System.out.println("Smallest transaction is " + t));
	}

	public static void main(String[] args) {
		new TraderStreamTest().go();	
	}
}