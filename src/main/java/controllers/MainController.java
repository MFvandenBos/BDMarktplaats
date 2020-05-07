package controllers;

import util.ScannerWrapper;

import javax.persistence.EntityManager;

import static util.Util.mysql;

public class MainController {

    private static MainController instance;
    private EntityManager em;
    private ScannerWrapper scanner;

    //static block initialization for exception handling
    static{
        try{
            instance = new MainController();
        }catch(Exception e){
            throw new RuntimeException("Exception occurred in creating singleton instance");
        }
    }

    public static MainController getInstance(){
        return instance;
    }

    private MainController(){
        this.em = mysql();
        scanner = new ScannerWrapper();
    }

    public EntityManager getEm() {
        return em;
    }

    public ScannerWrapper getScanner() {
        return scanner;
    }

    //to be used in test scope
    public void setScanner(ScannerWrapper scanner) {
        this.scanner = scanner;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }
}


