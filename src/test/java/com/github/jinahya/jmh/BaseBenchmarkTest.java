package com.github.jinahya.jmh;

import org.jboss.weld.junit5.WeldInitiator;
import org.jboss.weld.junit5.WeldSetup;
import org.jboss.weld.junit5.auto.WeldJunit5AutoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.logging.Logger;

import static java.lang.invoke.MethodHandles.lookup;
import static java.util.Objects.requireNonNull;
import static java.util.logging.Logger.getLogger;

@ExtendWith({WeldJunit5AutoExtension.class})
public abstract class BaseBenchmarkTest<T extends BaseBenchmark> {

    // -----------------------------------------------------------------------------------------------------------------
    private static final Logger logger = getLogger(lookup().lookupClass().getName());

    // -----------------------------------------------------------------------------------------------------------------
    public BaseBenchmarkTest(final Class<T> benchmarkClass) {
        super();
        this.benchmarkClass = requireNonNull(benchmarkClass, "benchmarkClass is null");
        weld = WeldInitiator.from(benchmarkClass).build();
    }

    // -----------------------------------------------------------------------------------------------------------------
//    protected abstract T benchmarkInstance();

    // -----------------------------------------------------------------------------------------------------------------
    protected final Class<T> benchmarkClass;

    @WeldSetup
    public WeldInitiator weld; //= WeldInitiator.ofTestPackage();
}