package old.approved.test525818105;

import net.egork.utils.old.checker.Checker;
import net.egork.utils.old.io.old.InputReader;
import java.util.Collections;
import java.util.Collection;
import net.egork.utils.old.test.Test;

public class TaskBChecker extends Checker {
	@Override
	public String check(InputReader input, InputReader expectedOutput, InputReader actualOutput) {
		return tokenCheck(expectedOutput, actualOutput);
	}

	@Override
	public double getCertainty() {
		return 0;
	}

	@Override
	public Collection<Test> generateTests() {
		return Collections.<Test>emptyList();
	}
}
