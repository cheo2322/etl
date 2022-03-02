package com.example.etl;

import com.example.etl.entity.Salary;
import com.opencsv.CSVWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
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

      TypedQuery<Salary> query = entityManager.createNamedQuery("Salary.getFieldingSalaries",
          Salary.class);
      TypedQuery<Salary> query2 = entityManager.createNamedQuery("Salary.getPitchingSalaries",
          Salary.class);

      Map<Integer, List<Double>> salaries = new TreeMap<>();

      String[] title = {"Year", "Fielding"};
      String name = "AverageSalaries.csv";

      CSVWriter writer = new CSVWriter(new FileWriter(name));
      writer.writeNext(title);

      for (Map.Entry<Integer, List<Integer>> list : mapSalaries(query.getResultList()).entrySet()) {
        salaries.put(list.getKey(), new ArrayList<>(Collections.singleton(
            list.getValue().stream().mapToDouble(value -> value).average().orElse(0.0))));
      }

      for (Map.Entry<Integer, List<Integer>> list : mapSalaries(
          query2.getResultList()).entrySet()) {
        List<Double> newList = new ArrayList<>(salaries.get(list.getKey()));
        newList.add(list.getValue().stream().mapToDouble(value -> value).average().orElse(0.0));
        salaries.put(list.getKey(), newList);

        writer.writeNext(new String[]{list.getKey().toString(), Arrays.toString(newList.toArray())});
      }

      writer.close();
      System.out.println(salaries);

      transaction.commit();
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      if (transaction.isActive()) {
        transaction.rollback();
      }

      entityManager.close();
      entityManagerFactory.close();
    }
  }

  public static Map<Integer, List<Integer>> mapSalaries(List<Salary> salaries) {
    Map<Integer, List<Integer>> result = new TreeMap<>();
    for (Salary s : salaries) {
      if (result.containsKey(s.getYearID())) {
        List<Integer> newList = new ArrayList<>(result.get(s.getYearID()));
        newList.add(s.getPlayerSalary());

        result.put(s.getYearID(), newList);
      } else {
        result.put(s.getYearID(), List.of(s.getPlayerSalary()));
      }
    }

    return result;
  }
}
