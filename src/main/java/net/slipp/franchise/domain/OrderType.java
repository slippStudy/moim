package net.slipp.franchise.domain;

public enum OrderType {

    ASC_DATE {
        @Override
        int compare(Item a, Item b) {
            return a.getDate().compareTo(b.getDate());
        }
    };
    // Sort 조건에 맞게.
    abstract int compare(Item a, Item b);
}
