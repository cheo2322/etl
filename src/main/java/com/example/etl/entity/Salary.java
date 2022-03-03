package com.example.etl.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@NamedNativeQuery(name = "selectSalary",
    query = "SELECT * FROM \"Salaries\"",
    resultClass = Salary.class)
@Getter
@Setter
@Table(name = "Salaries")
@Entity
public class Salary {

  @Id
  private Integer yearID;
  private String teamID;
  private String lgID;
  private String playerID;
  @Column(name = "salary")
  private Integer playerSalary;
}
