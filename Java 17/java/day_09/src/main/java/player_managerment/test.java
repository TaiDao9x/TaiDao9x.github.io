package player_managerment;

import player_managerment.service.PlayerService;

import java.util.Scanner;

public class test {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PlayerService playerService = new PlayerService();
        playerService.buildTeam(1, 4, 4, 2);
    }
}
