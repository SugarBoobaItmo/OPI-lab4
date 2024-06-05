package com.example.db;

import java.util.Collection;

import com.example.entity.PointsResultEntity;

import jakarta.enterprise.context.Dependent;

@Dependent
public interface ResultDAO {
    void addNewResult(PointsResultEntity result);

    void updateResult(Long result_id, PointsResultEntity result);

    PointsResultEntity getResultById(Long result_id);

    Collection<PointsResultEntity> getAllResults();

    void deleteResult(PointsResultEntity result);

    void clearResults();
}
