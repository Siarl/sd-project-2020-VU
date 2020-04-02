package game.applicationBase;

import game.*;
import game.Character;

import java.util.*;

public class GameFactory {

    public static Game createExample() {
        Map<Integer, Scene> sceneMap = exampleSceneMap();
        Map<Integer, Actions> actionsMap = exampleActionsMap();
        Map<String, Item> itemMap = exampleItemMap();
        Map<String, Character> characterMap = exampleCharacterMap();

        Game game = new Game("player", sceneMap, actionsMap, itemMap, characterMap, 0);

        return game;
    }

    private static Map<Integer, Actions> exampleActionsMap() {
        Map<Integer, Actions> actionsMap = new HashMap<>();

        // actions 0, scene 0
        Map<String, Effects> effectsMap0 =  new HashMap<>();
        effectsMap0.put("sleep", new Effects(
                new Effect("You go back to bed and never wake up again", new Stats(-999, 0, 0))
        ));
        actionsMap.put(0, new Actions(0, effectsMap0));

        // actions 1, scene 1
        actionsMap.put(1, new Actions(1, new HashMap<>()));

        // actions 2, scene 2
        actionsMap.put(2, new Actions(2, new HashMap<>()));

        // actions 3, scene 3
        actionsMap.put(3, new Actions(3, new HashMap<>()));

        // actions 4, scene 4
        actionsMap.put(4, new Actions(4, new HashMap<>()));

        // actions 10, item cigarettes
        Map<String, Effects> effectsMap10 = new HashMap<>();
        effectsMap10.put("grab", new Effects(new Effect("Cigarettes added to inventory.", Arrays.asList("cigarettes"), new ArrayList<>())));
        effectsMap10.put("smoke", new Effects(new Effect("Smoking is bad! But you do feel stronger...", new Stats(-10, 10, 0))));
        actionsMap.put(10, new Actions(10, effectsMap10));

        // actions 11, item joint
        Map<String, Effects> effectsMap11 = new HashMap<>();
        effectsMap11.put("grab", new Effects(new Effect("Joint added to inventory.", Arrays.asList("joint"), new ArrayList<>())));
        effectsMap11.put("smoke", new Effects(new Effect("You're in your bed again...", 0),new Effect("You feel calm but alive.", new Stats(10, -10, 0))));
        actionsMap.put(11, new Actions(11, effectsMap11));

        // actions 12, item bedroom-door
        Map<String, Effects> effectsMap12 = new HashMap<>();
        effectsMap12.put("open", new Effects(new Effect("You enter the bedroom.", 0)));
        actionsMap.put(12, new Actions(12, effectsMap12));

        // actions 13, item garage-door
        Map<String, Effects> effectsMap13 = new HashMap<>();
        effectsMap13.put("open", new Effects(new Effect("You enter the garage.", 2)));
        actionsMap.put(13, new Actions(13, effectsMap13));

        // actions 14, item livingroom-door
        Map<String, Effects> effectsMap14 = new HashMap<>();
        effectsMap14.put("open", new Effects(new Effect("You enter the living room.", 1)));
        actionsMap.put(14, new Actions(14, effectsMap14));

        // actions 15, item bike
        Map<String, Effects> effectsMap15 = new HashMap<>();
        effectsMap15.put("ride", new Effects(new Effect("You ride your bike onto the driveway.", 3)));
        actionsMap.put(15, new Actions(15, effectsMap15));

        return actionsMap;
    }

    private static Map<Integer, Scene> exampleSceneMap() {
        Map<Integer, Scene> sceneMap = new HashMap<>();

        /*
        Scene 0
         */

        List<String> items0 = new ArrayList<>();
        items0.add("cigarettes");
        items0.add("livingroom-door");

        Scene scene0 = new Scene(0, "bedroom",
                "Your bedroom. It's a bit of a mess...",
                items0,
                new ArrayList<>(),
                new HashMap<>(),
                0);

        /*
        Scene 1
         */

        List<String> items1 = new ArrayList<>();
        items1.add("garage-door");
        items1.add("bedroom-door");
        items1.add("joint");

        Scene scene1 = new Scene(1, "livingroom",
                "Your livingroom. You still have to do last night's dishes.",
                items1,
                Arrays.asList("Brother"),
                new HashMap<>(),
                1);

        /*
        Scene 2
         */

        List<String> items2 = new ArrayList<>();
        items2.add("livingroom-door");
        items2.add("bike");

        Scene scene2 = new Scene(2, "garage",
                "A nice place to park your car. But you are too poor and only have an old bike.",
                items2,
                new ArrayList<>(),
                new HashMap<>(),
                2);

        /*
        Scene 3
         */

        Map<String, Integer> nextScenes3 = new HashMap<>();
        nextScenes3.put("north", 4);
        Scene scene3 = new Scene(3, "driveway",
                "The driveway to your garage. It's awfully quiet outside...",
                new ArrayList<>(),
                new ArrayList<>(),
                nextScenes3,
                3);

        /*
        Scene 4
         */

        Map<String, Integer> nextScenes4 = new HashMap<>();
        nextScenes4.put("south", 3);

        Scene scene4 = new Scene(4, "road",
                "A nice road, paved last month.",
                new ArrayList<>(),
                Arrays.asList("Bully"),
                nextScenes4,
                4);

        sceneMap.put(scene0.getId(), scene0);
        sceneMap.put(scene1.getId(), scene1);
        sceneMap.put(scene2.getId(), scene2);
        sceneMap.put(scene3.getId(), scene3);
        sceneMap.put(scene4.getId(), scene4);

        return sceneMap;
    }
    
    private static Map<String, Character> exampleCharacterMap() {
        Map<String, Character> characterMap = new HashMap<>();

        Enemy enemy = new Enemy("Bully", "Nasty man that's always out to get you.", 20, new CharacterStats(75, 15, 2));

        List<String> lines = new ArrayList<>();
        lines.add("This is line one!");
        lines.add("This is line two!");
        lines.add("This is my last line! I will repeat it when you talk to me again.");

        Friend friend = new Friend("Brother", "Your younger brother.", 2, new Conversation(lines));

        characterMap.put(enemy.getName(), enemy);
        characterMap.put(friend.getName(), friend);
        
        return characterMap;
    }

    private static Map<String, Item> exampleItemMap() {
        Map<String, Item> itemMap = new HashMap<>();

        itemMap.put("cigarettes", new Item("cigarettes", "A pack of luckies, half empty. Or half full?", 10));
        itemMap.put("joint", new Item("joint", "No idea what's in it", 11));
        itemMap.put("bedroom-door", new Item("bedroom-door", "A door leading to the livingroom.", 12));
        itemMap.put("garage-door", new Item("garage-door", "A door leading to the garage.", 13));
        itemMap.put("livingroom-door", new Item("livingroom-door", "A door leading to the livingroom.", 14));
        itemMap.put("bike", new Item("bike", "An old bike. It works.", 15));

        return itemMap;
    }

}
