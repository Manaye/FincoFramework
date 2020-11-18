package edu.mum.cs.cs525.labs.exercises.framework.controller.sender_observers;

import java.util.Observable;
import java.util.Observer;

public class EmailSender implements Observer {
    
    private Observable observable;
    EmailSender(Observable observable){
        this.observable = observable;
        observable.register(this);
    }
    @Override
    public void update(Observable o, Object arg) {
        SYstem.out.println();
    }
}
