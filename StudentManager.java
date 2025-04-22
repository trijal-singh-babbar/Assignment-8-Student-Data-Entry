import java.util.ArrayList;
import java.util.Scanner;
import exceptions.*;

public class StudentManager {
    private ArrayList<Student> studentList = new ArrayList<>();

    public void addStudent(Scanner sc) throws DuplicateStudentException, EmptyFieldException {
        System.out.print("Enter PRN: ");
        String prn = sc.nextLine().trim();
        if (prn.isEmpty()) throw new EmptyFieldException("PRN cannot be empty.");

        for (Student s : studentList) {
            if (s.getPrn().equals(prn)) throw new DuplicateStudentException("Student with PRN already exists.");
        }

        System.out.print("Enter Name: ");
        String name = sc.nextLine().trim();
        System.out.print("Enter DOB: ");
        String dob = sc.nextLine().trim();
        System.out.print("Enter Marks: ");
        double marks = sc.nextDouble();
        sc.nextLine(); // consume newline

        if (name.isEmpty() || dob.isEmpty())
            throw new EmptyFieldException("Name and DOB cannot be empty.");

        studentList.add(new Student(prn, name, dob, marks));
        System.out.println("Student added successfully.");
    }

    public void displayStudents() throws InvalidInputException {
        if (studentList.isEmpty())
            throw new InvalidInputException("No student records available.");
        for (Student s : studentList) {
            System.out.println(s);
        }
    }

    public void searchStudent(Scanner sc) throws StudentNotFoundException, InvalidInputException {
        System.out.println("Search by:\n1. PRN\n2. Name\n3. Position");
        int option = sc.nextInt();
        sc.nextLine(); // consume newline
        boolean found = false;

        switch (option) {
            case 1 -> {
                System.out.print("Enter PRN to search: ");
                String prn = sc.nextLine().trim();
                for (Student s : studentList) {
                    if (s.getPrn().equals(prn)) {
                        System.out.println(s);
                        found = true;
                        break;
                    }
                }
            }
            case 2 -> {
                System.out.print("Enter Name to search: ");
                String name = sc.nextLine().trim();
                for (Student s : studentList) {
                    if (s.toString().contains(name)) {
                        System.out.println(s);
                        found = true;
                    }
                }
            }
            case 3 -> {
                System.out.print("Enter Position (Index starts from 0): ");
                int pos = sc.nextInt();
                if (pos < 0 || pos >= studentList.size())
                    throw new InvalidInputException("Invalid position entered.");
                System.out.println(studentList.get(pos));
                found = true;
            }
            default -> throw new InvalidInputException("Invalid option selected.");
        }

        if (!found) throw new StudentNotFoundException("Student not found.");
    }

    public void updateStudent(Scanner sc) throws StudentNotFoundException, EmptyFieldException {
        System.out.print("Enter PRN of student to update: ");
        String prn = sc.nextLine().trim();
        for (Student s : studentList) {
            if (s.getPrn().equals(prn)) {
                System.out.print("Enter new Name: ");
                String name = sc.nextLine().trim();
                System.out.print("Enter new DOB: ");
                String dob = sc.nextLine().trim();
                System.out.print("Enter new Marks: ");
                double marks = sc.nextDouble();
                sc.nextLine();

                if (name.isEmpty() || dob.isEmpty())
                    throw new EmptyFieldException("Fields cannot be empty during update.");

                s.setName(name);
                s.setDob(dob);
                s.setMarks(marks);
                System.out.println("Student record updated.");
                return;
            }
        }
        throw new StudentNotFoundException("Student with PRN not found.");
    }

    public void deleteStudent(Scanner sc) throws StudentNotFoundException {
        System.out.print("Enter PRN of student to delete: ");
        String prn = sc.nextLine().trim();
        for (Student s : studentList) {
            if (s.getPrn().equals(prn)) {
                studentList.remove(s);
                System.out.println("Student record deleted.");
                return;
            }
        }
        throw new StudentNotFoundException("Student with PRN not found.");
    }
}
