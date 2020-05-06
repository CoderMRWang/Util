package com.wanghaotian.example;

import com.wanghaotian.example.utils.mapsearch.MapSearchUtils;
import com.wanghaotian.example.utils.mapsearch.baidu.BaiduMapSearchUtils;
import com.wanghaotian.example.utils.mapsearch.baidu.BaseBaiduMapSearchObj;
import com.wanghaotian.example.utils.mapsearch.baidu.PlaceBaiduMapSearchObj;
import com.wanghaotian.example.utils.mapsearch.baidu.SortNameDetail;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.openjdk.jmh.annotations.Mode.Throughput;


/**
 * @author : wanghaotian
 * @despriction :
 * @date : Created in 2020/4/25
 * @modify By:
 */
@State(Scope.Thread)
public class MapSearchTest {
    @Benchmark()
    @BenchmarkMode(Throughput)
    @OutputTimeUnit(TimeUnit.MILLISECONDS) // 输出结果的时间粒度为微秒
    public void baiduTest(){
        //百度测试
        BaiduMapSearchUtils baiduMapSearchUtils = MapSearchUtils.getBaiduMapSearchUtils();
        PlaceBaiduMapSearchObj placeBaiduMapSearchObject =BaiduMapSearchUtils.getPlaceBaiduMapSearchObject();
        placeBaiduMapSearchObject.setQuery("ATM机");
        placeBaiduMapSearchObject.setAk("GQg4czMTKd2csVGqYGCyTkAyxFDu08fL");
        placeBaiduMapSearchObject.setRegion("北京");
        placeBaiduMapSearchObject.setTag("银行");
        placeBaiduMapSearchObject.setScope(BaseBaiduMapSearchObj.SCOPE_ENUM.DETILS);
        Map<BaseBaiduMapSearchObj.INDUSTRY_TYPE_ENUM, SortNameDetail> map=new HashMap<>();
        SortNameDetail sortNameDetail=new SortNameDetail();
        sortNameDetail.setSortName(BaseBaiduMapSearchObj.SORT_NAME_ENUM.HOTEL_LEVEL);
        Map<BaseBaiduMapSearchObj.SORT_RULE_ENUM, BaseBaiduMapSearchObj.SORT_CHOICE_ENUM> choiceEnumMap=new HashMap<>();
        choiceEnumMap.put(BaseBaiduMapSearchObj.SORT_RULE_ENUM.SORT_RULE, BaseBaiduMapSearchObj.SORT_CHOICE_ENUM.SORT_HIGHT_2_LOW);
        sortNameDetail.setChoice(choiceEnumMap);
        map.put(BaseBaiduMapSearchObj.INDUSTRY_TYPE_ENUM.HOTEL, sortNameDetail);
        placeBaiduMapSearchObject.getFilter().add(map);
        baiduMapSearchUtils.getResult(placeBaiduMapSearchObject, PlaceBaiduMapSearchObj.class);
    }
    public static void main(String[] args) throws RunnerException {

        Options options= new OptionsBuilder().include(MapSearchTest.class.getSimpleName())
                .forks(2).threads(4).measurementIterations(1).timeout(TimeValue.seconds(1))
                .output("test.log").build();
        new Runner(options).run();
//        MapSearchTest mapSearchTest=new MapSearchTest();
//        mapSearchTest.baiduTest();

    }
}
