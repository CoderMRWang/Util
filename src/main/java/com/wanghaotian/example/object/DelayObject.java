package com.wanghaotian.example.object;

import java.util.Date;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * author;Wanghaotian
 * data:2020/4/18 0018
 */
public class DelayObject implements Delayed {
    private Date startDate;
    private Date endDate;

    public DelayObject() {
    }
    public DelayObject(Date startDate, Date endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(startDate.getTime()-endDate.getTime(),unit);
    }

    @Override
    public int compareTo(Delayed o) {
       if (o instanceof DelayObject)
       {
           DelayObject other = DelayObject.class.cast(o);
          return (this.startDate.compareTo(other.startDate));
       }
       return -1;

    }
    @Override
    public String toString() {
        return "DelayObject{" +
                "startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
