/**
 *
 * @author Ayman_Al-khateeb
 * @author Mr_Robot
 */
package Mineswipper_Game;
import javafx.util.Pair;
public class Player {
    // متغييرين يمثلان مدخلات الاعب بعد التحقق منها
    static int row, col;
    // لمعرفة دور الكمبيوتر او لا
    static boolean skip_computer = false;


    //ادخال حركة اللاعب والتحقق منها
    static void play() {
        String r, c;
        System.out.println(Game_files.list_str.get(10));
            // له نتم ناخد منو حركات ليدخل وحدة صح
        while (true) {
            r = Game_files.cin.next();
            c = Game_files.cin.next();
            boolean a = false, b = false;
            try {
                for (int i = 1; i <= Game_files.num_rows; i++) {
                    if (i == Integer.parseInt(r)) {
                        row = i - 1;
                        a = true;
                    }
                }
                if (c.length() == 1)
                    for (int i = 0; i < Game_files.num_columns; i++)
                        if ((char) (i + 'A') == c.charAt(0)) {
                            col = i;
                            b = true;
                        }

                boolean op = true;
                if (a && b) {
                    if (Game_files.vis[row][col]) op = false;
                    else break;
                }
                if (!op) {

                    System.out.println(Game_files.list_str.get(18));
                } else {
                    System.out.println(Game_files.list_str.get(11));
                }
            } catch (Exception e) {
                System.out.println(Game_files.list_str.get(12));
            }
        }
        if (Game_files.inital_build) {
            // يتم البناء لمرة واحدة فقط
            Create_game.run_grid(Game_files.difficulty_level * Game_files.num_rows * Game_files.num_columns / 10, row, col);
        }
        // له نحطو false مشان ما يفوت مرة تانية عل if
        Game_files.inital_build = false;
        //تابع  بياخد o or p نوع الحركة
        event("");
    }

    static void event(String st) {
        String step = "";
        // هاد الشرط مشان اذا االكمبيوتر عم يلعب ما بدنا نخلي لمستخدم يأدخل حركة
        if (!skip_computer) {
            System.out.println(Game_files.list_str.get(15));
            step = Game_files.cin.next();
        } else {
            // الكمبيوتر دائما الحركة يلي له يساويها هية فتح خلية
            step = "o";
            //  مشان لما يتغير الدور ناخد حركة من اللاعب
            skip_computer = false;
        }
//اذا لحركة p
        if (step.equalsIgnoreCase("p")) {
            //تغيير الخلية في الغريد
            Game_files.grid[row][col] = 'P';
            //جمع النقاط للاعب
            if (Game_files.play_who % 2 == 0) {
                Game_files.points_one_player += (Game_files.mine_location[row][col] == 1) ? 5 : -1;
            } else {
                Game_files.points_two_player += (Game_files.mine_location[row][col] == 1) ? 5 : -1;
            }
        }
        //اذا لحركة o
        else {

//اذا كان في لغم
            if (Game_files.mine_location[row][col] == 1) {
                // تعليم الخلية
                Game_files.vis[row][col] = true;
                //تغيير الخلية
                Game_files.grid[row][col] = 'B';
                //زيادة عدد الخلايا المفتوحة
                Game_files.num_open_cell++;
                // طرح قيمة 250 واذا كان سالب له يخسر
                if (Game_files.play_who % 2 == 0) {
                    Game_files.points_one_player -= 250;
                    if (Game_files.points_one_player < 0) {
                        Save_Reload.delete_file();
                        game_over();
                        Create_game.print_grid();
                        System.out.println(Game_files.list_str.get(17) + Game_files.name_one_player + " hhhhhhh");
                        System.exit(0);
                    }
                } else {
                    if (Game_files.play_who % 2 == 1) {
                        Game_files.points_two_player -= 250;
                        if (Game_files.points_two_player < 0) {
                            Save_Reload.delete_file();
                            game_over();
                            Create_game.print_grid();
                            System.out.println(Game_files.list_str.get(17) + Game_files.name_two_player + " hhhhhhh");
                            System.exit(0);
                        }
                    }

                }
                // اذا كان بلخلية في عدد له نزيد عدد الخلايا المفتوحة و قيمة العدد للاعب يلي فتحها
            } else if (Game_files.cell_values[row][col] != 0) {
                Game_files.num_open_cell++;
                Game_files.vis[row][col] = true;
                if (Game_files.play_who % 2 == 0) {
                    Game_files.points_one_player += Game_files.cell_values[row][col];
                } else {
                    Game_files.points_two_player += Game_files.cell_values[row][col];
                }
                Game_files.grid[row][col] = String.valueOf(Game_files.cell_values[row][col]).charAt(0);
            } else {
                // اذا كانت الخلية فاضية\
                //الملئ بالطوفات
                Algorithms.DFS(row, col);

            }
        }
    }

    //التحقق من حالة الربح اذا كان عدد الالغام +الخلايا المفتوحة = الطول * العرض
    static void cheak_win() {
        if (Game_files.num_open_cell + Game_files.num_rows * Game_files.num_columns * Game_files.difficulty_level / 10 == Game_files.num_rows * Game_files.num_columns) {
           if(Game_files.points_one_player>=Game_files.points_two_player){
            System.out.println(Game_files.list_str.get(16)+Game_files.name_one_player);}
           else{
               System.out.println(Game_files.list_str.get(16)+Game_files.name_two_player);
            }
            Save_Reload.delete_file();
            System.exit(0);
        }

    }

    // طباعة الغام الغريد بعد تفجير لغم والخسارة
    static void game_over() {
        for (int i = 0; i < Game_files.num_rows; i++) {
            for (int j = 0; j < Game_files.num_columns; j++) {
                if (Game_files.mine_location[i][j] == 1) {
                    Game_files.grid[i][j] = 'B';
                } else if (Game_files.cell_values[i][j] != 0) {
                    Game_files.grid[i][j] = String.valueOf(Game_files.cell_values[i][j]).charAt(0);
                } else {
                    Game_files.grid[i][j] = 'A';
                }
            }
        }
    }

    static void auto_play_computer() {
        // جلب احداثيات لعبة الكمبيوتر
        Pair<Integer, Integer> p = Algorithms.play_computer();
        row = p.getKey();
        col = p.getValue();
        // حطيناه true مشان المستخد ما  ينطلب منو يادخل قيم
        skip_computer = true;
        System.out.println(Game_files.list_str.get(19) + Game_files.name_two_player);
        System.out.println(row + 1);
        System.out.println((char) (col + 'A'));
        event("o");

    }
}
