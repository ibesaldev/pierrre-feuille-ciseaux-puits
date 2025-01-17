package domain;

import java.util.List;
import java.util.Map;

public enum Symbol {
    ROCK, SCISSORS, PAPER, WELL;

    static final Map<Symbol, List<Symbol>> WINNING = Map.of(
            ROCK, List.of(SCISSORS),
            SCISSORS, List.of(PAPER),
            PAPER, List.of(ROCK, WELL),
            WELL, List.of(ROCK, SCISSORS)
    );

    public int compare(Symbol other) {
        if (this == other) return 0;
        if (WINNING.get(this).contains(other)) return 1;
        return -1;
    }
}
