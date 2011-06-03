package approved.test838545496;

import net.egork.numbers.IntegerUtils;
import net.egork.utils.Solver;
import net.egork.utils.io.InputReader;

import java.io.PrintWriter;

public class TaskC implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int side = in.readInt();
		int rookCount = in.readInt();
		if (rookCount > side) {
			out.println(0);
			return;
		}
		long[][] c = IntegerUtils.generateBinomialCoefficients(side);
		out.println(c[side][rookCount] * c[side][rookCount] * IntegerUtils.factorial(rookCount));
	}
}
