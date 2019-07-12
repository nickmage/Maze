import generator.MazeGenerator;

/*
* What this program can do and what I want to say:
* 1. Build a maze (exit is optional, there is no guarantee the exit isn't blocked, maybe I'll add some feature later).
* 2. Theseus is going through the maze and successfully finds the exit.
* 3. Theseus will slay Minotaur if it will face him (myth-like) - need testing.
* 4. I didn't have time to ensure Minotaur's motion, MB add later.
* 5. The code design is not as good as I wanted. I'll fix it after the Minotaur's motion logic will be done.
* 6. If Theseus finds the exit my program will print the whole way he did including getting back trace.
* 7. Some marks are replaced by letters (for example Theseus is T, exit is E) because my PC doesn't recognise marks you gave.
* 8. The heroes package is useless at the moment. The reasons was explained above.
* */

public class Main {
    public static void main(String[] args) {
        MazeGenerator generator = new MazeGenerator(15, true, true);
        TheseusAdventure adventure = new TheseusAdventure(generator.getMaze());
        adventure.searchExit();
    }
}