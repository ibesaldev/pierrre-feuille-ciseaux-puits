package domain.report;

import domain.Result;
import domain.Symbol;
import domain.loop.EndedGameLoop;
import domain.loop.Strategy;

import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.stream.Collectors;

public final class ReportWriter implements AutoCloseable {
    private final Writer writer;
    private final char dataSeparator;
    private final char decimalSeparator;
    private final char symbolListSeparator;
    private final Character symbolListStart;
    private final Character symbolListEnd;

    private ReportWriter(Writer writer, char dataSeparator, char decimalSeparator, char symbolListSeparator,
                         Character symbolListStart, Character symbolListEnd) {
        this.writer = writer;
        this.dataSeparator = dataSeparator;
        this.decimalSeparator = decimalSeparator;
        this.symbolListSeparator = symbolListSeparator;
        this.symbolListStart = symbolListStart;
        this.symbolListEnd = symbolListEnd;
    }

    public static ReportWriter defaultWriter(final Writer writer) {
        return new ReportWriter(writer, ',', '.', ' ', null, null);
    }

    public void reportHeader() throws IOException {
        writer.write(String.join(String.valueOf(dataSeparator), "RESULT", "PLAYER", "OPPONENT", "WIN RATE", "LOSE RATE", "TIE RATE%n".formatted()));
    }

    public void report(EndedGameLoop loop) throws IOException {
        writer.write(
                String.join(String.valueOf(dataSeparator),
                        printWinner(loop.result(), loop.strat1()),
                        printSymbols(loop.strat1().symbols()),
                        printSymbols(loop.strat2().symbols()),
                        printRate(loop.winRate1()),
                        printRate(loop.winRate2()),
                        printRate(loop.tieRate()) + "%n".formatted()));
    }

    private String printRate(double v) {
        return "%.2f".formatted(v).replace('.', this.decimalSeparator);
    }

    private String printSymbols(List<Symbol> symbols) {
        return toString(this.symbolListStart) + symbols.stream().map(Symbol::toString).collect(Collectors.joining(String.valueOf(this.symbolListSeparator))) + toString(this.symbolListEnd);
    }

    private static String toString(Character character) {
        return character == null ? "" : character.toString();
    }

    private static String printWinner(Result<Strategy> result, Strategy strat1) {
        return result.isTie() ? "TIE" : result.winner().equals(strat1) ? "WIN" : "LOSE";
    }

    @Override
    public void close() throws IOException {
        this.writer.close();
    }

    public static ReportWriterCreator writer(final Writer writer) {
        return new Builder().writer(writer);
    }

    public sealed interface ReportWriterCreator permits Builder {
        ReportWriterCreator dataSeparator(final char dataSeparator);

        ReportWriterCreator decimalSeparator(final char decimalSeparator);

        ReportWriterCreator symbolListSeparator(final char symbolListSeparator);

        ReportWriterCreator symbolListStartAndEnd(final Character symbolListStart, final Character symbolListEnd);

        ReportWriter build();
    }

    private static final class Builder implements ReportWriterCreator {

        private Writer writer;

        private char dataSeparator;

        private char decimalSeparator;

        private char symbolListSeparator;

        private Character symbolListStart;

        private Character symbolListEnd;

        private Builder() {
        }

        private ReportWriterCreator writer(final Writer writer) {
            this.writer = writer;
            return this;
        }

        @Override
        public ReportWriterCreator dataSeparator(final char dataSeparator) {
            this.dataSeparator = dataSeparator;
            return this;
        }

        @Override
        public ReportWriterCreator decimalSeparator(final char decimalSeparator) {
            this.decimalSeparator = decimalSeparator;
            return this;
        }

        @Override
        public ReportWriterCreator symbolListSeparator(final char symbolListSeparator) {
            this.symbolListSeparator = symbolListSeparator;
            return this;
        }

        @Override
        public ReportWriterCreator symbolListStartAndEnd(final Character symbolListStart, final Character symbolListEnd) {
            this.symbolListStart = symbolListStart;
            this.symbolListEnd = symbolListEnd;
            return this;
        }

        @Override
        public ReportWriter build() {
            return new ReportWriter(writer, dataSeparator, decimalSeparator, symbolListSeparator, symbolListStart, symbolListEnd);
        }

    }

}
