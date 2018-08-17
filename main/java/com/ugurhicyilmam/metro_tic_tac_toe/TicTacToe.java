package com.ugurhicyilmam.metro_tic_tac_toe;

class TicTacToe {
    public static void main(String[] args) {
        Config config = new Config(args);
        Engine ticTacToeEngine = new Engine(config);
        View consoleView = new View(ticTacToeEngine, config);
        consoleView.runGame();
    }
}
