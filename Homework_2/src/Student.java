/**
 * Student class inheriting from Person.
 * Each student has a registration number and project preferences.
 */
public class Student extends Person {
    private String regNo;
    private Project[] preferences;
    private Project assigned;

    public Student(String name, String dob, String regNo, Project[] preferences) {
        super(name, dob);
        this.regNo = regNo;
        this.preferences = preferences;
    }

    public String getRegNo() {
        return regNo;
    }

    public Project[] getPreferences() {
        return preferences;
    }

    public Project getAssigned() {
        return assigned;
    }

    public void assignProject(Project p) {
        this.assigned = p;
    }

    public boolean isAssigned() {
        return assigned != null;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Student) {
            Student s = (Student) o;
            return this.regNo.equals(s.regNo);
        }
        return false;
    }

    @Override
    public String toString() {
        return name + " (" + regNo + ") assigned: " + (assigned != null ? assigned : "None");
    }
}
