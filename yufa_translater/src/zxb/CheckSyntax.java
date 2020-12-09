package zxb;

import java.util.Vector;

public class CheckSyntax{
	// 已经计算出FIRST集的非终结符的个数
	int calculatedSysmbolNo = 0;
	// 这个文法的所有非终结符号，及是否能够推导出空串
	char[][] nonterminalSymbolTable = null;
	// 这个文法的所有终结符号
	StringBuffer endSymbol = new StringBuffer();
	// 这个文法的所有终结符号加  # 一起
	StringBuffer endSymbolAndNull = new StringBuffer();
	// 这个文法的所有非终结符的FIRST集
	StringBuffer[] firstCollection = null;
	// 这个文法的所有非终结符的FOLLOW集
	StringBuffer[] followCollection = null;
	// 每个非终结符是否已经推导出FIRST集
	char[][] calculatedFirstSymbol = null;
	// 每个非终结符是否已经推导出FOLLOW集
	char[][] calculatedFollowSymbol = null;
	// 这个文法的预测分析表
	String[][] preAnalysisTable = null;
	
	Resource resource = null;
	// 备份文法
	Vector<NonterminalSymbol> backupGrammar = new Vector<NonterminalSymbol>();
	
	public CheckSyntax() {
	}
	/**
	 * @自定义构造函数
	 * @param resource
	 */
	public void a (Resource resource) {//, DialogView jDialog
		this.resource = resource;
		//this.digView = jDialog;
		this.nonterminalSymbolTable = new char[resource.LL1_Grammar.size()][2];
		this.firstCollection = new StringBuffer[resource.LL1_Grammar.size()];
		this.followCollection = new StringBuffer[resource.LL1_Grammar.size()];
		this.calculatedFirstSymbol = new char[resource.LL1_Grammar.size()][2];
		this.calculatedFollowSymbol = new char[resource.LL1_Grammar.size()][2];
		
		this.actionPerformed();
	}
	
	private void actionPerformed() {
		// TODO Auto-generated method stub
		// 备份结点
		this.getBackupGrammar();
		// 求出能推出ε的非终结符
		this.getCouldNullSymbol(this.backupGrammar);
		// 计算FIRST集
		this.calculateFirstCollection(resource.LL1_Grammar);
		// 计算FOLLOW集
		this.calculateFollowCollection(resource.LL1_Grammar);
		// 计算SELECT集
		this.calculateSelectCollection(resource.LL1_Grammar);
		//通过SELECT集，检查该方法是否为LL1文法
		if (this.checkSyntaxLL1(resource.LL1_Grammar)) {
			// 得到这个文法的所有终结符
			this.getEndSymbol(resource);
		//	this.digView.displayCheckeResultLabel.setText("文法检查通过！");
		//	this.digView.firstCollectionButton.setEnabled(true);
		//	this.digView.followCollectionButton.setEnabled(true);
		//	this.digView.preAnalysisButton.setEnabled(true);
		//	this.digView.sentenceTextField.setEnabled(true);
			System.out.println("文法检查通过");
			this.preAnalysisTable = new String[this.nonterminalSymbolTable.length][this.endSymbolAndNull.length()];
			// 得到预测分析表
			this.getAnalysisTable(resource);
		}
		else {
			System.out.print("文法检查!通过");
			//this.digView.displayCheckeResultLabel.setText("文法检查不通过！");
		}
	}

	/**
	 * @备份结点
	 */
	private void getBackupGrammar() {
		// TODO Auto-generated method stub
		NonterminalSymbol symbol_copy = null;
		int index = 0, length, length1;
		length = resource.LL1_Grammar.size();
		while (index < length) {
			NonterminalSymbol symbol = new NonterminalSymbol();
			symbol_copy = resource.LL1_Grammar.get(index);
			symbol.symbol = symbol_copy.symbol;
			length1 = symbol_copy.grammar.size();
			for (int i = 0; i < length1; i ++) {
				StringBuffer strBuf = new StringBuffer(symbol_copy.grammar.get(i));
				symbol.grammar.add(strBuf);
			}
			this.backupGrammar.add(symbol);
			index ++;
		}
	}

	/**
	 * @param resource 
	 * @求出能推出ε的非终结符
	 */
	private void getCouldNullSymbol(Vector<NonterminalSymbol> backupGrammar) {
		// TODO Auto-generated method stub
		// 将非终结符能否推出空数组的标记置为“未定 U”
		this.setNodeUndefined(backupGrammar);
		// 扫描文法中的产生式
		this.scanSyntax(backupGrammar);
		// 扫描产生式右部的每一符号
		this.scanRightSyntaxSymbol(backupGrammar);
	}

	/**
	 * @计算FIRST集
	 * @param resource
	 */
	private void calculateFirstCollection(Vector<NonterminalSymbol> resource) {
		// TODO Auto-generated method stub	
		// 计算出所有能够直接求出FIRST集的非终结符
		this.canDirectFirstCollection(resource);
		// 求出每个非终结符的FIRST集
		this.getFirstCollection(resource);
	}
	
	/**
	 * @计算文法的FOLLOW集
	 * @param resource
	 */
	private void calculateFollowCollection(Vector<NonterminalSymbol> resource) {
		// TODO Auto-generated method stub
		// 把 # 加入到文法开始符号的FOLLOW集中
		int index = 0, length;
		boolean flag = true;
		char ch;
		this.followCollection[0].append('#');
		this.calculatedFollowSymbol[0][1] = 'Y';
		
		while (flag == true) {
			// 表示还存在非终结符的FOLLOW集可能增大
			flag = false;
			index = 0; length = resource.size();
			while (index < length) {
				ch = resource.elementAt(index).symbol;
				// 计算出当前这个非终结符的FOLLOW集
				if (this.getFollowCollection(ch, resource)) {
					flag = true;
				}
				index ++;
			}
		}
	}
	
	/**
	 * @计算文法的SELECT集
	 * @param lL1Grammar
	 */
	private void calculateSelectCollection(Vector<NonterminalSymbol> resource) {
		// TODO Auto-generated method stub
		int index = 0, length, index1, length1;
		NonterminalSymbol nonSymbol = null;
		StringBuffer strBuf = null;
		
		length = resource.size();
		while (index < length) {
			nonSymbol = resource.elementAt(index);
			index1 = 0; length1 = nonSymbol.grammar.size();
			while (index1 < length1) {
				strBuf = nonSymbol.grammar.elementAt(index1);
				// 计算每个产生式的右部符号串的开始符号集合的FIRST集
				this.calculateRightSymbolFirstCollection(index, strBuf);
				// 计算每个产生式的右部符号串集合的SELECT集
				this.calculateRightSymbolSelectCollection(index , index1, nonSymbol.symbol, strBuf);
				index1 ++;
			}
			index ++;
		}
	}

	
	/**
	 * @将非终结符能否推出空串的数组每位非终结符标记置为“未定 U”
	 * @param NonterminalSymbol
	 */
	private void setNodeUndefined(Vector<NonterminalSymbol> backupGrammar) {
		// TODO Auto-generated method stub
		for (int index = 0; index < this.nonterminalSymbolTable.length; index ++) {
			this.nonterminalSymbolTable[index][0] = backupGrammar.get(index).symbol;
			// 将非终结符能否推出空数组的标记置为“未定 U”
			this.nonterminalSymbolTable[index][1] = 'U';
			// 再赋值的同时也将firstCollection赋值
			this.firstCollection[index] = new StringBuffer();
			// 再赋值的同时也将followCollection赋值
			this.followCollection[index] = new StringBuffer();
			// 将calculatedFirstSymbol同时赋值
			this.calculatedFirstSymbol[index][0] = backupGrammar.get(index).symbol;
			this.calculatedFirstSymbol[index][1] = 'N';
			// 将calculatedFollowSymbol同时赋值
			this.calculatedFollowSymbol[index][0] = backupGrammar.get(index).symbol;
			this.calculatedFollowSymbol[index][1] = 'N';
		}
	}
	
	/**
	 * @计算每个产生式的右部符号串的开始符号集合
	 * @param index
	 * @param strBuf
	 */
	private void calculateRightSymbolFirstCollection(int index, StringBuffer strBuf) {
		// TODO Auto-generated method stub
		int index_1 = 0, length, loc, i, j;
		char ch, ch_1;
		StringBuffer firstCollection = new StringBuffer();
		
		length = strBuf.length();
		for (index_1 = 0; index_1 < length; index_1 ++) {
			ch = strBuf.charAt(index_1);
			if (this.isEndNode(ch)) {
				// 当前这个符号是终结符
				for (i = 0; i < firstCollection.length(); i ++) {
					if (ch == firstCollection.charAt(i))
						break;
				}
				if (i >= firstCollection.length())
					firstCollection.append(ch);
				break;
			} else {
				// 当前这个符号是非终结符
				loc = this.getSymbolLocation(ch);
				if (this.nonterminalSymbolTable[loc][1] == 'N') {
					// 表示当前这个非终结符不能够推出空串
					for (i = 0; i < this.firstCollection[loc].length(); i ++) {
						ch_1 = this.firstCollection[loc].charAt(i);
						for (j = 0; j < firstCollection.length(); j ++) {
							if (ch_1 == firstCollection.charAt(j))
								break;
						}
						if (j >= firstCollection.length())
							firstCollection.append(ch_1);
					}
					break;
				} else {
					// 表示当前这个非终结符能够推出空串
					for (i = 0; i < this.firstCollection[loc].length(); i ++) {
						ch_1 = this.firstCollection[loc].charAt(i);
						if (ch_1 != '@') {
							for (j = 0; j < firstCollection.length(); j ++) {
								if (ch_1 == firstCollection.charAt(j))
									break;
							}
							if (j >= firstCollection.length())
								firstCollection.append(ch_1);
						}
					}
				}
			}
		}
		if (index_1 >= strBuf.length()) {
			for (i = 0; i < firstCollection.length(); i ++) {
				if ('@' == firstCollection.charAt(i))
					break;
			}
			if (i >= firstCollection.length())
				firstCollection.append('@');
		}
		this.resource.LL1_Grammar.elementAt(index).first.add(firstCollection);
	}

	/**
	 * @计算每个产生式的右部符号串集合的SELECT集
	 * @param index
	 * @param index1
	 * @param symbol 
	 * @param strBuf
	 */
	private void calculateRightSymbolSelectCollection(int index, int index1, char symbol, StringBuffer strBuf) {
		// TODO Auto-generated method stub
		int i, j, loc;
		char ch;
		StringBuffer selectCollection = new StringBuffer();
		StringBuffer str;
		
		if (! this.canGetNull(strBuf.toString())) {
			// 这个字符串不能够推出空串
			// SELECT(A->a) = FIRST(a)
			selectCollection = resource.LL1_Grammar.elementAt(index).first.elementAt(index1);
		} else {
			// 这个字符串能够推出空串
			// SELECT(A->a) = (FIRST(a) - {e}) U FOLLOW(A)
			str = resource.LL1_Grammar.elementAt(index).first.elementAt(index1);
			for (i = 0; i < str.length(); i ++) {
				if (str.charAt(i) != '@')
					selectCollection.append(str.charAt(i));
			}
			loc = this.getSymbolLocation(symbol);
			for (i = 0; i < this.followCollection[loc].length(); i ++) {
				ch = this.followCollection[loc].charAt(i);
				for (j = 0; j < selectCollection.length(); j ++) {
					if (ch == selectCollection.charAt(j))
						break;
				}
				if (j >= selectCollection.length())
					selectCollection.append(ch);
			}
		}
		this.resource.LL1_Grammar.elementAt(index).select.add(selectCollection);
	}
	
	/**
	 * @判断该方法是否为LL1方法
	 * @param resource
	 * @return
	 */
	private boolean checkSyntaxLL1(Vector<NonterminalSymbol> resource) {
		// TODO Auto-generated method stub
		int index = 0, length, index1, length1, index2;
		NonterminalSymbol nonSymbol = null;
		
		length = resource.size();
		for (index = 0; index < length; index ++) {
			nonSymbol = resource.elementAt(index);
			length1 = nonSymbol.select.size();
			for (index1 = 0; index1 < length1-1; index1 ++) {
				for (index2 = index1+1; index2 < length1; index2++) {
					// 判断两个左部相同的产生式的右部有无相同的元素
					if (this.hasSameElement(nonSymbol.select.elementAt(index1), nonSymbol.select.elementAt(index2))) {
						// 两个串有相同的元素
						return false;
					}
				}
			}
		}
		return true;
	}
	
	/**
	 * @判断两个左部相同的产生式的右部有无相同的元素
	 * @param strBuf1
	 * @param strBuf2
	 * @return
	 */
	private boolean hasSameElement(StringBuffer strBuf1, StringBuffer strBuf2) {
		// TODO Auto-generated method stub
		int i, j, length1, length2;
		char ch;
		length1 = strBuf1.length();
		length2 = strBuf2.length();
		
		for (i = 0; i < length1; i ++) {
			ch = strBuf1.charAt(i);
			for (j = 0; j < length2; j ++) {
				if (ch == strBuf2.charAt(j))
					break;
			}
			if (j < length2)
				break;
		}
		if (i >= length1)                   // 表示无相同的元素
			return false;
		else
			return true;                    // 表示有相同的元素
	}

	/**
	 * @如果能够计算出这个非终结符的FOLLOW集，则计算出，否则等待下一次计算
	 * @param ch
	 * @param resource 
	 */
	private boolean getFollowCollection(char ch, Vector<NonterminalSymbol> resource) {
		// TODO Auto-generated method stub
		int index = 0, length, index1, length1, index_char, length_follow;
		int loc, locate;
		boolean flag = true;
		StringBuffer strBuf = null;
		NonterminalSymbol nonSymbol = null;
		length = resource.size();
		
		locate = this.getSymbolLocation(ch);
		length_follow = this.followCollection[locate].length();
		while (index < length) {
			nonSymbol = resource.elementAt(index);
			index1 = 0; length1 = nonSymbol.grammar.size();
			while (index1 < length1) {
				strBuf = nonSymbol.grammar.elementAt(index1);
				index_char = strBuf.indexOf(ch+"");
				if (index_char != -1) {
					// 表示这个文法中含有此非终结符
					this.setFirstToFollow(locate, strBuf.substring(index_char+1));
					
					if (this.canGetNull(strBuf.substring(index_char+1))) {
						// 能够得到空集
						if (nonSymbol.symbol != ch) {
							loc = this.getSymbolLocation(nonSymbol.symbol);
							if (this.calculatedFollowSymbol[loc][1] == 'Y') {
								this.setFollowtoFollow(locate, loc);
							} else {
								this.setFollowtoFollow(locate, loc);
								flag = false;
							}
						}
					}
				}// end_if
				index1 ++;
			}
			index ++;
		}
		if (flag == true) {
			this.calculatedFollowSymbol[locate][1] = 'Y';
		}
		if (this.followCollection[locate].length() != length_follow)
			return true;
		else 
			return false;
	}

	/**
	 * @将以loc位置的非终结符的FOLLOW集加入到locate位置的非终结符中
	 * @param locate
	 * @param loc
	 */
	private void setFollowtoFollow(int locate, int loc) {
		// TODO Auto-generated method stub
		int i, j;
		char ch;
		for (i = 0; i < this.followCollection[loc].length(); i ++) {
			ch = this.followCollection[loc].charAt(i);
			for (j = 0; j < this.followCollection[locate].length(); j ++) {
				if (ch == this.followCollection[locate].charAt(j))
					break;
			}
			if (j >= this.followCollection[locate].length())
				this.followCollection[locate].append(ch);
		}
	}

	/**
	 * @将以该字符串开头的字母的FIRST集加入到某个非终结符的FOLLOW集中
	 * @param locate
	 * @param substring
	 */
	private void setFirstToFollow(int locate, String str) {
		// TODO Auto-generated method stub
		int i, j;
		char ch_1;
		if (str.length() != 0) {
			char ch = str.charAt(0);
			if (this.isEndNode(ch)) {
				// 该字符为终结符
				for (i = 0; i < this.followCollection[locate].length(); i ++) {
					if (ch == this.followCollection[locate].charAt(i))
						return;
				}
				this.followCollection[locate].append(ch);
			} else {
				// 该字符是非终结符
				int loc = this.getSymbolLocation(ch);
				for (i = 0; i < this.firstCollection[loc].length(); i ++) {
					ch_1 = this.firstCollection[loc].charAt(i);
					if (ch_1 != '@') {
						for (j = 0; j < this.followCollection[locate].length(); j ++) {
							if (ch_1 == this.followCollection[locate].charAt(j))
								break;
						}
						if (j >= this.followCollection[locate].length())
							this.followCollection[locate].append(ch_1);
					}
				}
			}
		}
	}

	/**
	 * @判断当前这个字符串是否能够推导出 空串
	 * @param substring
	 * @return
	 */
	private boolean canGetNull(String str) {
		// TODO Auto-generated method stub
		int length = str.length();
		int loc;
		for (int i = 0; i < length; i ++) {
			if (! (this.isEndNode(str.charAt(i)))) {
				loc = this.getSymbolLocation(str.charAt(i));
				if (this.nonterminalSymbolTable[loc][1] == 'N')
					return false;
			} else if (str.charAt(i) != '@')
				return false;
		}
		return true;
	}
	

	/**
	 * @计算出所有能够直接求出FIRST集的非终结符
	 * @param resource
	 */
	private void canDirectFirstCollection(Vector<NonterminalSymbol> resource) {
		// TODO Auto-generated method stub
		// 只要以该非终结符为左部的所有产生式，右部以 终结符或ε打头
		int index = 0, length, index1, length1;
		NonterminalSymbol node = null;
		length = resource.size();
		for (index = 0; index < length; index ++) {
			node = resource.elementAt(index);
			index1 = 0; length1 = node.grammar.size();
			while (index1 < length1) {
				if (! (node.grammar.elementAt(index1).charAt(0) == '@' || this.isEndNode(node.grammar.elementAt(index1).charAt(0)))) {
					// 表示其中有一个产生式右部以非终结符打头
					break;
				}
				index1 ++;
			}
			if (index1 >= length1) {
				// 找到一个能够直接求出FIRST集的非终结符
				index1 = 0;
				while (index1 < length1) {
					this.firstCollection[index].append(node.grammar.elementAt(index1).charAt(0));
					index1 ++;
				}
				this.calculatedFirstSymbol[index][1] = 'Y';
				this.calculatedSysmbolNo ++;
			}
		}
	}
	

	/**
	 * @求出剩下的非终结符的FIRST集
	 * @param resource2
	 */
	private void getFirstCollection(Vector<NonterminalSymbol> resource) {
		// TODO Auto-generated method stub
		int index = 0;
		int nonterminalSymbolNum = this.resource.LL1_Grammar.size();
		NonterminalSymbol symbol = null;
		
		while (this.calculatedSysmbolNo < nonterminalSymbolNum) {
			// 表示并不是所有的非终结符都求出了FIRST集
			index = 0;
			while (index < nonterminalSymbolNum) {
				if (this.calculatedFirstSymbol[index][1] == 'N') {
					//表示当前这个非终结符还没有求出FIRST集
					symbol = resource.elementAt(index);
					this.getDirectFirstCollection(symbol);
				}
				index ++;
			}
		}
	}
	
	/**
	 * @若当前这个非终结符能够不用递归求出FIRST集，则求出，否则等待下次进求出
	 * @param symbol
	 */
	private void getDirectFirstCollection(NonterminalSymbol symbol) {
		// TODO Auto-generated method stub
		int index = 0, length, index1, length1, locate;
		boolean flag = true;
		StringBuffer strBuf = null;
		length = symbol.grammar.size();
		while (index < length) {
			strBuf = symbol.grammar.get(index);
			index1 = 0; length1 = strBuf.length();
			while (index1 < length1) {
				if (strBuf.charAt(index1) == '@' || this.isEndNode(strBuf.charAt(index1))) {
					break;
				} else {
					// 当前是非终结符
					int loc = this.getSymbolLocation(strBuf.charAt(index1));
					if (this.nonterminalSymbolTable[loc][1] == 'Y' ) {
						// 表示当前的这个非终结符能够推导出  空串
						if (this.calculatedFirstSymbol[loc][1] == 'N') {
							flag = false;
							break;
						}
					} else {
						// 表示当前的这个非终结符不能够推导出  空串
						if (this.calculatedFirstSymbol[loc][1] == 'N') {
							flag = false;
						}
						break;
					}
				}
				index1 ++;
			}
			if (flag == false)
				break;
			else 
				index ++;
		}
		if (index >= length) {
			// 表示该非终结符的FIRST集能够求出
			int loc; char ch;
			index = 0;
			locate = this.getSymbolLocation(symbol.symbol);
			while (index < length) {
				strBuf = symbol.grammar.elementAt(index);
				index1 = 0; length1 = strBuf.length();
				while (index1 < length1) {
					ch = strBuf.charAt(index1);
					if (ch == '@')
						break;
					else if (this.isEndNode(ch)) {
						if (! this.beyondFirstCollection(ch, this.firstCollection[locate]))
							this.firstCollection[locate].append(ch);
						break;
					} else {
						loc = this.getSymbolLocation(ch);
						if (this.nonterminalSymbolTable[loc][1] == 'Y') {
							for (int i = 0; i < this.firstCollection[loc].length(); i ++) {
								if (this.firstCollection[loc].charAt(i) != '@' && ! this.beyondFirstCollection(this.firstCollection[loc].charAt(i), this.firstCollection[locate]))
									this.firstCollection[locate].append(this.firstCollection[loc].charAt(i));
							}
						} else {
							for (int i = 0; i < this.firstCollection[loc].length(); i ++)
								if (! (this.beyondFirstCollection(this.firstCollection[loc].charAt(i), this.firstCollection[locate])))
									this.firstCollection[locate].append(this.firstCollection[loc].charAt(i));
							break;
						}
					}
					index1 ++;
				}
				index ++;
			}
			if (this.nonterminalSymbolTable[locate][1] == 'Y')
				this.firstCollection[locate].append('@');
			this.calculatedSysmbolNo ++;
			this.calculatedFirstSymbol[locate][1] = 'Y';
		}
	}

	/**
	 * @得到当前的这个非终结符的位置
	 * @param ch
	 * @return
	 */
	private int getSymbolLocation(char ch) {
		for (int i = 0; i < this.nonterminalSymbolTable.length; i ++) {
			if (ch == this.nonterminalSymbolTable[i][0])
				return i;
		}
		return -1;
	}
	
	/**
	 * @判断当前的这个字符是否也在给定的这个非终结符的FIRST集中存在
	 * @param ch
	 * @param strBuf
	 * @return
	 */
	private boolean beyondFirstCollection(char ch, StringBuffer strBuf) {
		int length = strBuf.length();
		for (int i = 0; i < length; i ++) {
			if (ch == strBuf.charAt(i))
				return true;
		}
		return false;
	}

	
	/**
	 * @扫描文法中的产生式
	 * @param NonterminalSymbol
	 */
	private void scanSyntax(Vector<NonterminalSymbol> backupGrammar) {
		// TODO Auto-generated method stub
		/* 
		 * 删除所有右部含有终结符的产生式，若这使得以某一非终结符为左部的所有产生式都被删除，
		 * 则将数组中对应该非终结符的标记值改为“否 N”，说明该非终结符不能推出
		*/
		this.deleteHasEndNodeProduction(backupGrammar);
		/*
		 * 若某一非终结符的某一产生式右部为ε，则将数组中对应该非终结符的标志置为“是”，
		 * 并从文法中删除该非终结符的所有产生式
		 */
		this.deleteProductionNull(backupGrammar);
	}
	
	/**
	 * @扫描产生式右部的每一符号
	 * @param backupGrammar2
	 */
	private void scanRightSyntaxSymbol(Vector<NonterminalSymbol> backupGrammar) {
		// TODO Auto-generated method stub
		// 数组中非终结符对应的特征有没改变
		int index, length, index1, length1, index2, length2;
		boolean flag = true;
		// 结点标记信息
		char nodeMark;
		StringBuffer strBuf = null;
		NonterminalSymbol node = null;
		while (flag == true) {
			flag = false;
			index = 0; length = backupGrammar.size();
			while (index < length) {
				node = backupGrammar.elementAt(index);
				index1 = 0; length1 = node.grammar.size();
				while (index1 < length1) {
					strBuf = node.grammar.elementAt(index1);
					index2 = 0; length2 = strBuf.length();
					while(index2 < length2) {
						boolean flag1 = true;
						nodeMark = this.getNodeNo(strBuf.charAt(index2));
						switch (nodeMark) {
						// 当前扫描到的非终结符号能够推导出ε
						case 'Y':
							// 删去该非终结符
							this.deleteNotEndChar(index2, strBuf);
							length2 --;
							break;
							
						// 当前扫描到的非终结符号不能够推导出ε
						case 'N':
							// 删除当前产生式
							flag1 = false;
							break;
							
						// 当前扫描到的非终结符号不确定是否能够推导出ε	
						default:
							index2 ++;
						}
						if (flag1 != true)
							// 表示扫描到非终结符不能够推导出ε, 跳出
							break;
						}
					if (index2 < length2) {
						// 表示扫描到非终结符不能够推导出ε
						node.grammar.remove(index1);
						length1 --;
						if (length1 == 0) {
							this.setNodeNo(node.symbol, 'N');
							this.backupGrammar.remove(node);
							length --;
							flag = true;
							break;
						}
					} else if (length2 == 0){
						// 使得产生式右部为空
						node.grammar.remove(index1);
						length1 --;
						if (length1 == 0) {
							this.setNodeNo(node.symbol, 'Y');
							this.backupGrammar.remove(node);
							length --;
							flag = true;
							break;
						} 
					} else {
						index1 ++;
					}
				}
				if (index1 >= length1)
					index ++;
			}
		}
	}
	
	/**
	 * @// 删去非终结符
	 * @param index
	 * @param length
	 * @param arrayChar
	 */
	private void deleteNotEndChar(int index, StringBuffer strBuf) {
		// TODO Auto-generated method stub
		strBuf.deleteCharAt(index);
	}

	/**
	 * @删除所有右部含有终结符的产生式
	 * @param NonterminalSymbol
	 */
	private void deleteHasEndNodeProduction(Vector<NonterminalSymbol> backupGrammar) {
		// TODO Auto-generated method stub
		int index = 0, index1 = 0, length, length1;
		NonterminalSymbol node = null;
		length = backupGrammar.size();
		while (index < length) {
			node = backupGrammar.get(index);
			index1 = 0; length1 = node.grammar.size();
			while (index1 < length1) {
				if (this.hasEndNode(node.grammar.get(index1))) {
					// 该文法产生式含有终结符
					node.grammar.remove(index1);
					length1 --;
				} else {
					index1 ++;
				}
			}
			if (length1 == 0) {
				// 使得以某一非终结符为左部的所有产生式都被删除
				// 给该非终结符的标记置为“否 N”
				this.setNodeNo(node.symbol, 'N');
				backupGrammar.remove(index);
				length --;
			} else {
				index ++;
			}
		}
	}
	
	/**
	 * @删除某一非终结符的某一产生式右部为ε
	 * @param backupGrammar2
	 */
	private void deleteProductionNull(Vector<NonterminalSymbol> backupGrammar2) {
		// TODO Auto-generated method stub
		int index = 0, index1, length, length1;
		NonterminalSymbol node = null;
		length = backupGrammar.size();
		while (index < length) {
			node = backupGrammar.get(index);
			index1 = 0;
			length1 = node.grammar.size();
			while (index1 < length1) {
				if (node.grammar.get(index1).charAt(0) == '@')
					break;
				index1 ++;
			}
			if (index1 < length1) {
				// 给该非终结符的标记置为“是 Y”
				this.setNodeNo(node.symbol, 'Y');
				backupGrammar.remove(index);
				length --;
			} else {
				index ++;
			}
		}
	}
	
	/**
	 * @判断一个文法表达式是否含有终结符
	 * @param arrayChar
	 * @return
	 */
	private boolean hasEndNode(StringBuffer strBuf) {
		int index, length;
		char[] arrayChar = strBuf.toString().toCharArray();
		length = arrayChar.length;
		for (index = 0; index < length; index ++) {
			if (arrayChar[index] != '@' && this.isEndNode(arrayChar[index]))
				return true;
		}
		return false;
	}
	
	/**
	 * @判断该字符是否为终结符
	 * @param c
	 * @return
	 */
	private boolean isEndNode(char c) {
		// TODO Auto-generated method stub
		for (int i = 0; i < this.nonterminalSymbolTable.length; i ++) {
			if (c == nonterminalSymbolTable[i][0])
				return false;
		}
		return true;
	}
	
	
	/**
	 * @得到该非终结符的标记
	 * @param symbol
	 * @return
	 */
	private char getNodeNo(char symbol) {
		// TODO Auto-generated method stub
		for (int i = 0; i < this.nonterminalSymbolTable.length; i ++) {
			if (symbol == this.nonterminalSymbolTable[i][0])
				return this.nonterminalSymbolTable[i][1];
		}
		return 0;
	}
	
	/**
	 * @给该非终结符的标记置为“char c”
	 * @param symbol
	 * @param c
	 */
	private void setNodeNo(char symbol, char c) {
		// TODO Auto-generated method stub
		for (int i = 0; i < this.nonterminalSymbolTable.length; i ++) {
			if (symbol == this.nonterminalSymbolTable[i][0]) {
				this.nonterminalSymbolTable[i][1] = c;
				break;
			}
		}
	}
	
	
	/**
	 * @得到这个文法的所有终结符
	 * @param resource2
	 */
	private void getEndSymbol(Resource resource) {
		// TODO Auto-generated method stub
		int index =0, length, index1, length1, index2, length2, i;
		char ch;
		NonterminalSymbol nonSymbol = null;
		StringBuffer strBuf = null;
		
		length = resource.LL1_Grammar.size();
		for (index = 0; index < length; index ++) {
			nonSymbol = resource.LL1_Grammar.elementAt(index);
			length1 = nonSymbol.grammar.size();
			for (index1 = 0; index1 < length1; index1 ++) {
				strBuf = nonSymbol.grammar.elementAt(index1);
				length2 = strBuf.length();
				for (index2 =0; index2 < length2; index2 ++) {
					ch = strBuf.charAt(index2);
					if (ch != '@' && this.isEndNode(ch)) {
						for (i = 0; i < this.endSymbol.length(); i ++) {
							if (ch == this.endSymbol.charAt(i))
								break;
						}
						if (i >= this.endSymbol.length())
							this.endSymbol.append(ch);
					}
				}
			}
		}
		for (int k = 0; k < this.endSymbol.length(); k ++)
			this.endSymbolAndNull.append(this.endSymbol.charAt(k));
		this.endSymbolAndNull.append('#');
	}
	
	
	/**
	 * @得到预测分析表
	 * @param resource2
	 */
	private void getAnalysisTable(Resource resource) {
		// TODO Auto-generated method stub
		int index = 0, length, index1, length1, index2, length2;
		char ch;
		int loc;
		NonterminalSymbol nonSymbol = null;
		StringBuffer strBuf = null;
		
		length = resource.LL1_Grammar.size();
		for (index = 0; index < length; index ++) {
			nonSymbol = resource.LL1_Grammar.elementAt(index);
			length1 = nonSymbol.select.size();
			for (index1 = 0; index1 < length1; index1 ++) {
				strBuf = nonSymbol.select.elementAt(index1);
				length2 = strBuf.length();
				for (index2 = 0; index2 < length2; index2 ++) {
					ch = strBuf.charAt(index2);
					loc = this.endSymbolAndNull.indexOf(ch+"");
					this.preAnalysisTable[index][loc] = nonSymbol.grammar.elementAt(index1).toString();
				}
			}
		}
	}
}
