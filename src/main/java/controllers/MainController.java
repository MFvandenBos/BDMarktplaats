package controllers;

import util.ScannerWrapper;

import javax.persistence.EntityManager;

import static util.Util.mysql;

public class MainController {

    private boolean useJPassword = false;
    private static MainController instance;
    private EntityManager em;
    private ScannerWrapper scanner;

    //static block initialization for exception handling
    static{
        try{
            System.out.println("Singleton is made");
            instance = new MainController("made");
        }catch(Exception e){
            throw new RuntimeException("Exception occurred in creating singleton instance");
        }
    }

    public static MainController getInstance(){
        if(instance == null){
            instance = new MainController();
        }
        return instance;
    }

    private MainController(){
        System.out.println("The MainController has been eaten by the GC monster");
        try {
            this.em = mysql();
        }catch (Exception e){
            e.printStackTrace();
        }
        scanner = new ScannerWrapper();
    }

    private MainController(String made){
        try {
            this.em = mysql();
        }catch (Exception e){
            e.printStackTrace();
        }
        scanner = new ScannerWrapper();
    }

    public EntityManager getEntityManager() {
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

    public boolean isUseJPassword() {
        return useJPassword;
    }

    public void setUseJPassword(boolean useJPassword) {
        this.useJPassword = useJPassword;
    }
}


