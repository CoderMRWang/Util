package com.wanghaotian.example.utils.mapsearch.gaode;

import lombok.Data;

/**
 * @author : wanghaotian
 * @despriction :
 * @date : Created in 2020/4/27
 * @modify By:
 */
@Data
public class IPGaodeMapSearchObj extends BaseGaodeMapSearchObj{
    private String ip;//ip

    private IPGaodeMapSearchObj(String ip) {
        this.ip = ip;
    }

    @Override
    public String CallBack(String responce) {
        return null;
    }
}
