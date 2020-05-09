package controllers;

import controllers.exceptions.WrongInstanceException;
import domain.Gebruiker;
import frontend.console.AbstractMenu;

import java.io.FileNotFoundException;

public abstract class AbstractController {

    protected AbstractMenu currentView;

    abstract public void load();

    abstract public void load(Gebruiker gebruiker) throws WrongInstanceException;
}
