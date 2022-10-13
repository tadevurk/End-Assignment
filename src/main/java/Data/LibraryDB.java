package Data;

import Model.Item;
import Model.Member;
import Model.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LibraryDB {

    public List<User> users;

    public List<Item> items;

    public List<Member> members;

    public LibraryDB() {
        users = new ArrayList<>();
        items = new ArrayList<>();
        members = new ArrayList<>();

        users.addAll(
                List.of(
                        new User(1, "Vedat", "Türk", "vedatturk", "12345"),
                        new User(2, "Riccardo", "Biggi", "riccobiggi", "12345")
                )
        );

        items.addAll(
                List.of(
                        new Item(1, null,false),
                        new Item(2,null,false)
                )
        );

        members.addAll(
                List.of(
                        new Member(1,"Vedat","Turk",LocalDate.of(1993,11,9)),
                        new Member(2,"Riccardo","Biggi",LocalDate.of(1996,9,12))
                )
        );
    }

    public List<User> getUsersList() {
        return users;
    }

    public List<Item> getItemsList() {
        return items;
    }
}
