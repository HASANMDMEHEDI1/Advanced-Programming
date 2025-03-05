public class Project {
    private String name;
    private boolean assigned = false;

    public Project(String name) {
        this.name = name;
    }

    public boolean isAssigned() {
        return assigned;
    }

    public void assign() {
        assigned = true;
    }

    @Override
    public String toString() {
        return name;
    }
}
