package heroes;

import generator.MazePoint;

import java.util.Random;

public class Minotaur {
    private int i;
    private int j;
    private Random random = new Random();
    private boolean visited = false;
    private MazePoint[][] maze;

    private void minotaursTurn() {
        maze[i][j] = (visited) ? MazePoint.VISITED : MazePoint.PASSAGE;
        if (maze[i][j] == MazePoint.THESEUS) {
            minotaurWin(i, j);
        }
        if (i - 1 >= 0) {
            if (maze[i - 1][j] == MazePoint.THESEUS) {
                minotaurWin(i - 1, j);
                return;
            }
        }
        if (i + 1 < maze.length) {
            if (maze[i + 1][j] == MazePoint.THESEUS) {
                minotaurWin(i + 1, j);
                return;
            }
        }
        if (j + 1 < maze.length) {
            if (maze[i][j + 1] == MazePoint.THESEUS) {
                minotaurWin(i, j + 1);
                return;
            }
        }
        if (j - 1 >= 0) {
            if (maze[i][j - 1] == MazePoint.PASSAGE) {
                minotaurWin(i, j - 1);
                return;
            }
        }
        minotaursStep(i, j);
    }

    private void minotaursStep(int i, int j) {
        if (i - 1 >= 0) {
            if (maze[i - 1][j] != MazePoint.WALL) {
                visited = maze[i - 1][j] == MazePoint.VISITED;
                maze[i - 1][j] = MazePoint.MINOTAUR;
                printMap();
                return;
            }
        }
        if (i + 1 < maze.length) {
            if (maze[i + 1][j] != MazePoint.WALL) {
                visited = maze[i + 1][j] == MazePoint.VISITED;
                maze[i + 1][j] = MazePoint.MINOTAUR;
                printMap();
                return;
            }
        }
        if (j + 1 < maze.length) {
            if (maze[i][j + 1] != MazePoint.WALL) {
                visited = maze[i][j + 1] == MazePoint.VISITED;
                maze[i][j + 1] = MazePoint.MINOTAUR;
                printMap();
                return;
            }
        }
        if (j - 1 >= 0) {
            if (maze[i][j - 1] != MazePoint.WALL) {
                visited = maze[i][j - 1] == MazePoint.VISITED;
                maze[i][j - 1] = MazePoint.MINOTAUR;
                printMap();
            }
        }
    }

    private void minotaurWin(int i, int j) {
        maze[i][j] = MazePoint.MINOTAUR;
        printMap();
        System.out.println("Theseus is dead");
        System.exit(0);
    }

    private void printMap() {
        System.out.println();
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze.length; j++) {
                switch (maze[i][j]) {
                    case WALL:
                        System.out.print(" * ");
                        break;
                    case PASSAGE:
                        System.out.print("   ");
                        break;
                    case EXIT:
                        System.out.print(" E ");//System.out.print(" \uDEAA ");
                        break;
                    case THESEUS:
                        System.out.print(" T ");//System.out.print(" \uDEB6 ");
                        break;
                    case MINOTAUR:
                        System.out.print(" M ");//System.out.print(" \uDDEB ");
                        break;
                    case ENTRANCE:
                        System.out.print(" N ");//System.out.print(" \u2386 ");
                        break;
                    case VISITED:
                        System.out.print(" x ");
                        break;
                }
            }
            System.out.println();
        }
    }
}
