/**
 *
 * @author Ayman_Al-khateeb
 * @author Mr_Robot
 */
package Mineswipper_Game;
import javafx.util.Pair;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Create_game {

    // انشاء الغريد وبقيم ابتدائية o
    static public void create_grid() {
        for (int i = 0; i < Game_files.num_rows; i++)
            for (int j = 0; j < Game_files.num_columns; j++)
                Game_files.grid[i][j] = 'o';
    }

// وضع الالغام في الغريد
    static void create_mine(int num_mine, int i, int j) {
        Set<Pair<Integer, String>> set_index_mine = new HashSet<>();
        int cnt = 0;

        while (true) {
            int x = new Random().nextInt(Game_files.num_rows);
            int y = new Random().nextInt(Game_files.num_columns);
            if (x != i && y != j) {
                set_index_mine.add(new Pair(x, y));
            }
            if (set_index_mine.size() > cnt) {
                cnt++;
                Game_files.mine_location[x][y] = 1;
            }
            if (set_index_mine.size() == num_mine) break;
        }
    }

// حساب قيم كل خلية في الغريد في حال كانت تمس الالغام
    static void cell_values() {
        for (int i = 0; i < Game_files.num_rows; i++) {
            for (int j = 0; j < Game_files.num_columns; j++) {
                if (Game_files.mine_location[i][j] == 1) {

                } else {
                    int sum = 0;

                    if (Game_files.mine_location[i][j + 1] == 1) sum++;

                    if (j - 1 >= 0) {
                        if (Game_files.mine_location[i][j - 1] == 1) sum++;
                    }

                    if (Game_files.mine_location[i + 1][j] == 1) sum++;

                    if (i - 1 >= 0) {
                        if (Game_files.mine_location[i - 1][j] == 1) sum++;
                    }

                    if (Game_files.mine_location[i + 1][j + 1] == 1) sum++;

                    if (i - 1 >= 0 && j - 1 >= 0) {
                        if (Game_files.mine_location[i - 1][j - 1] == 1) sum++;
                    }

                    if (j - 1 >= 0) {
                        if (Game_files.mine_location[i + 1][j - 1] == 1) sum++;
                    }

                    if (i - 1 >= 0) {
                        if (Game_files.mine_location[i - 1][j + 1] == 1) sum++;
                    }

                    Game_files.cell_values[i][j] = sum;
                }

            }
        }
    }
    // تابع يستدعي اخر تابعين فوق
    static void run_grid(int level, int i, int j) {
        create_mine(level, i, j);
        cell_values();
    }

//تابع لطباعة الغريد
    static void print_grid() {
        System.out.print("\t");
        for (int i = 0; i < Game_files.num_rows; i++) System.out.print((char) (i + 'A'));
        System.out.println();
        for (int i = 0; i < Game_files.num_rows; i++) {
            for (int j = 0; j < Game_files.num_columns; j++) {
                if (j == 0) System.out.print(i + 1 + "\t");

                System.out.print((Game_files.grid[i][j]));
            }
            System.out.println();
        }
        System.out.println();

    }
}
