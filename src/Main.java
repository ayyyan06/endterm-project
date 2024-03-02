import java.util.ArrayList;
import java.util.List;

interface StudentRepository {
    void add(Student student);
    void remove(Student student);
    List<Student> getAll();
}

class Student {
    private String name;
    private int age;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}

class Parents extends Student{

    public Parents(String name, int age) {
        super(name, age);
    }

    void parents(){
        System.out.println("Showing parents...");
    }
}

class MemoryStudentRepository implements StudentRepository {
    private List<Student> students; // Student storage

    public MemoryStudentRepository() {
        students = new ArrayList<>();
    }

    @Override
    public void add(Student student) {
        students.add(student);
    }

    @Override
    public void remove(Student student) {
        students.remove(student);
    }

    @Override
    public List<Student> getAll() {
        return students;
    }
}

class StudentManager {
    private StudentRepository repository; // Repository for working with students

    public StudentManager(StudentRepository repository) {
        this.repository = repository;
    }

    public void addStudent(String name, int age) {
        Student student = new Student(name, age);
        repository.add(student);
        System.out.println("Student " + name + " added.");
    }

    public void removeStudent(String name) {
        List<Student> students = repository.getAll();
        for (Student student : students) {
            if (student.getName().equals(name)) {
                repository.remove(student);
                System.out.println("Student" + name + " removed.");
                return;
            }
        }
        System.out.println("Student " + name + " not found.");
    }

    public void printAllStudents() {
        List<Student> students = repository.getAll();
        for (Student student : students) {
            System.out.println("Name: " + student.getName() + ", Age: " + student.getAge());
        }
    }
}

public class Main {
    public static void main(String[] args) {

        StudentRepository repository = new MemoryStudentRepository();

        StudentManager manager = new StudentManager(repository);
        manager.addStudent("Ayan", 17);
        manager.addStudent("Аlish", 22);
        manager.addStudent("Laura", 21);
        manager.removeStudent("Almat");
        manager.printAllStudents();

        Parents parent = new Parents("Ayan",17);
        parent.parents();
    }
}