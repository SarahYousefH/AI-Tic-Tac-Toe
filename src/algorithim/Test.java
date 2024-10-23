package algorithim;

import java.util.Scanner;

public class Test {

	public static void main(String[] args) {
		Game game = new Game(1);
			 
			game.addPlayer(new Player("ahmad", 'X'), true);
		 	game.addPlayer(new Player("notahmad", 'O'), false);
		 	
		 	/*
		 	Scanner scan= new Scanner(System.in);
		 	
		 	while(game.isRoundFinished()==false) {
		 		game.play(scan.nextInt());
		 		game.printBoard();
		 		
		 	}
		 	scan.close();
		 	*/
		 	//game.printBoard();
		 	//System.out.println("done");

		 	
		 	
		 	/*int y=game.getBestMove();
		 	game.play(y);
		 	System.out.println("I will play"+y);
		 	System.out.println(game.message);

		 	//game.printBoard();*/

		 	game.play(0);
		 	
		 	game.play(1);
		 	//System.out.println(game.isRoundFinished());
		 	//game.printBoard();
		 	
		 	
		 	game.play(2);
		 	//System.out.println(game.isRoundFinished());
		 	//game.printBoard();
		 	
		 	game.play(3);
		 	//System.out.println(game.isRoundFinished());
		 	//game.printBoard();
		 	
		 	game.play(7);
		 	//System.out.println(game.isRoundFinished());
		 	//game.printBoard();
		 	
		 	//game.play(7);
		 	
		 	game.play(4);
		 	//System.out.println(game.isRoundFinished());
		 	System.out.println("done1:");
		 	game.printBoard();
		 	
		 	
		 	
		 	
		 	System.out.println(game.getCurrentPlayer().getSymbol());
		 	int x=game.getBestMove();
		 	System.out.println(x);	
		 	game.play(x);
		 	System.out.println("done2:\n"+ game.getMessage());
		 	game.printBoard();
		 	System.out.println("*****************************");
		 	
		 	/*System.out.println(game.currentPlayer.getSymbol());
		 	System.out.println(game.getBestMove());	
		 	game.play(game.getBestMove());
		 	System.out.println("done3:");
		 	game.printBoard();
		 	
		 	System.out.println("*****************************");
		 	
		 	System.out.println(game.currentPlayer.getSymbol());
		 	System.out.println(game.getBestMove());	
		 	game.play(game.getBestMove());
		 	System.out.println("done4:");
		 	game.printBoard();
		 	*/
		 	
		 	//System.out.println("done3");
		 	//System.out.println(game.isRoundFinished());
		 	//game.printBoard();
		 	
		 	
		 	//game.play(0);
		 	/*
		 	System.out.println(game.isRoundFinished());
		 	game.printBoard();*/
	}

}
