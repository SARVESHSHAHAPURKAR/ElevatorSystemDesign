package org.example;

import java.util.List;

import static org.example.Direction.DOWN;
import static org.example.Direction.UP;
import static org.example.Status.MOVING;
import static org.example.Status.STOPPED;

public class Elevator {

    Display display;
    int currentFloor;
    Direction direction;
    Status status;
    List<InternalButton> buttons;


    public int getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }


    public void pressButton(InternalButton button, int floor){

        if(floor> currentFloor){
            button.internalButtonDispatcher.submitRequest(this,floor, UP);
        }
        else{
            button.internalButtonDispatcher.submitRequest(this,floor,DOWN);
        }

    }

    public void move(int floor, Direction direction){

        this.setCurrentFloor(floor);
        this.setDirection(direction);
        this.display.floor = floor;
        this.display.direction = direction;
        this.setStatus(MOVING);

        System.out.println("Moving to "+floor+" in direction "+direction);
    }

    public void stop(){
        this.status = STOPPED;
        System.out.println("Elevator stopped");
    }
}
