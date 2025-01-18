import domain.loop.Strategy;

import java.util.List;

import static domain.Symbol.*;

public final class Strategies {
    public final static List<Strategy> STRATEGIES = List.of(
            new Strategy(List.of(ROCK, PAPER, SCISSORS, WELL)),
            new Strategy(List.of(PAPER, SCISSORS, WELL)),
            new Strategy(List.of(ROCK, PAPER, SCISSORS)),
            new Strategy(List.of(ROCK, PAPER, WELL)),
            new Strategy(List.of(ROCK, SCISSORS, WELL)),
            new Strategy(List.of(PAPER, WELL)),
            new Strategy(List.of(ROCK, PAPER)),
            new Strategy(List.of(ROCK, SCISSORS)),
            new Strategy(List.of(ROCK, WELL)),
            new Strategy(List.of(PAPER, SCISSORS)),
            new Strategy(List.of(SCISSORS, WELL)),
            new Strategy(List.of(ROCK)),
            new Strategy(List.of(PAPER)),
            new Strategy(List.of(SCISSORS)),
            new Strategy(List.of(WELL))
    );

}
