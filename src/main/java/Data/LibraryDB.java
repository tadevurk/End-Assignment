package Data;

import Model.IsItemAvailable;
import Model.Item;
import Model.Member;
import Model.User;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LibraryDB {

    public List<User> users = new ArrayList<>();

    public List<Item> items = new ArrayList<>();

    public List<Member> members = new ArrayList<>();


    public LibraryDB() {
/*        users = new ArrayList<>();
        //items = new ArrayList<>();
        //members = new ArrayList<>();

        users.addAll(
                List.of(
                        new User(1, "Vedat", "Türk", "vedatturk", "12345"),
                        new User(2, "Riccardo", "Biggi", "riccobiggi", "12345")
                )
        );

        writeUserToFile();*/


        readDataFromFile();
    }

    public void readDataFromFile(){
        readUsersFromFile();
        readMembersFromFile();
        readItemsFromFile();
    }

    public void writeDataToFile(){
        writeUserToFile();
        writeMemberToFile();
        writeItemToFile();
    }


    // Reading data from separate files (Users, Members, Items)
    private void readUsersFromFile(){
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("Files/Users.dat")))){
            while (true){
                try {
                    User user = (User) ois.readObject();
                    users.add(user);
                } catch (EOFException e) {
                    //throw new RuntimeException(e);
                    break;
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
                //TODO: close the file here
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void readMembersFromFile(){
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("Files/Members.dat")))){
            while (true){
                try {
                    Member member = (Member) ois.readObject();
                    members.add(member);
                } catch (EOFException e) {
                    //throw new RuntimeException(e);
                    break;
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
                //TODO: close the file here
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void readItemsFromFile(){
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("Files/Item.dat")))){
            while (true){
                try {
                    Item item = (Item) ois.readObject();
                    items.add(item);
                } catch (EOFException e) {
                    break;
                    //throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
                //TODO: close the file here
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }




    // Writing data to separate files (Users, Members, Items)
    private void writeUserToFile() {
        try (FileOutputStream fos = new FileOutputStream(new File("Files/Users.dat"));
             ObjectOutputStream oos = new ObjectOutputStream(fos);) {

            for (User user : users) {
                oos.writeObject(user);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void writeMemberToFile() {
        try (FileOutputStream fos = new FileOutputStream(new File("Files/Members.dat"));
             ObjectOutputStream oos = new ObjectOutputStream(fos);) {

            for (Member member : members) {
                oos.writeObject(member);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void writeItemToFile() {
        try (FileOutputStream fos = new FileOutputStream(new File("Files/Item.dat"));
             ObjectOutputStream oos = new ObjectOutputStream(fos);) {

            for (Item item : items) {
                oos.writeObject(item);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void WriteDataToFile(){
        writeUserToFile();
        writeMemberToFile();
        writeItemToFile();
    }

    public List<User> getUsersList() {
        return users;
    }

    public List<Item> getItemsList() {
        return items;
    }
}
