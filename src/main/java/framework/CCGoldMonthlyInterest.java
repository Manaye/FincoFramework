package framework;

public class CCGoldMonthlyInterest implements InterestService {
    private double monthlyInterest = 0.06;
    private double minimumPayInterest = 0.10;

    @Override
    public double calInterest(double balance) {
        //TODO
        return balance*monthlyInterest;
    }

    @Override
    public double caclMinPayment(double balance) {
        return balance*minimumPayInterest;
    }

    @Override
    public double getMonthlyInterest() {
        return monthlyInterest;
    }

    @Override
    public double getMinimumPayInterest() {
        return minimumPayInterest;
    }
}
