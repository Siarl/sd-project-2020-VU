package game.stores;

import game.Scene;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SceneStore {

    private int startSceneId;
    private List<Scene> sceneList;

    public Map<Integer, Scene> toIntegerSceneMap() {
        Map<Integer, Scene> result = new HashMap<>();
        sceneList.forEach(scene -> result.put(scene.getId(), scene));
        return result;
    }

    public int getStartSceneId() {
        return startSceneId;
    }
}
