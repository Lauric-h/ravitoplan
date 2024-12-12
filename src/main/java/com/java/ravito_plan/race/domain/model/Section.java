package com.java.ravito_plan.race.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Section {

    @Id
    @GeneratedValue
    @Column(nullable = false)
    private Long id;

//    @Column(nullable = false)
//    private int distance;
//
//    @Column(nullable = false)
//    private int elevationPositive;
//
//    @Column(nullable = false)
//    private int elevationNegative;
//
//    @Column(nullable = false)
//    private int estimatedMinuteTime;
//
//    @Column(nullable = false)
//    private String startName;
//
//    @Column(nullable = false)
//    private String endName;

}
