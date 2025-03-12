/**
 * Problem class manages students, teachers, and project allocation.
 */
public class Problem {
    private Student[] students;
    private Teacher[] teachers;
    private Project[] projects;

    public Problem(Student[] students, Teacher[] teachers, Project[] projects) {
        this.students = students;
        this.teachers = teachers;
        this.projects = projects;
    }

    /**
     * Returns all people involved in the problem.
     * @return array of Persons
     */
    public Person[] getAllPersons() {
        Person[] persons = new Person[students.length + teachers.length];
        int index = 0;
        for (Student s : students) persons[index++] = s;
        for (Teacher t : teachers) persons[index++] = t;
        return persons;
    }

    /**
     * Simple Greedy Allocation based on student preferences.
     */
    public void allocateProjects() {
        for (Student s : students) {
            for (Project p : s.getPreferences()) {
                if (!p.isAssigned()) {
                    s.assignProject(p);
                    p.markAssigned();
                    break;
                }
            }
        }
    }

    /**
     * Prints current allocation status.
     */
    public void printAllocation() {
        for (Student s : students) {
            System.out.println(s);
        }
    }
}
