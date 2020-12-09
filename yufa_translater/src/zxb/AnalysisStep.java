package zxb;

public class AnalysisStep {
	int analysisStep;      // 分析步骤
	String analysisStack;  // 分析栈
	String remaindString;  // 剩余输入串
    String informationSting; // 推导所用产生式或匹配
	
	public AnalysisStep() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @自定义构造方法
	 * @param analysisStep
	 * @param analysisStack
	 * @param remainString
	 * @param informationString
	 */
	public AnalysisStep(int analysisStep, String analysisStack, String remainString, String informationString) {
		// TODO Auto-generated constructor stub
		this.analysisStep = analysisStep;
		this.analysisStack = analysisStack;
		this.remaindString = remainString;
		this.informationSting = informationString;
	}
}