package com.example.etl.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Appearances {

  @Id
  private String teamID;
  private String lgID;
  private String playerID;
  private int G_all;
  private String GS;
  private int G_batting;
  private int G_defense;
  private int G_p;
  private int G_c;
  private int G_1b;
  private int G_2b;
  private int G_3b;
  private int G_ss;
  private int G_lf;
  private int G_cf;
  private int G_rf;
  private int G_of;
  private String G_dh;
  private String G_ph;
  private String G_pr;
}
