package domain;

public record EndedGameLoop(int nbOfWins1, int nbOfWins2, int nbOfTies, Strategy strat1, Strategy strat2) {
    public Result<Strategy> result() {
        double winRate1 = winRate1();
        double winRate2 = winRate2();
        if (winRate1 > winRate2 + 3) {
            return Result.win(strat1);
        }
        if (winRate2 > winRate1 + 3) {
            return Result.win(strat2);
        }
        return Result.tie();
    }
    
    public double winRate1() {
        return (double) nbOfWins1 / nbOfGames() * 100;
    }

    public double winRate2() {
        return (double) nbOfWins2 / nbOfGames() * 100;
    }

    public double tieRate() {
        return (double) nbOfTies / nbOfGames() * 100;
    }

    private int nbOfGames() {
        return nbOfWins1 + nbOfWins2 + nbOfTies;
    }
}
