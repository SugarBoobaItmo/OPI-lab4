package com.example.mbeans;

public interface PointsCounterMBean {
    int getPoints();
    int getAreaPoints();
    void addPoint(boolean result);
    void resetAndInitCounts();
}
