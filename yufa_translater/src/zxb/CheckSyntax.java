package zxb;

import java.util.Vector;

public class CheckSyntax{
	// �Ѿ������FIRST���ķ��ս���ĸ���
	int calculatedSysmbolNo = 0;
	// ����ķ������з��ս���ţ����Ƿ��ܹ��Ƶ����մ�
	char[][] nonterminalSymbolTable = null;
	// ����ķ��������ս����
	StringBuffer endSymbol = new StringBuffer();
	// ����ķ��������ս���ż�  # һ��
	StringBuffer endSymbolAndNull = new StringBuffer();
	// ����ķ������з��ս����FIRST��
	StringBuffer[] firstCollection = null;
	// ����ķ������з��ս����FOLLOW��
	StringBuffer[] followCollection = null;
	// ÿ�����ս���Ƿ��Ѿ��Ƶ���FIRST��
	char[][] calculatedFirstSymbol = null;
	// ÿ�����ս���Ƿ��Ѿ��Ƶ���FOLLOW��
	char[][] calculatedFollowSymbol = null;
	// ����ķ���Ԥ�������
	String[][] preAnalysisTable = null;
	
	Resource resource = null;
	// �����ķ�
	Vector<NonterminalSymbol> backupGrammar = new Vector<NonterminalSymbol>();
	
	public CheckSyntax() {
	}
	/**
	 * @�Զ��幹�캯��
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
		// ���ݽ��
		this.getBackupGrammar();
		// ������Ƴ��ŵķ��ս��
		this.getCouldNullSymbol(this.backupGrammar);
		// ����FIRST��
		this.calculateFirstCollection(resource.LL1_Grammar);
		// ����FOLLOW��
		this.calculateFollowCollection(resource.LL1_Grammar);
		// ����SELECT��
		this.calculateSelectCollection(resource.LL1_Grammar);
		//ͨ��SELECT�������÷����Ƿ�ΪLL1�ķ�
		if (this.checkSyntaxLL1(resource.LL1_Grammar)) {
			// �õ�����ķ��������ս��
			this.getEndSymbol(resource);
		//	this.digView.displayCheckeResultLabel.setText("�ķ����ͨ����");
		//	this.digView.firstCollectionButton.setEnabled(true);
		//	this.digView.followCollectionButton.setEnabled(true);
		//	this.digView.preAnalysisButton.setEnabled(true);
		//	this.digView.sentenceTextField.setEnabled(true);
			System.out.println("�ķ����ͨ��");
			this.preAnalysisTable = new String[this.nonterminalSymbolTable.length][this.endSymbolAndNull.length()];
			// �õ�Ԥ�������
			this.getAnalysisTable(resource);
		}
		else {
			System.out.print("�ķ����!ͨ��");
			//this.digView.displayCheckeResultLabel.setText("�ķ���鲻ͨ����");
		}
	}

	/**
	 * @���ݽ��
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
	 * @������Ƴ��ŵķ��ս��
	 */
	private void getCouldNullSymbol(Vector<NonterminalSymbol> backupGrammar) {
		// TODO Auto-generated method stub
		// �����ս���ܷ��Ƴ�������ı����Ϊ��δ�� U��
		this.setNodeUndefined(backupGrammar);
		// ɨ���ķ��еĲ���ʽ
		this.scanSyntax(backupGrammar);
		// ɨ�����ʽ�Ҳ���ÿһ����
		this.scanRightSyntaxSymbol(backupGrammar);
	}

	/**
	 * @����FIRST��
	 * @param resource
	 */
	private void calculateFirstCollection(Vector<NonterminalSymbol> resource) {
		// TODO Auto-generated method stub	
		// ����������ܹ�ֱ�����FIRST���ķ��ս��
		this.canDirectFirstCollection(resource);
		// ���ÿ�����ս����FIRST��
		this.getFirstCollection(resource);
	}
	
	/**
	 * @�����ķ���FOLLOW��
	 * @param resource
	 */
	private void calculateFollowCollection(Vector<NonterminalSymbol> resource) {
		// TODO Auto-generated method stub
		// �� # ���뵽�ķ���ʼ���ŵ�FOLLOW����
		int index = 0, length;
		boolean flag = true;
		char ch;
		this.followCollection[0].append('#');
		this.calculatedFollowSymbol[0][1] = 'Y';
		
		while (flag == true) {
			// ��ʾ�����ڷ��ս����FOLLOW����������
			flag = false;
			index = 0; length = resource.size();
			while (index < length) {
				ch = resource.elementAt(index).symbol;
				// �������ǰ������ս����FOLLOW��
				if (this.getFollowCollection(ch, resource)) {
					flag = true;
				}
				index ++;
			}
		}
	}
	
	/**
	 * @�����ķ���SELECT��
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
				// ����ÿ������ʽ���Ҳ����Ŵ��Ŀ�ʼ���ż��ϵ�FIRST��
				this.calculateRightSymbolFirstCollection(index, strBuf);
				// ����ÿ������ʽ���Ҳ����Ŵ����ϵ�SELECT��
				this.calculateRightSymbolSelectCollection(index , index1, nonSymbol.symbol, strBuf);
				index1 ++;
			}
			index ++;
		}
	}

	
	/**
	 * @�����ս���ܷ��Ƴ��մ�������ÿλ���ս�������Ϊ��δ�� U��
	 * @param NonterminalSymbol
	 */
	private void setNodeUndefined(Vector<NonterminalSymbol> backupGrammar) {
		// TODO Auto-generated method stub
		for (int index = 0; index < this.nonterminalSymbolTable.length; index ++) {
			this.nonterminalSymbolTable[index][0] = backupGrammar.get(index).symbol;
			// �����ս���ܷ��Ƴ�������ı����Ϊ��δ�� U��
			this.nonterminalSymbolTable[index][1] = 'U';
			// �ٸ�ֵ��ͬʱҲ��firstCollection��ֵ
			this.firstCollection[index] = new StringBuffer();
			// �ٸ�ֵ��ͬʱҲ��followCollection��ֵ
			this.followCollection[index] = new StringBuffer();
			// ��calculatedFirstSymbolͬʱ��ֵ
			this.calculatedFirstSymbol[index][0] = backupGrammar.get(index).symbol;
			this.calculatedFirstSymbol[index][1] = 'N';
			// ��calculatedFollowSymbolͬʱ��ֵ
			this.calculatedFollowSymbol[index][0] = backupGrammar.get(index).symbol;
			this.calculatedFollowSymbol[index][1] = 'N';
		}
	}
	
	/**
	 * @����ÿ������ʽ���Ҳ����Ŵ��Ŀ�ʼ���ż���
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
				// ��ǰ����������ս��
				for (i = 0; i < firstCollection.length(); i ++) {
					if (ch == firstCollection.charAt(i))
						break;
				}
				if (i >= firstCollection.length())
					firstCollection.append(ch);
				break;
			} else {
				// ��ǰ��������Ƿ��ս��
				loc = this.getSymbolLocation(ch);
				if (this.nonterminalSymbolTable[loc][1] == 'N') {
					// ��ʾ��ǰ������ս�����ܹ��Ƴ��մ�
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
					// ��ʾ��ǰ������ս���ܹ��Ƴ��մ�
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
	 * @����ÿ������ʽ���Ҳ����Ŵ����ϵ�SELECT��
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
			// ����ַ������ܹ��Ƴ��մ�
			// SELECT(A->a) = FIRST(a)
			selectCollection = resource.LL1_Grammar.elementAt(index).first.elementAt(index1);
		} else {
			// ����ַ����ܹ��Ƴ��մ�
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
	 * @�жϸ÷����Ƿ�ΪLL1����
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
					// �ж���������ͬ�Ĳ���ʽ���Ҳ�������ͬ��Ԫ��
					if (this.hasSameElement(nonSymbol.select.elementAt(index1), nonSymbol.select.elementAt(index2))) {
						// ����������ͬ��Ԫ��
						return false;
					}
				}
			}
		}
		return true;
	}
	
	/**
	 * @�ж���������ͬ�Ĳ���ʽ���Ҳ�������ͬ��Ԫ��
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
		if (i >= length1)                   // ��ʾ����ͬ��Ԫ��
			return false;
		else
			return true;                    // ��ʾ����ͬ��Ԫ��
	}

	/**
	 * @����ܹ������������ս����FOLLOW����������������ȴ���һ�μ���
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
					// ��ʾ����ķ��к��д˷��ս��
					this.setFirstToFollow(locate, strBuf.substring(index_char+1));
					
					if (this.canGetNull(strBuf.substring(index_char+1))) {
						// �ܹ��õ��ռ�
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
	 * @����locλ�õķ��ս����FOLLOW�����뵽locateλ�õķ��ս����
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
	 * @���Ը��ַ�����ͷ����ĸ��FIRST�����뵽ĳ�����ս����FOLLOW����
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
				// ���ַ�Ϊ�ս��
				for (i = 0; i < this.followCollection[locate].length(); i ++) {
					if (ch == this.followCollection[locate].charAt(i))
						return;
				}
				this.followCollection[locate].append(ch);
			} else {
				// ���ַ��Ƿ��ս��
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
	 * @�жϵ�ǰ����ַ����Ƿ��ܹ��Ƶ��� �մ�
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
	 * @����������ܹ�ֱ�����FIRST���ķ��ս��
	 * @param resource
	 */
	private void canDirectFirstCollection(Vector<NonterminalSymbol> resource) {
		// TODO Auto-generated method stub
		// ֻҪ�Ը÷��ս��Ϊ�󲿵����в���ʽ���Ҳ��� �ս����Ŵ�ͷ
		int index = 0, length, index1, length1;
		NonterminalSymbol node = null;
		length = resource.size();
		for (index = 0; index < length; index ++) {
			node = resource.elementAt(index);
			index1 = 0; length1 = node.grammar.size();
			while (index1 < length1) {
				if (! (node.grammar.elementAt(index1).charAt(0) == '@' || this.isEndNode(node.grammar.elementAt(index1).charAt(0)))) {
					// ��ʾ������һ������ʽ�Ҳ��Է��ս����ͷ
					break;
				}
				index1 ++;
			}
			if (index1 >= length1) {
				// �ҵ�һ���ܹ�ֱ�����FIRST���ķ��ս��
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
	 * @���ʣ�µķ��ս����FIRST��
	 * @param resource2
	 */
	private void getFirstCollection(Vector<NonterminalSymbol> resource) {
		// TODO Auto-generated method stub
		int index = 0;
		int nonterminalSymbolNum = this.resource.LL1_Grammar.size();
		NonterminalSymbol symbol = null;
		
		while (this.calculatedSysmbolNo < nonterminalSymbolNum) {
			// ��ʾ���������еķ��ս���������FIRST��
			index = 0;
			while (index < nonterminalSymbolNum) {
				if (this.calculatedFirstSymbol[index][1] == 'N') {
					//��ʾ��ǰ������ս����û�����FIRST��
					symbol = resource.elementAt(index);
					this.getDirectFirstCollection(symbol);
				}
				index ++;
			}
		}
	}
	
	/**
	 * @����ǰ������ս���ܹ����õݹ����FIRST���������������ȴ��´ν����
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
					// ��ǰ�Ƿ��ս��
					int loc = this.getSymbolLocation(strBuf.charAt(index1));
					if (this.nonterminalSymbolTable[loc][1] == 'Y' ) {
						// ��ʾ��ǰ��������ս���ܹ��Ƶ���  �մ�
						if (this.calculatedFirstSymbol[loc][1] == 'N') {
							flag = false;
							break;
						}
					} else {
						// ��ʾ��ǰ��������ս�����ܹ��Ƶ���  �մ�
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
			// ��ʾ�÷��ս����FIRST���ܹ����
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
	 * @�õ���ǰ��������ս����λ��
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
	 * @�жϵ�ǰ������ַ��Ƿ�Ҳ�ڸ�����������ս����FIRST���д���
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
	 * @ɨ���ķ��еĲ���ʽ
	 * @param NonterminalSymbol
	 */
	private void scanSyntax(Vector<NonterminalSymbol> backupGrammar) {
		// TODO Auto-generated method stub
		/* 
		 * ɾ�������Ҳ������ս���Ĳ���ʽ������ʹ����ĳһ���ս��Ϊ�󲿵����в���ʽ����ɾ����
		 * �������ж�Ӧ�÷��ս���ı��ֵ��Ϊ���� N����˵���÷��ս�������Ƴ�
		*/
		this.deleteHasEndNodeProduction(backupGrammar);
		/*
		 * ��ĳһ���ս����ĳһ����ʽ�Ҳ�Ϊ�ţ��������ж�Ӧ�÷��ս���ı�־��Ϊ���ǡ���
		 * �����ķ���ɾ���÷��ս�������в���ʽ
		 */
		this.deleteProductionNull(backupGrammar);
	}
	
	/**
	 * @ɨ�����ʽ�Ҳ���ÿһ����
	 * @param backupGrammar2
	 */
	private void scanRightSyntaxSymbol(Vector<NonterminalSymbol> backupGrammar) {
		// TODO Auto-generated method stub
		// �����з��ս����Ӧ��������û�ı�
		int index, length, index1, length1, index2, length2;
		boolean flag = true;
		// �������Ϣ
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
						// ��ǰɨ�赽�ķ��ս�����ܹ��Ƶ�����
						case 'Y':
							// ɾȥ�÷��ս��
							this.deleteNotEndChar(index2, strBuf);
							length2 --;
							break;
							
						// ��ǰɨ�赽�ķ��ս���Ų��ܹ��Ƶ�����
						case 'N':
							// ɾ����ǰ����ʽ
							flag1 = false;
							break;
							
						// ��ǰɨ�赽�ķ��ս���Ų�ȷ���Ƿ��ܹ��Ƶ�����	
						default:
							index2 ++;
						}
						if (flag1 != true)
							// ��ʾɨ�赽���ս�����ܹ��Ƶ�����, ����
							break;
						}
					if (index2 < length2) {
						// ��ʾɨ�赽���ս�����ܹ��Ƶ�����
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
						// ʹ�ò���ʽ�Ҳ�Ϊ��
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
	 * @// ɾȥ���ս��
	 * @param index
	 * @param length
	 * @param arrayChar
	 */
	private void deleteNotEndChar(int index, StringBuffer strBuf) {
		// TODO Auto-generated method stub
		strBuf.deleteCharAt(index);
	}

	/**
	 * @ɾ�������Ҳ������ս���Ĳ���ʽ
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
					// ���ķ�����ʽ�����ս��
					node.grammar.remove(index1);
					length1 --;
				} else {
					index1 ++;
				}
			}
			if (length1 == 0) {
				// ʹ����ĳһ���ս��Ϊ�󲿵����в���ʽ����ɾ��
				// ���÷��ս���ı����Ϊ���� N��
				this.setNodeNo(node.symbol, 'N');
				backupGrammar.remove(index);
				length --;
			} else {
				index ++;
			}
		}
	}
	
	/**
	 * @ɾ��ĳһ���ս����ĳһ����ʽ�Ҳ�Ϊ��
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
				// ���÷��ս���ı����Ϊ���� Y��
				this.setNodeNo(node.symbol, 'Y');
				backupGrammar.remove(index);
				length --;
			} else {
				index ++;
			}
		}
	}
	
	/**
	 * @�ж�һ���ķ����ʽ�Ƿ����ս��
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
	 * @�жϸ��ַ��Ƿ�Ϊ�ս��
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
	 * @�õ��÷��ս���ı��
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
	 * @���÷��ս���ı����Ϊ��char c��
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
	 * @�õ�����ķ��������ս��
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
	 * @�õ�Ԥ�������
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
