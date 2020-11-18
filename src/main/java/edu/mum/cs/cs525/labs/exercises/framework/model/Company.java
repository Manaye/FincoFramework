package edu.mum.cs.cs525.labs.exercises.framework.model;

public class Company extends Customer {
    private int numOfEmployee;

    public Company(String name, int numOfEmployee) {
        super(name);
        this.numOfEmployee = numOfEmployee;
    }

    public int getNumOfEmployee() {
        return numOfEmployee;
    }

    public void setNumOfEmployee(int numOfEmployee) {
        this.numOfEmployee = numOfEmployee;
    }
}
