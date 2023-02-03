package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args){


        Building building = new Building();

        Elevator elevator = new Elevator();
        elevator.setDirection(Direction.UP);
        elevator.display = new Display();
        //elevator.setStatus(Status.STOPPED);
        //elevator.setCurrentFloor(floor0.floorNumber);

        ElevatorController elevatorController = new ElevatorController();
        elevatorController.setElevatorObj(elevator);

        List<Floor> floors = new ArrayList<Floor>();

        Floor floor0 = new Floor(0, new ExternalButton());
        floor0.externalButton.externalButtonDispatcher = new ExternalButtonDispatcher(Collections.singletonList(elevatorController));
        floors.add(floor0);
        Floor floor1 = new Floor(1, new ExternalButton());
        floor1.externalButton.externalButtonDispatcher = new ExternalButtonDispatcher(Collections.singletonList(elevatorController));
        floors.add(floor1);
        Floor floor2 = new Floor(2, new ExternalButton());
        floor2.externalButton.externalButtonDispatcher = new ExternalButtonDispatcher(Collections.singletonList(elevatorController));
        floors.add(floor2);
        Floor floor3 = new Floor(3, new ExternalButton());
        floor3.externalButton.externalButtonDispatcher = new ExternalButtonDispatcher(Collections.singletonList(elevatorController));
        floors.add(floor3);
        Floor floor4 = new Floor(4, new ExternalButton());
        floor4.externalButton.externalButtonDispatcher = new ExternalButtonDispatcher(Collections.singletonList(elevatorController));
        floors.add(floor4);

        InternalButton ib0 = new InternalButton();
        InternalButton ib1 = new InternalButton();
        InternalButton ib2 = new InternalButton();
        InternalButton ib3 = new InternalButton();
        InternalButton ib4 = new InternalButton();

        ib0.internalButtonDispatcher.controllers = Collections.singletonList(elevatorController);
        ib1.internalButtonDispatcher.controllers = Collections.singletonList(elevatorController);
        ib2.internalButtonDispatcher.controllers = Collections.singletonList(elevatorController);
        ib3.internalButtonDispatcher.controllers = Collections.singletonList(elevatorController);
        ib4.internalButtonDispatcher.controllers = Collections.singletonList(elevatorController);

        List<InternalButton> internalButtons = new ArrayList<InternalButton>();
        internalButtons.add(ib0);
        internalButtons.add(ib1);
        internalButtons.add(ib2);
        internalButtons.add(ib3);
        internalButtons.add(ib4);

        elevator.buttons = internalButtons;

        building.floors = floors;

        elevator.setCurrentFloor(floor0.floorNumber);


        floor2.pressButton(Direction.UP);
        elevator.pressButton(ib1, floor1.floorNumber);
        elevator.pressButton(ib2, floor2.floorNumber);
        floor3.pressButton(Direction.UP);
        floor4.pressButton(Direction.DOWN);
        elevator.pressButton(ib3, floor3.floorNumber);
        floor1.pressButton(Direction.DOWN);
        floor0.pressButton(Direction.UP);

        elevatorController.controlElevator();

    }
}