package net.slipp.franchise.domain;

import java.util.ArrayList;
import java.util.List;

public class Curriculum implements Plan {

    private List<Item> items;

    public Curriculum(List<Item> items) {
        this.items = items;
    }

    public List<Item> getItems(OrderType type) {
        List<Item> _items = new ArrayList<>(items);
        _items.sort(type::compare);
        return _items;
    }
}
