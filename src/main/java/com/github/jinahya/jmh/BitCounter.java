/*
 * Copyright (c) 2014, Oracle America, Inc.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  * Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *
 *  * Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 *
 *  * Neither the name of Oracle nor the names of its contributors may be used
 *    to endorse or promote products derived from this software without
 *    specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF
 * THE POSSIBILITY OF SUCH DAMAGE.
 */

package com.github.jinahya.jmh;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import static java.util.concurrent.ThreadLocalRandom.current;

public class BitCounter extends BaseBenchmark {


    // -----------------------------------------------------------------------------------------------------------------
    public static void main(String[] args) throws RunnerException {
        final Options options = new OptionsBuilder()
                .include(BitCounter.class.getSimpleName())
                .forks(1)
                .build();
        new Runner(options).run();
    }

    // -----------------------------------------------------------------------------------------------------------------
    @State(Scope.Benchmark)
    public static class BitCounterState {

        static final int N = 1122334455;

        // -------------------------------------------------------------------------------------------------------------
        public BitCounterState() {
            super();
            number = N;
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
    public BitCounter() {
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
