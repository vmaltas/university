package com.philips.university.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "department")
public class Department extends BaseEntity {

    private String name;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Professor> professorList;

}
