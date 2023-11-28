package com.training.pojo;

import java.util.Comparator;
import java.util.Map;
import java.util.Map.Entry;

public class SortByPriceComparator implements Comparator<Map.Entry<Integer, Product>>{

	@Override
	public int compare(Entry<Integer, Product> o1, Entry<Integer, Product> o2) {
		return Double.compare(o1.getValue().getSellingPrice(), o2.getValue().getSellingPrice());
	}

}

