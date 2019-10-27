package com.chelsea.elevator.main;

import com.chelsea.elevator.direction.Direction;
import com.chelsea.elevator.elevator.Elevator;
import com.chelsea.elevator.elevatorController.ElevatorController;
import com.chelsea.elevator.request.ExternalRequest;

public class Main {

    public static void main(String args[]) {
        Elevator elevator = new Elevator(10);

        Thread controller = new ElevatorController(elevator);
        controller.start();

        elevator.handleExternalRequest(new ExternalRequest(Direction.UP, 5));
        elevator.handleExternalRequest(new ExternalRequest(Direction.UP, 3));
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        elevator.handleExternalRequest(new ExternalRequest(Direction.UP, 8));
    }
}
