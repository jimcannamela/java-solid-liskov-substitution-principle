package com.galvanize;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class Flight {
    private String departingAirport;
    private String arrivingAirport;
    private int capacity;
    protected ArrayList<Ticket> tickets = new ArrayList<>();

    public Flight(String departingAirport, String arrivingAirport, int capacity) {
        this.departingAirport = departingAirport;
        this.arrivingAirport = arrivingAirport;
        this.capacity = capacity;
    }

    public String getDepartingAirport() {
        return departingAirport;
    }

    public String getArrivingAirport() {
        return arrivingAirport;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void addTicket(Ticket ticket) {
        if (tickets.size() < getCapacity()) {
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
