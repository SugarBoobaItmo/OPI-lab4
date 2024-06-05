package com.example.db;

import java.util.Collection;

import com.example.entity.PointsResultEntity;

import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

@Dependent
public class ResultDAOImpl implements ResultDAO {
    @Inject
    private EntityManager entityManager;

    @Override
    public void addNewResult(PointsResultEntity result) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(result);
        transaction.commit();
    }

    @Override
    public void updateResult(Long result_id, PointsResultEntity result) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.merge(result);
        transaction.commit();

    }

    @Override
    public PointsResultEntity getResultById(Long result_id) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<PointsResultEntity> query = builder.createQuery(PointsResultEntity.class);
        Root<PointsResultEntity> root = query.from(PointsResultEntity.class);
        query.select(root).where(builder.equal(root.get("id"), result_id));
        return entityManager.createQuery(query).getSingleResult();
    }

    @Override
    public Collection<PointsResultEntity> getAllResults() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<PointsResultEntity> query = builder.createQuery(PointsResultEntity.class);
        Root<PointsResultEntity> root = query.from(PointsResultEntity.class);
        query.select(root);
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public void deleteResult(PointsResultEntity result) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.remove(result);
        transaction.commit();
    }

    @Override
    public void clearResults() {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.createQuery("DELETE FROM PointsResultEntity").executeUpdate();
        transaction.commit();
    }
}
