package game.applicationBase;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import game.Game;

import java.io.FileNotFoundException;
import java.io.FileReader;

// this is just a PoC
public class LocalFileTool {

    private static final String DEFAULT_DIR = System.getProperty("user.home") +
            System.getProperty("file.seperator") + "software-design-vu-2020" + System.getProperty("file.seperator");

    public static Game fromFile(String fileName) throws FileNotFoundException {
        fileName = DEFAULT_DIR + fileName;
        Gson gson = new Gson();
        JsonReader jsonReader = new JsonReader(new FileReader(fileName));

        return gson.fromJson(jsonReader, Game.class);
    }

}
