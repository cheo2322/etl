package com.example.etl.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Table(name = "AllstarFull")
@NamedNativeQuery(name = "AllStarFull.byYear", query = "SELECT * FROM \\\"AllstarFull\\\" WHERE \\\"yearID\\\"=?1", resultClass = AllStarFull.class)
@Entity
public class AllStarFull {

  @Id
  private String playerID;
  private Integer yearID;
  private Integer gameNum;
  private String gameID;
  private String teamID;
  private String lgID;

  @Column(name = "GP")
  private Integer gP;
  private String startingPos;
}
