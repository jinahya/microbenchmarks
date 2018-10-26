package com.github.jinahya.jmh.search;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

public class BinarySearch<T extends Comparable<T>> {

    // -----------------------------------------------------------------------------------------------------------------
    public static void main(final String... args) throws RunnerException {
        final Options options = new OptionsBuilder()
                .include(BinarySearch.class.getSimpleName())
                .forks(1)
                .build();
        new Runner(options).run();
    }

    // -----------------------------------------------------------------------------------------------------------------
    @State(Scope.Benchmark)
    public static class BinarySearchState {

        static final int N = 1122334455;

        // -------------------------------------------------------------------------------------------------------------
        BinarySearchState(final int number) {
            super();
            this.number = number;
        }

        public BinarySearchState() {
            this(N);
        }

        // -------------------------------------------------------------------------------------------------------------
        final int number;
    }

    // -----------------------------------------------------------------------------------------------------------------
    public int i1(final T[] array, final T value) {
        return -1;
    }
}
