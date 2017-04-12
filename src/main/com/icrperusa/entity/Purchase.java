/**
 * 
 */
package entity;

/**
 * @author christian
 *
 */
public class Purchase {

	
	private String purchase;
	private double price;
	private double quantity;
	private double discount;
	private double perception;
	
	private double discounttotal;
	
	private double amount;

	public double getDiscounttotal() {
		return discounttotal;
	}

	public void setDiscounttotal(double discounttotal) {
		this.discounttotal = discounttotal;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public double getPerception() {
		return perception;
	}

	public void setPerception(double perception) {
		this.perception = perception;
	}

	public String getPurchase() {
		return purchase;
	}

	public void setPurchase(String purchase) {
		this.purchase = purchase;
	}
	
}
