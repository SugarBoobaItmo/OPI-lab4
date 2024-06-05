package com.example.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "results", schema = "public")
@Setter
@EqualsAndHashCode
@ToString
public class PointsResultEntity {
    private long id;
    private double x;
    private double y;
    private double r;
    private boolean result;
    private String time;
    private String executionTime;

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence-generator")
    @SequenceGenerator(name = "sequence-generator", sequenceName = "lab3_x_test_table_id_seq", allocationSize = 1)
    public long getId() {
        return id;
    }

    @Column
    public double getX() {
        return x;
    }

    @Column
    public double getY() {
        return y;
    }

    @Column
    public double getR() {
        return r;
    }

    @Column
    public boolean getResult() {
        return result;
    }

    @Column
    public String getTime() {
        return time;
    }

    @Column
    public String getExecutionTime() {
        return executionTime;
    }

}
