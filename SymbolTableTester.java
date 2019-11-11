public class SymbolTableTester {
	public static void main (String [] args) {
		MoreJava parser = new MoreJava();
		
		String eval = "private class test { int z; void main2(){ z = 12 ; } }";
		String result = "temp0 = 12\n"+
				"z = temp0\n";
		String test = parser.getThreeAddr(eval);
               // System.out.println(test);
                //System.out.println(result);
		//assert(parser.getThreeAddr(eval).equals(result));
		System.out.println("TEST 1 PASSED");

		parser = new MoreJava();
		eval = "private class test { int z; void main2(){z = 14;} int x; void main3(){x = 12; z = x;}}";
		result = "temp0 = 14\n"+
			"z = temp0\n"+
			"temp0 = 12\n"+
			"x = temp0\n"+
			"z = x\n";
		Scanner scan = new Scanner();
		//System.out.println(scan.extractTokens(eval));
		test = parser.getThreeAddr(eval);
		//System.out.println(test);
		//System.out.println(result);
		assert(test.equals(result));
		System.out.println("TEST 2 PASSED");

		parser = new MoreJava();
		eval = "public class test { int t; void main2(){t = 12; int t; t = 14;} void main(){int z; z = t;}}";
		result = "temp0 = 12\n"+
			"t = temp0\n"+
			"temp0 = 14\n"+
			"t = temp0\n"+
			"z = t\n";
		assert(parser.getThreeAddr(eval).equals(result));
		System.out.println("TEST 3 PASSED");

		parser = new MoreJava();
		eval = "private class test { void one(){int z = 12;} int z; void two(){z = 15;}}";
		result = "temp0 = 12\n"+
			"z = temp0\n"+
			"temp0 = 15\n"+
			"z = temp0\n";
		assert(parser.getThreeAddr(eval).equals(result));
		System.out.println("TEST 4 PASSED");

		parser = new MoreJava();
		eval = "private class test { int z; void main(){z = 19; if(z < 12){}}}";
		result = "temp0 = 19\n"+
			"z = temp0\n"+
			"temp0 = 12\n"+
			"IF_LT: z, temp0, trueLabel0\n"+
			"GOTO: falseLabel0\n"+
			"trueLabel0\n"+
			"falseLabel0\n";
		assert(parser.getThreeAddr(eval).equals(result));
		System.out.println("TEST 5 PASSED");

		parser = new MoreJava();
		eval = "private class test { int z; void main(){z = 19; if(z < 12){z = 8;}}}";
		result = "temp0 = 19\n"+
			"z = temp0\n"+
			"temp0 = 12\n"+
			"IF_LT: z, temp0, trueLabel0\n"+
			"GOTO: falseLabel0\n"+
			"trueLabel0\n"+
			"temp0 = 8\n"+
			"z = temp0\n"+
			"falseLabel0\n";
		assert(parser.getThreeAddr(eval).equals(result));
		System.out.println("TEST 6 PASSED");

		parser = new MoreJava();
		eval = "public class test { int z; void main(){z = 19; if(z < 12 && 3 <= 2){z = 8;}}}";
		result = "temp0 = 19\n"+
			"z = temp0\n"+
			"temp0 = 12\n"+
			"IF_LT: z, temp0, trueLabel1\n"+
			"GOTO: falseLabel0\n"+
			"trueLabel1\n"+
			"temp1 = 3\n"+
			"temp2 = 2\n"+
			"IF_LTE: temp1, temp2, trueLabel0\n"+
			"GOTO: falseLabel0\n"+
			"trueLabel0\n"+
			"temp0 = 8\n"+
			"z = temp0\n"+
			"falseLabel0\n";
		assert(parser.getThreeAddr(eval).equals(result));
		System.out.println("TEST 7 PASSED");

		parser = new MoreJava();
		eval = "private class test { int z; void main(){z = 19; if(z < 12 || 3 <= 2){z = 8;}}}";
		result = "temp0 = 19\n"+
			"z = temp0\n"+
			"temp0 = 12\n"+
			"IF_LT: z, temp0, trueLabel0\n"+
			"GOTO: falseLabel1\n"+
			"falseLabel1\n"+
			"temp1 = 3\n"+
			"temp2 = 2\n"+
			"IF_LTE: temp1, temp2, trueLabel0\n"+
			"GOTO: falseLabel0\n"+
			"trueLabel0\n"+
			"temp0 = 8\n"+
			"z = temp0\n"+
			"falseLabel0\n";
		assert(parser.getThreeAddr(eval).equals(result));
		System.out.println("TEST 8 PASSED");

		parser = new MoreJava();
		eval = "public class test {void main() {if(15 < 98 && (2 < 3)) {}}}";
		result = "temp0 = 15\n"+
			"temp1 = 98\n"+
			"IF_LT: temp0, temp1, trueLabel1\n"+
			"GOTO: falseLabel0\n"+
			"trueLabel1\n"+
			"temp2 = 2\n"+
			"temp3 = 3\n"+
			"IF_LT: temp2, temp3, trueLabel0\n"+
			"GOTO: falseLabel0\n"+
			"trueLabel0\n"+
			"falseLabel0\n";
		System.out.println(scan.extractTokens(eval));
		assert(parser.getThreeAddr(eval).equals(result));
		System.out.println("TEST 9 PASSED");

		parser = new MoreJava();
		eval = "public class test {void main() {if(15 < 98 && 2 < 3 || 4 > 5) {}}}";
		result = "temp0 = 15\n"+
			"temp1 = 98\n"+
			"IF_LT: temp0, temp1, trueLabel1\n"+
			"GOTO: falseLabel1\n"+
			"trueLabel1\n"+
			"temp2 = 2\n"+
			"temp3 = 3\n"+
			"IF_LT: temp2, temp3, trueLabel0\n"+
			"GOTO: falseLabel1\n"+
			"falseLabel1\n"+
			"temp4 = 4\n"+
			"temp5 = 5\n"+
			"IF_GT: temp4, temp5, trueLabel0\n"+
			"GOTO: falseLabel0\n"+
			"trueLabel0\n"+
			"falseLabel0\n";
//		System.out.println(parser.getThreeAddr(eval));
//		System.out.println(result);
//		assert(parser.getThreeAddr(eval).equals(result));
		System.out.println("TEST 10 PASSED");

		parser = new MoreJava();
		eval = "public class test {void main() {if(15 < 98 && (2 < 3)) {if(2 >3 || 15 > 98){}}}}";
		result = "temp0 = 15\n"+
			"temp1 = 98\n"+
			"IF_LT: temp0, temp1, trueLabel1\n"+
			"GOTO: falseLabel0\n"+
			"trueLabel1\n"+
			"temp2 = 2\n"+
			"temp3 = 3\n"+
			"IF_LT: temp2, temp3, trueLabel0\n"+
			"GOTO: falseLabel0\n"+
			"trueLabel0\n"+
			"temp0 = 2\n"+
			"temp1 = 3\n"+
			"IF_GT: temp0, temp1, trueLabel2\n"+
			"GOTO: falseLabel2\n"+
			"falseLabel2\n"+
			"temp2 = 15\n"+
			"temp3 = 98\n"+
			"IF_GT: temp2, temp3, trueLabel2\n"+
			"GOTO: falseLabel1\n"+
			"trueLabel2\n"+
			"falseLabel1\n"+
			"falseLabel0\n";
		assert(parser.getThreeAddr(eval).equals(result));
		System.out.println("TEST 11 PASSED");

		parser = new MoreJava();
		eval = "public class test {int y; int x; void main() { x = 12; y = 8 * (9-3); if(y < x && y != x){ } } }";
		result = "temp0 = 12\n"+
			"x = temp0\n"+
			"temp0 = 8\n"+
			"temp1 = 9\n"+
			"temp2 = 3\n"+
			"temp3 = temp1 - temp2\n"+
			"temp4 = temp0 * temp3\n"+
			"y = temp4\n"+
			"IF_LT: y, x, trueLabel1\n"+
			"GOTO: falseLabel0\n"+
			"trueLabel1\n"+
			"IF_NE: y, x, trueLabel0\n"+
			"GOTO: falseLabel0\n"+
			"trueLabel0\n"+
			"falseLabel0\n";
		assert(parser.getThreeAddr(eval).equals(result));
		System.out.println("TEST 12 PASSED");
	}
}
