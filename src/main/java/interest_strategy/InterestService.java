package interest_strategy;

public interface InterestService {
    double getMonthlyInterest();
    double getMinimumPayInterest();
    double calInterest(double balance);
    double caclMinPayment(double balance);
}
