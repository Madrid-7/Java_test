package zxb;

import java.util.Iterator;
import java.util.Vector;

public class Resource {
	public Vector<NonterminalSymbol> LL1_Grammar = null;
	public Vector<AnalysisStep> AnaStep = null;
	Object[][] object = new Object[40][4];
	String[] indexName = {"步骤","分析栈","剩余输入串","推导所用产生式或匹配"};
	
	public Resource () {
		this.LL1_Grammar = new Vector<NonterminalSymbol>();
		this.AnaStep = new Vector<AnalysisStep>();
	}

	public void showTable() {
		Iterator<AnalysisStep> itr = this.AnaStep.iterator();
		int i = 0;
		AnalysisStep step = null;
		
		while (itr.hasNext()) {
			step = itr.next();
			this.object[i][0] = step.analysisStep;
			this.object[i][1] = step.analysisStack;
			this.object[i][2] = step.remaindString;
			this.object[i][3] = step.informationSting;
			i ++;
		}
	}
}
