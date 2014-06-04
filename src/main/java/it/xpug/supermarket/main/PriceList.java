package it.xpug.supermarket.main;

public interface PriceList {

	int findPrice(String code);

	public class PriceNotFound extends RuntimeException {
	}

}