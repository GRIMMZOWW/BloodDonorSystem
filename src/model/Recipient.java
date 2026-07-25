package model;

import enums.BloodGroup;
import enums.Urgency;

public class Recipient extends Person {

    private Urgency urgency;
    private boolean fulfilled;

    // constructor - recipient needs urgency level too, starts as not fulfilled
    public Recipient(String name, int age, BloodGroup bloodGroup, Urgency urgency) {
        super(name, age, bloodGroup);
        this.urgency = urgency;
        this.fulfilled = false;
    }

    public Urgency getUrgency() {
        return urgency;
    }

    public boolean isFulfilled() {
        return fulfilled;
    }

    public void setFulfilled(boolean fulfilled) {
        this.fulfilled = fulfilled;
    }

    // recipient's own version of display() - shows recipient-specific info
    @Override
    public void display() {
        System.out.println("Recipient: " + getName() + " | Age: " + getAge() +
                " | Blood Group Needed: " + getBloodGroup().getLabel() +
                " | Urgency: " + urgency +
                " | Fulfilled: " + (fulfilled ? "Yes" : "No"));
    }

    @Override
    public String toString() {
        return getName() + " (" + getBloodGroup().getLabel() + ", " + urgency + ")";
    }
}