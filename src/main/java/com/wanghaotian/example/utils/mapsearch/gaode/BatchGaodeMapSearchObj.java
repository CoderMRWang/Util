package com.wanghaotian.example.utils.mapsearch.gaode;

import lombok.Data;

/**
 * @author : wanghaotian
 * @despriction :
 * @date : Created in 2020/4/27
 * @modify By:
 */
@Data
public class BatchGaodeMapSearchObj extends BaseGaodeMapSearchObj{
    private BatchGaodeMapSearchObj() {
    }

    @Override
    public String CallBack(String responce) {
        return null;
    }
}
