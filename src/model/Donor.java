package model;

import enums.BloodGroup;
import java.time.LocalDate;

public class Donor extends Person {

    private LocalDate lastDonationDate;
    private boolean available;

    // Constructor overloading - one simple, one full
    public Donor(String name, int age, BloodGroup bloodGroup) {
        super(name, age, bloodGroup);
        this.lastDonationDate = null;
        this.available = true;
    }

    public Donor(String name, int age, BloodGroup bloodGroup, LocalDate lastDonationDate) {
        super(name, age, bloodGroup);
        this.lastDonationDate = lastDonationDate;
        this.available = true;
    }

    public LocalDate getLastDonationDate() {
        return lastDonationDate;
    }

    public void setLastDonationDate(LocalDate date) {
        this.lastDonationDate = date;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    // Overridden abstract method - Donor's own version
    @Override
    public void display() {
        System.out.println("Donor: " + getName() + " | Age: " + getAge() +
                " | Blood Group: " + getBloodGroup().getLabel() +
                " | Last Donated: " + (lastDonationDate == null ? "Never" : lastDonationDate) +
                " | Available: " + (available ? "Yes" : "No"));
    }

    @Override
    public String toString() {
        return getName() + " (" + getBloodGroup().getLabel() + ")";
    }
}	