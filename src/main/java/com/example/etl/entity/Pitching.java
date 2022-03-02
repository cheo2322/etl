package com.example.etl.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Pitching {

  @Id
  private String playerID;
  private Integer yearID;
  private Integer stint;
  private String teamID;
  private String lgID;
  @Column(name = "W")
  private Integer wins;
  @Column(name = "L")
  private Integer losses;
  @Column(name = "G")
  private Integer games;
  @Column(name = "GS")
  private Integer gamesStarted;
  @Column(name = "CG")
  private Integer completeGames;
  @Column(name = "SHO")
  private Integer shutouts;
  @Column(name = "SV")
  private Integer saves;
  @Column(name = "IPouts")
  private Integer outsPitched;
  @Column(name = "H")
  private Integer hits;
  @Column(name = "ER")
  private Integer earnedRuns;
  @Column(name = "HR")
  private Integer homeRuns;
  @Column(name = "BB")
  private Integer walks;
  @Column(name = "SO")
  private Integer strikeouts;
  @Column(name = "BAOpp")
  private Integer opponentsBattingAverage;
  @Column(name = "ERA")
  private Integer earnedRunAverage;
  @Column(name = "IBB")
  private Integer intentionalWalks;
  @Column(name = "WP")
  private Integer wildPitches;
  @Column(name = "HBP")
  private Integer hitByPitch;
  @Column(name = "BK")
  private Integer balks;
  @Column(name = "BFP")
  private Integer facedByPitcher;
  @Column(name = "GF")
  private Integer gamesFinished;
  @Column(name = "R")
  private Integer runsAllowed;
  @Column(name = "SH")
  private Integer sacrifices;
  @Column(name = "SF")
  private Integer sacrificeFlies;
  @Column(name = "GIDP")
  private Integer groundedDoublePlay;
}
