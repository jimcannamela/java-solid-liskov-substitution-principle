package com.galvanize;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class Flight {
    private String departingAirport;
    private String arrivingAirport;
    private int capacity;
    private int weightLimit;
    protected ArrayList<Ticket> tickets = new ArrayList<>();

    public Flight(String departingAirport, String arrivingAirport, int capacity, int weightLimit) {
        this.departingAirport = departingAirport;
        this.arrivingAirport = arrivingAirport;
        this.capacity = capacity;
        this.weightLimit = weightLimit;
    }

    public String getDepartingAirport() {
        return departingAirport;
    }

    public String getArrivingAirport() {
        return arrivingAirport;
    }
    public int getWeightLimit() {
        return weightLimit;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void addTicket(Ticket ticket) {
        if (tickets.size() < getCapacity() && (tickets.stream().map(Ticket::getPassenger).mapToInt(Passenger::getWeight).sum() + ticket.getPassenger().getWeight() <= weightLimit)) {
            tickets.add(ticket);
        }
    }

    public int getCapacity() {
        return capacity;
    }

    public BigDecimal getRevenue() {
        return tickets.stream().map(Ticket::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
