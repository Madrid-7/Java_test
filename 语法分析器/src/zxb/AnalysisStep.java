package zxb;

public class AnalysisStep {
	int analysisStep;      // ��������
	String analysisStack;  // ����ջ
	String remaindString;  // ʣ�����봮
    String informationSting; // �Ƶ����ò���ʽ��ƥ��
	
	public AnalysisStep() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @�Զ��幹�췽��
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