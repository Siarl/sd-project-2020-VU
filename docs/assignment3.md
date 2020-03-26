# Assignment 3

Maximum number of words for this document: 18000

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
    </tr>
    <tr>
        <td><b>Design Pattern</b></td>
        <td>Name of the applied pattern</td>
    </tr>
    <tr>
        <td><b>Problem</b></td>
        <td>A paragraph describing the problem you want to solve</td>
    </tr>
    <tr>
        <td><b>Solution</b></td>
        <td>A paragraph describing why with the application of the design pattern you solve the identified problem</td>
    </tr>
    <tr>
        <td><b>Intended use</b></td>
        <td>A paragraph describing how you instend to use at run-time the objects involved in the applied design patterns (you can refer to small sequence diagrams here if you want to detail how the involved parties interact at run-time)</td>
    </tr>
    <tr>
        <td><b>Constraints</b></td>
        <td>Any additional constraints that the application of the design pattern is imposing, if any </td>
    </tr>
    <tr>
        <td><b>Additional remarks</b></td>
        <td>Optional, only if needed</td>
    </tr>
</table>

Maximum number of words for this section: 2000

## Class diagram
Author(s): Sofia Konovalova

`Figure representing the UML class diagram`

<u>Main</u> \
This is the main class of the program. The main method of the class creates a *game* object which uses the **LocalFileTool** class
to load all the game information from json files. The way that the game is created depends on if there exists a save file
for the game already, or if there needs to be a new game created. This choice is picked by the user itself.
The json files contain all the necessary information about the game: the scenes of the game,
with the actions, characters and and items of each scene. The main method also contains an endless while loop, which constantly takes input
from the user until they write a command to quit the game.

<u>LocalFileTool</u> \
This is the class where all of the file handling happens.

Maximum number of words for this section: 4000 \
Word Count: 131

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
