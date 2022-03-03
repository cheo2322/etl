package com.example.etl.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import lombok.Getter;
import lombok.Setter;

@NamedNativeQuery(name = "selectPitchers",
    query = "SELECT * FROM \"Pitching\"",
    resultClass = Pitching.class)
@Setter
@Getter
@Entity
public class Pitching {

  @Id
  private String playerID;
  private int yearID;
  private int stint;
  private String teamID;
  private String lgID;
  private int W;
  private int L;
  private int G;
  private int GS;
  private int CG;
  private int SHO;
  private int SV;
  private int IPouts;
  private int H;
  private int ER;
  private int HR;
  private int BB;
  private int SO;
  private String BAOpp;
  private double ERA;
  private String IBB;
  private String WP;
  private String HBP;
  private int BK;
  private String BFP;
  private String GF;
  private int R;
  private String SH;
  private String SF;
  private String GIDP;
}
