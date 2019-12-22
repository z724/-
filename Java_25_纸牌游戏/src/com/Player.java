package com;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Player {
	// 玩家信息
	private String name;
	// 玩家的手牌
	private List<Card> cards;
	// 保存玩家手牌的等级
	private int gread;

	public Player(String name) {
		super();
		this.name = name;
		this.cards = new ArrayList<Card>();
	}

	public void receiveCard(Card card) {
		cards.add(card);
	}

	public String getName() {
		return name;
	}

	public int getGread() {
		return gread;
	}

	public void setGread(int gread) {
		this.gread = gread;
	}

	public Card getCard(int i) {
		return cards.get(i - 1);
	}
	
	public void clearCard() {
		cards.clear();
	}

	// 排序手牌
	public void sortCards() {
		cards.sort(new Comparator<Card>() {
			@Override
			public int compare(Card c1, Card c2) {
				// TODO Auto-generated method stub
				return c1.getCount() - c2.getCount();
			}
		});
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.name + "[" + gread + "]:" + this.cards;
	}

}
