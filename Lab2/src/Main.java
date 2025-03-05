import java.util.*;

public class Main {
    public static void main(String[] args) {
        Project p1 = new Project("P1");
        Project p2 = new Project("P2");
        Project p3 = new Project("P3");
        Project p4 = new Project("P4");

        List<Project> projects = Arrays.asList(p1, p2, p3, p4);

        Student s1 = new Student("S1", new Project[]{p1, p2});
        Student s2 = new Student("S2", new Project[]{p1, p3});
        Student s3 = new Student("S3", new Project[]{p3, p4});
        Student s4 = new Student("S4", new Project[]{p2, p4});

        List<Student> students = Arrays.asList(s1, s2, s3, s4);

        for (Student s : students) {
            for (Project p : s.getPref()) {
                if (!p.isAssigned()) {
                    s.assignProject(p);
                    break;
                }
            }
        }

        System.out.println("=== Allocation ===");
        for (Student s : students) {
            System.out.println(s);
        }

    }
}
