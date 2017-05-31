package com.javahelps.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class Main {
    // Create an EntityManagerFactory when you start the application.
    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
            .createEntityManagerFactory("JavaHelps");

    public static void main(String[] args) {

        // Create two Students
//        create("karthik", 20000.00, "BE"); // Alice will get an id 1

        // Update the age of Bob using the id
//        upate(2, "Bob", 25);

        // Delete the Alice from database
//        delete(1);

        // Print all the Students
//        List<Employee> employees = readAll();
//        if (employees != null) {
//            for (Employee stu : employees) {
//                System.out.println(stu);
//            }
//        }

    	customMethod();
        // NEVER FORGET TO CLOSE THE ENTITY_MANAGER_FACTORY
        ENTITY_MANAGER_FACTORY.close();
    }

    /**
     * Create a new Employee.
     * 
     * @param name
     * @param age
     */
    public static void create(String name, double salary, String dept) {
        // Create an EntityManager
        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;

        try {
            // Get a transaction
            transaction = manager.getTransaction();
            // Begin the transaction
            transaction.begin();

            // Create a new Employee object
            Employee emp = new Employee();
            emp.setEname(name);
            emp.setSalary(salary);
            emp.setDeg(dept);

            // Save the Employee object
            manager.persist(emp);

            // Commit the transaction
            transaction.commit();
        } catch (Exception ex) {
            // If there are any exceptions, roll back the changes
            if (transaction != null) {
                transaction.rollback();
            }
            // Print the Exception
            ex.printStackTrace();
        } finally {
            // Close the EntityManager
            manager.close();
        }
    }

    /**
     * Read all the Students.
     * 
     * @return a List of Students
     */
    public static List readAll() {

        List employees = null;

        // Create an EntityManager
        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;

        try {
            // Get a transaction
            transaction = manager.getTransaction();
            // Begin the transaction
            transaction.begin();

            System.out.println("etner");
            // Get a List of Students
            employees = manager.createQuery("SELECT e.ename, e.salary FROM Employee e",
            		Object[].class).getResultList();
           
            // Commit the transaction
            transaction.commit();
        } catch (Exception ex) {
            // If there are any exceptions, roll back the changes
            if (transaction != null) {
                transaction.rollback();
            }
            // Print the Exception
            ex.printStackTrace();
        } finally {
            // Close the EntityManager
            manager.close();
        }
        return employees;
    }

    /**
     * Delete the existing Student.
     * 
     * @param id
     */
    public static void delete(int id) {
        // Create an EntityManager
        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;

        try {
            // Get a transaction
            transaction = manager.getTransaction();
            // Begin the transaction
            transaction.begin();

            // Get the Student object
            Employee stu = manager.find(Employee.class, id);

            // Delete the student
            manager.remove(stu);

            // Commit the transaction
            transaction.commit();
        } catch (Exception ex) {
            // If there are any exceptions, roll back the changes
            if (transaction != null) {
                transaction.rollback();
            }
            // Print the Exception
            ex.printStackTrace();
        } finally {
            // Close the EntityManager
            manager.close();
        }
    }

    /**
     * Update the existing Student.
     * 
     * @param id
     * @param name
     * @param age
     */
    public static void upate(int id, String name, int age) {
        // Create an EntityManager
        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;

        try {
            // Get a transaction
            transaction = manager.getTransaction();
            // Begin the transaction
            transaction.begin();

            // Get the Student object
            Employee stu = manager.find(Employee.class, id);

            // Change the values
//            stu.setName(name);
//            stu.setAge(age);

            // Update the student
            manager.persist(stu);

            // Commit the transaction
            transaction.commit();
        } catch (Exception ex) {
            // If there are any exceptions, roll back the changes
            if (transaction != null) {
                transaction.rollback();
            }
            // Print the Exception
            ex.printStackTrace();
        } finally {
            // Close the EntityManager
            manager.close();
        }
    }
    
    private static void customMethod() {
    	 EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
         EntityTransaction transaction = null;
         
         try {
             transaction = manager.getTransaction();
             transaction.begin();
             System.out.println("etner");
             List<Employee> employees = manager.createNamedQuery("SELECT e.ename, e.salary FROM employee e", Employee.class)
                     .getResultList();
             System.out.println("etner");
             transaction.commit();
             manager.close();
         } catch(Exception ex){
        	System.out.println(ex.getMessage());
         }
    }
}