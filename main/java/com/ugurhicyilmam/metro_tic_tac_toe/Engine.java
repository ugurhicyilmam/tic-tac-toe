package com.ugurhicyilmam.metro_tic_tac_toe;

import java.util.Arrays;

public class Engine {
    private static final int MAX_WIN_SCORE = 5;

    private int AI_TURN = 2;
    private int currentPlayer;
    private int winningScore;
    private Integer winner;

    private Integer[][] board;
    private int boardSize;
    private int slots;

    private Config config;


    private GameStatus status;

    public Engine(Config config) {
        this.config = config;
        this.winningScore = Math.min(MAX_WIN_SCORE, config.getBoardSize());
        this.currentPlayer = 0;
        this.status = GameStatus.RUNNING;
        this.boardSize = config.getBoardSize();
        this.board = new Integer[this.boardSize][this.boardSize];
        this.slots = this.boardSize * this.boardSize;
        this.winner = null;
    }


    public GameStatus getStatus() {
        return status;
    }

    public Integer getWinner() {
        return winner;
    }

    public void applyMove(Move move) {
        this.put(move);
        this.currentPlayer = (this.currentPlayer + 1) % 3;

        if (this.getHighestScore() >= winningScore) {
            this.status = GameStatus.STOPPED;
            this.winner = this.currentPlayer;
        } else if (this.isFull()) {
            this.status = GameStatus.STOPPED;
            this.winner = null;
        }
    }

    private boolean isFull() {
        return this.slots == 0;
    }

    private int getHighestScore() {
        int highestDiagonal = this.getHighestDiagonalScore();
        int highestLinear = this.getHighestLinearScore();
        int max = Math.max(highestDiagonal, highestLinear);
        return max;
    }

    private int getHighestDiagonalScore() {
        int firstDiagonal[] = new int[3];
        int secondDiagonal[] = new int[3];
        for (int i = 0; i < this.boardSize; i++) {
            if (this.board[i][i] != null)
                firstDiagonal[this.board[i][i]]++;
            if (this.board[this.boardSize - 1 - i][i] != null)
                secondDiagonal[this.board[this.boardSize - 1 - i][i]]++;
        }
        Arrays.sort(firstDiagonal);
        Arrays.sort(secondDiagonal);
        int max = Math.max(firstDiagonal[2], secondDiagonal[2]);
        return max;
    }

    private int getHighestLinearScore() {
        int highest = 0;
        for (int i = 0; i < this.boardSize; i++) {
            int horizontal[] = new int[3];
            int vertical[] = new int[3];
            for (int j = 0; j < this.boardSize; j++) {
                if (this.board[i][j] != null)
                    horizontal[this.board[i][j]]++;
                if (this.board[j][i] != null)
                    vertical[this.board[j][i]]++;
            }
            Arrays.sort(horizontal);
            Arrays.sort(vertical);
            int currentHighest = Math.max(horizontal[2], vertical[2]);
            highest = Math.max(highest, currentHighest);
        }
        return highest;
    }

    private void put(Move move) {
        if (shouldApplyMove(move)) {
            this.board[move.getX()][move.getY()] = move.getPlayer();
            this.slots--;
        } else {
            throw new InvalidMoveException();
        }
    }

    private boolean shouldApplyMove(Move move) {
        int x = move.getX();
        int y = move.getY();
        return x <= this.boardSize
                && y <= this.boardSize
                && board[x][y] == null;
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public boolean isAI() {
        return this.currentPlayer == this.AI_TURN;
    }

    public void makeAIMove() {
        for (int i = 0; i < this.boardSize; i++) {
            for (int j = 0; j < this.boardSize; j++) {
                if (this.board[i][j] == null) {
                    this.applyMove(new Move(this.AI_TURN, i, j));
                    return;
                }
            }
        }
    }

    public int getBoardSize() {
        return boardSize;
    }

    public Integer[][] getBoard() {
        return this.board;
    }
}
