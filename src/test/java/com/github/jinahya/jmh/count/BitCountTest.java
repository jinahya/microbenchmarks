package com.github.jinahya.jmh.count;

import com.github.jinahya.jmh.BaseBenchmarkTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BitCountTest extends BaseBenchmarkTest<BitCount> {

    // -----------------------------------------------------------------------------------------------------------------
    public BitCountTest() {
        super(BitCount.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Test
    void test() {
        final BitCount.BitCounterState state = new BitCount.BitCounterState();
        final int result1 = benchmarkInstance.method1(state);
        final int result2 = benchmarkInstance.method2(state);
        final int result3 = benchmarkInstance.method3(state);
        final int result4 = benchmarkInstance.method4(state);
        assertEquals(result1, result2);
        assertEquals(result2, result3);
        assertEquals(result3, result4);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Inject
    private BitCount benchmarkInstance;
}
