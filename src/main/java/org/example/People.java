package org.example;

public class People implements Comparable<People> {
    private String fullName;
    private String lastName;

    public People() {
    }

    public People(String fullName, String lastName) {
        this.fullName = fullName;
        this.lastName = lastName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public int compareTo(People o) {
        return this.fullName.compareTo(o.getFullName());
    }
}
