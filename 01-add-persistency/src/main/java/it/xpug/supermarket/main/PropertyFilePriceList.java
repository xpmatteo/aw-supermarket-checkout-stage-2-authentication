package it.xpug.supermarket.main;

import java.io.*;
import java.util.*;

public class PropertyFilePriceList implements PriceList {

	private String file;

	public PropertyFilePriceList(String file) {
		this.file = file;
	}

	@Override
	public int findPrice(String code) {
		String property = getProperties().getProperty("price." + code);
		if (null == property) {
			throw new PriceNotFound();
		} else {
			return Integer.valueOf(property);
		}
	}

	private Properties getProperties() {
		try {
			Properties properties = new Properties();
			properties.load(new FileInputStream(file));
			return properties;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
