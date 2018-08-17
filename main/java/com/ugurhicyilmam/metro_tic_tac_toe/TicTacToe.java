package com.ugurhicyilmam.metro_tic_tac_toe;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Properties;

class TicTacToe {
    public static void main(String[] args) {
        String fileName = args[0];

        Properties properties = new Properties();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            properties.load(br);
            Config config = new Config(properties);
            Engine ticTacToeEngine = new Engine(config);
            View consoleView = new View(ticTacToeEngine, config);
            consoleView.runGame();
        } catch (Exception e) {
            System.out.println("File not found.");
        }
    }
}
