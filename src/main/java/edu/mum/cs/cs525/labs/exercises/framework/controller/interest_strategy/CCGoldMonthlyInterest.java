package edu.mum.cs.cs525.labs.exercises.framework.controller.interest_strategy;

public class CCGoldMonthlyInterest implements InterestService {
    @Override
    public double calInterest(double balance) {
        //TODO
        return balance*0.06;
    }

    @Override
    public double caclMinPayment(double balance) {
        return balance*0.10;
    }
}
