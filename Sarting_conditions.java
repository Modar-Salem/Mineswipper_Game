/**
 *
 * @author Ayman_Al-khateeb
 * @author Mr_Robot
 *
 */
package Mineswipper_Game;
public class Sarting_conditions {
    static void sarting_conditions() {

        System.out.println(Game_files.list_str.get(0));
        System.out.println(Game_files.list_str.get(1));
        String s = Game_files.cin.next();
        if (s.equalsIgnoreCase("Yes")) {
            if (Save_Reload.found()) {
                Save_Reload.reload();
                System.out.println(Game_files.list_str.get(2));
                Create_game.print_grid();
                return;
            } else {
                System.out.println(Game_files.list_str.get(3));
            }
        }

        if (Save_Reload.found()) Save_Reload.delete_file();
        //
        System.out.println(Game_files.list_str.get(4));
        Game_files.num_rows = Game_files.cin.nextInt();
        Game_files.num_columns = Game_files.cin.nextInt();
        if (Game_files.num_rows > 26 || Game_files.num_rows < 10) {
            Game_files.num_rows = 5;
        }
        if (Game_files.num_columns > 26 || Game_files.num_columns < 10) {
            Game_files.num_columns = 5;
        }
        //
        System.out.println(Game_files.list_str.get(5));
        Game_files.difficulty_level = Game_files.cin.nextInt();
        if (Game_files.difficulty_level > 3 || Game_files.difficulty_level < 1) Game_files.difficulty_level = 2;
        //
        System.out.println(Game_files.list_str.get(6));
        Game_files.type_player = Game_files.cin.next();
        if (Game_files.type_player.equalsIgnoreCase("1") || Game_files.type_player.equalsIgnoreCase("3")) {
            System.out.println(Game_files.list_str.get(7));
            Game_files.name_one_player = Game_files.cin.next();
            Game_files.name_two_player = "computer";
        } else {
            System.out.println(Game_files.list_str.get(8));
            Game_files.name_one_player = Game_files.cin.next();
            System.out.println(Game_files.list_str.get(9));
            Game_files.name_two_player = Game_files.cin.next();
        }
        //
        Save_Reload.create_file();
        System.out.println(Game_files.list_str.get(13));
        System.out.println(Game_files.list_str.get(14));
        Create_game.create_grid();
        Create_game.print_grid();
    }
}
