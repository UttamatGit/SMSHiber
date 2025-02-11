package Views;


import Controller.StudentController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Scanner;

public class AdminMenu {
    public static void runMenu() {
        StudentController controller = new StudentController();
        Scanner input = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- Student Management System ---");
            System.out.println("1. Add Student");
            System.out.println("2. Update Student");
            System.out.println("3. Delete Student");
            System.out.println("4. View Student");
            System.out.println("5. View All Students");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            choice = input.nextInt();
            input.nextLine(); // Consume newline after nextInt()

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter Name: ");
                    String name = input.nextLine();

                    System.out.print("Enter Course: ");
                    String course = input.nextLine();

                    System.out.print("Enter Date of Birth (dd-MM-yyyy): ");
                    String dobInput = input.nextLine();
                    Date dob = null;

                    try {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                        dob = java.sql.Date.valueOf(LocalDate.parse(dobInput, formatter));
                    } catch (Exception e) {
                        System.out.println("Invalid date format! Please enter in dd-MM-yyyy format.");
                        break;
                    }

                    System.out.print("Enter Email: ");
                    String email = input.nextLine();

                    System.out.print("Enter Phone: ");
                    String phone = input.nextLine();

                    controller.addNewStudent(name, course, dob, email, phone);
                    System.out.println("Student added successfully!");
                }

                case 2 -> {
                    System.out.print("Enter Roll Number: ");
                    int rollNo = input.nextInt();
                    input.nextLine();  // ✅ Consume the leftover newline
                    System.out.print("Enter Email: ");
                    String email = input.nextLine();
                    controller.updateEmail(rollNo, email);
                }
                case 3 -> {
                    System.out.print("Enter Roll Number: ");
                    int rollNo = input.nextInt();
                    controller.deleteStudent(rollNo);
                }
                case 4 -> {
                    System.out.print("Enter Roll Number: ");
                    int rollNo = input.nextInt();
                    controller.viewStudent(rollNo);
                }
                case 5 -> controller.viewAllStudents();
                case 6 -> System.out.println("Exiting...");
                default -> System.out.println("Invalid choice! Please try again.");
            }
        } while (choice != 6);

        input.close();
    }

}
