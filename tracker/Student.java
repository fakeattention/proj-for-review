package tracker;

import java.util.HashMap;
import java.util.Map;
/**
 * A class representing a student in the system.
 * Contains information about the student, their ID, courses and grades.
 */
public class Student {
    private String firstName;
    private String lastName;
    private String email;
    private String id;
    private final Map<String, Integer> grades;
    private final Map<String, Integer> submissions;
    private final Map<String, Course> courseMap;

    // Creating student
    public Student(String id, Map<String, Course> courseMap) {
        this.id = id;
        this.courseMap = courseMap;
        this.grades = new HashMap<>();
        this.submissions = new HashMap<>();
        for (CourseType course : CourseType.values()) {
            this.grades.put(course.getName(), 0);
            this.submissions.put(course.getName(), 0);
        }
    }

    public boolean addStudent(String input) {
        String[] entry = input.split(" ");
        if (entry.length < 3) {
            System.out.println("Incorrect credentials.");
            return false;
        }
        if (!Validator.isValidName(entry[0])) {
            System.out.println("Incorrect first name.");
            return false;
        }
        firstName = entry[0];

        StringBuilder lastNameBuilder = new StringBuilder();
        for (int i = 1; i < entry.length - 1; i++) {
            lastNameBuilder.append(entry[i]).append(" ");
        }
        String untrimmedLastName = lastNameBuilder.toString();
        if (!Validator.isValidName(untrimmedLastName.trim())) {
            System.out.println("Incorrect last name.");
            return false;
        }
        lastName = untrimmedLastName.trim();

        if (!Validator.isValidEmail(entry[entry.length - 1])) {
            System.out.println("Incorrect email.");
            return false;
        }
        email = entry[entry.length - 1];
        return true;
    }

    public void sendSubmission(int javaGrade, int dsaGrade, int databasesGrade, int springGrade) {
        updateGradeAndSubmission(CourseType.JAVA.getName(), javaGrade);
        updateGradeAndSubmission(CourseType.DSA.getName(), dsaGrade);
        updateGradeAndSubmission(CourseType.DATABASES.getName(), databasesGrade);
        updateGradeAndSubmission(CourseType.SPRING.getName(), springGrade);
    }

    private void updateGradeAndSubmission(String course, int points) {
        if (points > 0) {
            grades.put(course, grades.get(course) + points);
            if (submissions.get(course) == 0) {
                courseMap.get(course).enrollStudent(this);
            }
            submissions.put(course, submissions.get(course) + 1);
            courseMap.get(course).addEntry(points);
        }
    }

    public void printGrades() {
        String gradesInfo = String.format("%s points: Java=%d; DSA=%d; Databases=%d; Spring=%d",
                id, getJavaGrade(), getDSAGrade(), getDatabasesGrade(), getSpringGrade());
        System.out.println(gradesInfo);
    }

    public String getEmail() {
        return email;
    }

    public String getId() {
        return id;
    }

    public int getSpecifiedGrade(String course) {
        return grades.get(course);
    }

    public int getJavaGrade() {
        return grades.get(CourseType.JAVA.getName());
    }

    public int getDSAGrade() {
        return grades.get(CourseType.DSA.getName());
    }

    public int getDatabasesGrade() {
        return grades.get(CourseType.DATABASES.getName());
    }

    public int getSpringGrade() {
        return grades.get(CourseType.SPRING.getName());
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}