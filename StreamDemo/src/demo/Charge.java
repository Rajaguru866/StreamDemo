package demo;

public class Charge {

	private String charge_id;
	private double amount;
	private boolean status;
	public Charge(String charge_id, double amount, boolean status) {
		this.charge_id = charge_id;
		this.amount = amount;
		this.status = status;
	}
	public String getCharge_id() {
		return charge_id;
	}
	
	public double getAmount() {
		return amount;
	}
	
	public boolean getStatus() {
		return status;
	}
	@Override
	public String toString() {
		return "Charge [charge_id=" + charge_id + ", amount=" + amount + ", status=" + status + "]";
	}
	
	
}
