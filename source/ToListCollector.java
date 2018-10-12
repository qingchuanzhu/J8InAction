import java.util.*;
import java.util.function.*;
import java.util.stream.Collector;
import static java.util.stream.Collector.Characteristics.*;

class ToListCollector<T> implements Collector<T, List<T>, List<T>> {
	@Override
	public Supplier<List<T>> supplier() {
		return ArrayList::new;
	}

	@Override
	public BiConsumer<List<T>, T> accumulator() {
		return (l, e) -> l.add(e); // or more concisely List::add
	}

	@Override
	public Function<List<T>, List<T>> finisher() {
		return Function.identity(); // static method of Function interface
	}

	@Override
	public BinaryOperator<List<T>> combiner() {
		return (l1, l2) -> {
				l1.addAll(l2); 
				return l1;
			};
	}

	@Override
	public Set<Characteristics> characteristics() {
		return Collections.unmodifiableSet(EnumSet.of(
			IDENTITY_FINISH, CONCURRENT));
	}
}