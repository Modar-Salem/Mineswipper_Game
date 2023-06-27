/**
 *
 * @author Ayman_Al-khateeb
 * @author Mr_Robot
 */
package Mineswipper_Game;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Algorithms {
    //الملئ بالطوفان
    static void DFS(int i, int j) {


        if (i < 0 || i > Game_files.num_rows - 1 || j < 0 || j > Game_files.num_columns - 1 || Game_files.vis[i][j] ||
                Game_files.mine_location[i][j] == 1 || Game_files.grid[i][j] == 'P' || Game_files.grid[i][j] == 'p') {
            return;
        }

        Game_files.vis[i][j] = true;

        if (Game_files.cell_values[i][j] != 0) {
            if (Game_files.play_who % 2 == 0) Game_files.points_one_player++;
            if (Game_files.play_who % 2 == 1) Game_files.points_two_player++;
            Game_files.num_open_cell++;

            Game_files.grid[i][j] = String.valueOf(Game_files.cell_values[i][j]).charAt(0);

            return;
        }

        for (int k = 0; k < 8; k++) DFS(Game_files.idfs[k]+i, Game_files.jdfs[k]+j);

            //تغيير الخلايا واحتساب النقاط
        if (Game_files.play_who % 2 == 0) {
            if (Game_files.grid[i][j] == 'o') {
                Game_files.points_one_player++;
                Game_files.grid[i][j] = 'F';
                Game_files.num_open_cell++;
            }
        } else {
            if (Game_files.grid[i][j] == 'o') {
                Game_files.points_two_player++;
                Game_files.grid[i][j] = 'S';
                Game_files.num_open_cell++;
            }
        }

    }

    static Pair<Integer, Integer> play_computer() {
        // اختيار لعبة عشوائية للكمبيوتر من ضمن الخلايا غير المفتوحة
        boolean boom=true;
        // المتغير البولياني استخدمتة لضمان وجود  قنبلة واحدة من بين خيارات الكمبيوتر لزيادة نسبة الربه للكمبيوتر
        List<Pair<Integer, Integer>> list_cell_not_open = new ArrayList<>();
        for (int i = 0; i < Game_files.num_rows; i++) {
            for (int j = 0; j < Game_files.num_columns; j++) {

                if (Game_files.grid[i][j] == 'o'&&(boom||Game_files.mine_location[i][j]==0)) {
                    if(Game_files.mine_location[i][j]==1)boom=false;
                    list_cell_not_open.add(new Pair<>(i, j));
                }

            }
        }
        //ارجاع خلية عشوائية من الخيارات المتاحة للكمبيوتر
        return list_cell_not_open.get(new Random().nextInt(list_cell_not_open.size()));
    }

}
