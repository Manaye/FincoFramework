package interest_strategy;

public class CCSilverMonthlyInterest implements InterestService {
    @Override
    public double calInterest(double balance) {
        //TODO
        return balance*0.08;
    }

    @Override
    public double caclMinPayment(double balance) {
        return balance*0.12;
    }
}
