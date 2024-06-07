package com.example.mbeans;

import java.util.Collection;

import javax.management.Notification;
import javax.management.NotificationBroadcasterSupport;

import com.example.db.ResultDAO;
import com.example.entity.PointsResultEntity;

public class PointsCounter extends NotificationBroadcasterSupport implements PointsCounterMBean {
    private int points;
    private int areaPoints;
    private ResultDAO resultDAO;

    public PointsCounter(ResultDAO resultDAO) {
        this.resultDAO = resultDAO;
        resetAndInitCounts();
    }

    @Override
    public int getPoints() {
        return points;
    }

    @Override
    public int getAreaPoints() {
        return areaPoints;
    }

    @Override
    public void resetAndInitCounts() {
        points = 0;
        areaPoints = 0;
        Collection<PointsResultEntity> results = resultDAO.getAllResults();
        for (PointsResultEntity result : results) {
            points++;
            if (result.getResult()) {
                areaPoints++;
            } else {
                Notification notification = new Notification("MissedPoint", this, 0, System.currentTimeMillis(),
                        "Missed point occurred");
                sendNotification(notification);
            }
        }
    }

    @Override
    public void addPoint(boolean result) {
        points++;
        if (result) {
            areaPoints++;
        } else {
            Notification notification = new Notification("MissedPoint", this, 0, System.currentTimeMillis(),
                    "Missed point occurred");
            sendNotification(notification);
        }
    }

}
