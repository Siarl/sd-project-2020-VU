# Assignment 3

Maximum number of words for this document: 18000

Word Count: 647

**IMPORTANT**: In this assignment you will fully model and impement your system. The idea is that you improve your UML models and Java implementation by (i) applying (a subset of) the studied design patterns and (ii) adding any relevant implementation-specific details (e.g., classes with “technical purposes” which are not part of the domain of the system). The goal here is to improve the system in terms of maintainability, readability, evolvability, etc.

**Format**: establish formatting conventions when describing your models in this document. For example, you style the name of each class in bold, whereas the attributes, operations, and associations as underlined text, objects are in italic, etc.

### Summary of changes of Assignment 2
Author(s): Bogdan Cercel

Provide a bullet list summarizing all the changes you performed in Assignment 2 for addressing our feedback.

Maximum number of words for this section: 1000

### Application of design patterns
Author(s): Sofia Konovalova, Wilkin van Roosmalen

`Figure representing the UML class diagram in which all the applied design patterns are highlighted graphically (for example with a red rectangle/circle with a reference to the ID of the applied design pattern`

For each application of any design pattern you have to provide a table conforming to the template below.

<table>
    <tr>
        <td><b>ID</b></td>
        <td><b>DP1</b></td>
        <td><b>DP2</b></td>
        <td><b>DP3</b></td>
    </tr>
    <tr>
        <td><b>Design Pattern</b></td>
        <td>Command</td>
        <td>Name of the applied pattern</td>
        <td>Name of the applied pattern</td>
    </tr>
    <tr>
        <td><b>Problem</b></td>
        <td>The problem that needs to be solved is with implementing commands with the different
        interacable objects within the game. Actions, scenes, items and players all have commands that are
        associated with them, that are different and have a different effect on the game. The problem is to
        have all of these separate commands implemented, but not have to do separate command handling for
        every single one of the objects that are interactable in the game.</td>
        <td>A paragraph describing the problem you want to solve</td>
        <td>A paragraph describing the problem you want to solve</td>
    </tr>
    <tr>
        <td><b>Solution</b></td>
        <td>This design pattern involves the creation of an interface, from which different encapsulated objects which behave similarly
        can implement. This means that our interactable objects within the game can use a common interface and use the methods
        in the interface to execute the commands appropriate to them. </td>
        <td>A paragraph describing why with the application of the design pattern you solve the identified problem</td>
        <td>A paragraph describing why with the application of the design pattern you solve the identified problem</td>
    </tr>
    <tr>
        <td><b>Intended use</b></td>
        <td>At run-time, the interface is used whenever a command is executed, depending on which interactable object is being used: an item, a player or a
        scene.
        //insert sequence diagram here</td>
        <td>A paragraph describing how you instend to use at run-time the objects involved in the applied design patterns (you can refer to small sequence diagrams here if you want to detail how the involved parties interact at run-time)</td>
        <td>A paragraph describing how you instend to use at run-time the objects involved in the applied design patterns (you can refer to small sequence diagrams here if you want to detail how the involved parties interact at run-time)</td>
    </tr>
    <tr>
        <td><b>Constraints</b></td>
        <td>One of the constraints is that commands for each of the interactable features have to be quite similar to each other, with not a lot of room
        for difference or creativity. This is not a big problem, but if the game were to be more complicated this could be a difficulty.</td>
        <td>Any additional constraints that the application of the design pattern is imposing, if any </td>
        <td>Any additional constraints that the application of the design pattern is imposing, if any </td>
    </tr>
    <tr>
        <td><b>Additional remarks</b></td>
        <td>N/A</td>
        <td>Optional, only if needed</td>
        <td>Optional, only if needed</td>
    </tr>
</table>

Maximum number of words for this section: 2000
Word count: 201

## Class diagram
Author(s): Sofia Konovalova

**not the final class diagram, but needed for me to write this up for now**

![](images-assignment3/ClassDiagram.png)

- [x] LocalFileTool
- [x] Main
- [ ] ActionStore
- [ ] ItemStore
- [ ] SceneStore
- [ ] Actions
- [ ] Command
- [x] Effect
- [x] Effects
- [x] Game
- [x] Interactable
- [ ] Item
- [ ] Player
- [ ] PlayerStats
- [ ] Scene
- [ ] Stats

The class **Main** is the class that starts up the program and contains the main method of the program.
The main method of the class creates a *game* object which uses the **LocalFileTool** class
to load all the game information from json files. The way that the game is created depends on if there exists a save file
for the game already, or if there needs to be a new game created. This choice is picked by the user itself.
The json files contain all the necessary information about the game: the scenes of the game,
with the actions, characters and and items of each scene. The main method also contains an endless while loop, which constantly takes input
from the user until they write a command to quit the game.

**LocalFileTool** is the class where all of the file handling happens. It has the attributes *MAIN_DIR*, *SAVE_DIR*, and *GAME_DIR*, which are static variables
holding the directories of the respective files. The function *fromFile(String)* takes in the name of the main game file as a string, and converts
that into information the game can use. The function *makeNewGameFromFile(ClassLoader)* makes a new game from the resource files that are included with the
game. *makeNewGameFromFile(ClassLoader, File)* creates a new game from the json files that may be provided by the user. *makeNewGameFromSaveFile(ClassLoader, File)*
creates a new game from a save file that is locally stored. The three functions mentioned above return a *game* object. *listSaveFiles()* lists the save files
that are available to the user to play from. \
The **LocalFileTool** class and the **Game** class have a dependency association which is named "create", since the LocalFileTool creates the game from the
json files. The **Game** class uses the information from **LocalFileTool** to define it's attributes.

The **Game** class is the most important class of the game. It determines the current game state, which has all the necessary information like the scenes,
items in the scene, the players, and the actions available in the game state. It has the following attributes: *currentSceneId*, which is the unique ID
of the scene the user is currently playing in; *actionsMap*, *sceneMap* and *itemMap* are all Maps that help allocate actions, scenes and items to a scene.
The *actionsMap* and *sceneMap* map an integer to an action or a scene respectively, and the *itemMap* maps a string to an item. The *player* attribute is just
an object of the class **Player**. The **Game** class makes use of constructor overloading: the constructor
*Game(String, Map<Integer, Scene>, Map<Integer, Actions>, Map<String, Item>, int)* is used to create the game.
The class also has the functions *start(Listener)*, *handleCommand(String)*, *subscribeListener(Listener)*, and *unsubscribeListener(Listener)*,
which handles the commands that the player types into the console using Listeners which come with the standard Java library,
by making use of the callback functions that are provided with the **Interactable** interface.

The **Effect** class handles the effects of each of the action. One of the most important aspects of the class is that is has an enumerator named *Type*, which determines
the type of effect that an action has -- the attributes of the enumerator are *NAVIGATION*, *INVENTORY*, *STATS*, which determine that an action can have an effect on the
navigation through the world (the player moving from one place to another), on the inventory (picking up an object), or on the stats of the player (sleeping increasing health
points, smoking cigarettes decreasing them). The class has the following attributes: *description*, which is the description of the scene, printed out to the player
once they enter the scene; *sceneIdChange* which determines to which scene the player changes to, as actions can have navigation effects, meaning a change
of scene; *type*, which is the enumerator described above; *statsChange*, which returns an object of the class **Stats** which describes any changes that have
been made to the player's stats based on their actions; *inventoryAddChange* and *inventoryRemoveChange* which returns a list of the inventory after a new item
is being added or removed as a result of the player's action. \
Now, if we focus on this part of the previous version of the class diagram: \
![](images-assignment3/ClassDiagramEffectFocus.png)
What happened before is when a command is entered, the *Actions* object handles it in the *onCommand(Command, Callback)* method.
Then, the *Effect* object is retrieved and applied. What exactly happens when an *Effect* is applied depends on the *Effect.type*. We decided to improve this
by having one command to apply multiple effect types. This is implemented using the **Effects** class. \
The **Effects** class has two attributes: *effectList*, which is a list of *Effect* objects, and the method *apply(Game)*, to apply the effects to the game.
Now, the **Effects** class contains a collection of effects, and the *apply(Game)* method applies all the effects at once. Instead of mapping a command to an
*Effect* object, it is now mapped to an *Effects* object, which handles the application of the effects to the game.

The **Interactable** interface defines two methods, which deal with command handling within the game. It has an interface named **Callback** which deals
with outgoing messages in the CLI during gameplay. The *listCommands(Game)* lists the possible commands that can be written by the player at a particular
game state.

Maximum number of words for this section: 4000 \
Word Count: 473

## Object diagrams
Author(s): Koen van den Burg

This chapter contains the description of a "snapshot" of the status of your system during its execution.
This chapter is composed of a UML object diagram of your system, together with a textual description of its key elements.

`Figure representing the UML class diagram`

`Textual description`

Maximum number of words for this section: 1000

## State machine diagrams
Author(s): Claudia Grigoras

This chapter contains the specification of at least 2 UML state machines of your system, together with a textual description of all their elements. Also, remember that classes the describe only data structures (e.g., Coordinate, Position) do not need to have an associated state machine since they can be seen as simple "data containers" without behaviour (they have only stateless objects).

For each state machine you have to provide:
- the name of the class for which you are representing the internal behavior;
- a figure representing the part of state machine;
- a textual description of all its states, transitions, activities, etc. in a narrative manner (you do not need to structure your description into tables in this case). We expect 3-4 lines of text for describing trivial or very simple state machines (e.g., those with one to three states), whereas you will provide longer descriptions (e.g., ~500 words) when describing more complex state machines.

The goal of your state machine diagrams is both descriptive and prescriptive, so put the needed level of detail here, finding the right trade-off between understandability of the models and their precision.

Maximum number of words for this section: 4000

## Sequence diagrams
Author(s): Bogdan Cercel

This chapter contains the specification of at least 2 UML sequence diagrams of your system, together with a textual description of all its elements. Here you have to focus on specific situations you want to describe. For example, you can describe the interaction of player when performing a key part of the videogame, during a typical execution scenario, in a special case that may happen (e.g., an error situation), when finalizing a fantasy soccer game, etc.

For each sequence diagram you have to provide:
- a title representing the specific situation you want to describe;
- a figure representing the sequence diagram;
- a textual description of all its elements in a narrative manner (you do not need to structure your description into tables in this case). We expect a detailed description of all the interaction partners, their exchanged messages, and the fragments of interaction where they are involved. For each sequence diagram we expect a description of about 300-500 words.

The goal of your sequence diagrams is both descriptive and prescriptive, so put the needed level of detail here, finding the right trade-off between understandability of the models and their precision.

Maximum number of words for this section: 4000

## Implementation
Author(s): Wilkin van Roosmalen

In this chapter you will describe the following aspects of your project:
- the strategy that you followed when moving from the UML models to the implementation code;
- the key solutions that you applied when implementing your system (for example, how you implemented the syntax highlighting feature of your code snippet manager, how you manage fantasy soccer matches, etc.);
- the location of the main Java class needed for executing your system in your source code;
- the location of the Jar file for directly executing your system;
- the 30-seconds video showing the execution of your system (you can embed the video directly in your md file on GitHub).

IMPORTANT: remember that your implementation must be consistent with your UML models. Also, your implementation must run without the need from any other external software or tool. Failing to meet this requirement means 0 points for the implementation part of your project.

Maximum number of words for this section: 2000

## References

References, if needed.
