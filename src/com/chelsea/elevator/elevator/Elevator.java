package com.chelsea.elevator.elevator;

import com.chelsea.elevator.button.Button;
import com.chelsea.elevator.direction.Direction;
import com.chelsea.elevator.elevatorStatus.ElevatorStatus;
import com.chelsea.elevator.request.ExternalRequest;
import com.chelsea.elevator.request.InternalRequest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Elevator {
    private List<Button> buttons = new ArrayList<>();
    private PriorityQueue<Integer> upStops = new PriorityQueue<>();
    private PriorityQueue<Integer> downStops = new PriorityQueue<>((x, y) -> y - x);
    private int currentLevel;
    private ElevatorStatus status = ElevatorStatus.UP;
    private boolean isGateOpen;
    private float weightLimit;
    private int numOfFloors;

    public Elevator(int numOfFloors) {
        this.numOfFloors = numOfFloors;
    }

    public void handleExternalRequest(ExternalRequest r) {
        // judge if this is upstops / downstops, add to queue
        int destinationLevel = r.getFloorNumber();
        Direction direction = r.getDirection();
        if (direction == Direction.UP) {
            upStops.add(destinationLevel);
        } else if (direction == Direction.DOWN) {
            downStops.add(destinationLevel);
        }
    }

    public void handleInternalRequest(InternalRequest r) {
        int destinationLevel = r.getFloorNumber();
        System.out.println("cuur level is: " + currentLevel + " destinationLevel is: " + destinationLevel);

        if (destinationLevel > currentLevel) {
            upStops.add(destinationLevel);
        } else if (destinationLevel < currentLevel) {
            downStops.add(destinationLevel);
        } else {
            openGate();
        }
    }

    private void openGate() {
        System.out.println("Opening Gate....elevator at level " + currentLevel + " status: " + status);
    }

    public int goToNextStop() {
        System.out.println("elevator at level " + currentLevel + " status: " + status);

        int nextLevel = 0;

        if (status == ElevatorStatus.UP) {
            if (upStops.size() > 0) {
                nextLevel = upStops.poll();
            } else {
                if (downStops.size() > 0) {
                    status = ElevatorStatus.DOWN;
                    nextLevel = downStops.poll();
                }
            }
        } else if (status == ElevatorStatus.DOWN) {
            if (downStops.size() > 0) {
                nextLevel = downStops.poll();
            } else {
                if (upStops.size() > 0) {
                    status = ElevatorStatus.UP;
                    nextLevel = upStops.poll();
                }
            }
        } else {
            if (upStops.size() > 0) {
                status = ElevatorStatus.UP;
                nextLevel = upStops.poll();
            } else if (downStops.size() > 0) {
                status = ElevatorStatus.DOWN;
                nextLevel = downStops.poll();
            }
        }

        // if idle and no requests
        if (nextLevel == 0) {
            status = ElevatorStatus.IDLE;
            openGate();
            return currentLevel;
        }

        currentLevel = nextLevel;
        System.out.println("current-level: " + currentLevel);
        openGate();
        return nextLevel;
    }

    private void closeGate() {
        System.out.println("Closing Gate....elevator at level " + currentLevel + " status: " + status);
    }

    private boolean isRequestValid(InternalRequest r) {
        if (r.getFloorNumber() > numOfFloors || r.getFloorNumber() < 1) {
            return false;
        }
        return true;
    }

    public void printUpQueue() {
        System.out.println("upQueue is: " + Arrays.toString(upStops.toArray()));
    }

    public void printDownQueue() {
        System.out.println("downQueue is: " +  Arrays.toString(downStops.toArray()));
    }

}
