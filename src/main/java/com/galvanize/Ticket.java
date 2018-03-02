package com.galvanize;

import java.math.BigDecimal;

public class Ticket {
    private final Passenger passenger;
    private final BigDecimal price;

    public Ticket(Passenger passenger, BigDecimal price) {
        this.passenger = passenger;
        this.price = price;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ticket ticket = (Ticket) o;

        if (passenger != null ? !passenger.equals(ticket.passenger) : ticket.passenger != null) return false;
        return price != null ? price.equals(ticket.price) : ticket.price == null;
    }

    @Override
    public int hashCode() {
        int result = passenger != null ? passenger.hashCode() : 0;
        result = 31 * result + (price != null ? price.hashCode() : 0);
        return result;
    }
}
