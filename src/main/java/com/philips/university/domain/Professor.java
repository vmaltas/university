package com.philips.university.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "professor")
public class Professor extends BaseEntity {

    private String name;

    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

    @OneToMany(mappedBy = "professor", cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Schedule> scheduleList;
}
