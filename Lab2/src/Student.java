import java.util.Arrays;

public class Student {
    private String name;
    private Project[] pref;
    private Project assigned;

    public Student(String name, Project[] pref) {
        this.name = name;
        this.pref = pref;
    }

    public String getName() {
        return name;
    }

    public Project[] getPref() {
        return pref;
    }


    public void assignProject(Project p) {
        this.assigned = p;
        p.assign();
    }


    @Override
    public String toString() {
        return name + " { Preferences: " + Arrays.toString(pref) +
                ", Assigned: " + (assigned != null ? assigned : "None") + " }";
    }
}
