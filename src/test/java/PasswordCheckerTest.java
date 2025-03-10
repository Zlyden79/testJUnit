import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class PasswordCheckerTest {
    PasswordChecker pwc;

    @BeforeEach
    public void init() {
        pwc = new PasswordChecker();
        pwc.setMinLegth(8);
        pwc.setMaxRepeats(3);
    }

    @AfterEach
    public void cleanUp(){
        pwc = null;
    }

    public static Stream<Arguments> parameterizedTestCalcMaxRepeats() {
        return Stream.of(
                Arguments.of("passsword", 3),
                Arguments.of("password", 2),
                Arguments.of("passwwwword", 4)
        );
    }

    public static Stream<Arguments> testVerify() {
        return Stream.of(
                Arguments.of("password", true),
                Arguments.of("passssword", false),
                Arguments.of("paswd", false)
        );
    }

    @ParameterizedTest
    @MethodSource
    void parameterizedTestCalcMaxRepeats(String password, int expected) {
        //arrange

        // act
        int result = pwc.calcMaxRepeats(password);
        // assert
        Assertions.assertEquals(expected, result);
    }

    @ParameterizedTest
    @MethodSource
    void testVerify(String password, boolean expected) {
        //arrange

        //act
        boolean result = pwc.verify(password);
        //assert
        Assertions.assertEquals(expected, result);
    }

    @Test
    void testSetMinLegth(){
        //arrange
        int minLength = -2;
        Class<IllegalArgumentException> expected = IllegalArgumentException.class;
        //act
        Executable exectable = () -> pwc.setMinLegth(minLength);
        //assert
        Assertions.assertThrowsExactly(expected, exectable);
    }
}