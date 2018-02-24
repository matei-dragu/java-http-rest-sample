package com.sampleapp.vavr.functions;

import io.vavr.Function1;
import io.vavr.Function2;
import io.vavr.Function5;
import io.vavr.control.Option;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.BDDAssertions.then;

class FunctionsTest {

    @Test
    void simpleFunctionTest() {
        Function1<Long, Long> plusOne = in -> in + 1;
        Function2<Long, Long, Long> addition = (first, second) -> first + second;

        then(plusOne.apply(3L)).isEqualTo(4L);
        then(addition.apply(2L, 3L)).isEqualTo(5L);
    }

    @Test
    void compositionAndThenTest() {
        Function1<Long, Long> plusOne = in -> in + 1;
        Function1<Long, Long> multiplyByTwo = in -> in * 2L;

        // (first function to apply) andThen (second function to apply)
        Function1<Long, Long> addOneAndMultiplyByTwo = plusOne.andThen(multiplyByTwo);

        then(addOneAndMultiplyByTwo.apply(2L)).isEqualTo(6L);
    }

    @Test
    void compositionComposeTest() {
        Function1<Long, Long> plusOne = in -> in + 1;
        Function1<Long, Long> multiplyByTen = in -> in * 10L;

        // (second function to apply) compose (first function to apply)
        Function1<Long, Long> addOneAndMultiplyByTen = multiplyByTen.compose(plusOne);
        Function1<Long, Long> multiplyByTenAndAddOne = plusOne.compose(multiplyByTen);

        then(addOneAndMultiplyByTen.apply(2L)).isEqualTo(30L);
        then(multiplyByTenAndAddOne.apply(2L)).isEqualTo(21L);
    }

    @Test
    void liftingTest() {
        Function2<Long, Long, Long> longDivisionPartialFunction  = (a, b) -> a / b;
        Function2<Long, Long, Option<Long>> longDivisionLifted = Function2.lift(longDivisionPartialFunction);

        // = None
        Option<Long> result1 = longDivisionLifted.apply(1L, 0L);

        // = Some(2)
        Option<Long> result2 = longDivisionLifted.apply(6L, 2L);

        then(result1).isEqualTo(Option.none());
        then(result2.isDefined()).isTrue();
        then(result2.get()).isEqualTo(3L);
    }

    @Test
    void parameterFixingTest() {
        Function5<Long, Long, Long, Long, Long, Long> add5Longs = (a, b, c, d, e) -> a + b + c + d + e;

        // add5Longs(1L, 2L, 3L, c, d)
        // a fixed to 1L, b fixed to 2L, c fixed to 3L
        Function2<Long, Long, Long> add2LongsTo6 = add5Longs.apply(1L, 2L, 3L);

        // 11 + 13 + 6 = 30
        then(add2LongsTo6.apply(11L, 13L)).isEqualTo(30L);

        // Arity = number of function arguments
        then(add5Longs.arity()).isEqualTo(5);
        then(add2LongsTo6.arity()).isEqualTo(2);
    }
}
