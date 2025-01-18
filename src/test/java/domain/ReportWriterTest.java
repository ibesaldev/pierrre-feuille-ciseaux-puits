package domain;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

import static domain.Symbol.PAPER;
import static domain.Symbol.ROCK;
import static org.assertj.core.api.Assertions.assertThat;

class ReportWriterTest {

    @Test
    void shouldReportHeader() throws IOException {
        StringWriter stringWriter = new StringWriter();

        try (ReportWriter reportWriter = ReportWriter.defaultWriter(stringWriter)) {

            reportWriter.reportHeader();

            assertThat(stringWriter.toString()).isEqualTo("RESULT,PLAYER,OPPONENT,WIN RATE,LOSE RATE,TIE RATE\n");
        }
    }

    @Test
    void shouldReportHeaderWithSpecifiedSymbols() throws IOException {
        StringWriter stringWriter = new StringWriter();
        var reportWriterBuilder = ReportWriter
                .writer(stringWriter)
                .dataSeparator(';')
                .decimalSeparator(',')
                .symbolListSeparator('|')
                .symbolListStartAndEnd('(', ')');

        try (ReportWriter reportWriter = reportWriterBuilder.build()) {

            reportWriter.reportHeader();

            assertThat(stringWriter.toString()).isEqualTo("RESULT;PLAYER;OPPONENT;WIN RATE;LOSE RATE;TIE RATE\n");
        }
    }

    @Test
    void shouldReport() throws IOException {
        Strategy strat1 = new Strategy(List.of(ROCK));
        Strategy strat2 = new Strategy(List.of(ROCK, PAPER));

        StringWriter stringWriter = new StringWriter();

        try (ReportWriter reportWriter = ReportWriter.defaultWriter(stringWriter)) {
            reportWriter.report(new EndedGameLoop(46, 33, 21, strat1, strat2));
            assertThat(stringWriter.toString()).isEqualTo("WIN,ROCK,ROCK PAPER,46.00,33.00,21.00\n");
        }

    }

    @Test
    void shouldReportWithSpecifiedSymbols() throws IOException {
        Strategy strat1 = new Strategy(List.of(ROCK));
        Strategy strat2 = new Strategy(List.of(ROCK, PAPER));

        StringWriter stringWriter = new StringWriter();
        var reportWriterBuilder = ReportWriter
                .writer(stringWriter)
                .dataSeparator(';')
                .decimalSeparator(',')
                .symbolListSeparator('|')
                .symbolListStartAndEnd('(', ')');

        try (ReportWriter reportWriter = reportWriterBuilder.build()) {
            reportWriter.report(new EndedGameLoop(46, 33, 21, strat1, strat2));
            assertThat(stringWriter.toString()).isEqualTo("WIN;(ROCK);(ROCK|PAPER);46,00;33,00;21,00\n");
        }
    }
}