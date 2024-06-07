package com.example.mbeans;

public class MissedRatio implements MissedRatioMBean {
    private PointsCounterMBean pointsCounter;

    public MissedRatio(PointsCounterMBean pointsCounter) {
        this.pointsCounter = pointsCounter;
    }

    @Override
    public double getMissedRatio() {
        double points = pointsCounter.getPoints();
        double missedPoints = points - pointsCounter.getAreaPoints();
        return missedPoints / points;
    }
    
}
