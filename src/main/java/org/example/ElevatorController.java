package org.example;

import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

import static org.example.Direction.DOWN;
import static org.example.Direction.UP;

public class ElevatorController {

    int id;

    Elevator elevatorObj;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Elevator getElevatorObj() {
        return elevatorObj;
    }

    public void setElevatorObj(Elevator obj) {
        this.elevatorObj = obj;
    }

    // Min-heap
    PriorityQueue<Integer> upCalls = new PriorityQueue<>();

    //Max-heap
    PriorityQueue<Integer> downCalls = new PriorityQueue<>(Collections.reverseOrder());

    Queue<Integer> pendingCalls = new LinkedList<Integer>();

    public void acceptNewRequest(Direction direction, int floor){

        //System.out.println("Request accepted for "+direction+" and floor "+floor);

        // ALGORITHM

        // IF LIFT IS GOING UP, AND YOU GET AN UP-DIRECTION CALL FROM ABOVE FLOOR, THEN PUT THAT
        // CALL IN A MIN HEAP(LOWEST TO BE ATTENDED FIRST).
        // IF YOU GET A DOWN-DIRECTION CALL FROM ANY FLOOR,
        // THEN PUT IT IN MAX HEAP(HIGHEST TO BE ATTENDED FIRST)
        // eg: LIFT IS GOING UPWARDS FROM 5TH FLOOR,
        // AND YOU GET UP-DIRECTION CALLS FROM FLOOR 9 AND 11. THEN LIFT WILL FIRST STOP AT FLOOT 9,
        // AND THEN 11.
        // SIMILARLY, IF LIFT IS GOING DOWN AND YOU GET DOWN CALLS FROM LOWER FLOORS, PUT THEM IN
        // MAX HEAP. IF YOU GET UP CALLS FROM ANY FLOORS, PUT THEM IN MIN HEAP

        // IF LIFT IS GOING DOWN AND YOU GET DOWN CALLS FROM ANY OF THE ABOVE FLOORS, OR THE LIFT IS
        // GOING UP AND YOU GET UP-DIRECTION CALLS FROM ANY OF THE LOWER CALLS, PUT ALL THOSE IN THE
        // PENDING QUEUE

        // WHILE THE LIFT IS MOVING UP, STOP AT THE FLOORS IN MIN HEAP AND KEEP POPPING THEM,
        //UNTIL IT IS EMPTY. ONCE EMPTY, PUT ALL THE PENDING CALLS INTO THE MIN HEAP, AND NOW
        // START MVING DOWN TILL MAX HEAP IS EMPTY. ONCE EMPTY, AGAIN CHANGE DIRECTION, AND
        // PUT ALL THE PENDING CALLS IN THE MAX HEAP..... AND KEEP DOING SO ON.


        if(elevatorObj.getDirection()==UP && direction==DOWN){
            downCalls.add(floor);
        }
        else if(elevatorObj.getDirection()==UP && direction==UP){
            if(floor< elevatorObj.getCurrentFloor()){
                pendingCalls.add(floor);
            }
            else if(floor> elevatorObj.getCurrentFloor()){
                upCalls.add(floor);
            }
        }

        else if(elevatorObj.getDirection()== DOWN && direction==UP){
            upCalls.add(floor);
        }

        else if(elevatorObj.getDirection()==DOWN && direction==DOWN){
            if(floor>elevatorObj.getCurrentFloor()){
                pendingCalls.add(floor);
            }
            else if(floor< elevatorObj.getCurrentFloor()){
                downCalls.add(floor);
            }

        }

        /*System.out.println("up calls size is "+upCalls.size());
        System.out.println("down calls size is "+downCalls.size());
        System.out.println("pending calls size is "+pendingCalls.size());*/
    }

    public void controlElevator(){

        while(true){
            //System.out.println("Entered thr loop");
            while(!upCalls.isEmpty()){
                //System.out.println("Inside upcall");
                int nextUpperFloor = upCalls.poll();

                elevatorObj.move(nextUpperFloor,UP);
                elevatorObj.stop();
            }

            while(!pendingCalls.isEmpty()){
                int nextUpperFloor = pendingCalls.poll();
                upCalls.add(nextUpperFloor);
            }

            while(!downCalls.isEmpty()){
                //System.out.println("Inside down call");
                int nextLowerFloor = downCalls.poll();
                elevatorObj.move(nextLowerFloor,DOWN);
                elevatorObj.stop();
            }

            while(!pendingCalls.isEmpty()){
                int nextLowerFloor = pendingCalls.poll();
                downCalls.add(nextLowerFloor);
            }


        }

    }
}
