/**
 * @author Ayman_Al-khateeb
 * @author Mr_Robot
 */
package Mineswipper_Game;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

// كلاس للتخزين فقط وكله static للوصول بدون تعريف اوبجكت
public class Game_files {
    static Scanner cin = new Scanner(System.in);
    static File last_game = new File("last_game.txt");
    static int num_rows, num_columns, num_open_cell = 0, difficulty_level, play_who = 0, points_one_player = 0, points_two_player = 0;
    static boolean inital_build = true;
    static String type_player, name_one_player = "", name_two_player = "";
    static int[][] mine_location = new int[60][60];
    static int[][] cell_values = new int[60][60];
    static int[] idfs = {1, -1, 1, -1, 0, 0, 1, -1};
    static int[] jdfs = {1, -1, -1, 1, 1, -1, 0, 0};
    static char[][] grid = new char[60][60];
    static boolean[][] vis = new boolean[60][60];
    static List<String> list_str = new ArrayList<>(Arrays.asList(
            /*0*/ "Hello my friend,I hope you enjoy playing",
            /*1*/ "If you want to restore the last game you played, type Yes, otherwise press any letter",
            /*2*/ "The last game has been restored",
            /*3*/ "Sorry,there is no previous game",
            /*4*/"Please enter the length and then the width of the playing grid (10 ==> 50)",
            /*5*/"Please enter difficulty level 1, 2 or 3",
            /*6*/"Choose a number to select how to play\n1)Playing on my own\n2)Playing with my friend\n3)Playing with the computer",
            /*7*/ "Enter your name",
            /*8*/ "Enter the name of the first player",
            /*9*/"Enter the name of the second player",
            /*10*/"Please enter the Row number and then the Column letter and then Operation",
            /*11*/"Please adhere to the numbers and letter on the margins of the grid",
            /*12*/"Do not enter letters instead of numbers",
            /*13*/"Enter the letter (o) to open the cell",
            /*14*/"Enter (p) to mark the cell",
            /*15*/"Enter the movement",
            /*16*/"Well done, you won,",
            /*17*/"Game over :",
            /*18*/"An open cell is selected",
            /*19*/"The role of the player :"
    ));
}