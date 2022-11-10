package com.mycompany.myapp.domain.enumeration;

/**
 * The DatesOfPeriod enumeration.
 */
public enum DatesOfPeriod {
    Year(31_536_000),
    HalfYear(15_768_000),
    Month(2_592_000),
    HalfMonth(1_296_000),
    Week(604_800),
    Day(86_400);

    private long second;

    DatesOfPeriod(long second) {
        this.second = second;
    }

    public long getSecond() {
        return second;
    }
}
//    Year(31_536_000),
//    HalfYear(15_768_000),
//    Month(2_592_000),
//    HalfMonth(1_296_000),
//    Week(604_800),
//    Day(86_400);
//
//    long second;
//
//    DatesOfPeriod(long second) {
//        this.second = second;
//    }
//
//    public Long second() {
//        return this.second;
//    }
