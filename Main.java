import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Abstract class representing a university member (Abstraction)
abstract class UniversityMember {
    private String name; // Encapsulation: Private attribute
    private int id; // Encapsulation: Private attribute

    public UniversityMember(String name, int id) {
        this.name = name;
        this.id = id;
    }

    // Abstract method to get details (Abstraction)
    public abstract String getDetails();

    public String getName() {
        return name; // Getter method for encapsulated attribute
    }

    public int getId() {
        return id; // Getter method for encapsulated attribute
    }
}

// Class representing a student (Inheritance from UniversityMember)
class Student extends UniversityMember {
    private String course; // Encapsulation: Private attribute

    public Student(String name, int id, String course) {
        super(name, id); // Calling the constructor of the superclass (Inheritance)
        this.course = course;
    }

    // Implementation of the abstract method (Polymorphism)
    @Override
    public String getDetails() {
        return "Student ID: " + getId() + "\n" +
               "Student Name: " + getName() + "\n" +
               "Course: " + course;
    }

    public String getCourse() {
        return course; // Getter method for encapsulated attribute
    }
}

// Class representing a teacher (Inheritance from UniversityMember)
class Teacher extends UniversityMember {
    private String subject; // Encapsulation: Private attribute

    public Teacher(String name, int id, String subject) {
        super(name, id); // Calling the constructor of the superclass (Inheritance)
        this.subject = subject;
    }

    // Implementation of the abstract method (Polymorphism)
    @Override
    public String getDetails() {
        return "Teacher ID: " + getId() + "\n" +
               "Teacher Name: " + getName() + "\n" +
               "Subject: " + subject;
    }
}

// Class representing a course
class Course {
    private String courseName; // Encapsulation: Private attribute
    private List<Student> enrolledStudents; // Encapsulation: Private attribute

    public Course(String courseName) {
        this.courseName = courseName;
        this.enrolledStudents = new ArrayList<>();
    }

    public void enrollStudent(Student student) {
        enrolledStudents.add(student); // Adding student to the list
        System.out.println("Student " + student.getName() + " enrolled in " + courseName);
    }

    public String getCourseName() {
        return courseName; // Getter method for encapsulated attribute
    }

    public void displayEnrolledStudents() {
        System.out.println("Enrolled Students in " + courseName + ":");
        if (enrolledStudents.isEmpty()) {
            System.out.println("No students enrolled in this course.");
        } else {
            for (Student student : enrolledStudents) {
                System.out.println(student.getName()); // Display each student's name
            }
        }
    }
}

// Class responsible for user interaction
class GU {
    private List<UniversityMember> members; // Encapsulation: Private attribute
    private List<Course> courses; // Encapsulation: Private attribute
    private Scanner scanner; // Encapsulation: Private attribute

    public GU() {
        members = new ArrayList<>(); // Initializing the list of members
        courses = new ArrayList<>(); // Initializing the list of courses
        scanner = new Scanner(System.in); // Initializing the scanner for user input
    }

    // Method to add a student
    public void addStudent() {
        System.out.print("Enter Student Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Student ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Clear the buffer
        System.out.print("Enter Course: ");
        String course = scanner.nextLine();

        Student student = new Student(name, id, course); // Creating a new Student object
        members.add(student); // Adding the student to the list of members
        System.out.println("Student added successfully!\n");
    }

    // Method to add a teacher
    public void addTeacher() {
        System.out.print("Enter Teacher Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Teacher ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Clear the buffer
        System.out.print("Enter Subject: ");
        String subject = scanner.nextLine();

        Teacher teacher = new Teacher(name, id, subject); // Creating a new Teacher object
        members.add(teacher); // Adding the teacher to the list of members
        System.out.println("Teacher added successfully!\n");
    }

    // Method to add a course
    public void addCourse() {
        System.out.print("Enter Course Name: ");
        String courseName = scanner.nextLine();
        Course course = new Course(courseName); // Creating a new Course object
        courses.add(course); // Adding the course to the list of courses
        System.out.println("Course " + courseName + " added successfully!\n");
    }

    // Method to enroll a student in a course
    public void enrollStudentInCourse() {
        System.out.print("Enter Student ID to enroll: ");
        int studentId = scanner.nextInt();
        scanner.nextLine(); // Clear the buffer

        Student student = findStudentById(studentId); // Finding the student by ID
        if (student == null) {
            System.out.println("Student not found!\n");
            return;
        }

        System.out.print("Enter Course Name to enroll in: ");
        String courseName = scanner.nextLine();

        Course course = findCourseByName(courseName); // Finding the course by name
        if (course != null) {
            course.enrollStudent(student); // Enrolling the student in the course
        } else {
            System.out.println("Course not found!\n");
        }
    }

    // Method to display member details
    public void displayMember() {
        System.out.print("Enter ID of the member to display: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Clear the buffer

        UniversityMember member = findMemberById(id); // Finding the member by ID
        if (member != null) {
            System.out.println(member.getDetails()); // Displaying the member's details
        } else {
            System.out.println("Member not found!\n");
        }
    }

    // Method to display enrolled students in a course
    public void displayEnrolledStudentsInCourse() {
        System.out.print("Enter Course Name to display enrolled students: ");
        String courseName = scanner.nextLine();
        
        Course course = findCourseByName(courseName); // Finding the course by name
        if (course != null) {
            course.displayEnrolledStudents(); // Displaying enrolled students in the course
        } else {
            System.out.println("Course not found!\n");
        }
    }

    // Helper method to find a member by ID
    private UniversityMember findMemberById(int id) {
        for (UniversityMember member : members) {
            if (member.getId() == id) {
                return member; // Returning the found member
            }
        }
        return null; // Returning null if no member found
    }

    // Helper method to find a student by ID
    private Student findStudentById(int id) {
        for (UniversityMember member : members) {
            if (member instanceof Student && member.getId() == id) { // Type checking for Student
                return (Student) member; // Returning the found student
            }
        }
        return null; // Returning null if no student found
    }

    // Helper method to find a course by name
    private Course findCourseByName(String courseName) {
        for (Course course : courses) {
            if (course.getCourseName().equalsIgnoreCase(courseName)) {
                return course; // Returning the found course
            }
        }
        return null; // Returning null if no course found
    }

    // Method to display the main menu and handle user interactions
    public void displayMenu() {
        while (true) {
            System.out.println("==== Welcoem Goa University Management System ====");
            System.out.println("1. Add Student");
            System.out.println("2. Add Teacher");
            System.out.println("3. Add Course");
            System.out.println("4. Enroll Student in Course");
            System.out.println("5. Display Member Details");
            System.out.println("6. Display Enrolled Students in Course");
            System.out.println("7. Exit");
            System.out.print("Choose an option (1-7): ");

            String choice = scanner.nextLine();

            // Input validation
            if (!choice.matches("[1-7]")) {
                System.out.println("Invalid choice. Please select a number between 1 and 7.\n");
                continue;
            }

            // Handling user choices
            switch (Integer.parseInt(choice)) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    addTeacher();
                    break;
                case 3:
                    addCourse();
                    break;
                case 4:
                    enrollStudentInCourse();
                    break;
                case 5:
                    displayMember();
                    break;
                case 6:
                    displayEnrolledStudentsInCourse();
                    break;
                case 7:
                    System.out.println("Exiting the system. Goodbye!");
                    return; // Exit the loop
            }
        }
    }
}

// Entry point for the application
public class Main {
    public static void main(String[] args) {
        GU ums = new GU(); // Creating an instance of the management system
        ums.displayMenu(); // Displaying the menu for user interaction
    }
}
