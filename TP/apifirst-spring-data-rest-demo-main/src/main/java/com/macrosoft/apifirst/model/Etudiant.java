package com.macrosoft.apifirst.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(indexes = {
  @Index(columnList = "firstName"),
  @Index(name = "fn_index", columnList = "id"),
  @Index(name = "multiIndex1", columnList = "firstName, lastName"),
  @Index(name = "multiIndex2", columnList = "lastName, firstName"),
  @Index(name = "multiSortIndex", columnList = "firstName, lastName DESC"),
  @Index(name = "uniqueIndex", columnList = "firstName", unique = true),
  @Index(name = "uniqueMultiIndex", columnList = "firstName, lastName", unique = true)
})
public class Etudiant implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    private String firstName;
    private String lastName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


}