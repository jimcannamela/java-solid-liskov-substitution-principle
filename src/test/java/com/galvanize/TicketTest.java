package com.galvanize;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static com.galvanize.Passenger.AgeGroup;
import static org.junit.jupiter.api.Assertions.*;

class TicketTest {

    private Passenger passenger;
    private BigDecimal price = new BigDecimal("222.22");
    private Ticket ticket;

    @BeforeEach
    void setUp() {
        passenger = new Passenger("Ash", AgeGroup.ADULT);
        ticket = new Ticket(passenger, price);
    }

    @Test
    void getPassenger() {
        assertEquals(passenger, ticket.getPassenger());
    }

    @Test
    void getPrice() {
        assertEquals(price, ticket.getPrice());
    }

    @Test
    void toStringContainsPassengerAndPriceInformation() {
        String defaultToString = ticket.getClass().getName() + "@" + Integer.toHexString(ticket.hashCode());
        assertNotEquals(defaultToString, ticket.toString(), "Overridden toString value");

        assertEquals(true, ticket.toString().contains(passenger.toString()),
                String.format("toString \"%s\" contains passenger's info: \"%s\"", ticket, passenger));

        assertEquals(true, ticket.toString().contains(price.toString()),
                String.format("toString \"%s\" contains price: %s", ticket, price));
    }
}