package com.company;

import com.company.players.Player;
import com.company.players.NaivePlayer;
import com.company.players.Player94105571;

public class Main {

    public static void main(String[] args) {
        Player p1 = new Player94105571(1);
        Player p2 = new Player94105571(2);
        Game g = new Game(p1, p2);
        g.start();
    }

}