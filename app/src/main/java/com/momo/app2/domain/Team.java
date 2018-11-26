package com.momo.app2.domain;

public class Team {

    private boolean sparePlayer;

    private int player1;
    private int player2;

    public Team(int player1, int player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    public Team(int sparePlayer) {
        this.player1 = sparePlayer;
        this.sparePlayer = true;
    }

    public int getPlayer1() {
        return player1;
    }

    public int getPlayer2() {
        return player2;
    }

    public boolean hasSparePlayer() {
        return sparePlayer;
    }
}
