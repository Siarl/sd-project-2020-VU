package game.stores;

import game.Character;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CharacterStore {

    private List<Character> characterList;

    public Map<String, Character> toStringCharacterMap() {
        Map<String, Character> result = new HashMap<>();
        characterList.forEach(c -> result.put(c.getName(), c));
        return result;
    }
}
