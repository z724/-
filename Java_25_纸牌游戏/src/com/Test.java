package com;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GameSystem gs = new GameSystem();

		Player p1 = new Player("诸葛亮");
		Player p2 = new Player("刘备");
		Player p3 = new Player("张飞");
		Player p4 = new Player("关羽");
		Player p5 = new Player("赵云");
		Player p6 = new Player("马超");
		Player p7 = new Player("貂蝉");
		Player p8 = new Player("大乔");

		for (int i = 0; i < 100; i++) {
			System.out.println();
			gs.startGame(p1, p2, p3, p4, p5, p6, p7, p8);
			System.out.println();
		}

	}
}
