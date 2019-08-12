package com.itheima.pojo;

import java.util.Date;

/***
 *                  God Bless No Bugs!
 * @author 昌g
 * @date 2019/8/11 23:48
 */
public class Time {

    private Date start;
    private Date end;

    public Time() {
    }

    public Time(Date start, Date end) {
        this.start = start;
        this.end = end;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }
}
