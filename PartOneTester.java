import java.util.*;

public class PartOneTester {
	public static void main (String [] args) {
		EvalParser parse = new EvalParser();
		String eval = "private class test { int z; void main(){int z = 19; if(z < 12){z = 8;}}}";
		LinkedList<CodeGenTuple> codeGen = parse.getThreeAddr(eval);

		for (int i = 0; i < codeGen.size(); i++) {
			System.out.println(codeGen.get(i));
			System.out.println();
		}
	}
}
