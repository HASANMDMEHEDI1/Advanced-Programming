/**
 * Abstract class representing a person with a name and date of birth.
 */
public abstract class Person {
    protected String name;
    protected String dob;

    public Person(String name, String dob) {
        this.name = name;
        this.dob = dob;
    }

    public String getName() {
        return name;
    }

    public String getDob() {
        return dob;
    }

    @Override
    public abstract boolean equals(Object o);
}
