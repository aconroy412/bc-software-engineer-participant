package com.academy.student;

import java.util.Scanner;

public class StudentManager {

    private static final int MAX_STUDENTS = 20;

    private final Student[] students = new Student[MAX_STUDENTS];
    private int studentCount = 0;
    private final Scanner scanner;

    public StudentManager(Scanner scanner) {
        this.scanner = scanner;
    }

    public void displayMenu() {
        System.out.println("====================================");
        System.out.println("Student Management System");
        System.out.println("====================================");
        System.out.println("1. Add Student");
        System.out.println("2. Display Students");
        System.out.println("3. Search Student");
        System.out.println("4. Average Marks");
        System.out.println("5. Exit");
        System.out.print("Enter Choice : ");
    }

    // Methods addStudent, displayStudents, searchStudent, calculateAverage
    // will be filled in later steps.

    public boolean addStudent() {
            
            // Check if max students already
            if (studentCount >= MAX_STUDENTS) {
                System.out.println("Class Full");
                return false;
            }
    
            // Get id
            int id = 0;
            boolean validId = false;
            while (!validId || id < 0){
                System.out.println("Please tell me the ID you'd like to assign this student: ");
                try {
                    id = Integer.parseInt(scanner.nextLine());
                    validId = true;
                }
                catch (NumberFormatException e) {
                    System.out.println("Please enter a valid positive number");
                }

            }
            
            
    
            // check if ID exists O(n)
            for (int i = 0; i < studentCount; i++) {
                if (id == students[i].getStudentId()) {
                    System.out.println("ID already exists!");
                    return false;
                }
            }
    
            String name;
            do {
                System.out.println("What is the student's name?: ");
                name = scanner.nextLine();
            }
            while(name.isBlank() || name.isEmpty());
    
            String course;
            do {

                System.out.println("What is the course they are in?: ");
                course = scanner.nextLine();
            }
            while(course.isBlank() || course.isEmpty());
    

            double marks = 0.0;
            boolean validDouble = false;
            while (!validDouble || (marks < 0.0 || marks > 100.00)){

                System.out.println("What is their mark (grade) (0 - 100.00)");
                try {
                    marks = Double.parseDouble(scanner.nextLine());
                    validDouble = true;
                }
                catch(NumberFormatException e) {
                    System.out.println("Enter a valid fractional value please");
                }
            }

            Student stu = new Student(id, name, course, marks);

            students[studentCount] = stu;
            studentCount++;
            System.out.println("Student Added Successfully");

            return true;
    }

    public void displayStudents() {

        if (studentCount < 1) {
            System.out.println("No studetns to display");
        }
        for (int i = 0; i < studentCount; i++) {
            Student student = students[i];
            System.out.println("-----------------------------------------------------");
            System.out.printf("%-8s %-20s %-15s %-8s%n",
                "ID",
                "Name",
                "Course",
                "Marks");
            System.out.println("-----------------------------------------------------");
            System.out.printf("%-8d %-20s %-15s %-8.2f%n",
                student.getStudentId(),
                student.getName(),
                student.getCourse(),
                student.getMarks());
            System.out.println("-----------------------------------------------------");
        }
    }


    public void searchStudent() {
        System.out.println("What is the student's id you're looking for: ");
        int id = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < studentCount; i++) {
            if (this.students[i].getStudentId() == id) {
                this.students[i].display();
                return;
            }
        }

        System.out.println("Student not found");
    }

    

    public void calculateAverage() {
        double top = 0.0;
        
        if (studentCount == 0) {
            System.out.println("No students in list");
            return;
        }

        for (int i = 0; i < studentCount; i++) {
            top += this.students[i].getMarks();
        }

        double avg = top / studentCount;

        System.out.println("Average Marks: " + avg);
    }
}

