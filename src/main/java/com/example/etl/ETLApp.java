package com.example.etl;

import com.example.etl.entity.Fielding;
import com.example.etl.entity.Pitching;
import com.example.etl.entity.Salary;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class ETLApp {

  public static void main(String[] args) {
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    EntityTransaction transaction = entityManager.getTransaction();

    try {
      transaction.begin();

      List<Salary> salaryList = getSalaries(entityManager);
      List<Fielding> fielders = getFielders(entityManager);
      List<Pitching> pitchers = getPitchers(entityManager);

      Map<Integer, Double> averageSalaryByYear = getAverageSalaryByYear(
          getFieldingSalariesByYear(salaryList, fielders));
      Map<Integer, Double> averageSalaryByYear1 = getAverageSalaryByYear(
          getPitchingSalariesByYear(salaryList, pitchers));

      transaction.commit();
//    } catch (IOException e) {
//      e.printStackTrace();
    } finally {
      if (transaction.isActive()) {
        transaction.rollback();
      }

      entityManager.close();
      entityManagerFactory.close();
    }
  }

//  private Map<Integer, List<Double>> mergeMaps(Map<Integer, Double> map1,
//      Map<Integer, Double> map2) {
//
//    Map<Integer, List<Double>> result = new TreeMap<>();
//
//
//  }

  private static List<Salary> getSalaries(EntityManager entityManager) {
    TypedQuery<Salary> salaryTypedQuery = entityManager.createNamedQuery("selectSalary",
        Salary.class);

    return salaryTypedQuery.getResultList();
  }

  private static List<Fielding> getFielders(EntityManager entityManager) {
    TypedQuery<Fielding> fieldingTypedQuery = entityManager.createNamedQuery("getFielders",
        Fielding.class);

    return fieldingTypedQuery.getResultList();
  }

  private static List<Pitching> getPitchers(EntityManager entityManager) {
    TypedQuery<Pitching> pitchingTypedQuery = entityManager.createNamedQuery("selectPitchers",
        Pitching.class);

    return pitchingTypedQuery.getResultList();
  }

  private static Map<Integer, Double> getAverageSalaryByYear(Map<Integer, List<Double>> map) {
    TreeMap<Integer, Double> result = new TreeMap<>();

    map.forEach((integer, doubles) ->
        result.put(integer, doubles.stream().mapToDouble(value -> value).average().orElse(0.0)));

    return result;
  }

  private static Map<Integer, List<Double>> getFieldingSalariesByYear(List<Salary> salaries,
      List<Fielding> fielders) {

    TreeMap<Integer, List<Double>> result = new TreeMap<>();

    Set<Fielding> set = new HashSet<>(fielders);
    fielders.clear();
    fielders.addAll(set);

    salaries.forEach(salary -> fielders.forEach(fielding -> {
          if (salary.getPlayerID().equals(fielding.getPlayerID())) {
            if (result.containsKey(salary.getYearID())) {
              List<Double> newList = new ArrayList<>(result.get(salary.getYearID()));
              newList.add(salary.getPlayerSalary().doubleValue());

              result.put(salary.getYearID(), newList);
            } else {
              result.put(salary.getYearID(), List.of(salary.getPlayerSalary().doubleValue()));
            }
          }
        })
    );

    return result;
  }

  private static Map<Integer, List<Double>> getPitchingSalariesByYear(List<Salary> salaries,
      List<Pitching> pitching) {

    TreeMap<Integer, List<Double>> result = new TreeMap<>();

    Set<Pitching> set = new HashSet<>(pitching);
    pitching.clear();
    pitching.addAll(set);

    salaries.forEach(salary -> pitching.forEach(fielding -> {
          if (salary.getPlayerID().equals(fielding.getPlayerID())) {
            if (result.containsKey(salary.getYearID())) {
              List<Double> newList = new ArrayList<>(result.get(salary.getYearID()));
              newList.add(salary.getPlayerSalary().doubleValue());

              result.put(salary.getYearID(), newList);
            } else {
              result.put(salary.getYearID(), List.of(salary.getPlayerSalary().doubleValue()));
            }
          }
        })
    );

    return result;
  }
}
