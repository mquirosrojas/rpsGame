package com.mqr.rsp;

public class GameResult {

	Item userItem = new Item();
	Item machineItem = new Item();
	int resultCode;

	public Item getUserItem() {
		return userItem;
	}

	public void setUserItem(Item userItem) {
		this.userItem = userItem;
	}

	public Item getMachineItem() {
		return machineItem;
	}

	public void setMachineItem(Item machineItem) {
		this.machineItem = machineItem;
	}

	public int getResultCode() {
		return resultCode;
	}

	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}

}
