package com.galvanize;

public class Passenger {
    public enum AgeGroup {
        ADULT(173), CHILD(87);

        private final int averageWeight;

        AgeGroup(int weight) {
            this.averageWeight = weight;
        }

        public int getAverageWeight() {
            return averageWeight;
        }
    }

    private String name;
    private AgeGroup ageGroup;
    private int weight;

    public Passenger(String name, AgeGroup ageGroup) {
        this(name, ageGroup, ageGroup.getAverageWeight());
    }

    public Passenger(String name, AgeGroup ageGroup, int weight) {
        this.name = name;
        this.ageGroup = ageGroup;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Passenger passenger = (Passenger) o;

        if (weight != passenger.weight) return false;
        if (name != null ? !name.equals(passenger.name) : passenger.name != null) return false;
        return ageGroup == passenger.ageGroup;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (ageGroup != null ? ageGroup.hashCode() : 0);
        result = 31 * result + weight;
        return result;
    }

    @Override
    public String toString() {
        return String.format("%s (%s)", name, ageGroup);
    }
}
