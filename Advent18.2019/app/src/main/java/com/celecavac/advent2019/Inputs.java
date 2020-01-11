package com.celecavac.advent2019;

public class Inputs {

    public static String Input1 = "#################################################################################\n" +
            "#...............#.......#...............#.........#.......#.......#.....#.......#\n" +
            "#.#########.#.###.#####.#.#.#######.#####.#######.#.#######.#.###.###.#.#.#####.#\n" +
            "#.#.#.....#.#.#...#...#..f#...#.....#...#...#.....#.........#...#.....#...#m....#\n" +
            "#.#.#.###.###.#.#####.#######.#######.#.#.#.#.#######.#########.###########.#####\n" +
            "#...#...#.#...#.......#.....#.#.......#.#.#.#...#...#...#.......#..h......#.....#\n" +
            "###.###.#.#.#########.#.###.#T#.#######.###.###.#B#.#.###.#######.#######.#####.#\n" +
            "#...#...#...#.....#...#...#.#...#.....#.#...#.#...#.#.#...#.........#...#.......#\n" +
            "#####.#######.###.#.###.#.#######.###.#.#.###.#####.###.#.###########.#.#######.#\n" +
            "#...#...#.....#.#...#.#.#.....#...#.....#.#.....#...#...#.#...........#.#.....#.#\n" +
            "#.#.###.#.#####.#####.#.#####.#.###.#####.#.###.#.###.#####.###########.#.###.#.#\n" +
            "#.#.....#.#.....#.........#...#...#.#...#.#...#..x#...W.....#.....#.....#.#...#.#\n" +
            "#.#######.###.#.#########.#.#####.###.#.#.###.#######.#######.###.#.#####.#.#####\n" +
            "#.........#...#.......#.#.#.#...#.....#.#.#.#.#.....#...#.....#...#.#.....#.....#\n" +
            "#.#########O#########.#.#.#.#.#.#.#####.#.#.#.###.#.#.###.#####.###.#.#########.#\n" +
            "#.#.........#.........#...#.#.#.#...#.#.#.#.......#.#.#...#...#.#.......#.....#.#\n" +
            "#.#####.#########.#########.#.#.###.#.#.#.#########.###.###.###.#####.###.###.#.#\n" +
            "#.....#...#.....#.#.........#.#.#.....#.#.....#.....#...#...........#.#...#e..#.#\n" +
            "#####.###.#K###.#.#####.#####.#.#######.#####.#####.#.#.###########.###.###.###.#\n" +
            "#...#.....#.#...#.......#.....#.#.......#.....#...#...#.#.........#.....#.#.#...#\n" +
            "#.#####.###.#.#########.#.#####.#.#####.#.#####.#.#######.#####.#.#######.#.#.#.#\n" +
            "#.#j..#.#...#a..#.....#.#.#.......#.....#.......#...........#.#.#.......#.#...#.#\n" +
            "#.#.#.#.#.#####.#.#####.#.#############.#.###################.#########.#.#####.#\n" +
            "#...#.#.#.#...#...#.....#.#..z..#.....#.#.....#.......#.....#...#...#...#...#...#\n" +
            "#.###.#.#.###.#####.#####.#.###.#.###.#######.#.#.#####.###.###.#.#.#.###.###.###\n" +
            "#.#...#.#.....#.#...#...#.#...#.#.#.#...#.....#.#.....#...#.....#.#...#.#...#...#\n" +
            "###.###.#####.#.#.###.#.#.###.#.#.#.###.#.###########.###.#######.#####.#.#.###.#\n" +
            "#...#...#...#.#.......#.#.....#.#.....#.#.#.............#...#...#...#.....#.#...#\n" +
            "#.#.#.#####.#.#############.###.###.###.#.#.#########.#####.###.#.#.#####.#.#.###\n" +
            "#.#.#.#.....#.............#...#.....#...#.#.....#...#.#.....#...#.#.#...#.#.#.#.#\n" +
            "#.###.#.#.###############.###.#####.#.#.#.#####.#.#.#.#.#####.###.#.#.#.#.#.#.#.#\n" +
            "#.#...#.#.......#.........#...#...#.#.#.#.#.....#.#...#.#.........#.#.#.#.#...#.#\n" +
            "#.#.#######.###.#.#########.###.#.###.#.#.#.###########.#.#########.#.#.#####.#.#\n" +
            "#.#.......#...#.#.#.......#...#.#.#...#.#.#...#.......#.#.#.......#...#.#...#c..#\n" +
            "#.#######.#.#.###.###.###.#####.#.#.###.#.###.#.#####.#.###.#####.#.###.#.#.###.#\n" +
            "#.L..q..#.#.#...#...#.#.#.....#.#...#...#...#.....#...#.....#.....#...#...#.#...#\n" +
            "#.#######.#####.###.#.#.#####.#.#####.#####.#######S#########.#############.#.###\n" +
            "#...#...#.#.......#...#.#.....#.#...#...#...#.....#.....#...#.#.....#.....#k#...#\n" +
            "#.#.#.#.#.#.#####.#####.#.#####.#.#.###.#.###.###.###.#.###.#.#.###.#.###.#.###.#\n" +
            "#.#...#.....#...........#.......#.#...........#.......#.....#...#.....#.....#...#\n" +
            "#######################################.@.#######################################\n" +
            "#.#...#...........#u....#...#.......#...............#.....#...................#.#\n" +
            "#.#.#.#.#####.###.#.#.#.#.#.#.#.#####.#.#####.#####.#.###.#.###.#############.#.#\n" +
            "#.#.#...#...#...#.#.#.#.#.#...#.#s....#.#.....#...#.#.#.#..p#.#.#.....#.....#...#\n" +
            "#.#.#######.###.###.#.###.#####.#.#####.#.#######.#.#.#.#####.#.#.###.#.###.###.#\n" +
            "#...#.........#.#...#.#...#...#...#...#.#...#.....#...#.......#.#.#.#.....#.#.#.#\n" +
            "#.###.#.#####.#.#.###.#.###.#######.#.#.#.#.#.###.#####.#######.#.#.#######.#.#.#\n" +
            "#...#.#.#.....#...#...#.#...#.....#.#...#.#.#.#...#.....#.......#.#...#.V...#...#\n" +
            "###.###.#.#########.###.#.#.#.###.#.###.###.#.#####.#.###.#######.###.#.#####.###\n" +
            "#.#.....#.#.....#...#.....#.#...#...#...#...#.#.....#.......#...#.#...#.#.....#.#\n" +
            "#.#######.#N#.#.#.#.#.#########.#.#######.###.#.#############.#.#.#.#.#.#.#####.#\n" +
            "#.......#.#.#.#.#.#.#.#...#.....#.#.....#.#.........#.........#.#...#.#.#.....U.#\n" +
            "#.#######.###.#.#.###.#.#P#.#######.###.#.###.#######.#########.#####.#.#######.#\n" +
            "#.#.....#...#.#.#.....#.#...#.......#...#...#...#.....#.......#.......#.#...#...#\n" +
            "#.#.###.###.#.#########.###.#.#######.#####.###.#.#####.###.###########.#.#.#.###\n" +
            "#...#...#.#.#.#.....#...#...#.#.....#...#...#...#.......#...#.........#.#.#.#.#.#\n" +
            "#.###.#.#.#.#.#.#.###.#######.#.###.###.#.#####.#####.#####.#.#######.#.#.#Q#.#.#\n" +
            "#.#...#...#.#...#...#.....#...#.#...#...#.....#.....#.#.Yi#.#.#...#...#.#.#.#...#\n" +
            "#.#.#######.###.###.#####.#.###.#.#.#.###.###.#######.#.#.#.#.###.#.###.#.#.###.#\n" +
            "#.#.......#...#.#.....#...#.#...#.#.#...#.#...#.......#.#.#.#...#.....#.#.#.#...#\n" +
            "#.#######.###.###.#.###.###.#####.#####.#.#.#.#.#######.#.#.###.#####.#.#.#.#####\n" +
            "#.#.....#.....#...#.#.#.........#.....#.#.#.#.#.#...#...#.#.#.#.#...#...#.#.....#\n" +
            "#.#.###.#######.#####.#########.#.#.###.#.#.#.#.#.###.###.#.#.#.#.#.#####.#####.#\n" +
            "#.#.#.....#.........#.#.....#.#.#.#b#...#.#.#.#.#.#..y#.#.#.#.#.#.#...#...#.....#\n" +
            "#.###.#####.#######.#.#.###.#.#.#.#.#.#####.###.#.#.###.#.###.#.#.###.#.###.###.#\n" +
            "#.E.#....d..#.......#...#.#.#.#.#.#.#...#...#...#.#...#.#...I.#...#..l#.#...#...#\n" +
            "###.#.#######.###########F#.#.#.###.###.#.#.#.###.###.#.#########.#.###.#.###J###\n" +
            "#...#...#...#...........#...#.......#...#.#.#.#.....#.G.#.......#.#.....#.#.#.#.#\n" +
            "#.#####.#.#########.#.#.#.#########.#.#.#.###.#####.###.#.###.#.#####.###.#.#.#.#\n" +
            "#..v..#...#.......#.#.#.#.#...#.....#.#.#...#.......#.#.#...#.#.....#.#...#.#...#\n" +
            "#####.#####.#####.###.###.#.#.#.#####.#.#Z#.#######X#.#.#####.#####.#.#.###.###.#\n" +
            "#...#.....#...#.....#...#...#.#.#...#.#.#.#.......#...#.....#.....#.#.#.#...#...#\n" +
            "#.#######.#.#.#####.###.#####.#.#.###.#.###.#.#####.#######.#.###.#.###.#.###.###\n" +
            "#.........#.#.#...#...#...#...#...#...#.#...#.#...#.#.R....g#.#...#.....#.#...#.#\n" +
            "#.###########.#.#.###.###.#.###.###.###.#.#####.#.#.#.#########.#########.#.###.#\n" +
            "#...#.#.....#...#.#.#.#...#..t#.#...#...#.#.....#...#...#.....#.#..n....#.M.#...#\n" +
            "###.#.#.#.#.#####.#.#.#.#####.###.#####.#.#.###########.#.###.#.#.#######.###.#.#\n" +
            "#...#...#.#.#..w#.#.#.#.....#...#.....#.#.#.......#.....#.#...#.#.#.D...#.....#.#\n" +
            "#C#######.#.#.#.#.#.#.###.#.###.#####.#.#.#######.#.#####.#.###.#.#.###.#######H#\n" +
            "#......o..#...#...#.......#...#.A.....#.#.........#r......#.....#.....#.........#\n" +
            "#################################################################################";

    public static String Input2 = "#################################################################################\n" +
            "#...............#.......#...............#.........#.......#.......#.....#.......#\n" +
            "#.#########.#.###.#####.#.#.#######.#####.#######.#.#######.#.###.###.#.#.#####.#\n" +
            "#.#.#.....#.#.#...#...#..f#...#.....#...#...#.....#.........#...#.....#...#m....#\n" +
            "#.#.#.###.###.#.#####.#######.#######.#.#.#.#.#######.#########.###########.#####\n" +
            "#...#...#.#...#.......#.....#.#.......#.#.#.#...#...#...#.......#..h......#.....#\n" +
            "###.###.#.#.#########.#.###.#T#.#######.###.###.#B#.#.###.#######.#######.#####.#\n" +
            "#...#...#...#.....#...#...#.#...#.....#.#...#.#...#.#.#...#.........#...#.......#\n" +
            "#####.#######.###.#.###.#.#######.###.#.#.###.#####.###.#.###########.#.#######.#\n" +
            "#...#...#.....#.#...#.#.#.....#...#.....#.#.....#...#...#.#...........#.#.....#.#\n" +
            "#.#.###.#.#####.#####.#.#####.#.###.#####.#.###.#.###.#####.###########.#.###.#.#\n" +
            "#.#.....#.#.....#.........#...#...#.#...#.#...#..x#...W.....#.....#.....#.#...#.#\n" +
            "#.#######.###.#.#########.#.#####.###.#.#.###.#######.#######.###.#.#####.#.#####\n" +
            "#.........#...#.......#.#.#.#...#.....#.#.#.#.#.....#...#.....#...#.#.....#.....#\n" +
            "#.#########O#########.#.#.#.#.#.#.#####.#.#.#.###.#.#.###.#####.###.#.#########.#\n" +
            "#.#.........#.........#...#.#.#.#...#.#.#.#.......#.#.#...#...#.#.......#.....#.#\n" +
            "#.#####.#########.#########.#.#.###.#.#.#.#########.###.###.###.#####.###.###.#.#\n" +
            "#.....#...#.....#.#.........#.#.#.....#.#.....#.....#...#...........#.#...#e..#.#\n" +
            "#####.###.#K###.#.#####.#####.#.#######.#####.#####.#.#.###########.###.###.###.#\n" +
            "#...#.....#.#...#.......#.....#.#.......#.....#...#...#.#.........#.....#.#.#...#\n" +
            "#.#####.###.#.#########.#.#####.#.#####.#.#####.#.#######.#####.#.#######.#.#.#.#\n" +
            "#.#j..#.#...#a..#.....#.#.#.......#.....#.......#...........#.#.#.......#.#...#.#\n" +
            "#.#.#.#.#.#####.#.#####.#.#############.#.###################.#########.#.#####.#\n" +
            "#...#.#.#.#...#...#.....#.#..z..#.....#.#.....#.......#.....#...#...#...#...#...#\n" +
            "#.###.#.#.###.#####.#####.#.###.#.###.#######.#.#.#####.###.###.#.#.#.###.###.###\n" +
            "#.#...#.#.....#.#...#...#.#...#.#.#.#...#.....#.#.....#...#.....#.#...#.#...#...#\n" +
            "###.###.#####.#.#.###.#.#.###.#.#.#.###.#.###########.###.#######.#####.#.#.###.#\n" +
            "#...#...#...#.#.......#.#.....#.#.....#.#.#.............#...#...#...#.....#.#...#\n" +
            "#.#.#.#####.#.#############.###.###.###.#.#.#########.#####.###.#.#.#####.#.#.###\n" +
            "#.#.#.#.....#.............#...#.....#...#.#.....#...#.#.....#...#.#.#...#.#.#.#.#\n" +
            "#.###.#.#.###############.###.#####.#.#.#.#####.#.#.#.#.#####.###.#.#.#.#.#.#.#.#\n" +
            "#.#...#.#.......#.........#...#...#.#.#.#.#.....#.#...#.#.........#.#.#.#.#...#.#\n" +
            "#.#.#######.###.#.#########.###.#.###.#.#.#.###########.#.#########.#.#.#####.#.#\n" +
            "#.#.......#...#.#.#.......#...#.#.#...#.#.#...#.......#.#.#.......#...#.#...#c..#\n" +
            "#.#######.#.#.###.###.###.#####.#.#.###.#.###.#.#####.#.###.#####.#.###.#.#.###.#\n" +
            "#.L..q..#.#.#...#...#.#.#.....#.#...#...#...#.....#...#.....#.....#...#...#.#...#\n" +
            "#.#######.#####.###.#.#.#####.#.#####.#####.#######S#########.#############.#.###\n" +
            "#...#...#.#.......#...#.#.....#.#...#...#...#.....#.....#...#.#.....#.....#k#...#\n" +
            "#.#.#.#.#.#.#####.#####.#.#####.#.#.###.#.###.###.###.#.###.#.#.###.#.###.#.###.#\n" +
            "#.#...#.....#...........#.......#.#....@#@....#.......#.....#...#.....#.....#...#\n" +
            "#################################################################################\n" +
            "#.#...#...........#u....#...#.......#..@#@..........#.....#...................#.#\n" +
            "#.#.#.#.#####.###.#.#.#.#.#.#.#.#####.#.#####.#####.#.###.#.###.#############.#.#\n" +
            "#.#.#...#...#...#.#.#.#.#.#...#.#s....#.#.....#...#.#.#.#..p#.#.#.....#.....#...#\n" +
            "#.#.#######.###.###.#.###.#####.#.#####.#.#######.#.#.#.#####.#.#.###.#.###.###.#\n" +
            "#...#.........#.#...#.#...#...#...#...#.#...#.....#...#.......#.#.#.#.....#.#.#.#\n" +
            "#.###.#.#####.#.#.###.#.###.#######.#.#.#.#.#.###.#####.#######.#.#.#######.#.#.#\n" +
            "#...#.#.#.....#...#...#.#...#.....#.#...#.#.#.#...#.....#.......#.#...#.V...#...#\n" +
            "###.###.#.#########.###.#.#.#.###.#.###.###.#.#####.#.###.#######.###.#.#####.###\n" +
            "#.#.....#.#.....#...#.....#.#...#...#...#...#.#.....#.......#...#.#...#.#.....#.#\n" +
            "#.#######.#N#.#.#.#.#.#########.#.#######.###.#.#############.#.#.#.#.#.#.#####.#\n" +
            "#.......#.#.#.#.#.#.#.#...#.....#.#.....#.#.........#.........#.#...#.#.#.....U.#\n" +
            "#.#######.###.#.#.###.#.#P#.#######.###.#.###.#######.#########.#####.#.#######.#\n" +
            "#.#.....#...#.#.#.....#.#...#.......#...#...#...#.....#.......#.......#.#...#...#\n" +
            "#.#.###.###.#.#########.###.#.#######.#####.###.#.#####.###.###########.#.#.#.###\n" +
            "#...#...#.#.#.#.....#...#...#.#.....#...#...#...#.......#...#.........#.#.#.#.#.#\n" +
            "#.###.#.#.#.#.#.#.###.#######.#.###.###.#.#####.#####.#####.#.#######.#.#.#Q#.#.#\n" +
            "#.#...#...#.#...#...#.....#...#.#...#...#.....#.....#.#.Yi#.#.#...#...#.#.#.#...#\n" +
            "#.#.#######.###.###.#####.#.###.#.#.#.###.###.#######.#.#.#.#.###.#.###.#.#.###.#\n" +
            "#.#.......#...#.#.....#...#.#...#.#.#...#.#...#.......#.#.#.#...#.....#.#.#.#...#\n" +
            "#.#######.###.###.#.###.###.#####.#####.#.#.#.#.#######.#.#.###.#####.#.#.#.#####\n" +
            "#.#.....#.....#...#.#.#.........#.....#.#.#.#.#.#...#...#.#.#.#.#...#...#.#.....#\n" +
            "#.#.###.#######.#####.#########.#.#.###.#.#.#.#.#.###.###.#.#.#.#.#.#####.#####.#\n" +
            "#.#.#.....#.........#.#.....#.#.#.#b#...#.#.#.#.#.#..y#.#.#.#.#.#.#...#...#.....#\n" +
            "#.###.#####.#######.#.#.###.#.#.#.#.#.#####.###.#.#.###.#.###.#.#.###.#.###.###.#\n" +
            "#.E.#....d..#.......#...#.#.#.#.#.#.#...#...#...#.#...#.#...I.#...#..l#.#...#...#\n" +
            "###.#.#######.###########F#.#.#.###.###.#.#.#.###.###.#.#########.#.###.#.###J###\n" +
            "#...#...#...#...........#...#.......#...#.#.#.#.....#.G.#.......#.#.....#.#.#.#.#\n" +
            "#.#####.#.#########.#.#.#.#########.#.#.#.###.#####.###.#.###.#.#####.###.#.#.#.#\n" +
            "#..v..#...#.......#.#.#.#.#...#.....#.#.#...#.......#.#.#...#.#.....#.#...#.#...#\n" +
            "#####.#####.#####.###.###.#.#.#.#####.#.#Z#.#######X#.#.#####.#####.#.#.###.###.#\n" +
            "#...#.....#...#.....#...#...#.#.#...#.#.#.#.......#...#.....#.....#.#.#.#...#...#\n" +
            "#.#######.#.#.#####.###.#####.#.#.###.#.###.#.#####.#######.#.###.#.###.#.###.###\n" +
            "#.........#.#.#...#...#...#...#...#...#.#...#.#...#.#.R....g#.#...#.....#.#...#.#\n" +
            "#.###########.#.#.###.###.#.###.###.###.#.#####.#.#.#.#########.#########.#.###.#\n" +
            "#...#.#.....#...#.#.#.#...#..t#.#...#...#.#.....#...#...#.....#.#..n....#.M.#...#\n" +
            "###.#.#.#.#.#####.#.#.#.#####.###.#####.#.#.###########.#.###.#.#.#######.###.#.#\n" +
            "#...#...#.#.#..w#.#.#.#.....#...#.....#.#.#.......#.....#.#...#.#.#.D...#.....#.#\n" +
            "#C#######.#.#.#.#.#.#.###.#.###.#####.#.#.#######.#.#####.#.###.#.#.###.#######H#\n" +
            "#......o..#...#...#.......#...#.A.....#.#.........#r......#.....#.....#.........#\n" +
            "#################################################################################";

    public static String Example1 = "#########\n" +
            "#b.A.@.a#\n" +
            "#########";

    public static String Example2 = "########################\n" +
            "#f.D.E.e.C.b.A.@.a.B.c.#\n" +
            "######################.#\n" +
            "#d.....................#\n" +
            "########################";

    public static String Example3 = "#################\n" +
            "#i.G..c...e..H.p#\n" +
            "########.########\n" +
            "#j.A..b...f..D.o#\n" +
            "########@########\n" +
            "#k.E..a...g..B.n#\n" +
            "########.########\n" +
            "#l.F..d...h..C.m#\n" +
            "#################";

    public static String Example4 = "########################\n" +
            "#@..............ac.GI.b#\n" +
            "###d#e#f################\n" +
            "###A#B#C################\n" +
            "###g#h#i################\n" +
            "########################";
}