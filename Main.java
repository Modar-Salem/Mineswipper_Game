// * @author Ayman_Al-khateeb
// * @author Mr_Robot
package Mineswipper_Game;

import java.lang.*;

public class Main {
    public static void main(String[] args) {
        //  شروط البدء الحجم الاسماء .....
        Sarting_conditions.sarting_conditions();

        while (true) {
            // اذا كان نوع اللعب شحص واحد
            if (Game_files.type_player.equalsIgnoreCase("1")) {
                Player.play();
                //طباعة النقاط
                System.out.println("point " + Game_files.name_one_player + " : " + Game_files.points_one_player);
                //تابع يتحقق من الربح
                Player.cheak_win();

            } else {
                //في حال كان لاعبين
                if (Game_files.type_player.equalsIgnoreCase("2")) {
                    //اذا كان اللاعب الاول هو من يلعب
                    if (Game_files.play_who % 2 == 0) {
                        //طباعة دور من في اللعب
                        System.out.println(Game_files.list_str.get(19) + Game_files.name_one_player);
                        //إدخال لعب اللاعب الأول والتحقق منها
                        Player.play();
                        System.out.println("point " + Game_files.name_one_player + " : " + Game_files.points_one_player);
                        Player.cheak_win();
                        //دور اللاعب الثان
                    } else {
                        System.out.println(Game_files.list_str.get(19) + Game_files.name_two_player);
                        Player.play();
                        System.out.println("point " + Game_files.name_two_player + " : " + Game_files.points_two_player);
                        Player.cheak_win();
                    }
                    // اللعب مع الكمبيوتر
                } else {

                    if (Game_files.play_who % 2 == 0) {
                        System.out.println(Game_files.list_str.get(19) + Game_files.name_one_player);
                        Player.play();
                        System.out.println("point " + Game_files.name_one_player + " : " + Game_files.points_one_player);
                        Player.cheak_win();
                    } else {
                        //دور الكمبيوتر في اللعب
                        Player.auto_play_computer();
                        System.out.println("point " + Game_files.name_two_player + " : " + Game_files.points_two_player);
                    }
                }
                //تغيير دور اللاعب
                Game_files.play_who++;
            }
            // طباعة الخلايا (grid)
            Create_game.print_grid();
            //حفظ اللعب للتمكن من استعادتها
            Save_Reload.svae_game();
        }
    }
}
