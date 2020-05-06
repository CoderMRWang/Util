package com.wanghaotian.example;


import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author : wanghaotian
 * @despriction :
 * @date : Created in 2020/5/4
 * @modify By:
 */

    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    @Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
    @Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
    @Fork(5)
    public class JMHSample_38_PerInvokeSetup {

        /*
         * This example highlights the usual mistake in non-steady-state benchmarks.
         *
         * Suppose we want to test how long it takes to bubble sort an array. Naively,
         * we could make the test that populates an array with random (unsorted) values,
         * and calls sort on it over and over again:
         */

        private void bubbleSort(byte[] b) {
            boolean changed = true;
            while (changed) {
                changed = false;
                for (int c = 0; c < b.length - 1; c++) {
                    if (b[c] > b[c + 1]) {
                        byte t = b[c];
                        b[c] = b[c + 1];
                        b[c + 1] = t;
                        changed = true;
                    }
                }
            }
        }

        // Could be an implicit State instead, but we are going to use it
        // as the dependency in one of the tests below
        @State(Scope.Benchmark)
        public static class Data {

            @Param({"1", "16", "256"})
            int count;

            byte[] arr;

            @Setup
            public void setup() {
                arr = new byte[count];
                Random random = new Random(1234);
                random.nextBytes(arr);
            }
        }

        @Benchmark
        public byte[] measureWrong(Data d) {
            bubbleSort(d.arr);
            return d.arr;
        }

        /*
         * The method above is subtly wrong: it sorts the random array on the first invocation
         * only. Every subsequent call will "sort" the already sorted array. With bubble sort,
         * that operation would be significantly faster!
         *
         * This is how we might *try* to measure it right by making a copy in Level.Invocation
         * setup. However, this is susceptible to the problems described in Level.Invocation
         * Javadocs, READ AND UNDERSTAND THOSE DOCS BEFORE USING THIS APPROACH.
         */

        @State(Scope.Thread)
        public static class DataCopy {
            byte[] copy;

            @Setup(Level.Invocation)
            public void setup2(Data d) {
                copy = Arrays.copyOf(d.arr, d.arr.length);
            }
        }

        @Benchmark
        public byte[] measureNeutral(DataCopy d) {
            bubbleSort(d.copy);
            return d.copy;
        }

        /*
         * In an overwhelming majority of cases, the only sensible thing to do is to suck up
         * the per-invocation setup costs into a benchmark itself. This work well in practice,
         * especially when the payload costs dominate the setup costs.
         */

        @Benchmark
        public byte[] measureRight(Data d) {
            byte[] c = Arrays.copyOf(d.arr, d.arr.length);
            bubbleSort(c);
            return c;
        }
    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(".*" + JMHSample_38_PerInvokeSetup.class.getSimpleName() + ".*")
                .build();

        new Runner(opt).run();
    }
}
