package game.applicationBase;

import game.Game;
import game.Player;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main (String[] args){
        // TODO: 06-03-2020 rewrite this mess
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to our Software Design game.");
        System.out.println("Type \"quit\" to exit");

        List<File> saveFileList = LocalFileTool.listSaveFiles(Main.class.getClassLoader());
        System.out.println("Pick an option:");
        System.out.println("1.\tNew Game");

        int i = 2;
        for (File file : saveFileList) {
            System.out.println(i + ".\t" + file.getName());
            i++;
        }

        String choiceString = scanner.nextLine();
        int choiceInt = Integer.parseInt(choiceString);

        Game game = null;
        if (choiceInt == 1) {
            game = GameFactory.createExample();
            System.out.println("Enter a name:");
            choiceString = scanner.nextLine();
            game.setPlayer(new Player(choiceString));

        } else {
            int foo = choiceInt - 2;
            if (foo >= 0 && foo < saveFileList.size()) {
                try {
                    game = LocalFileTool.fromFile(saveFileList.get(foo));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }

        if (game == null) {
            return;
        }

        game.start(System.out::println);
        while (true) {
            String command = scanner.nextLine();

            if (command.equals("quit")) {
                try {
                    LocalFileTool.toFile(game, Main.class.getClassLoader());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return;
            }

            game.handleCommand(command);
        }

    }

}
