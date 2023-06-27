/**
 *
 * @author Ayman_Al-khateeb
 * @author Mr_Robot
 */
package Mineswipper_Game;
import java.io.*;
public class Save_Reload {
    // للتحقق من وجود ملف اذا وجد ملف اي يوجد لعبة سابقة يمكن استعادتها
    static boolean found() {
        if (Game_files.last_game.exists() && !Game_files.last_game.isDirectory()) {
            return true;
        } else return false;
    }

    static void create_file() {
        try {
            Game_files.last_game.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
// لحذف الملف في حال الربح او الخسارة او انشاء لعبة جديدة ويوجد لعبة قديمة محفوظة
    static void delete_file() {
        Game_files.last_game.delete();
    }

    // لحفط كل ملفات اللعبة المطلوبة لاستعادة اللعبة
    static void svae_game() {
        PrintWriter s;
        try {
            s = new PrintWriter("last_game.txt");
            s.println(Game_files.num_rows);
            s.println(Game_files.num_columns);
            s.println(Game_files.difficulty_level);
            s.println(Game_files.num_open_cell);
            s.println(Game_files.type_player);
            s.println(Game_files.inital_build);
            s.println(Game_files.play_who);
            s.println(Game_files.points_one_player);
            s.println(Game_files.points_two_player);
            s.println(Game_files.name_one_player);
            s.println(Game_files.name_two_player);
            for (int i = 0; i < Game_files.num_rows; i++)
                for (int j = 0; j < Game_files.num_columns; j++)
                    s.print(Game_files.grid[i][j]);
            s.println();
            for (int i = 0; i < Game_files.num_rows; i++)
                for (int j = 0; j < Game_files.num_columns; j++)
                    s.print(Game_files.mine_location[i][j]);
            s.println();
            for (int i = 0; i < Game_files.num_rows; i++)
                for (int j = 0; j < Game_files.num_columns; j++)
                    s.print(Game_files.cell_values[i][j]);
            s.println();
            for (int i = 0; i < Game_files.num_rows; i++)
                for (int j = 0; j < Game_files.num_columns; j++) {
                    if (Game_files.vis[i][j]) {
                        s.print("1");
                    } else {
                        s.print("0");
                    }
                }
            s.close();
        } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }

    // استعادة اخر لعبة
    static void reload() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(Game_files.last_game));
            String str;
            int n = 0;
            while ((str = br.readLine()) != null) {
                n++;
                if (n == 1) {
                    Game_files.num_rows = Integer.parseInt(str);
                }
                //
                else if (n == 2) {
                    Game_files.num_columns = Integer.parseInt(str);
                }
                //
                else if (n == 3) {
                    Game_files.difficulty_level = Integer.parseInt(str);
                }
                //
                else if (n == 4) {
                    Game_files.num_open_cell = Integer.parseInt(str);
                }
                //
                else if (n == 5) {
                    Game_files.type_player = str;
                }
                //
                else if (n == 6) {
                    Game_files.inital_build = Boolean.parseBoolean(str);
                }
                //
                else if (n == 7) {
                    Game_files.play_who = Integer.parseInt(str);
                }
                //
                else if (n == 8) {
                    Game_files.points_one_player = Integer.parseInt(str);
                }
                //
                else if (n == 9) {
                    Game_files.points_two_player = Integer.parseInt(str);
                }
                //
                else if (n==10) {
                    Game_files.name_one_player = str;
                }
                else if (n==11){
                    Game_files.name_two_player = str;

                }
                else if (n == 12) {
                    int k = 0;
                    for (int i = 0; i < Game_files.num_rows; i++)
                        for (int j = 0; j < Game_files.num_columns; j++)
                            Game_files.grid[i][j] = str.charAt(k++);
                }
                //
                else if (n == 13) {
                    int k = 0;
                    for (int i = 0; i < Game_files.num_rows; i++)
                        for (int j = 0; j < Game_files.num_columns; j++)
                            Game_files.mine_location[i][j] = Integer.parseInt(str.charAt(k++) + "");
                }
                //
                else if (n == 14) {
                    int k = 0;

                    for (int i = 0; i < Game_files.num_rows; i++)
                        for (int j = 0; j < Game_files.num_columns; j++)
                            Game_files.cell_values[i][j] = Integer.parseInt(str.charAt(k++) + "");
                }
                //
                else if (n == 15) {
                    int k = 0;
                    for (int i = 0; i < Game_files.num_rows; i++)
                        for (int j = 0; j < Game_files.num_columns; j++) {
                            if (str.charAt(k++) == '0') {
                                Game_files.vis[i][j] = false;
                            } else {
                                Game_files.vis[i][j] = true;
                            }
                        }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage() + "err");
        }
    }
}


