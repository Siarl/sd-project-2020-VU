package game.applicationBase;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import game.Game;
import game.stores.ActionStore;
import game.stores.ItemStore;
import game.stores.SceneStore;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

public class LocalFileTool {
    // TODO: 06-03-2020 clean this mess
    private static final String SLASH = System.getProperty("file.separator");
    private static final String MAIN_DIR = System.getProperty("user.home") + SLASH + ".spork" + SLASH ;
    private static final String GAME_DIR = MAIN_DIR + "data" + SLASH;
    private static final String SAVE_DIR = MAIN_DIR + "saves" + SLASH;

    private static File mainItemFile = new File(GAME_DIR + "main-game.items.json");
    private static File mainSceneFile = new File(GAME_DIR + "main-game.scenes.json");
    private static File mainActionsFile = new File(GAME_DIR + "main-game.actions.json");

    public static Game fromFile(String fileName) throws FileNotFoundException {
        fileName = MAIN_DIR + fileName;
        Gson gson = new Gson();
        JsonReader jsonReader = new JsonReader(new FileReader(fileName));

        return gson.fromJson(jsonReader, Game.class);
    }

    public static List<File> listSaveFiles(ClassLoader classLoader) {
        setupDirectories(classLoader);
        File saveDir = new File(SAVE_DIR);
        return Arrays.asList(saveDir.listFiles());
    }

    public static Game makeNewGameFromFile(ClassLoader classLoader) throws FileNotFoundException {
        return makeNewGameFromFile(classLoader, new File(GAME_DIR + "main-game.json"));
    }

    public static Game makeNewGameFromFile(ClassLoader classLoader, File file) throws FileNotFoundException {
        setupDirectories(classLoader);

        Gson gson = new Gson();
        JsonReader actionStoreReader = new JsonReader(new FileReader(mainActionsFile));
        JsonReader itemStoreReader = new JsonReader(new FileReader(mainItemFile));
        JsonReader sceneStoreReader = new JsonReader(new FileReader(mainSceneFile));

        SceneStore sceneStore = gson.fromJson(sceneStoreReader, SceneStore.class);
        ItemStore itemStore = gson.fromJson(itemStoreReader, ItemStore.class);
        ActionStore actionStore = gson.fromJson(actionStoreReader, ActionStore.class);

        Game game = new Game(sceneStore.toIntegerSceneMap(),
                actionStore.toIntegerActionMap(), itemStore.toStringItemMap(), sceneStore.getStartSceneId());

        return game;
    }

    public static Game makeGameFromSaveFile(ClassLoader classLoader, File file) throws FileNotFoundException {
        setupDirectories(classLoader);

        if (file.exists() && file.isFile()) {
            JsonReader jsonReader = new JsonReader(new FileReader(file));
            return new Gson().fromJson(jsonReader, Game.class);
        } else {
            throw new FileNotFoundException("Could not find save file.");
        }
    }

    private static void setupDirectories(ClassLoader classLoader) {
        File mainDir = new File(MAIN_DIR);
        File gameDir = new File(GAME_DIR);
        File saveDir = new File(SAVE_DIR);

        if (!mainDir.exists()) {
            mainDir.mkdir();
        }

        if (!gameDir.exists()) {
            gameDir.mkdir();
        }

        if (!saveDir.exists()) {
            saveDir.mkdir();
        }

        if (!mainItemFile.exists()) {
            URL resource = classLoader.getResource("main-game.items.json");
            if (resource == null) {
                throw new IllegalArgumentException("Resource not found!");
            } else {
                File resourceFile = new File(resource.getFile());
                try {
                    FileUtils.copyFile(resourceFile, mainItemFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        if (!mainSceneFile.exists()) {
            URL resource = classLoader.getResource("main-game.scenes.json");
            if (resource == null) {
                throw new IllegalArgumentException("Resource not found!");
            } else {
                File resourceFile = new File(resource.getFile());
                try {
                    FileUtils.copyFile(resourceFile, mainSceneFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        if (!mainActionsFile.exists()) {
            URL resource = classLoader.getResource("main-game.actions.json");
            if (resource == null) {
                throw new IllegalArgumentException("Resource not found!");
            } else {
                File resourceFile = new File(resource.getFile());
                try {
                    FileUtils.copyFile(resourceFile, mainActionsFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
