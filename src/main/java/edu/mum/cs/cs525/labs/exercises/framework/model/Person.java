package edu.mum.cs.cs525.labs.exercises.framework.model;

import java.time.LocalDate;

public class Person extends Customer {
    private LocalDate birthDay;

    public Person(String name, LocalDate birthDay) {
        super(name);
        this.birthDay = birthDay;
    }

    public LocalDate getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(LocalDate birthDay) {
        this.birthDay = birthDay;
    }
}
