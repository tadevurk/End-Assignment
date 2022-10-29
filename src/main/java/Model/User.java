package Model;

import java.io.Serializable;

public class User implements Serializable {
    private Integer id;
    private String name;
    private String lastName;
    private String userName;
    private String password;

    public User(Integer id, String name, String lastName, String userName, String password) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
    }

    @Override
    public String toString() {
        return
                 name + " " +
                 lastName
                ;
    }

    public void getId(int id){
        this.id = id;
    }
    public String getName(){
        return name;
    }
    public String getLastName(){
        return lastName;
    }
    public String getUserName(){
        return userName;
    }
    public String getPassword(){
        return password;
    }
}
