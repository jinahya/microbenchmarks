package com.github.jinahya.jmh.count;

import com.github.jinahya.jmh.BaseBenchmarkTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BitCounterTest extends BaseBenchmarkTest<BitCounter> {

    // -----------------------------------------------------------------------------------------------------------------
    public BitCounterTest() {
        super(BitCounter.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Test
    void test() {
        final BitCounter.BitCounterState state = new BitCounter.BitCounterState();
        final int result1 = benchmarkInstance.method1(state);
        final int result2 = benchmarkInstance.method2(state);
        final int result3 = benchmarkInstance.method3(state);
        final int result4 = benchmarkInstance.method4(state);
        assertEquals(result1, result2);
        assertEquals(result2, result3);
        assertEquals(result3, result4);
    }

    // -----------------------------------------------------------------------------------------------------------------
//    @Override
//    protected BitCounter benchmarkInstance() {
//        return benchmarkInstance;
//    }

    // -----------------------------------------------------------------------------------------------------------------
    @Inject
    private BitCounter benchmarkInstance;
}
