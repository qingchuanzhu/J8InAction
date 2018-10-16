import java.util.function.*;

class ParallelStreamTest {
	static long measureSumPerf(Function<Long, Long> adder, long n) {
		long fastest = Long.MAX_VALUE;
		for (int i = 0; i < 10; i++) {
			long start = System.nanoTime();
			long sum = adder.apply(n);
			long duration = (System.nanoTime() - start) / 1_000_000;
			if (duration < fastest) {
				fastest = duration;
			}
		}
		return fastest;
	}

	public static void main(String[] args) {
		System.out.println("Sequential sum done in: " +
			measureSumPerf(ParallelStream::sequentialSum, 10_000_000) + " msecs");
		System.out.println("Native Iterative sum done in: " +
			measureSumPerf(ParallelStream::iterativeSum, 10_000_000) + " msecs");
		System.out.println("Parallel sum done in: " +
			measureSumPerf(ParallelStream::parallelSum, 10_000_000) + " msecs");
		System.out.println("LongStream range sum done in: " +
			measureSumPerf(ParallelStream::rangedSum, 10_000_000) + " msecs");
	}
}