package com;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameSystem {
	private Cards cards;
	private List<Player> players;

	public GameSystem() {
		// 创建扑克牌
		cards = new Cards(false);
	}


	public void startGame(Player... players) {
		if (players.length < 2 || players.length > 8) {
			System.out.println("玩家人数不合法！");
			return;
		}
		// 把玩家保存到全局，方便后期使用
		this.players = new ArrayList<Player>(Arrays.asList(players));
		for (Player p : this.players) {
			p.clearCard();
		}
		// 洗牌
		cards.shuffle();
		// 发牌
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < this.players.size(); j++) {
				this.players.get(j).receiveCard(cards.dealCard());
			}
		}
		// 看牌(判断自己的等级)
		setAllPlayerGread();
		for (Player p : this.players) {
			System.out.println(p);
		}
		// 比大小
		getResult();
	}

	// 比较大小判断输赢
	public void getResult() {
		List<Player> maxPlayer = new ArrayList<Player>();
		// 假设玩家一为最大
		maxPlayer.add(players.get(0));
		// 找出最大值
		for (int i = 1; i < this.players.size(); i++) {
			Player p = this.players.get(i);
			if (p.getGread() > maxPlayer.get(0).getGread()) {
				maxPlayer.clear();
				maxPlayer.add(p);
			} else if (p.getGread() == maxPlayer.get(0).getGread()) {
				maxPlayer.add(p);
			}
		}
		if (maxPlayer.size() == 1) {
			System.out.println("恭喜:" + maxPlayer.get(0) + "玩家获得胜利！奖励女/男朋友一个");
			return;
		}
		// 更新未淘汰的玩家
		players.clear();
		players.addAll(maxPlayer);
		// 重置中间参数
		maxPlayer.clear();
		maxPlayer.add(players.get(0));
		// 进行后续比较
		if (players.get(0).getGread() == 5 || players.get(0).getGread() == 4 || players.get(0).getGread() == 2) {
			for (int i = 1; i < this.players.size(); i++) {
				Player p = this.players.get(i);
				if (p.getCard(2).getCount() > maxPlayer.get(0).getCard(2).getCount()) {
					maxPlayer.clear();
					maxPlayer.add(p);
				} else if (p.getGread() == maxPlayer.get(0).getGread()) {
					maxPlayer.add(p);
				}
			}
		} else if (players.get(0).getGread() == 3 || players.get(0).getGread() == 0) {
			for (int i = 1; i < this.players.size(); i++) {
				// 取出玩家的牌
				Card c1 = this.players.get(i).getCard(1);
				Card c2 = this.players.get(i).getCard(2);
				Card c3 = this.players.get(i).getCard(3);
				// 取出最大值得牌
				Card m1 = maxPlayer.get(0).getCard(1);
				Card m2 = maxPlayer.get(0).getCard(2);
				Card m3 = maxPlayer.get(0).getCard(3);
				if (c3.getCount() > m3.getCount()) {
					maxPlayer.clear();
					maxPlayer.add(this.players.get(i));
				} else if (c3.getCount() == m3.getCount()) {
					if (c2.getCount() > m2.getCount()) {
						maxPlayer.clear();
						maxPlayer.add(this.players.get(i));
					} else if (c2.getCount() == m2.getCount()) {
						if (c1.getCount() > m1.getCount()) {
							maxPlayer.clear();
							maxPlayer.add(this.players.get(i));
						} else if (c1.getCount() == m1.getCount()) {
							maxPlayer.add(this.players.get(i));
						}
					}
				}
			}
		} else {
			for (int i = 1; i < this.players.size(); i++) {
				// 取出玩家的牌
				Card c1 = this.players.get(i).getCard(1);
				Card c2 = this.players.get(i).getCard(2);
				Card c3 = this.players.get(i).getCard(3);
				// 取出最大值得牌
				Card m1 = maxPlayer.get(0).getCard(1);
				Card m2 = maxPlayer.get(0).getCard(2);
				Card m3 = maxPlayer.get(0).getCard(3);

				if (c2.getCount() > m2.getCount()) {
					maxPlayer.clear();
					maxPlayer.add(this.players.get(i));
				} else if (c2.getCount() == m2.getCount()) {
					if (c3.getCount() > m3.getCount()) {
						maxPlayer.clear();
						maxPlayer.add(this.players.get(i));
					} else if (c3.getCount() == m3.getCount()) {
						if (c1.getCount() > m1.getCount()) {
							maxPlayer.clear();
							maxPlayer.add(this.players.get(i));
						} else if (c1.getCount() == m1.getCount()) {
							maxPlayer.add(this.players.get(i));
						}
					}
				}
			}
		}
		for (Player player : maxPlayer) {
			System.out.println("恭喜:" + player + "玩家获得胜利！奖励女/男朋友一个");
		}
	}

	// 判断每个玩家的手牌等级
	private void setAllPlayerGread() {
		for (Player player : players) {
			// 把玩家手牌进行排序
			player.sortCards();
			// 得到玩家的三张手牌
			Card c1 = player.getCard(1);
			Card c2 = player.getCard(2);
			Card c3 = player.getCard(3);

			if (c1.getCount() == c3.getCount()) {
				player.setGread(5);
			} else if ((c1.getType() == c2.getType() && c2.getType() == c3.getType() && c3.getType() == c1.getType())
					&& ((c2.getCount() - c1.getCount() == 1 && c3.getCount() - c2.getCount() == 1)
							|| (c1.getCount() == 2 && c2.getCount() == 3 && c3.getCount() == 14))) {
				player.setGread(4);
			} else if (c1.getType() == c2.getType() && c2.getType() == c3.getType() && c3.getType() == c1.getType()) {
				player.setGread(3);
			} else if ((c2.getCount() - c1.getCount() == 1 && c3.getCount() - c2.getCount() == 1)
					|| (c1.getCount() == 2 && c2.getCount() == 3 && c3.getCount() == 14)) {
				player.setGread(2);
			} else if (c1.getCount() == c2.getCount() || c2.getCount() == c3.getCount()) {
				player.setGread(1);
			} else {
				player.setGread(0);
			}
		}
	}
}
