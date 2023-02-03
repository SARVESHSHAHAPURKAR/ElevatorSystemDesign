package org.example;

import java.util.ArrayList;
import java.util.List;

public class ExternalButtonDispatcher {

    List<ElevatorController> controllers;

    public ExternalButtonDispatcher(){
        this.controllers = new ArrayList<ElevatorController>();
    }

    public ExternalButtonDispatcher(List<ElevatorController> controllers) {
        this.controllers = controllers;
    }

    public void submitRequest(int floor, Direction direction){

        // floor means the floor number on which the external button was pressed

        // For simplicity, we will use odd-even algo for dispatcher

        for(ElevatorController controller : controllers){

            //controller.acceptNewRequest(direction,floor); return;

            if(controller.getId()%2==0 && floor%2==0){
                controller.acceptNewRequest(direction,floor);
                return;
            }
            if(controller.getId()%2==1 && floor%2==1){
                controller.acceptNewRequest(direction,floor);
                return;
            }
        }
    }
}
