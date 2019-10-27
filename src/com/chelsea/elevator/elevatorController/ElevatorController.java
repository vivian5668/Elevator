package com.chelsea.elevator.elevatorController;

import com.chelsea.elevator.elevator.Elevator;
import com.chelsea.elevator.request.ExternalRequest;
import com.chelsea.elevator.request.InternalRequest;

public class ElevatorController extends Thread {
    private Elevator elevator;

    public ElevatorController(Elevator elevator) {
        this.elevator = elevator;
    }

    @Override
    public void run() {
        System.out.println("Inside : " + Thread.currentThread().getName());
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int nextStop = elevator.goToNextStop();
            System.out.println("at level: " + nextStop);
        }
    }

}
