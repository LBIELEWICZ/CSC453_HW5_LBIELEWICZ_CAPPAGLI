import java.util.*;
import java.io.*;

public class AdvancedJava {
	
	public void codeGen (String eval, String fileName) {
		EvalParser parse = new EvalParser();
		
		LinkedList<CodeGenTuple> codeGen = parse.getThreeAddr(eval);

		TreeMap<String,SymbolType> symTab;
		String str;
		
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter(fileName));	

			writer.write("#include <stdio.h>\n"+
					"#include <inttypes.h>\n"+
					"\n"+
					"int main(int argc, char **argv){\n"+
					"int64_t r1 = 0, r2 = 0, r3 = 0, r4 = 0, r5 = 0;\n"+
					"int64_t stack[100];\n"+
					"int64_t *sp = &stack[99];\n"+
					"int64_t *fp = &stack[99];\n"+
					"int64_t *ra = &&exit;\n"+
					"goto mainEntry;\n");

			symTab = parse.getGlobalSymTab();
			if(symTab.size() > 0){
				str = "int64_t";
				int temp = 0;
				for(Map.Entry<String,SymbolType> entry : symTab.entrySet()){
					String key = entry.getKey();
					SymbolType value = entry.getValue();
					str = str + " " + key + " = 0";
					temp++;
					if(temp < symTab.size()){
						str = str + ",";
					}
					else{
						str = str + ";\n";
					}
				}
				writer.write(str);
			}

			while(codeGen.peek != null){
				CodeGenTuple cur = codeGen.pop();
				symTab = cur.getSymTab();
				str = cur.getName() + ":\n" +
						"sp = sp - 2;\n" +
						"*(sp+2) = fp;\n" +
						"*(sp+1) = ra;\n" +
						"fp = sp;\n" +
						"sp = sp - " + Integer.toString(symTab.size()) + ";\n";
				writer.write(str);

				//TODO: Local Steps 3

				str = "sp = sp + " + Integer.toString(symTab.size()) + ";\n" +
						"fp = *(sp+2);\n" +
						"ra = *(sp+1);\n" +
						"sp = sp + 2;\n" +
						"goto *ra;\n";
				writer.write(str);
			}
		
			writer.write("exit:\n"+
					"return reserved;\n"+
					"}");
		} catch (IOException ie) {
			System.out.println("ERROR: File error");
			System.exit(1);
		}
	}
}
