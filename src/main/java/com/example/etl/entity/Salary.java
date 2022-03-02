package com.example.etl.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@NamedNativeQuery(name = "Salary.getFieldingSalaries",
    query = "SELECT DISTINCT * FROM \"Fielding\" "
        + "INNER JOIN \"Salaries\" "
        + "WHERE \"Salaries\".\"yearID\"=\"Fielding\".\"yearID\" "
        + "AND \"Salaries\".\"playerID\"=\"Fielding\".\"playerID\"",
    resultClass = Salary.class)
@NamedNativeQuery(name = "Salary.getPitchingSalaries",
    query = "SELECT DISTINCT * FROM \"Pitching\" "
        + "INNER JOIN \"Salaries\" "
        + "WHERE \"Salaries\".\"yearID\"=\"Pitching\".\"yearID\" AND \"Salaries\".\"playerID\"=\"Pitching\".\"playerID\"",
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
