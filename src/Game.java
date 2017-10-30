
public class Game {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		TicTacToe ttt = new TicTacToe();
		ttt.createBoard();

		System.out.println("Below is your starting board.");
		ttt.printBoard();

		do {

			ttt.openPositions();
			ttt.move();
			ttt.printBoard();
			ttt.checkWin();

		} while (ttt.notDone());
		//input.close();
		System.out.println("Game Over.");

	}

}
