package io.muic.ooc.fab.view;

import io.muic.ooc.fab.FieldStats;

public class Counter {
    private FieldStats stats;

    protected Counter(FieldStats stats) { this.stats = stats; }

    protected FieldStats update(Object animal) {
        stats.incrementCount(animal.getClass());
        return stats;
    }
}
