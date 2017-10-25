import java.util.Scanner;
import java.util.Random;

/**
 * @author Matt Lawson
 *
 */
public class TicTacToe {
	static char[][] board = new char[3][3];
	static Scanner input = new Scanner(System.in);
	static char player = 'X';
	static char human = 'X';
	static char cpu = 'O';
	static int humanMoves = 0;
	static int cpuMoves = 0;
	static int totalMoves = 0;
	static int stillOpen = (9 - totalMoves);
	static char lastMover = 'X';
	static boolean win = false;
	static boolean draw = false;
	static int[] freeX = new int[stillOpen + 1];
	static int[] freeY = new int[stillOpen + 1];

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		createBoard();

		System.out.println("Below is your starting board.");
		printBoard();

		do {
			openPositions();
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
		System.out.println("0 " + board[0][0] + "|" + board[0][1] + "|" + board[0][2]);
		System.out.println(" -------");
		System.out.println("1 " + board[1][0] + "|" + board[1][1] + "|" + board[1][2]);
		System.out.println(" -------");
		System.out.println("2 " + board[2][0] + "|" + board[2][1] + "|" + board[2][2]);
	} // end printBoard();

	public static void move() {
		if (player == human) {
			System.out.println(
					"Player " + player + ", where would you like to move? Say row and then column between 0 and 2.");
			int xRow = input.nextInt();
			int xColumn = input.nextInt();
			if (xRow > 2 || xColumn > 2) {
				System.out.println("Invalid Move. Row or Column out of bounds.");
				System.out.println("Player " + player
						+ ", where would you like to move? Say row and then column between 0 and 2.");
				xRow = input.nextInt();
				xColumn = input.nextInt();
			} // end check move on board

			if (board[xRow][xColumn] == ' ') {

				board[xRow][xColumn] = player;
				totalMoves = +1;
				player = 'O';
				humanMoves = +1;

			} else {
				System.out.println("Invalid Move. " + board[xRow][xColumn] + " has already gone there!");
			} // end change board
		} else {
			int decision;
			
			do {
				openPositions();
				decision = cpuRuleChoice();
			} while(board[freeX[decision]][freeY[decision]] != ' ');
			
			board[freeX[decision]][freeY[decision]] = player;
			System.out.println("Board has chosen row: " + freeX[decision] + " and column: " + freeY[decision]);
			player = 'X';
			cpuMoves = +1;
			totalMoves = +1;
		} // end else
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

	public static void openPositions() {
		int j = 0;
		for (int q = 0; q < 3; q++) {
			for (int f = 0; f < 3; f++) {
				if (board[q][f] == ' ') {
					freeX[j] = q;
					freeY[j] = f;
					j++;
				}
			}
		}
	} // end openPositions()

	public static int cpuRandomChoice() {
		Random rand = new Random();
		int choice = rand.nextInt(freeX.length);
		return choice;
	} // end cpuRandomChoice
	
	//public static int[][] cpuPrefferedChoice() {
	//}

	public static int cpuRuleChoice() {
		// rule 1: horizontal
		// currently. rule 2 is being calc (copy and paste for CPU to try and win instead and make 3 more choices)
		int choice1 = -1;
		int choice2 = -1;
		int choice3 = -1;
		int finalChoice = 0;
		for (int i = 0; i < freeX.length - 1; i++) {
			if ((board[freeX[i]][0] == board[freeX[i]][1] && board[freeX[i]][0] == human) || (board[freeX[i]][0] == board[freeX[i]][2]  && board[freeX[i]][0] == human) || (board[freeX[i]][1] == board[freeX[i]][2] && board[freeX[i]][1] == human)) {
				choice1 = i;
			}
		} // end for
		// rule 1: vertical
		for (int i = 0; i < freeY.length - 1; i++) {
			if ((board[0][freeY[i]] == board[1][freeY[i]] && board[0][freeY[i]] == human)|| (board[0][freeY[i]] == board[2][freeY[i]] && board[0][freeY[i]] == human)|| (board[1][freeY[i]] == board[1][freeY[2]] && board[1][freeY[i]] == human)) {
				choice2 = i;
			}
		} // end for
		// rule 1: diagonal from top left
		for (int i = 0; i < freeY.length - 1; i++) {
			if ((board[0][0] == board[1][1] && freeX[i] == 2 && freeY[i] == 2 && board[1][1] == human) || (board[1][1] == board[2][2] && freeX[i] == 0 && freeY[i] == 0 && board[1][1] == human) || (board[0][0] == board[2][2] && freeX[i] == 1 && freeY[i] == 1 && board[0][0] == human)) {
				choice3 = i;
			}
		} // end for
		// rule 1: diagonal from top right
		for (int i = 0; i < freeY.length - 1; i++) {
			if ((board[0][2] == board[1][1] && freeX[i] == 2 && freeY[i] == 0 && board[1][1] == human) || (board[1][1] == board[2][0] && freeX[i] == 0 && freeY[i] == 2 && board[1][1] == human) || (board[0][2] == board[2][0] && freeX[i] == 1 && freeY[i] == 1 && board[0][2] == human)) {
				choice3 = i;
			}
		} // end for
		// nest the ifs to prioritize choice to win (real choice 1) 
		if (choice1 > -1) {
			finalChoice = choice1;
			System.out.println("Choice 1 :" + freeX[choice1] + freeY[choice1] + ":: " + choice1);
		} else if(choice2 > -1) {
			finalChoice =  choice2;
			System.out.println("Choice 2: " + freeX[choice2] + freeY[choice2] + ":: " + choice2);
		} else if(choice3 > -1) {
			finalChoice =  choice3;
			System.out.println("Choice 3: " + freeX[choice3] + freeY[choice3] + ":: " + choice3);
		} else {
			finalChoice = cpuRandomChoice();
		}
		return finalChoice;
	} // end Rule choice
	
} // end class
