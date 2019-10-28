### Elevator

current issue:
elevatorController is in its own thread.

HandleINternal request is writtent o check curr level compare to destination
level. currLevel may not have been updated by the other thread since its asynchronized


how to solve?
