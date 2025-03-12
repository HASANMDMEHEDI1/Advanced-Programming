/**
 * Class representing a Project.
 * Each project is proposed by a teacher.
 */
public class Project {
    private String title;
    private Teacher teacher;
    private boolean assigned = false;

    public Project(String title) {
        this.title = title;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public boolean isAssigned() {
        return assigned;
    }

    public void markAssigned() {
        this.assigned = true;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Project) {
            Project p = (Project) o;
            return this.title.equals(p.title);
        }
        return false;
    }

    @Override
    public String toString() {
        return title;
    }
}
