package com.momo.app2.builder;

import com.momo.app2.domain.Team;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TeamBuilder {

    private Integer unluckyPlayer;

    private List<Team> teams;

    private List<Integer> players = new ArrayList<>();

    public TeamBuilder(int numberOfPlayers) {
        for (int i = 1; i <= numberOfPlayers; i++) {
            players.add(i);
        }
        Collections.shuffle(players);
    }

    public List<Team> getTeams() {
        teams = new ArrayList<>();

        if (players.size() % 2 > 0) {
            unluckyPlayer = players.remove(players.size() - 1);
        }

        for (int i = 0; i < players.size() - 1; i += 2) {
            teams.add(new Team(players.get(i), players.get(i + 1)));
        }
        return teams;
    }

    public int getUnluckyPlayer() {
        return unluckyPlayer;
    }

    public boolean hasUnluckyPlayer() {
        return unluckyPlayer != null;
    }
}
