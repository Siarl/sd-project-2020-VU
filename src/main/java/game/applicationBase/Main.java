package game.applicationBase;

import game.Game;
import game.Player;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main (String[] args){
        // TODO: 06-03-2020 rewrite this mess
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to our Software Design game.");

        List<File> saveFileList = LocalFileTool.listSaveFiles(Main.class.getClassLoader());
        System.out.println("Pick an option:");
        System.out.println("1.\tNew Game");
        for (int i = 2; i < saveFileList.size(); i++) {
            File file = saveFileList.get(i - 2);
            System.out.println(i + ".\t" + file.getName());
        }

        String choiceString = scanner.nextLine();
        int choiceInt = Integer.parseInt(choiceString);

        Game game = null;
        if (choiceInt == 1) {
            try {
                game = LocalFileTool.makeNewGameFromFile(Main.class.getClassLoader());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            int foo = choiceInt - 2;
            if (foo >= 0 && foo < saveFileList.size()) {
                try {
                    game = LocalFileTool.makeGameFromSaveFile(Main.class.getClassLoader(), saveFileList.get(foo));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }

        if (game == null) {
            return;
        }

        game.setPlayer(new Player("Bogdan"));
        game.start(System.out::println);


        while (true) {
            String command = scanner.nextLine();

            if (command.equals("quit")) {
                return;
            }

            game.handleCommand(command);
        }

    }

}
