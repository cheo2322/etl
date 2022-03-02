package com.example.etl.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Fielding {

  @Id
  private String playerID;
  private Integer yearID;
  private Integer stint;
  private String teamID;
  private String lgID;

  @Column(name = "POS")
  private String position;
  @Column(name = "G")
  private Integer games;
  @Column(name = "Gs")
  private String gamesStarted;
  @Column(name = "InnOuts")
  private String innOuts;
  @Column(name = "PO")
  private Integer putOuts;
  @Column(name = "A")
  private Integer assists;
  @Column(name = "E")
  private Integer errors;
  @Column(name = "DP")
  private Integer doublePlays;
  @Column(name = "PB")
  private String passedBalls;
  @Column(name = "WP")
  private String wildPitches;
  @Column(name = "SB")
  private String stolenBasses;
  @Column(name = "CS")
  private String caughtStealing;
  @Column(name = "ZR")
  private String zoneRating;
}
