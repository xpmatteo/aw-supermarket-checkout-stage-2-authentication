package it.xpug.supermarket.main;

public class SupermarketCheckout {

	private int total;
	private PriceList priceList;
	private int id;

	public SupermarketCheckout(Integer id, PriceList priceList) {
		this.id = id;
		this.priceList = priceList;
	}

	public int scan(String code) {
		int price = priceList.findPrice(code);
		this.total += price;
		return price;
	}

	public int total() {
		return total;
	}

	public void setTotal(int newTotal) {
		this.total = newTotal;
	}

	public int id() {
		return id;
	}

}
