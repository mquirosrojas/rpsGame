package com.mqr.rsp;

public class ThrowSet {

	private Item[] items;
	private int n;

	public ThrowSet() {
		n = 3;
		items = new Item[n];
		items[0] = new Item(0, "Rock");
		items[1] = new Item(1, "Paper");
		items[2] = new Item(2, "Scissors");
	}

	public Item[] getItems() {
		return items;
	}

	public int getN() {
		return n;
	}

}
