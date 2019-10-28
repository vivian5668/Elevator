package com.chelsea.elevator.main;

import com.chelsea.elevator.direction.Direction;
import com.chelsea.elevator.elevator.Elevator;
import com.chelsea.elevator.elevatorController.ElevatorController;
import com.chelsea.elevator.request.ExternalRequest;
import com.chelsea.elevator.request.InternalRequest;

public class Main {

    public static void main(String args[]) {
        Elevator elevator = new Elevator(10);

        Thread controller = new ElevatorController(elevator);
        controller.start();

        elevator.printUpQueue();
        elevator.printDownQueue();
        elevator.handleExternalRequest(new ExternalRequest(Direction.UP, 5));
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        elevator.printUpQueue();
        elevator.printDownQueue();
        elevator.handleInternalRequest(new InternalRequest(7));



        elevator.handleExternalRequest(new ExternalRequest(Direction.DOWN, 3));
        elevator.printUpQueue();
        elevator.printDownQueue();

        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        elevator.printUpQueue();
        elevator.printDownQueue();
        elevator.handleInternalRequest(new InternalRequest(1));
        elevator.printUpQueue();
        elevator.printDownQueue();
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        elevator.handleExternalRequest(new ExternalRequest(Direction.UP, 8));
    }
}
