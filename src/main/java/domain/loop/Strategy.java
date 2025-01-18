package domain.loop;

import domain.Symbol;

import java.util.List;
import java.util.Random;

public record Strategy(List<Symbol> symbols) {
    Symbol selectSymbol() {
        return symbols.get(new Random().nextInt(symbols.size()));
    }

    @Override
    public String toString() {
        return symbols.toString();
    }
}
