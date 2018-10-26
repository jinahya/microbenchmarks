
package com.github.jinahya.jmh.count;

import com.github.jinahya.jmh.BaseBenchmark;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

public class BitCount extends BaseBenchmark {

    // -----------------------------------------------------------------------------------------------------------------
    public static void main(final String... args) throws RunnerException {
        final Options options = new OptionsBuilder()
                .include(BitCount.class.getSimpleName())
                .forks(1)
                .build();
        new Runner(options).run();
    }    // -----------------------------------------------------------------------------------------------------------------
    @State(Scope.Benchmark)
    public static class BitCounterState {

        static final int N = 1122334455;

        // -------------------------------------------------------------------------------------------------------------
        BitCounterState(final int number) {
            super();
            this.number = number;
        }

        public BitCounterState() {
            this(N);
        }

        // -------------------------------------------------------------------------------------------------------------
        @Setup(Level.Trial)
        public void onSetUp() {
        }

        @TearDown(Level.Trial)
        public void onTearDown() {
        }

        // -------------------------------------------------------------------------------------------------------------
        public int getNumber() {
            return number;
        }

        // -------------------------------------------------------------------------------------------------------------
        private final int number;
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Creates a new instance.
     */
    public BitCount() {
        super();
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Benchmark
    public int method1(final BitCounterState state) {
        int count = 0;
        int number = state.getNumber();
        for (int i = 0; i < Integer.SIZE; i++) {
            if ((number & 0b01) == 0b01) {
                count++;
            }
            number >>>= 1;
        }
        return count;
    }

    @Benchmark
    public int method2(final BitCounterState state) {
        int count = 0;
        int number = state.getNumber();
        for (int i = 0; i < Integer.SIZE; i++) {
            if ((number & 0b01) == 0b01) {
                count++;
            }
            number >>>= 1;
            if (number == 0) {
                break;
            }
        }
        return count;
    }

    @Benchmark
    public int method3(final BitCounterState state) {
        int count = 0;
        int number = state.getNumber();
        for (; number > 0; count++) {
            number &= number - 1;
        }
        return count;
    }

    /**
     * Returns the number of one-bits in given {@link BitCounterState#number} using {@link Integer#bitCount(int)}.
     *
     * @param state a state
     * @return the number of one-bits in given state's {@link BitCounterState#number}.
     * @see Integer#bitCount(int)
     */
    @Benchmark
    public int method4(final BitCounterState state) {
        return Integer.bitCount(state.getNumber());
    }
}
