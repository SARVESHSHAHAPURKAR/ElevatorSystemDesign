package org.example;

public class Floor {

    int floorNumber;
    ExternalButton externalButton;

    public Floor(int floorNumber, ExternalButton externalButton) {
        this.floorNumber = floorNumber;
        this.externalButton = externalButton;
    }

    public void pressButton(Direction direction){

        externalButton.externalButtonDispatcher.submitRequest(floorNumber, direction);

    }
}
