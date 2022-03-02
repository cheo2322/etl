package com.example.etl.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class HallOfFame {

  @Id
  private String playerID;
  @Column(name = "yearid")
  private Integer yearId;
  private String votedBy;
  private Integer ballots;
  private Integer needed;
  private Integer votes;
  private String inducted;
  private String category;
  @Column(name = "needed_note")
  private String neededNote;
}
