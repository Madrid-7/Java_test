package zxb;

import java.util.Vector;

public class NonterminalSymbol {
	char symbol;
	Vector<StringBuffer> grammar = null;
	Vector<StringBuffer> first = null;
	Vector<StringBuffer> select = null;
	
	public NonterminalSymbol() {
		this.grammar = new Vector<StringBuffer>();
		this.first = new Vector<StringBuffer>();
		this.select = new Vector<StringBuffer>();
	}
	
	public NonterminalSymbol (char nodeStatue, String str) {
		StringBuffer strBuf = new StringBuffer(str);
		this.symbol = nodeStatue;
		this.grammar = new Vector<StringBuffer>();
		this.grammar.add(strBuf);
		this.first = new Vector<StringBuffer>();
		this.select = new Vector<StringBuffer>();
	}
}
