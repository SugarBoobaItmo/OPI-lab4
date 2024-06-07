package com.example.model;

import jakarta.annotation.PostConstruct;
import jakarta.inject.Inject;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.lang.management.ManagementFactory;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;

import javax.management.MBeanServer;
import javax.management.NotificationListener;
import javax.management.ObjectName;
import javax.management.StandardMBean;

import com.example.db.ResultDAO;
import com.example.entity.PointsResultEntity;
import com.example.utils.AreaValidator;
import com.example.mbeans.MissedRatio;
import com.example.mbeans.MissedRatioMBean;
import com.example.mbeans.PointsCounter;
import com.example.mbeans.PointsCounterMBean;

@Getter
@Setter
@Slf4j
public class ResultsControllerBean implements Serializable {
    private XBean xBean;
    private YBean yBean;
    private RBean rBean;
    private Collection<PointsResultEntity> results = new ArrayList<>();
    private PointsCounterMBean pointsCounter;
    private MissedRatioMBean missedRatio;

    @Inject
    private ResultDAO resultDAO;


    @PostConstruct
    public void init() {
        results = resultDAO.getAllResults();
        log.info("ResultsControllerBean initialized with {} results", results.size());

        try {
            MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
            
            ObjectName pointsCounterName = new ObjectName("com.example.mbeans:type=PointsCounter");
            pointsCounter = new PointsCounter(resultDAO);
            mBeanServer.registerMBean(pointsCounter, pointsCounterName);

            log.info("PointsCounter registered as MBean");

            NotificationListener notificationListener = (notification, handback) -> {
                System.out.println("Notification received: " + notification.getMessage());
            };
            mBeanServer.addNotificationListener(pointsCounterName, notificationListener, null, null);
            
            missedRatio = new MissedRatio(pointsCounter);
            ObjectName missedRatioName = new ObjectName("com.example.mbeans:type=MissedRatio");
            StandardMBean missedRatioMBean = new StandardMBean(missedRatio, MissedRatioMBean.class);
            mBeanServer.registerMBean(missedRatioMBean, missedRatioName);
        } catch (Exception e) {
            log.error("Error initializing PointsCounter", e);
        }
    }

    public void addResult(Double x, Double y, Double r) {
        PointsResultEntity point = new PointsResultEntity();

        final long start = System.nanoTime();

        point.setX(x);
        point.setY(y);
        point.setR(r);
        point.setTime(java.time.LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss")));
        boolean result = AreaValidator.checkArea(x, y, r);
        pointsCounter.addPoint(result);
        point.setResult(result);
        point.setExecutionTime(String.format("%.9f", (System.nanoTime() - start) / 1000000000.0));

        resultDAO.addNewResult(point);
        results.add(point);

        yBean.setValue(0.0);
        xBean.setValue(0.0);
    }

    public void updateR(Double r) {
        for (PointsResultEntity point : results) {
            point.setR(r);
            point.setResult(AreaValidator.checkArea(point.getX(), point.getY(), r));

        }
    }

    public void clearResults() {
        results.clear();
        resultDAO.clearResults();
        pointsCounter.resetAndInitCounts();
    }
}