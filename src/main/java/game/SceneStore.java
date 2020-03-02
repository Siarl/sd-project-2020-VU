package game;

import java.util.Map;

public class SceneStore {

    private int startSceneId;
    private Map<Integer, Scene> scenes;

    /*
    Getters and Setters
     */

    public int getStartSceneId() {
        return startSceneId;
    }

    public Scene getSceneById(int id) {
        return scenes.get(id);
    }

    public boolean hasScene(int sceneId) {
        return scenes.containsKey(sceneId);
    }

    public Map<Integer, Scene> getScenes() {
        return scenes;
    }

}
