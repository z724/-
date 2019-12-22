package com;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Cards {
	private List<Card> cards;
	private int index;
	
	public Cards() {
		this(true);
	}
	
	public Cards(boolean full) {
		cards = new LinkedList<Card>();
		for (int i = 0; i < 4; i++) {
			for (int j = 2; j < 15; j++) {
				cards.add(new Card(i, j));
			}
		}
		if(full) {
			cards.add(new Card(0, 15));
			cards.add(new Card(0, 16));
		}
	}
	
	//洗牌
	public void shuffle() {
		index = 0;
		Random random = new Random();
		for (int i = 0; i < 600; i++) {
			Card card = cards.remove(random.nextInt(52));
			cards.add(card);
		}
	}
	
	//发牌
	public Card dealCard() {
		if(index >= cards.size()) {
			System.out.println("没有牌了，小哥！");
			return null;
		}
		return cards.get(index++);
	}
	
	public void showCards() {
		for (int i = 1; i <= cards.size(); i++) {
			System.out.print(cards.get(i-1) + " ");
			if(i % 13 == 0) {
				System.out.println();
			}
		}
	}
}
