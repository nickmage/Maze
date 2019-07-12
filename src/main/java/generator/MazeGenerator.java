package generator;

import java.util.Random;

public class MazeGenerator {
    private int size;
    private boolean hasMinotaur;
    private boolean hasExit;
    private MazePoint[][] maze;
    private Random random = new Random();

    public MazeGenerator(int size, boolean hasMinotaur, boolean hasExit) {
        this.size = size;
        if (size < 15) {
            throw new IllegalArgumentException("The size must be at least 15");
        }
        this.hasMinotaur = hasMinotaur;
        this.hasExit = hasExit;
        maze = new MazePoint[size][size];
        generateMaze();
    }

    private void generateMaze() {
        generateBorders();
        generateWallsAndPassages();
        generateEntrance();
        if (hasExit)
            generateExit();
        if (hasMinotaur)
            generateMinotaur();
    }

    private void generateBorders() {
        for (int i = 0; i < size; i++) {
            maze[0][i] = MazePoint.WALL;           // north wall
            maze[size - 1][i] = MazePoint.WALL;    // south wall
            maze[i][size - 1] = MazePoint.WALL;    // east wall
            maze[i][0] = MazePoint.WALL;           // west wall
        }
    }

    private void generateWallsAndPassages() {
        for (int i = 1; i < size - 1; i++) {
            for (int j = 1; j < size - 1; j++) {
                switch (random.nextInt(3)) {
                    case 0:
                        maze[i][j] = MazePoint.WALL;
                        break;
                    case 1:
                    case 2:
                        maze[i][j] = MazePoint.PASSAGE;
                        break;
                }
            }
        }
    }

    private void generateEntrance() {
        switch (random.nextInt(4)) {
            case 0:
                maze[0][random.nextInt(size - 2) + 1] = MazePoint.ENTRANCE;
                break;                                                             // set entrance to the north wall
            case 1:
                maze[size - 1][random.nextInt(size - 2) + 1] = MazePoint.ENTRANCE;
                break;                                                             // set entrance to the south wall
            case 2:
                maze[random.nextInt(size - 2) + 1][size - 1] = MazePoint.ENTRANCE;
                break;                                                             // set entrance to the east wall
            case 3:
                maze[random.nextInt(size - 2) + 1][0] = MazePoint.ENTRANCE;
                break;                                                             // set entrance to the west wall
        }
    }

    private void generateExit() {
        int j = random.nextInt(size - 2) + 1;
        switch (random.nextInt(4)) {
            case 0:                                                             // set exit to the north wall
                if (maze[0][j] == MazePoint.ENTRANCE) {
                    generateExit();
                    break;
                } else {
                    maze[0][j] = MazePoint.EXIT;

                    break;
                }
            case 1:                                                             // set exit to the south wall
                if (maze[size - 1][j] == MazePoint.ENTRANCE) {
                    generateExit();
                    break;
                } else {
                    maze[size - 1][j] = MazePoint.EXIT;
                    break;
                }
            case 2:                                                             // set exit to the east wall
                if (maze[j][size - 1] == MazePoint.ENTRANCE) {
                    generateExit();
                    break;
                } else {
                    maze[j][size - 1] = MazePoint.EXIT;
                    break;
                }
            case 3:                                                             // set exit to the west wall
                if (maze[j][0] == MazePoint.ENTRANCE) {
                    generateExit();
                    break;
                } else {
                    maze[j][0] = MazePoint.EXIT;
                    break;
                }
        }
    }

    private void generateMinotaur() {
        maze[random.nextInt(size - 2) + 1][random.nextInt(size - 2) + 1] = MazePoint.MINOTAUR;
    }

    public MazePoint[][] getMaze() {
        return maze;
    }
}