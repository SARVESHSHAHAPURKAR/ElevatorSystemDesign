package org.example;

import java.util.ArrayList;
import java.util.List;

public class InternalButtonDispatcher {

    List<ElevatorController> controllers = new ArrayList<ElevatorController>();

    public void submitRequest(Elevator elevator, int floor, Direction direction){

        for(ElevatorController controller : controllers){

            if(controller.getElevatorObj()==elevator){
                controller.acceptNewRequest(direction, floor);
                return;
            }
        }

    }
}
