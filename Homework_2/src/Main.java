
/**
 * Main class to run the problem setup and allocation.
 */
public class Main {
    public static void main(String[] args) {
        Project p1 = new Project("AI");
        Project p2 = new Project("Data Science");
        Project p3 = new Project("Game Design");
        Project p4 = new Project("Cybersecurity");

        Project[] teacher1Projects = {p1, p2};
        Project[] teacher2Projects = {p3, p4};

        Teacher t1 = new Teacher("Prof. Dan", "1975-04-01", teacher1Projects);
        Teacher t2 = new Teacher("Prof. Adrian", "1980-11-15", teacher2Projects);

        Student s1 = new Student("Stefan", "2000-01-01", "S001", new Project[]{p1, p2});
        Student s2 = new Student("Radu", "2000-02-02", "S002", new Project[]{p3, p1});
        Student s3 = new Student("Alin", "2000-03-03", "S003", new Project[]{p4, p3});
        Student s4 = new Student("Popa", "2000-04-04", "S004", new Project[]{p2, p4});

        Project[] allProjects = {p1, p2, p3, p4};
        Student[] allStudents = {s1, s2, s3, s4};
        Teacher[] allTeachers = {t1, t2};

        Problem problem = new Problem(allStudents, allTeachers, allProjects);
        problem.allocateProjects();
        problem.printAllocation();
    }
}
