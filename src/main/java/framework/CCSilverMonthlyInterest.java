package framework;

public class CCSilverMonthlyInterest implements InterestService {
    private double monthlyInterest = 0.08;
    private double minimumPayInterest = 0.12;

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
    public double getMinimumPayInterest() {
        return minimumPayInterest;
    }

    @Override
    public double getMonthlyInterest() {
        return monthlyInterest;
    }
}
