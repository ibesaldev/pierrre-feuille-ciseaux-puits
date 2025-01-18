import domain.loop.EndedGameLoop;
import domain.loop.GameLoop;
import domain.loop.Strategy;
import domain.report.BestStrategyCalculator;
import domain.report.ReportWriter;

import java.io.FileWriter;
import java.io.IOException;

public class Main {

    public static final char FRAMACALC_DATA_SEPARATOR = '\t';
    public static final char FRAMACALC_DECIMAL_SEPARATOR = '.';

    public static void main(String[] args) {
        BestStrategyCalculator calculator = new BestStrategyCalculator();

        try (ReportWriter report = ReportWriter
                .writer(new FileWriter("game_results.txt"))
                .dataSeparator(FRAMACALC_DATA_SEPARATOR)
                .decimalSeparator(FRAMACALC_DECIMAL_SEPARATOR)
                .symbolListSeparator('|')
                .symbolListStartAndEnd('(', ')')
                .build()) {
            report.reportHeader();

            for (Strategy strategy1 : Strategies.STRATEGIES) {
                for (Strategy strategy2 : Strategies.STRATEGIES) {
                    if (!strategy1.equals(strategy2)) {
                        EndedGameLoop run = new GameLoop(strategy1, strategy2).run(10000);
                        calculator.addLoop(run);
                        report.report(run);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Best strategies: " + calculator.bestStrategy());
    }
}
