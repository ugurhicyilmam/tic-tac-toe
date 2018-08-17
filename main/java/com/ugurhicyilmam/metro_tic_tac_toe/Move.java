package com.ugurhicyilmam.metro_tic_tac_toe;

public class Move {
    private final int player;
    private final int x;
    private final int y;

    public Move(int player, int x, int y) {
        this.player = player;
        this.x = x;
        this.y = y;
    }

    public int getPlayer() {
        return player;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
