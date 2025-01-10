import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PassTimeValidatorTest {

	PassTimeValidator passTimeValidator;

	@BeforeEach
	void setUp() {
		passTimeValidator = new PassTimeValidator();
	}

	@Test
	void passing_a_valid_amount_of_time() {
		assertTrue(passTimeValidator.validate("pass 12"));
	}

	@Test
	void passing_time_with_camel_case() {
		assertTrue(passTimeValidator.validate("pAsS 60"));
	}

	@Test
	void passing_a_invalid_amount_of_time() {
		assertFalse(passTimeValidator.validate("pass 0"));
	}

	@Test
	void passing_a_invalid_amount_of_time_2() {
		assertFalse(passTimeValidator.validate("pass 61"));
	}

	@Test
	void passing_a_negative_amount_of_time() {
		assertFalse(passTimeValidator.validate("pass -10"));
	}
}
