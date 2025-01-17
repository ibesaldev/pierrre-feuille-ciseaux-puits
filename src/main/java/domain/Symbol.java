package domain;

import java.util.Map;

public enum Symbol {
    ROCK, SCISSORS, PAPER;

    static final Map<Symbol, Symbol> WINNING = Map.of(
        ROCK, SCISSORS,
        SCISSORS, PAPER,
        PAPER, ROCK
    );

    public int compare(Symbol other) {
        if (this == other) return 0;
        if (WINNING.get(this) == other) return 1;
        return -1;
    }
}
