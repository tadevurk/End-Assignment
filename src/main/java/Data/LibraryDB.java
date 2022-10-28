package Data;

import Model.Item;
import Model.Member;
import Model.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LibraryDB {

    private List<User> users = new ArrayList<>();
    private List<Item> items = new ArrayList<>();

    private List<Member> members = new ArrayList<>();

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }

    public LibraryDB() {
        readDataFromFiles();
    }

    public void readDataFromFiles(){
        readFromFile("Files/Users.dat", users);
        readFromFile("Files/Members.dat",members);
        readFromFile("Files/Items.dat",items);
    }
    public void writeDataToFiles(){
        writeDataToFile("Files/Users.dat",users);
        writeDataToFile("Files/Members.dat",members);
        writeDataToFile("Files/Items.dat",items);
    }

    private  <T> void readFromFile(String pathName, List<T> objectList){
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File(pathName)))){
            while (true){
                try {
                    T element = (T) ois.readObject();
                    objectList.add(element);
                } catch (EOFException e) {
                    //throw new RuntimeException(e);
                    break;
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private <T> void writeDataToFile(String pathName, List<T> objectList){
        try (FileOutputStream fos = new FileOutputStream(new File(pathName));
             ObjectOutputStream oos = new ObjectOutputStream(fos);) {

            for (T element : objectList) {
                oos.writeObject(element);
                //oos.flush();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
