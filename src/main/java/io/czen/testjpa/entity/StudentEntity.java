package io.czen.testjpa.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "student")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class StudentEntity {

    @Id
    @Column
    @EqualsAndHashCode.Include
    private Long id;

    @Column
    private String firstName;

    @Column
    private String lastName;

}
