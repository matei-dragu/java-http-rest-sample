package london.dragu.matei.java.http.rest.sample.vavr.values;

import io.vavr.Lazy;
import io.vavr.control.Option;
import io.vavr.control.Try;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static io.vavr.API.*;
import static io.vavr.Predicates.*;

import static org.assertj.core.api.BDDAssertions.then;

@Slf4j
class ValuesTest {

    @Test
    void optionValueTest() {
        Option<String> stringOption = Option.of("SomeString");

        then(stringOption.get()).isEqualTo("SomeString");
        then(stringOption).isEqualTo(Option.some("SomeString"));

        Option<String> mappedToNull = stringOption.map(s -> (String) null);
        log.info("stringOption: {} => mappedToNull: {}", stringOption, mappedToNull);

        then(mappedToNull).isEqualTo(Option.some(null));
    }

    @Test
    void optionOfNullValueTest() {
        Option<String> optionOfNull = Option.of(null);
        log.info("optionOfNull: {}", optionOfNull);

        Option<String> mappedOptionOfNull = optionOfNull.map(s -> s + "bar");
        log.info("mappedOptionOfNull: {}", mappedOptionOfNull);

        Option<String> maybeString = optionOfNull.flatMap(s -> Option.of(s).map(innerS -> innerS + "baz"));
        log.info("maybeString: {}", maybeString);

        then(optionOfNull).isEqualTo(Option.none());
        then(mappedOptionOfNull).isEqualTo(Option.none());
        then(maybeString).isEqualTo(Option.none());
    }

    @Test
    void tryTest() {
        Try<String> stringTry = Try.of(this::thisFunctionThrowsCheckedException);
        log.info("stringTry: {}", stringTry);
        then(stringTry.isSuccess()).isFalse();
        then(stringTry.isFailure()).isTrue();

        String out = stringTry.getOrElse("inCaseOfFailure");
        then(out).isEqualTo("inCaseOfFailure");
    }

    @Test
    void tryRecoverTest() {
        String tryRecoverResult = Try.of(this::thisFunctionThrowsCheckedException)
                .recover(throwable -> Match(throwable).of(
                        Case($(instanceOf(IOException.class)), ioException -> "IO Exception"),
                        Case($(instanceOf(InterruptedException.class)), interruptedException -> "Interrupted!"),
                        Case($(), innerThrowable -> "Throwable message was: " + innerThrowable.getMessage())
                ))
                .getOrElse("__ELSE__");

        log.info("tryRecoverResult: {}", tryRecoverResult);
        then(tryRecoverResult).isEqualTo("IO Exception");
    }

    private String thisFunctionThrowsCheckedException() throws Exception {
        throw new IOException("Checked Exception Thrown - IOException!");
    }

    @Test
    void lazyTest() {
        Lazy<Double> randomLazy = Lazy.of(Math::random);

        then(randomLazy.isEvaluated()).isFalse();

        Double currentRandom = randomLazy.get();

        then(randomLazy.isEvaluated()).isTrue();
        then(randomLazy.get()).isEqualTo(currentRandom); // memoized !!!
    }
}
