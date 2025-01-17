package domain.utils;

import domain.Result;
import org.assertj.core.api.ObjectAssert;

import static org.assertj.core.api.Assertions.assertThat;

public final class Assert {
    public static <R> ObjectAssert<R> assertWinner(Result<R> result) {
        assertThat(result.isTie()).isFalse();
        return assertThat(result.winner());
    }
    public static <R> void assertIsTie(Result<R> result) {
        assertThat(result.isTie()).isTrue();
        assertThat(result.winner()).isNull();
    }
}