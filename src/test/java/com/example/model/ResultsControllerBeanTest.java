package com.example.model;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;

import com.example.db.ResultDAO;
import com.example.entity.PointsResultEntity;

public class ResultsControllerBeanTest {

    private ResultsControllerBean resultsControllerBean;
    private ResultDAO resultDAO;

    @Before
    public void setUp() {
        resultDAO = mock(ResultDAO.class);

        resultsControllerBean = new ResultsControllerBean();
        
        resultsControllerBean.setResultDAO(resultDAO);
        resultsControllerBean.setXBean(new XBean());
        resultsControllerBean.setYBean(new YBean());
        resultsControllerBean.setRBean(new RBean());
    }

    @Test
    public void testInit() {
        Collection<PointsResultEntity> mockResults = new ArrayList<>();
        when(resultDAO.getAllResults()).thenReturn(mockResults);

        resultsControllerBean.init();

        assertEquals(mockResults, resultsControllerBean.getResults());
    }

    @Test
    public void testAddResult() {
        double x = 1.0;
        double y = 0.0;
        double r = 3.0;

        resultsControllerBean.addResult(x, y, r);

        Collection<PointsResultEntity> results = resultsControllerBean.getResults();
        assertEquals(1, results.size());
        PointsResultEntity addedResult = results.iterator().next();
        assertEquals(x, addedResult.getX(), 0.000001);
        assertEquals(y, addedResult.getY(), 0.000001);
        assertEquals(r, addedResult.getR(), 0.000001);
    }

    @Test
    public void testUpdateR() {
        double newR = 3.0;
        resultsControllerBean.updateR(newR);
        Collection<PointsResultEntity> results = resultsControllerBean.getResults();

        for (PointsResultEntity result : results) {
            assertEquals(newR, result.getR(), 0.000001);
        }
    }

    @Test
    public void testClearResults() {
        Collection<PointsResultEntity> mockResults = new ArrayList<>();
        mockResults.add(new PointsResultEntity());
        when(resultDAO.getAllResults()).thenReturn(mockResults);

        resultsControllerBean.clearResults();

        assertEquals(0, resultsControllerBean.getResults().size());
    }
}
