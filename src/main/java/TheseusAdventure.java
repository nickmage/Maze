import generator.MazePoint;

import java.util.Deque;
import java.util.LinkedList;

public class TheseusAdventure {
    private int turns = 0;
    private MazePoint[][] maze;
    private Deque<Character> path = new LinkedList<>();
    private StringBuilder builder = new StringBuilder();

    public TheseusAdventure(MazePoint[][] maze) {
        this.maze = maze;
    }

    void searchExit() {
        boolean isExit = false;
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze.length; j++) {
                if (maze[i][j] == MazePoint.EXIT) {
                    isExit = true;
                }
            }
        }
        if (!isExit) {
            System.out.println("There is no exit");
        } else {
            searchEntrance();
        }
    }

    private void searchEntrance() {
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze.length; j++) {
                if (maze[i][j] == MazePoint.ENTRANCE) {
                    searchPath(i, j);
                }
            }
        }
    }

    private void searchPath(int i, int j) {
        turns++;
        System.out.println(turns);
        if (maze[i][j] == MazePoint.EXIT) {
            printMap();
            System.out.println("Bingo");
            System.out.println(builder.toString());
            System.exit(0);
        }
        maze[i][j] = MazePoint.THESEUS;//'\uD83D';
        printMap();
        maze[i][j] = MazePoint.VISITED;
        if (isDeadEnd(i, j)) {
            getBack(i, j);
        } else {
            if (i - 1 >= 0 && maze[i - 1][j] != MazePoint.WALL && maze[i - 1][j] != MazePoint.VISITED) {// go up
                path.add('u');
                builder.append("u");
                searchPath(i - 1, j);
            }
            if (j + 1 < maze.length && maze[i][j + 1] != MazePoint.WALL && maze[i][j + 1] != MazePoint.VISITED) {// go right
                path.add('r');
                builder.append("r");
                searchPath(i, j + 1);
            }
            if (i + 1 < maze.length && maze[i + 1][j] != MazePoint.WALL && maze[i + 1][j] != MazePoint.VISITED) {// go down
                path.add('d');
                builder.append("d");
                searchPath(i + 1, j);
            }
            if (j - 1 >= 0 && maze[i][j - 1] == MazePoint.PASSAGE) {// go left
                path.add('l');
                builder.append("l");
                searchPath(i, j - 1);
            }
        }
    }

    private boolean isDeadEnd(int i, int j) {
        if (i - 1 >= 0) {
            if (maze[i - 1][j] != MazePoint.VISITED && maze[i - 1][j] != MazePoint.WALL) {
                return false;
            }
        }
        if (i + 1 < maze.length) {
            if (maze[i + 1][j] != MazePoint.VISITED && maze[i + 1][j] != MazePoint.WALL) {
                return false;
            }
        }
        if (j + 1 < maze.length) {
            if (maze[i][j + 1] != MazePoint.VISITED && maze[i][j + 1] != MazePoint.WALL) {
                return false;
            }
        }
        if (j - 1 >= 0) {
            return maze[i][j - 1] == MazePoint.VISITED || maze[i][j - 1] == MazePoint.WALL;
        }
        return true;
    }

    private void getBack(int i, int j) {
        char c = ' ';
        while (!hasUnvisited(i, j) && !path.isEmpty()) {
            maze[i][j] = MazePoint.VISITED;
            switch (path.getLast()) {
                case 'u':
                    i++;
                    builder.append("d");
                    break;
                case 'd':
                    builder.append("u");
                    i--;
                    break;
                case 'r':
                    builder.append("l");
                    j--;
                    break;
                case 'l':
                    builder.append("r");
                    j++;
                    break;
            }
            c = path.getLast();
            path.removeLast();
            maze[i][j] = MazePoint.THESEUS;
            printMap();
        }
        if (path.isEmpty()) {
            System.out.println("There is no path to exit");
            System.exit(0);
        }
        maze[i][j] = MazePoint.VISITED;
        path.add(c);
        System.out.println(c);
        path.removeLast();
    }

    private boolean hasUnvisited(int i, int j) {
        if (i - 1 >= 0) {
            if (maze[i - 1][j] == MazePoint.PASSAGE) {
                return true;
            }
        }
        if (i + 1 < maze.length) {
            if (maze[i + 1][j] == MazePoint.PASSAGE) {
                return true;
            }
        }
        if (j + 1 < maze.length) {
            if (maze[i][j + 1] == MazePoint.PASSAGE) {
                return true;
            }
        }
        if (j - 1 >= 0) {
            return maze[i][j - 1] == MazePoint.PASSAGE;
        }
        return false;
    }

    private void printMap() {
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
        System.out.println();
    }
}