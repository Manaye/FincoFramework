package framework;

public class CCBronzeMonthlyInterest implements InterestService {
    private double monthlyInterest = 0.01;
    private double minimumPayInterest = 0.14;

    @Override
    public double calInterest(double balance) {
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
