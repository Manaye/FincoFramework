package interest_strategy;

public class CCBronzeMonthlyInterest implements InterestService {
    @Override
    public double calInterest(double balance) {
        //TODO
        return balance*0.01;
    }

    @Override
    public double caclMinPayment(double balance) {
        return balance*0.14;
    }
}
