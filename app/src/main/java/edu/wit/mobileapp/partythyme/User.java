package edu.wit.mobileapp.partythyme;

import java.util.ArrayList;
import java.util.List;

public class User {

    public String username;
    public String email;
    public List<Plant> plantsAdded= new ArrayList<>();

    public User(String username, String email){
        this.username=username;
        this.email=email;

        //crreate database?


    }



}


