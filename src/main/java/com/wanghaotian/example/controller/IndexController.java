package com.wanghaotian.example.controller;

import com.github.kevinsawicki.http.HttpRequest;
import com.wanghaotian.example.utils.mapsearch.MapSearchUtils;
import com.wanghaotian.example.utils.mapsearch.baidu.BaiduMapSearchUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import sun.misc.BASE64Decoder;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

@Slf4j
/**
 * @author : wanghaotian
 * @despriction :
 * @date : Created in 2020/5/7
 * @modify By:
 */
@Controller
public class IndexController {
    @RequestMapping("/")
    public String toIndex() {
        return "index";
    }

    @RequestMapping("/map")
    public String toStatic() {
        return "smap";
    }

    public static void main(String[] args) {

            for (int x = 0; x < 99999; x++) {
                for (int y = 0; y < 99999; y++) {
                    for (int z = 0; z < 20; z++) {
                        BaiduMapSearchUtils baiduMapSearchUtils= MapSearchUtils.getBaiduMapSearchUtils();
                        baiduMapSearchUtils.getStaticResource(x,y,z);
                    }
                }
            }

//        BaiduMapSearchUtils baiduMapSearchUtils= MapSearchUtils.getBaiduMapSearchUtils();
//                        baiduMapSearchUtils.getStaticResource(12643,4713,16);

    }







    @RequestMapping("/getResouce")
    public String getResouce() {
        String tocken = "http://api.map.baidu.com/?qt=business_accredit&ak=Vrv2rCkmEi1TXNEKCT8njLyECvGFSxqk&callback=BMap._rd._cbk59813";
        String tocken1 = "http://api.map.baidu.com/?qt=cen&b=12950835.96%2C4825158.07%3B12963260.02%2C4828942.04&l=15&ie=utf-8&oue=1&fromproduct=jsapi&callback=BMap._rd._cbk24735&ak=Vrv2rCkmEi1TXNEKCT8njLyECvGFSxqk";
        String tocken2 = "http://api.map.baidu.com/?qt=verify&ak=Vrv2rCkmEi1TXNEKCT8njLyECvGFSxqk&callback=BMap._rd._cbk77925";
        String tocken3 = "http://api.map.baidu.com/api?v=2.0&ak=Vrv2rCkmEi1TXNEKCT8njLyECvGFSxqk";
        String tocken4 = "http://api.map.baidu.com/getscript?v=2.0&ak=Vrv2rCkmEi1TXNEKCT8njLyECvGFSxqk&services=&t=20200327103013";

        HttpRequest.get(tocken).body();
        HttpRequest.get(tocken1).body();
        HttpRequest.get(tocken2).body();
        HttpRequest.get(tocken3).body();
        HttpRequest.get(tocken4).body();


        return null;
    }
}
