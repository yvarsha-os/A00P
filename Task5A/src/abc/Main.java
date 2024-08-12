package abc;

import java.util.HashSet;
import java.util.Set;

// Interface for Student
interface Student {
    String getName();
    String getId();
    void enrollInCourse(Course course);
}

// Interface for Course
interface Course {
    String getCourseName();
    String getCourseId();
    void addStudent(Student student);
    void removeStudent(Student student);
}

// Interface for Enrollment Manager
interface EnrollmentManager {
    void enrollStudentInCourse(Student student, Course course);
    void withdrawStudentFromCourse(Student student, Course course);
}

// Concrete implementation of Student
class StudentImpl implements Student {
    private String name;
    private String id;
    private Set<Course> enrolledCourses;

    public StudentImpl(String name, String id) {
        this.name = name;
        this.id = id;
        this.enrolledCourses = new HashSet<>();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void enrollInCourse(Course course) {
        enrolledCourses.add(course);
        course.addStudent(this);
    }
}

// Concrete implementation of Course
class CourseImpl implements Course {
    private String courseName;
    private String courseId;
    private Set<Student> enrolledStudents;

    public CourseImpl(String courseName, String courseId) {
        this.courseName = courseName;
        this.courseId = courseId;
        this.enrolledStudents = new HashSet<>();
    }

    @Override
    public String getCourseName() {
        return courseName;
    }

    @Override
    public String getCourseId() {
        return courseId;
    }

    @Override
    public void addStudent(Student student) {
        enrolledStudents.add(student);
    }

    @Override
    public void removeStudent(Student student) {
        enrolledStudents.remove(student);
    }
}

// Concrete implementation of Enrollment Manager
class EnrollmentManagerImpl implements EnrollmentManager {
    @Override
    public void enrollStudentInCourse(Student student, Course course) {
        student.enrollInCourse(course);
    }

    @Override
    public void withdrawStudentFromCourse(Student student, Course course) {
        course.removeStudent(student);
    }
}

// Main class to demonstrate the system
public class Main {
    public static void main(String[] args) {
        // Create instances of students and courses
        Student student1 = new StudentImpl("Alice", "S001");
        Student student2 = new StudentImpl("Bob", "S002");

        Course course1 = new CourseImpl("Mathematics", "C001");
        Course course2 = new CourseImpl("Physics", "C002");

        // Create the Enrollment Manager
        EnrollmentManager enrollmentManager = new EnrollmentManagerImpl();

        // Enroll students in courses
        enrollmentManager.enrollStudentInCourse(student1, course1);
        enrollmentManager.enrollStudentInCourse(student2, course2);

        // Withdraw a student from a course
        enrollmentManager.withdrawStudentFromCourse(student1, course1);
    }
}
