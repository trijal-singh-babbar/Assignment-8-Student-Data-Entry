/*
 * Name: Parnika Jain
 * PRN: 23070126087
 * Batch: B1
 */

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        StudentManager manager = new StudentManager();
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n===== Student Data Entry Menu =====");
            System.out.println("1. Add Student");
            System.out.println("2. Display Students");
            System.out.println("3. Search Student");
            System.out.println("4. Update Student");
            System.out.println("5. Delete Student");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            try {
                switch (choice) {
                    case 1 -> manager.addStudent(sc);
                    case 2 -> manager.displayStudents();
                    case 3 -> manager.searchStudent(sc);
                    case 4 -> manager.updateStudent(sc);
                    case 5 -> manager.deleteStudent(sc);
                    case 6 -> System.out.println("Exiting...");
                    default -> System.out.println("Invalid choice.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }

        } while (choice != 6);
    }
}
