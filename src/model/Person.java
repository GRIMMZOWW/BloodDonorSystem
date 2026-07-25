package model;

import enums.BloodGroup;

public abstract class Person {

    private String name;
    private int age;
    private BloodGroup bloodGroup;

    // Constructor
    public Person(String name, int age, BloodGroup bloodGroup) {
        this.name = name;
        this.age = age;
        this.bloodGroup = bloodGroup;
    }

    // Getters and setters (encapsulation)
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public BloodGroup getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(BloodGroup bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    // Abstract method - Donor and Recipient will show info differently
    public abstract void display();
}