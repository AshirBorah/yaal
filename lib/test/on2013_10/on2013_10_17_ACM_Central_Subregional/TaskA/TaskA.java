package on2013_10.on2013_10_17_ACM_Central_Subregional.TaskA;



import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int[] size = IOUtils.readIntArray(in, 3);
		out.printLine(ArrayUtils.maxElement(size));
    }
}
