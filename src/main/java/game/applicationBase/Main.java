package game.applicationBase;

import game.Game;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main (String[] args){
        System.out.println("Welcome to our Software Design game.");

        Game game;
        try {
            game = LocalFileTool.fromFile("test-game.json");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("First add the folder \"software-design-vu-2020\" to your home folder, then add the test-game.json file!");
            return;
        }

        game.start(System.out::println);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            String command = scanner.nextLine();

            if (command.equals("quit")) {
                return;
            }

            game.handleCommand(command);
        }

    }

}
