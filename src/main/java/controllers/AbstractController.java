package controllers;

import domain.Gebruiker;
import frontend.console.AbstractMenu;

public abstract class AbstractController {

    protected AbstractMenu currentView;

    abstract public void load();

    abstract public void load(Gebruiker gebruiker);
}
