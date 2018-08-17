package com.ugurhicyilmam.metro_tic_tac_toe;

public enum Message {
    WINNER("Winner of the game is: %s"),
    DRAW("The game finished with a draw");

    private String message;

    Message(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
