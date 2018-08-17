package com.ugurhicyilmam.metro_tic_tac_toe;

import java.util.Scanner;

public class View {
    private Engine gameEngine;
    private Config config;
    private Scanner scanner;

    public View(Engine engine, Config config) {
        this.gameEngine = engine;
        this.scanner = new Scanner(System.in);
        this.config = config;
    }

    public void runGame() {
        System.out.println("Game has started");
        while (this.gameEngine.getStatus() == GameStatus.RUNNING) {
            int currentPlayer = this.gameEngine.getCurrentPlayer();
            if (this.gameEngine.isAI()) {
                System.out.println("Computer make its move");
                this.gameEngine.makeAIMove();
            } else {
                System.out.println(String.format("Player %d. Make your move:", currentPlayer + 1));
                Move move = this.readPlayerAction(currentPlayer);
                try {
                    this.gameEngine.applyMove(move);
                } catch (InvalidMoveException e) {
                    System.out.println("Invalid move");
                    continue;
                }
            }
            printStatus();
        }
        System.out.println("Game has ended");
    }

    private Move readPlayerAction(int currentPlayer) {
        Move move = null;
        while (move == null) {
            try {
                String input = scanner.nextLine();
                String[] parts = input.split(",");
                int x = Integer.valueOf(parts[0]) - 1;
                int y = Integer.valueOf(parts[1]) - 1;
                if (x >= 0 && x <= this.config.getBoardSize() && y >= 0 && y <= this.config.getBoardSize()) {
                    move = new Move(currentPlayer, x, y);
                } else {
                    System.out.println("Invalid move");
                }
            } catch (Exception e) {
                System.out.println("Invalid move");
            }

        }
        return move;
    }

    private void printStatus() {
        printBoard();

        if (this.gameEngine.getWinner() != null) {
            int current = this.gameEngine.getCurrentPlayer();
            String player = current == 0 ? "AI" : String.valueOf(current);
            System.out.println(String.format(Message.WINNER.getMessage(), player));
        } else if (this.gameEngine.getStatus() == GameStatus.STOPPED) {
            System.out.println(Message.DRAW.getMessage());
        }
    }

    private void printBoard() {
        int size = this.gameEngine.getBoardSize();
        Integer[][] board = this.gameEngine.getBoard();
        StringBuilder separator = new StringBuilder();
        for (int i = 0; i < size; i++) {
            separator.append("---");
        }
        System.out.println(separator.toString());
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Integer value = board[i][j];
                String symbol = (value != null) ? this.config.getSymbols()[value] : " ";
                System.out.print(String.format("| %s |", symbol));
            }
            System.out.println();
            System.out.println(separator.toString());
        }
    }
}
