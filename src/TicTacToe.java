import java.util.Scanner;

/**
 * @author Matt Lawson
 *
 */
public class TicTacToe {
	static char[][] board = new char[3][3];
	static Scanner input = new Scanner(System.in);
	static char player = 'X';
	static char lastMover = 'X';
	static boolean win = false;
	static boolean draw = false;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		createBoard();

		System.out.println("Below is your starting board.");
		printBoard();

		do {
			move();
			printBoard();
			checkWin();

		} while (win == false && draw == false);
		input.close();
		System.out.println("Game Over.");
	}

	public static void printBoard() {
		System.out.println("");
		System.out.println("  0 1 2");
		// System.out.println("");
		System.out.println("0 " + board[0][0] + "|" + board[0][1] + "|" + board[0][2]);
		System.out.println(" -------");
		System.out.println("1 " + board[1][0] + "|" + board[1][1] + "|" + board[1][2]);
		System.out.println(" -------");
		System.out.println("2 " + board[2][0] + "|" + board[2][1] + "|" + board[2][2]);
	} // end printBoard();

	public static void move() {

		System.out.println(
				"Player " + player + ", where would you like to move? Say row and then column between 0 and 2.");
		int xRow = input.nextInt();
		int xColumn = input.nextInt();
		if (xRow > 2 || xColumn > 2) {
			System.out.println("Invalid Move. Row or Column out of bounds.");
			System.out.println(
					"Player " + player + ", where would you like to move? Say row and then column between 0 and 2.");
			xRow = input.nextInt();
			xColumn = input.nextInt();
		} // end check move on board
		if (board[xRow][xColumn] == ' ') {
			board[xRow][xColumn] = player;
			if (player == 'X') { // switching player after move
				player = 'O';
			} else {
				player = 'X';
			} // end change player
		} else {
			System.out.println("Invalid Move. " + board[xRow][xColumn] + " has already gone there!");
		} // end change board
	} // end move();

	public static void createBoard() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				board[i][j] = ' ';
			} // end for j
		} // end for i
	} // end createBoard();

	public static void checkWin() {
		for (int r = 0; r < 3; r++) { // checking for horizontal wins
			if (board[r][0] == board[r][1] && board[r][1] == board[r][2] && board[r][0] != ' ') {
				win = true;
				System.out.println(board[r][0] + " wins!");
				input.close();
			} // end if
		} // end for horizontal
		for (int r = 0; r < 3; r++) { // checking for vertical wins
			if (board[0][r] == board[1][r] && board[1][r] == board[2][r] && board[0][r] != ' ') {
				win = true;
				System.out.println(board[0][r] + " wins!");
				input.close();
			} // end if
		} // end for vertical
		if (board[0][0] == board[1][1] && board[0][0] == board[2][2] && board[0][0] != ' ') { // if diagonal from top
																								// left
			win = true;
			System.out.println(board[0][0] + " wins!");
			input.close();
		} // end if diagonal from top left
		if (board[0][2] == board[1][1] && board[0][2] == board[2][0] && board[0][2] != ' ') { // if diagonal from top
																								// right
			win = true;
			System.out.println(board[0][2] + " wins!");
			input.close();
		} // end if diagonal from top right
		boolean foundSpace = false;
		for (int q = 0; q < 3; q++) {
			for (int f = 0; f < 3; f++) {
				if (board[q][f] == ' ') {
					foundSpace = true;
				}
			}
		}
		if (foundSpace == false && win == false) {
			draw = true;
			System.out.println("The game has ended in a draw!");
		}
	} // end checkWin();
} // end class
