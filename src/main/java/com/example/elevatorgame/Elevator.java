package com.example.elevatorgame;

import java.lang.Math;

public class Elevator {

    private static int travelMeter;
    private int currentFloor;
    private int maxFloor;
    private int minFloor;
    private MyStack people;
    private int capacity;
    private String exitingName;

    public Elevator() {
        this(4, 0, 10);
    }

    public Elevator(int size, int minFloor, int maxFloor) {
        people = new MyStack();
        currentFloor = 0;
        this.maxFloor = maxFloor;
        this.minFloor = minFloor;
        capacity = size;

    }

    public static int getTravelMeter() {
        return travelMeter;
    }


    public boolean enter(String name, int target) throws IllegalArgumentException{

        if(target < minFloor || target > maxFloor){
            throw new IllegalArgumentException("target " + target + " out of bounds.");
        }

        else if(!isFull()){
            ElevatorPerson ePerson = new ElevatorPerson(name,currentFloor,target);
            people.push((ElevatorPerson)ePerson);
            people.toString();
            return true;
        }
        else
            return false;
    }


    public void goToFloor(int floor){

        if(floor < minFloor || floor > maxFloor){
            throw new IllegalArgumentException("floor " + floor + " out of bounds.");
        }

        travelMeter = travelMeter + Math.abs(floor - currentFloor);
        currentFloor = floor;
        if(((ElevatorPerson)people.peek()).getTarget() == currentFloor) {
            exitingName = (((ElevatorPerson)people.peek()).name);
            System.out.println(((ElevatorPerson)people.pop()).name + " is out.");
        }

        System.out.println(this);
    }

    public void releaseEveryone() {
        while(!people.isEmpty()) {
            goToFloor(((ElevatorPerson)people.peek()).getTarget());
        }
    }

    public boolean isFull() {
        if(capacity == people.getSize())
            return true;

        return false;
    }



    public boolean isEmpty() {
        if(people.isEmpty())
            return true;

        return false;
    }



    public String toString() {
        return "Elevator is on floor: " + currentFloor + ", Number of people: " + people.getSize();
    }


    public int getCurrentFloor() {
        return currentFloor;
    }
    public int getMaxFloor() {
        return maxFloor;
    }
    public int getMinFloor() {
        return minFloor;
    }
    public Object getExitingName() {
        return exitingName;
    }
    public MyStack getPeople() {
        return people;
    }
    public int getCapacity() {
        return capacity;
    }
}
