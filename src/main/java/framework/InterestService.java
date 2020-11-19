package framework;

public interface InterestService {
    double getMonthlyInterest();
    double getMinimumPayInterest();
    double calInterest(double balance);
    double caclMinPayment(double balance);
}
