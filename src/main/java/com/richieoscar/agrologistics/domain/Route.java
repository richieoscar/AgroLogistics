package com.richieoscar.agrologistics.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Getter
@Setter
@DynamicInsert
@DynamicUpdate
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private String path;
    @Column
    private int distance;
    @Column
    private  double time;
    @Column
    private boolean isTraffic;

    @ManyToOne
    private Location location;
}
