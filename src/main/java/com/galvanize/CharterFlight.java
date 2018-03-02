package com.galvanize;

public class CharterFlight extends Flight {

    private static final int AVERAGE_FOOD_WEIGHT = 50;
    private int weightLimit;
    private boolean catered;

    public CharterFlight(String departingAirport, String arrivingAirport, int capacity, int weightLimit) {
        super(departingAirport, arrivingAirport, capacity);
        this.weightLimit = weightLimit;
    }

    public void addTicket(Ticket ticket) {
        if (totalWeight() + ticket.getPassenger().getWeight() <= weightLimit) {
            tickets.add(ticket);
        }
    }

    private int totalWeight() {
        int additionalWeight = isCatered() ? Passenger.AgeGroup.ADULT.getAverageWeight() + AVERAGE_FOOD_WEIGHT : 0;
        return tickets.stream().map(Ticket::getPassenger).mapToInt(Passenger::getWeight).sum() + additionalWeight;
    }

    public boolean isCatered() {
        return catered;
    }

    public void setCatered(boolean catered) {
        this.catered = catered;
    }
}
