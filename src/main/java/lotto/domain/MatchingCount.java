package lotto.domain;

import java.util.Objects;

public class MatchingCount {

    private final int matchingCount;
    private static final int INIT = 0;
    private static final int ADD = 1;

    public MatchingCount() {
        this.matchingCount = INIT;
    }

    public MatchingCount(int matchingCount) {
        this.matchingCount = matchingCount;
    }

    public int getMatchingCount() {
        return this.matchingCount;
    }

    public MatchingCount addMatchingCount(boolean command) {
        if (command) {
            return new MatchingCount(this.matchingCount + ADD);
        }
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MatchingCount that = (MatchingCount) o;
        return matchingCount == that.matchingCount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(matchingCount);
    }

}
