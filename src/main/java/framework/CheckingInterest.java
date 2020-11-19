package framework;

public class CheckingInterest implements InterestStrategy{

	public CheckingInterest() {
		
	}
	
	public double calInterest(double balance) {
		// TODO Auto-generated method stub
		return balance * 0.01;
	}

	
}
