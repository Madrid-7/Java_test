package zxb;

import java.io.BufferedReader;  
import java.io.FileReader; 
import java.util.Scanner;


public class MainExe {
	public char[] VT_symbol = null;
	int stepNO = 0;
	int loc_VN, loc_VT;
	char X; // 上托栈顶符号
	char a; // 当前终结符
	boolean continue_analysis = true; // 对符号串是否继续分析
	boolean debugButtonPressed = false;
	boolean succeed = false, falue = false;
	char[] VT = null;
	char[] VN = null;
	char[] VT_EXPAND = null;
	String[][] preAnalysisTable = null;
	StringBuffer sentence = null;       // 分析的字符串
	StringBuffer analysisStack = new StringBuffer(2);  // 分析栈
	//DialogView diaView = null;
	//****************************************************************************
	public String[][] LL1preAnalysisTable = null;
	public char[] VT_end_symbol = null;
	public char[] VN_symbol = null;
	public Resource resource = null;
	public CheckSyntax checkSyntax = new CheckSyntax();
	private String eachLine; //当前行字符
	private BufferedReader sourceFile;//文件
	public MainExe(){};
	private void readall(String originalFile) throws Exception{
		this.sourceFile = new BufferedReader(new FileReader(originalFile)); 
		String[] strSplit = null;
		resource = new Resource();
		char symbol;
		String grammar = null;
		int locationNode;
		while((eachLine = sourceFile.readLine())!=null){//读入文件中的字符，逐行读入，并显示
			System.out.println( ": " + eachLine); //****************************
//			sourceSyntaxTextArea.setLineWrap(true);
			strSplit = eachLine.split("->", -1);
//			System.out.println(strSplit[1]);
//			System.out.println("length" + strSplit.length);
			symbol = strSplit[0].trim().charAt(0);
//			System.out.println(strSplit[1].trim().toCharArray());
			grammar = strSplit[1].trim();
			locationNode = nodeHasExists(symbol);
			if ( locationNode == -1) {
				// 表明该非终态结点不在在
				NonterminalSymbol node = new NonterminalSymbol(symbol, grammar);
				resource.LL1_Grammar.add(node);
			} else {
				// 表明该非终态结点已在在
				StringBuffer strBuf = new StringBuffer(grammar);
				resource.LL1_Grammar.elementAt(locationNode).grammar.add(strBuf);
			}

		}
		
//		DialogView a=new DialogView();
		//checkSyntax = new CheckSyntax();//,a
		checkSyntax.a(resource);
		System.out.println("First集" );
		VN_symbol = new char[checkSyntax.nonterminalSymbolTable.length];
		
		int length0;//输出first集
		length0 = VN_symbol.length;
		for (int i = 0; i < length0; i ++)
			VN_symbol[i] = checkSyntax.nonterminalSymbolTable[i][0];
		
		int length;
		StringBuffer strBuf = null;
		for (int i = 0; i < VN_symbol.length; i ++) {
			System.out.print("First(" + VN_symbol[i] + ")={");
			strBuf = checkSyntax.firstCollection[i];
			length = strBuf.length();
			for (int j = 0; j < length; j ++) {
				System.out.print(strBuf.charAt(j)+"");
				if (j != length-1)
					System.out.print(",");
			}
			System.out.print("}\n");
		}
		
		System.out.println("follow集" );
		int length1;//输出follow集
		StringBuffer strBuf1 = null;
		for (int i = 0; i < VN_symbol.length; i ++) {
			System.out.print("Follow(" + VN_symbol[i] + ")={");
			strBuf1 = checkSyntax.followCollection[i];
			length1 = strBuf1.length();
			for (int j = 0; j < length1; j ++) {
				System.out.print(strBuf1.charAt(j)+"");
				if (j != length1-1)
					System.out.print(",");
			}
			System.out.print("}\n");
		}
		
		System.out.println("输出预测表");
		VT_end_symbol = checkSyntax.endSymbolAndNull.toString().toCharArray();
		int length3, length2;//输出预测表
		System.out.print('\t');
		System.out.print(" ");
		for (int i = 0; i < VT_end_symbol.length; i ++) {
			System.out.print('\t');
			System.out.print(VT_end_symbol[i]);
		}
		LL1preAnalysisTable = checkSyntax.preAnalysisTable;
		length3 = LL1preAnalysisTable.length;
		for (int i = 0; i < length3; i ++) {
			System.out.print('\n');
			System.out.print('\t');
			System.out.print(VN_symbol[i]);
			length2 = LL1preAnalysisTable[i].length;
			for (int j = 0; j < length2; j ++) {
				if (LL1preAnalysisTable[i][j] != null){}
				System.out.print('\t');
				System.out.print(LL1preAnalysisTable[i][j]);
			}
		}
//**************************************************************************************************
		VT_symbol = checkSyntax.endSymbol.toString().toCharArray();
		this.VT = new char[this.VT_symbol.length];
		this.VT = this.VT_symbol;
		
		this.VN = new char[this.VN_symbol.length];
		this.VN = this.VN_symbol;
		this.VT_EXPAND = new char[this.VT_end_symbol.length];
		this.VT_EXPAND = this.VT_end_symbol;
		
		this.preAnalysisTable = this.LL1preAnalysisTable;
		this.analysisStack.append(this.VN[0]);  // 将文法的开始符号进入分析栈
		this.analysisStack.insert(0, '#');      // 将‘#’也加入到分析栈中
		Scanner sc = new Scanner(System.in);
		System.out.print('\n');
		System.out.println("输入一个等待检查的句子:");//输入一个待检擦的句子
		String name = sc.nextLine();
		System.out.println("按照LL（1）分析表分析过程如下：");
		this.sentence  = new StringBuffer(name);
		this.sentence.append('#');
		preAnalysisprocess();//调用函数
	}
	
	
	public void preAnalysisprocess() {//依据LL（1）分析表来分析句子，验证是否采纳
		// TODO Auto-generated method stub
		while (this.continue_analysis == true) {
			this.stepNO ++;
			a = this.sentence.charAt(0);
			X = this.analysisStack.charAt(this.analysisStack.length()-1);
			// 判断该字符是否属于 VT
			if (this.beyondVT(X)) {
				// 该字符属于 VT
				if (X == a) {
					AnalysisStep step = new AnalysisStep(this.stepNO, this.analysisStack.toString(), this.sentence.toString(), "\""+a+"\"匹配");
					System.out.printf("%-20s",step.analysisStep);
					System.out.printf("%-20s",step.analysisStack);
					System.out.printf("%-20s",step.remaindString);
					System.out.printf("%-20s",step.informationSting);
					System.out.print('\n');
					this.resource.AnaStep.add(step);
					this.sentence.deleteCharAt(0);
					this.analysisStack.deleteCharAt(this.analysisStack.length()-1);
				} else {
					// 出错，句子分析结束
					AnalysisStep step = new AnalysisStep(this.stepNO, this.analysisStack.toString(), this.sentence.toString(), "出错");
					System.out.printf("%-20s",step.analysisStep);
					System.out.printf("%-20s",step.analysisStack);
					System.out.printf("%-20s",step.remaindString);
					System.out.printf("%-20s",step.informationSting);
					System.out.print('\n');
					this.resource.AnaStep.add(step);
					this.continue_analysis = false;
					this.falue = true;
					break;
				}
			} else {
				// 该字符不属于 VT
				if (X == '#') {
					if (X == a) {
						// 结束，成功
						AnalysisStep step = new AnalysisStep(this.stepNO, this.analysisStack.toString(), this.sentence.toString(), "接收");
						System.out.printf("%-20s",step.analysisStep);
						System.out.printf("%-20s",step.analysisStack);
						System.out.printf("%-20s",step.remaindString);
						System.out.printf("%-20s",step.informationSting);
						System.out.print('\n');
						this.succeed = true;
						break;
					} else {
						AnalysisStep step = new AnalysisStep(this.stepNO, this.analysisStack.toString(), this.sentence.toString(), "出错");
						System.out.printf("%-20s",step.analysisStep);
						System.out.printf("%-20s",step.analysisStack);
						System.out.printf("%-20s",step.remaindString);
						System.out.printf("%-20s",step.informationSting);
						System.out.print('\n');
						this.resource.AnaStep.add(step);
						this.continue_analysis = false;
						break;
					}
				} else {
					this.loc_VN = this.getVN_location(X);
					this.loc_VT = this.getVT_EXPAND_location(a);
					if (this.loc_VN != -1 && this.loc_VT != -1) {
						if (this.preAnalysisTable[loc_VN][loc_VT] == null) {
							// 表示该产生式不存在
							AnalysisStep step = new AnalysisStep(this.stepNO, this.analysisStack.toString(), this.sentence.toString(), "出错");
							System.out.printf("%-20s",step.analysisStep);
							System.out.printf("%-20s",step.analysisStack);
							System.out.printf("%-20s",step.remaindString);
							System.out.printf("%-20s",step.informationSting);
							System.out.print('\n');
							this.resource.AnaStep.add(step);
							this.continue_analysis = false;
							this.falue = true;
							break;
						} else {
							// 表示该产生式存在
							String str = this.preAnalysisTable[loc_VN][loc_VT];
							AnalysisStep step = new AnalysisStep(this.stepNO, this.analysisStack.toString(), this.sentence.toString(), X+"->"+str);
							System.out.printf("%-20s",step.analysisStep);
							System.out.printf("%-20s",step.analysisStack);
							System.out.printf("%-20s",step.remaindString);
							System.out.printf("%-20s",step.informationSting);
							System.out.print('\n');
							this.resource.AnaStep.add(step);
							this.analysisStack.deleteCharAt(this.analysisStack.length()-1);
							for (int i = str.length()-1; i >= 0; i --) {
								if (str.charAt(i) != '@')
									this.analysisStack.append(str.charAt(i));
							}
						}
					} else {
						// 表示该产生式不存在
						AnalysisStep step = new AnalysisStep(this.stepNO, this.analysisStack.toString(), this.sentence.toString(), "出错");
						System.out.printf("%-20s",step.analysisStep);
						System.out.printf("%-20s",step.analysisStack);
						System.out.printf("%-20s",step.remaindString);
						System.out.printf("%-20s",step.informationSting);
						System.out.print('\n');
						this.resource.AnaStep.add(step);
						this.continue_analysis = false;
						this.falue = true;
						break;
					}
				}
			}
			if (this.debugButtonPressed == true)
				break;
		}
		//this.diaView.analysisProcessTable.updateUI();
		this.resource.showTable();
		if (this.succeed == true)
			//JOptionPane.showMessageDialog(null, "该句子可接收", "Message", JOptionPane.INFORMATION_MESSAGE);
			System.out.print("该句子可接收");
		else if (this.falue == true)
			//JOptionPane.showMessageDialog(null, "该句子不可接收", "Message", JOptionPane.ERROR_MESSAGE);
			System.out.print("该句子不可接收");
		/*
		if (this.continue_analysis == true) {
			JOptionPane.showMessageDialog(null, "接收", "Message", JOptionPane.INFORMATION_MESSAGE);
		} else
			JOptionPane.showMessageDialog(null, "不接收", "Message", JOptionPane.INFORMATION_MESSAGE);
		*/
	}

	/**
	 * @判断字符是否属于终结符集
	 * @param ch
	 * @return
	 */
	private boolean beyondVT(char ch) {
		// TODO Auto-generated method stub
		int length = this.VT.length;
		for (int i = 0; i < length; i ++) {
			if (this.VT[i] == ch)
				return true;
		}
		return false;
	}
	
	/**
	 * @得到该字符在非终结符中的位置
	 * @param ch
	 */
	private int getVN_location(char ch) {
		// TODO Auto-generated method stub
		int length = this.VN.length;
		for (int i = 0; i < length; i ++)
			if (ch == this.VN[i])
				return i;
		return -1;
	}

	/**
	 * @得到该字符在扩充的终结符中的位置
	 * @param ch
	 */
	private int getVT_EXPAND_location(char ch) {
		// TODO Auto-generated method stub
		int length = this.VT_EXPAND.length;
		for (int i = 0; i < length; i ++)
			if (ch == this.VT_EXPAND[i])
				return i;
		return -1;
	}
//*************************************************************************************************	
	
	/**
	 * @判断该非终态结点是否存在
	 * @若存在则返回下标号，否则返回-1
	 * @param nodeStatue
	 * @return
	 */
	private int nodeHasExists(char symbol) {
		int length =this.resource.LL1_Grammar.size();
		
		for (int index = 0; index < length; index ++) {
			if (resource.LL1_Grammar.get(index).symbol == symbol) {
				return index;
			}
		}
		return -1;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//View view = new View();原代码
		MainExe exe=new MainExe();
		try {
			exe.readall("LL1_1.TXT");
		} catch(Exception e1){
			
		};
		System.exit(0);
	};
};
