package com.tsstu.console.core.entity;

import java.util.HashMap;

public class Condition<K, V> extends HashMap<K, V> {

	private static final long serialVersionUID = 1L;
	
	public Condition<K, V> putVal(K key, V value) {
		this.put(key, value);
		return this;
	}

}
