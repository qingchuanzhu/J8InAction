import java.util.*;
import java.util.stream.*;

class ParallelStream {
	static long sequentialSum(long n) {
		return Stream.iterate(1L, i -> i + 1)
					 .limit(n)
					 .reduce(0L, Long::sum);
	}

	static long iterativeSum(long n) {
		long result = 0;
		for (long i = 1L; i <= n; i++) {
			result += i;
		}
		return result;
	}

	static long parallelSum(long n) {
		return Stream.iterate(1L, i -> i+ 1)
					 .limit(n)
					 .parallel()
					 .reduce(0L, Long::sum);
	}

	static long rangedSum(long n) {
		return LongStream.rangeClosed(1, n)
						 .parallel()
						 .reduce(0L, Long::sum);
	}

	static long sideEffectSum(long n) {
		Accumulator acc = new Accumulator();
		LongStream.rangeClosed(1, n).forEach(acc::add);
		return acc.total;
	}

	static long sideEffectParallelSum(long n) {
		Accumulator acc = new Accumulator();
		LongStream.rangeClosed(1, n).parallel().forEach(acc::add);
		return acc.total;
	}
}

class Accumulator {
	long total = 0;
	void add(long value) { total += value; }
}
