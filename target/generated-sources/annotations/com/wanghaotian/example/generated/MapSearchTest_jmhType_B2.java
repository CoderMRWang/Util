package com.wanghaotian.example.generated;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
public class MapSearchTest_jmhType_B2 extends MapSearchTest_jmhType_B1 {
    public volatile int setupTrialMutex;
    public volatile int tearTrialMutex;
    public final static AtomicIntegerFieldUpdater<MapSearchTest_jmhType_B2> setupTrialMutexUpdater = AtomicIntegerFieldUpdater.newUpdater(MapSearchTest_jmhType_B2.class, "setupTrialMutex");
    public final static AtomicIntegerFieldUpdater<MapSearchTest_jmhType_B2> tearTrialMutexUpdater = AtomicIntegerFieldUpdater.newUpdater(MapSearchTest_jmhType_B2.class, "tearTrialMutex");

    public volatile int setupIterationMutex;
    public volatile int tearIterationMutex;
    public final static AtomicIntegerFieldUpdater<MapSearchTest_jmhType_B2> setupIterationMutexUpdater = AtomicIntegerFieldUpdater.newUpdater(MapSearchTest_jmhType_B2.class, "setupIterationMutex");
    public final static AtomicIntegerFieldUpdater<MapSearchTest_jmhType_B2> tearIterationMutexUpdater = AtomicIntegerFieldUpdater.newUpdater(MapSearchTest_jmhType_B2.class, "tearIterationMutex");

    public volatile int setupInvocationMutex;
    public volatile int tearInvocationMutex;
    public final static AtomicIntegerFieldUpdater<MapSearchTest_jmhType_B2> setupInvocationMutexUpdater = AtomicIntegerFieldUpdater.newUpdater(MapSearchTest_jmhType_B2.class, "setupInvocationMutex");
    public final static AtomicIntegerFieldUpdater<MapSearchTest_jmhType_B2> tearInvocationMutexUpdater = AtomicIntegerFieldUpdater.newUpdater(MapSearchTest_jmhType_B2.class, "tearInvocationMutex");

}
