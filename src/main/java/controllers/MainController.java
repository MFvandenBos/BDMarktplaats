package controllers;

import javax.persistence.EntityManager;

import static util.Util.mysql;

public class MainController {

    private static MainController instance;
    private final EntityManager em;

    //static block initialization for exception handling
    static{
        try{
            instance = new MainController();
        }catch(Exception e){
            throw new RuntimeException("Exception occured in creating singleton instance");
        }
    }

    public static MainController getInstance(){
        return instance;
    }

    public MainController(){
        this.em = mysql();
    }



}


