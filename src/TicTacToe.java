import java.util.Scanner;

public class TicTacToe {
    private static final int ROW = 3;
    private static final int COL = 3;
    private static String[][] board = new String[ROW][COL];
    private static String player = "X";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean playAgain;
        do {
            playGame(scanner);
            playAgain = SafeInput.getYNConfirm(scanner, "Do you want to play again?");
            if (playAgain) {
                clearBoard();
            }
        } while (playAgain);
        System.out.println("Thanks for playing!");
    }

    private static void playGame(Scanner scanner) {
        clearBoard();
        display();
        while (true) {
            int rowMove, colMove;
            do {
                rowMove = SafeInput.getRangedInt(scanner, "Enter row (1-3): ", 1, 3) - 1;
                colMove = SafeInput.getRangedInt(scanner, "Enter column (1-3): ", 1, 3) - 1;
            } while (!isValidMove(rowMove, colMove));
            board[rowMove][colMove] = player;
            display();
            if (isWin(player)) {
                System.out.println(player + " wins!");
                break;
            } else if (isTie()) {
                System.out.println("It's a tie!");
                break;
            }
            player = (player.equals("X")) ? "O" : "X";
        }
    }

    private static void clearBoard() {
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                board[i][j] = " ";
            }
        }
    }

    private static void display() {
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                System.out.print(board[i][j]);
                if (j < COL - 1) {
                    System.out.print(" | ");
                }
            }
            System.out.println();
            if (i < ROW - 1) {
                System.out.println("---------");
            }
        }
    }

    private static boolean isValidMove(int row, int col) {
        if (row < 0 || row >= ROW || col < 0 || col >= COL) {
            System.out.println("Invalid move. Please try again.");
            return false;
        } else if (!board[row][col].equals(" ")) {
            System.out.println("Cell already taken. Please try again.");
            return false;
        }
        return true;
    }

    private static boolean isWin(String player) {
        return isRowWin(player) || isColWin(player) || isDiagonalWin(player);
    }

    private static boolean isRowWin(String player) {
        for (int i = 0; i < ROW; i++) {
            if (board[i][0].equals(player) && board[i][1].equals(player) && board[i][2].equals(player)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isColWin(String player) {
        for (int i = 0; i < COL; i++) {
            if (board[0][i].equals(player) && board[1][i].equals(player) && board[2][i].equals(player)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isDiagonalWin(String player) {
        return (board[0][0].equals(player) && board[1][1].equals(player) && board[2][2].equals(player)) ||
                (board[0][2].equals(player) && board[1][1].equals(player) && board[2][0].equals(player));
    }

    private static boolean isTie() {
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                if (board[i][j].equals(" ")) {
                    return false;
                }
            }
        }
        return true;
    }
}