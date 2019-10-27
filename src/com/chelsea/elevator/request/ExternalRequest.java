package com.chelsea.elevator.request;

import com.chelsea.elevator.direction.Direction;

public class ExternalRequest extends Request{
    private Direction direction;
    public ExternalRequest (Direction direction, int floor) {
        super(floor);
        this.direction = direction;
    }

    public Direction getDirection () {
        return direction;
    }

}
