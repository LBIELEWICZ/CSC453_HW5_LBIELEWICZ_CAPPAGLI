import java.util.*;

public class CodeGenTuple {

	private List<TACObject> threeAddrList;
	private TreeMap<String, EvalParser.SymbolType> symbolTable;
	String name;
	ASTNode funcRoot;

	public CodeGenTuple(String name) {
		this.name = name;
	}

	public void setSymTab(TreeMap<String, EvalParser.SymbolType> symTab) {
		this.symbolTable = symTab;
	}

	public void setList(List<TACObject> list) {
		this.threeAddrList = list;
	}

	public void setRoot(ASTNode root) {
		funcRoot = root;
	}

	public ASTNode getRoot() {
		return funcRoot;
	}

	public String toString() {
		String str = "";
		str += "FUNCTION NAME: " + name + "\n\n";
		for (int i = 0; i < threeAddrList.size(); i++) {
			str += threeAddrList.get(i);
		}
		str += "\n";
		
		str += "SYMBOL TABLE: " + symbolTable;
		return str;
	}
}
