package domain.report;

import domain.loop.EndedGameLoop;
import domain.loop.Strategy;

import java.util.*;

public class BestStrategyCalculator {
    private final Map<Strategy, Stats> stats = new HashMap<>();

    public BestStrategyCalculator addLoop(EndedGameLoop endedGameLoop) {
        Strategy strat1 = endedGameLoop.strat1();
        Strategy strat2 = endedGameLoop.strat2();

        Stats stats1 = getStats(stats, strat1);
        Stats stats2 = getStats(stats, strat2);

        if (endedGameLoop.result().isTie()) {
            stats.put(strat1, stats1.addTie());
            stats.put(strat2, stats2.addTie());
        } else if (endedGameLoop.result().winner().equals(strat1)) {
            stats.put(strat1, stats1.addWin());
            stats.put(strat2, stats2.addLose());
        } else {
            stats.put(strat1, stats1.addLose());
            stats.put(strat2, stats2.addWin());
        }
        return this;
    }

    private static Stats getStats(Map<Strategy, Stats> map, Strategy strategy) {
        Stats value = map.get(strategy);
        return value == null ? Stats.empty() : value;
    }

    public List<Strategy> bestStrategy() {
        List<Map.Entry<Strategy, Stats>> entries = stats.entrySet().stream().toList();

        List<Strategy> bestStrategies = mutableListOf(entries.getFirst().getKey());

        for (int i = 1; i < entries.size(); i++) {
            Map.Entry<Strategy, Stats> current = entries.get(i);
            int comparator = current.getValue().compareTo(stats.get(bestStrategies.getFirst()));
            if (comparator == 0) {
                bestStrategies.add(current.getKey());
            } else if (comparator > 0) {
                bestStrategies = mutableListOf(current.getKey());
            }
        }

        return List.copyOf(bestStrategies);
    }

    private static List<Strategy> mutableListOf(Strategy strat) {
        List<Strategy> list = new ArrayList<>();
        list.add(strat);
        return list;
    }

    private static final class Stats implements Comparable<Stats> {
        private int wins;
        private int ties;
        private int loses;

        private Stats(int wins, int ties, int loses) {
            this.wins = wins;
            this.ties = ties;
            this.loses = loses;
        }

        private static Stats empty() {
            return new Stats(0, 0, 0);
        }

        private Stats addWin() {
            wins++;
            return this;
        }

        private Stats addTie() {
            ties++;
            return this;
        }

        private Stats addLose() {
            loses++;
            return this;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) return true;
            if (obj == null || obj.getClass() != this.getClass()) return false;
            var that = (Stats) obj;
            return this.wins == that.wins &&
                    this.ties == that.ties &&
                    this.loses == that.loses;
        }

        @Override
        public int hashCode() {
            return Objects.hash(wins, ties, loses);
        }

        @Override
        public int compareTo(Stats other) {
            int compareWins = Integer.compare(other.loses, this.loses);
            return compareWins != 0 ? compareWins : Integer.compare(other.loses, this.loses);
        }
    }
}
