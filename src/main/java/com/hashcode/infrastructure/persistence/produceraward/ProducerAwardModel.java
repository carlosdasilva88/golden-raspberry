package com.hashcode.infrastructure.persistence.produceraward;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "producer_award")
public class ProducerAwardModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "movie_year")
    private Integer year;
    @Column(name = "title")
    private String title;
    @Column(name = "studios")
    private String studios;
    @Column(name = "producers")
    private String producers;
    @Column(name = "winner")
    private Boolean winner;
}
