/**
 * Teacher class inheriting from Person.
 * Each teacher proposes an array of projects.
 */
public class Teacher extends Person {
    private Project[] proposedProjects;

    public Teacher(String name, String dob, Project[] proposedProjects) {
        super(name, dob);
        this.proposedProjects = proposedProjects;
        for (Project p : proposedProjects) {
            p.setTeacher(this);
        }
    }

    public Project[] getProposedProjects() {
        return proposedProjects;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Teacher) {
            Teacher t = (Teacher) o;
            return this.name.equals(t.name) && this.dob.equals(t.dob);
        }
        return false;
    }

    @Override
    public String toString() {
        return "Teacher: " + name;
    }
}