package edu.mum.cs.cs525.labs.exercises.framework.controller.sender_observers;

import java.util.Observable;
import java.util.Observer;

public class Logger implements Observer {
    @Override
    public void update(Observable o, Object arg) {
        System.out.println("Log an update: " + o.toString());
    }
}
