package com.ugurhicyilmam.metro_tic_tac_toe;

import java.util.Properties;

class Config {

    private String[] symbols;
    private int boardSize;
    private Properties configInput;

    Config(Properties configuration) {
        this.configInput = configuration;
        this.symbols = this.initSymbols();
        this.boardSize = this.initBoardSize();
    }

    private int initBoardSize() {
        String size = (String) this.configInput.getOrDefault("BOARD_SIZE", "3");
        try {
            return Integer.valueOf(size);
        } catch (Exception e) {
            return 3;
        }
    }

    private String[] initSymbols() {
        String[] symbols = new String[3];
        symbols[0] = (String) this.configInput.getOrDefault("P1_SYMBOL", "a");
        symbols[1] = (String) this.configInput.getOrDefault("P2_SYMBOL", "b");
        symbols[2] = (String) this.configInput.getOrDefault("AI_SYMBOL", "c");
        return symbols;
    }

    int getBoardSize() {
        return this.boardSize;
    }

    String[] getSymbols() {
        return symbols;
    }
}
