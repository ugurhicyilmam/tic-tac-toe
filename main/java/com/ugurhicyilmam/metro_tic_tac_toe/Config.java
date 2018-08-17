package com.ugurhicyilmam.metro_tic_tac_toe;

import java.util.HashMap;
import java.util.Map;

class Config {

    private String[] symbols;
    private int boardSize;
    private Map<String, String> configInput;

    Config(String[] configuration) {
        this.configInput = this.processConfig(configuration);
        this.symbols = this.initSymbols();
        this.boardSize = this.initBoardSize();
    }

    private Map<String, String> processConfig(String[] c) {
        Map<String, String> configs = new HashMap<>();
        for (int i = 0; i < c.length; i = i + 2) {
            if (i % 2 == 0) {
                configs.put(c[i], c[i + 1]);
            }
        }
        return configs;
    }

    private int initBoardSize() {
        String size = this.configInput.getOrDefault("-s", "3");
        try {
            return Integer.valueOf(size);
        } catch (Exception e) {
            return 3;
        }
    }

    private String[] initSymbols() {
        String[] symbols = new String[3];
        symbols[0] = this.configInput.getOrDefault("-p1", "a");
        symbols[1] = this.configInput.getOrDefault("-p2", "b");
        symbols[2] = this.configInput.getOrDefault("-p3", "c");
        return symbols;
    }

    int getBoardSize() {
        return this.boardSize;
    }

    String[] getSymbols() {
        return symbols;
    }
}
