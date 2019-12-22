package com;

public class Card {
	// 3 - 0 3-黑 2-红 1-梅 0-方
	private int type;
	// 2-16 2-A
	private int count;
	// 用于显示
	private String showCard;

	public Card(int type, int count) {
		this.type = type;
		this.count = count;

		switch (type) {
		case 0:
			showCard = "♦";
			break;
		case 1:
			showCard = "♣";
			break;
		case 2:
			showCard = "♥";
			break;
		case 3:
			showCard = "♠";
			break;
		}

		if (count <= 10) {
			showCard += count;
		} else {
			switch (count) {
			case 11:
				showCard += "J";
				break;
			case 12:
				showCard += "Q";
				break;
			case 13:
				showCard += "K";
				break;
			case 14:
				showCard += "A";
				break;
			case 15:
				showCard = "小王";
				break;
			case 16:
				showCard = "大王";
				break;
			}
		}
	}

	public int getCount() {
		return count;
	}

	public int getType() {
		return type;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return showCard;
	}
}
