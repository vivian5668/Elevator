package com.chelsea.elevator.request;

public class InternalRequest extends Request{
    public InternalRequest (int floorNumber) {
        // this floor number is destination
        super(floorNumber);
    }
}
