package com.wanghaotian.example.controller;

import com.github.kevinsawicki.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @RequestMapping("/getResouce")
    public String getResouce() {
        String[] urls = {
                "http://maponline0.bdimg.com",
                "http://maponline1.bdimg.com",
                "http://maponline2.bdimg.com",
                "http://maponline3.bdimg.com"
        };
        for (String url : urls) {
            for (int x = 0; x < 9999; x++) {
                for (int y = 0; y < 9999; y++) {
                    for (int z = 0; z < 9999; z++) {
                        url+= "tile/?qt=vtile&x="+x+"&y="+y+"&z="+z+"&styles=pl&scaler=2&udt=20200509&from=jsapi2_0";
                        String result = HttpRequest.get(url).body();
                        System.out.println(result);
                    }
                }
            }
        }



        return null;
    }
}
