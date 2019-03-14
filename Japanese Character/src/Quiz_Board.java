import java.awt.event.*;
import java.awt.*;
import java.awt.Font;

import javax.swing.*;

enum Mode{
	None, Hiragana, Gatakana, March11;
}

public class Quiz_Board extends JFrame implements ActionListener, MouseListener{
	double CorrectPer = 100.0;
	int rand[], sequence =1;
	
	String modeList[] = {"모드 선택","히라가나","가타카나","1주차(3.11)","프로그램 정보"};
	Mode m;
	String set = "┌───일본어 단어 배우기 프로그램──┐\n"
			   + "│  \t\t             \n"
			   + "│      제작자: J4BEZ \t              \n"
			   + "│      현재버젼: ver. a.0.0.2\t             \n"
			   + "├─────업데이트  로그──────┤\n"
			   + "│* ver a.0.0.1   \t     \t             \n"
			   + "│* 2019년 01월 15일 업데이트            \n"
			   + "│* '히라가나' 단어장 업데이트               \n"
			   + "│* '가타카나' 단어장 업데이트               \n"
			   + "│* 정답률 및 오답노트 업데이트          \t             \n"
			   + "│------------------------------\n"
			   + "│* ver a.0.0.2   \t  \n"
			   + "│* 2019년 01월 18일 업데이트            \n"
			   + "│* '히라가나' 퀴즈 업데이트          \t           \n"
			   + "│* '가타카나' 퀴즈 업데이트          \t           \n"
			   + "│* 퀴즈 모드시 중복방지 설정            \n"
			   + "│* 퀴즈 정오답 표시 설정            \n"
			   +" ------------------------------\n"
			   + "│* ver a.0.0.3   \t  \n"
			   + "│* 2019년 01월 21일 업데이트            \n"
			   + "│* 퀴즈모드 결과에 등급설정           \n"
			   + "│* 퀴즈의 마무리 업데이트        \n"
			   + "│* 퀴즈의 진행도 표시 업데이트   \n"
			   + "│* 퀴즈 모드시 커서가 자동으로    \n"
			   + "│  정답입력창에 위치됩니다.   \n"
			   + "│* 제출 버튼대신 Enter키로도 정답 제출   \n"
			   + "│* シツッ  ンソ 구분을 조금 더 명확하게  \n";
			   
	
	String grade = "";
	
	Color c;
	
	JLabel jlb, answer, correcT, progress, CrctPer;
	JTextField ans;
	JTextArea inf, inc, inct;
	
	JScrollPane sp, spinc;
	
	JButton identify, Next;
	JComboBox jcb;
	JCheckBox QorS;
	
	
	
	Font alert = new Font("나눔고딕",Font.PLAIN,15);
	
	QuizLabel ql;
	btnKeyBind bkb;
	
	public boolean quizmode = false;
	boolean HideButtonforMenu = true;
	
	
	Quiz_Board(){
		m = m.None;
		/////////////////////////////////////////////////////////////
		setSize(500,350); // 사이즈
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("일본어 배우기 a.0.03");
		setLayout(null);
		setResizable(false);//창 크기 변경 불가
		
		addMouseListener(this);
		/////////////////////////////////////////////////////////////
		
		/////////////////////////////////////////////////////////////
		bkb = new btnKeyBind(this);
		/////////////////////////////////////////////////////////////
		
		/////////////////////////////////////////////////////////////
		jlb = new JLabel("모드를 선택해 주세요↗");
		jlb.setBounds(100,150,320,15);
		add(jlb);
		/////////////////////////////////////////////////////////////
		
		/////////////////////////////////////////////////////////////
		jcb = new JComboBox(modeList);//리스트 콤보박스 만들기
		jcb.setSelectedIndex(0);//0번째 인덱스 선택
		jcb.setBounds(320,20,160,30);//절대위치 지정
		jcb.setBackground(Color.decode("#A13BDAF"));
		jcb.setForeground(Color.WHITE);
		jcb.addActionListener(this);
		add(jcb);
		/////////////////////////////////////////////////////////////
		
		/////////////////////////////////////////////////////////////
		QorS = new JCheckBox("단어장");
		QorS.setBounds(410,50,120,30);
		QorS.addActionListener(this);
		QorS.setVisible(false);
		add(QorS);
		/////////////////////////////////////////////////////////////
		
		/////////////////////////////////////////////////////////////
		progress = new JLabel("");
		progress.setFont(new Font("나눔바른펜",Font.BOLD,30));
		progress.setBounds(200,8,110,60);
		progress.setVisible(false);
		progress.setHorizontalAlignment(SwingConstants.RIGHT);
		add(progress);
		/////////////////////////////////////////////////////////////
		
		/////////////////////////////////////////////////////////////
		correcT = new JLabel("정답률 ::");
		correcT.setFont(new Font("나눔바른펜",Font.BOLD,15));
		correcT.setBounds(320,70,90,30);
		correcT.setVisible(false);
		add(correcT);
		/////////////////////////////////////////////////////////////
		
		/////////////////////////////////////////////////////////////
		
		CrctPer = new JLabel(String.format("%.1f", CorrectPer)+"%");
		CrctPer.setForeground(Color.decode("#A13BDAF"));
		CrctPer.setFont(new Font("나눔바른펜",Font.BOLD,15));
		CrctPer.setBounds(380,70,90,30);
		CrctPer.setVisible(false);
		add(CrctPer);
		
		
		/////////////////////////////////////////////////////////////
		
		/////////오답 정리 보여주는 창///////////////////////////////////////
		inct = new JTextArea("오답목록");
		inct.setFont(new Font("나눔바른펜",Font.BOLD,10));
		inct.setDisabledTextColor(Color.decode("#A13BDAF"));
		inct.setBackground(new Color(40,40,40));
		inct.setEnabled(false);
		inct.setBounds(320,95,160,15);
		add(inct);
		
		inc = new JTextArea();
		inc.setFont(new Font("나눔고딕",Font.BOLD,12));
		inc.setBackground(Color.WHITE);
		inc.setDisabledTextColor(new Color(40,40,40));
		inc.setDragEnabled(true);
		inc.setEditable(false);
		
		spinc = new JScrollPane(inc,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED
				,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		spinc.setBounds(320,110,160,70);
		this.getContentPane().add(spinc);
		spinc.setVisible(false);inct.setVisible(false);
		 
		/////////////////////////////////////////////////////////////
		
		/////////////////////////////////////////////////////////////
		answer = new JLabel("정답 입력::");
		answer.setBounds(320,190,100,15);
		add(answer);
		if(!quizmode) 
			answer.setVisible(false);
		else
			answer.setVisible(true);
		/////////////////////////////////////////////////////////////
		
		/////////////////////////////////////////////////////////////
		ans = new JTextField(15);
		ans.setBounds(320,210,100,30);
		add(ans);
		ans.setFocusable(true);
		ans.setVisible(false);
		
		/////////////////////////////////////////////////////////////
		
		/////////////////////////////////////////////////////////////
		identify = new JButton("제출");
		identify.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
		.put(KeyStroke.getKeyStroke("ENTER"),"IDENT");
		identify.getActionMap().put("IDENT", bkb);
		// keybinding
		
		identify.addActionListener(this);
		identify.setBackground(Color.decode("#A13BDAF"));
		identify.setForeground(Color.WHITE);
		identify.setBounds(420,210,60,30);
		identify.setVisible(false);
		add(identify);
		
		/////////////////////////////////////////////////////////////
		
		
		/////////////////////////////////////////////////////////////
		Next = new JButton("다 음 단 어");
		Next.addActionListener(this);
		Next.setBounds(320, 250, 160, 50);
		Next.setBackground(Color.decode("#A13BDAF"));
		Next.setForeground(Color.WHITE);
		Next.setVisible(false);
		add(Next);
		/////////////////////////////////////////////////////////////
		
		/////////////////////////////////////////////////////////////
	
		inf = new JTextArea(set);
		inf.setFont(alert);
		inf.setBackground(new Color(40,40,40));
		inf.setDisabledTextColor(Color.decode("#A13DBAF"));
		inf.setEnabled(false);
		
		
		sp = new JScrollPane(inf,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED
				,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		sp.setBounds(20,20,290,250);
		this.getContentPane().add(sp);
		sp.setVisible(false);
		/////////////////////////////////////////////////////////////
		
		/////////////////////////////////////////////////////////////
		ql = new QuizLabel(0,this);
		ql.setBounds(20,20,300,300);//절대위치 선언하구
		ql.ModeChecking(m,0);//현재 모드에 따라 레이블을 추가시켜줍니당
		add(ql);
		/////////////////////////////////////////////////////////////
		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		/////////////////////////////////////////////////////////////
		if(jcb.getSelectedIndex()==0) {
			HideButtonforMenu = true;
			jlb.setText("모드를 선택해 주세요↗");
			sp.setVisible(false);
			m = Mode.None;
		}
		else if((String)jcb.getSelectedItem() =="프로그램 정보") {
			HideButtonforMenu = true;
			jlb.setText(null);
			sp.setVisible(true);
			m = Mode.None;
			
		}
		else if("히라가나".equals((String)jcb.getSelectedItem())){
			HideButtonforMenu = false; //메뉴(프로그램정보, 히라가나텝, 가타카나텝, 등에 따라 일부버튼을 보이게 혹은 안보이게 설정하였습니다.)
			sp.setVisible(false);//현재 정보를 보여주는 텍스트 창입니다. 상관없는 친구에용
			if(m!=Mode.Hiragana) {
				m = Mode.Hiragana;
				ql.ModeChecking(m,0);
				sequence = 1;
			}
		}
		
		else if("가타카나".equals((String)jcb.getSelectedItem())){
			HideButtonforMenu = false;
			sp.setVisible(false);
			if(m!=Mode.Gatakana) {
				m = Mode.Gatakana;
				ql.ModeChecking(m,0);
				sequence = 1;
			}
		}
		
		else if("1주차(3.11)".equals((String)jcb.getSelectedItem())){
			HideButtonforMenu = false;
			sp.setVisible(false);
			if(m!=Mode.March11) {
				m = Mode.March11;
				ql.ModeChecking(m,0);
				sequence = 1;
			}
		}
		/////////////////////////////////////////////////////////////
	
		
		/////////////////////////////////////////////////////////////
		if(QorS.isSelected()) {
			if(!quizmode) {
				resetQuiz();
				if(rand!=null) {
					for(int i : rand) {
						System.out.print(i+",");
					}
					System.out.println("");
					ql.ModeChecking(m, rand[0]);
				}
			}
			quizmode = true;
			QorS.setText("퀴즈");
			mark(quizmode,e);
		}
		else {
			exitQuiz();
			ql.resetQuiz();
			QorS.setText("단어장");
		}
		/////////////////////////////////////////////////////////////
		
		/////////////////////////////////////////////////////////////
		//여기에 내가 무엇을 하려했더라
		
		/////////////////////////////////////////////////////////////
		
		/////////////////////////////////////////////////////////////
		if(e.getSource() == Next) {//다 음 단 어 버튼을 누르면
			NextChar();//글자 변경이후
			//아마도 이 곳 아래에 
			//텍스트필드에 마우스커서(?)를 올리기
			//(=마우스로 텍스트필드를 누르지 않고도, 키보드를 누르면 바로 글자 입력이되는상태)
			//가 되게 하고 싶은데 어떻게 하면 될까요
		
		}
		
		/////////////////////////////////////////////////////////////
		modeChecking(HideButtonforMenu);
		QuizorNote(quizmode,m);
		inc.setText(ql.WhichAnswer);
		CorrectPercentage();
		/////////////////////////////////////////////////////////////
	}
	
	
	/////////////////////////////////////////////////////////////
	public void modeChecking(boolean _hide) { // 콤보박스의 모드 체크
		if(_hide) {
			jlb.setVisible(true);
			QorS.setVisible(false);
			QorS.setSelected(false);
			Next.setVisible(false);
			quizmode = false;
			QuizorNote(quizmode,m);
			ql.ModeChecking(m,0);
			
		}else {
			jlb.setVisible(false);
			QorS.setVisible(true);
			Next.setVisible(true);
		}
	}
	/////////////////////////////////////////////////////////////
	
	/////////////////////////////////////////////////////////////
	public void QuizorNote(boolean _quiz,Mode m) {//퀴즈 혹은 단어장
		if(_quiz) {
			correcT.setVisible(true);
			CrctPer.setVisible(true);
			spinc.setVisible(true);
			inct.setVisible(true);
			answer.setVisible(true);
			ans.setVisible(true);
			ans.requestFocusInWindow();
			identify.setVisible(true);
			if(sequence!=rand.length) {
			
				ql.korPr.setVisible(false);
				ql.Mean.setVisible(false);
				progress.setVisible(true);
				progress.setText(sequence+"/"+(rand.length-1));
			}
			else {
				progress.setVisible(false);
			}
			jcb.setEnabled(false);
		}else {
			correcT.setVisible(false);
			CrctPer.setVisible(false);
			spinc.setVisible(false);
			inct.setVisible(false);
			answer.setVisible(false);
			ans.setVisible(false);
			ans.setText(null);
			identify.setVisible(false);
			if(m==Mode.Hiragana||m==Mode.Gatakana) {
				ql.korPr.setVisible(true);
			}else {
				ql.korPr.setVisible(false);
			}if(m==Mode.March11) {
				ql.Mean.setVisible(true);
			}else {
				ql.Mean.setVisible(false);
			}
			Next.setEnabled(true);
			jcb.setEnabled(true);
			progress.setVisible(false);
		}
	}
	/////////////////////////////////////////////////////////////
	public void mark(boolean _quiz,ActionEvent e) {
		
			Next.setEnabled(false);
			if(!identify.isEnabled()) {
				identify.setEnabled(true);
				ans.setEnabled(true);
				ans.requestFocusInWindow();
			}
			
			if(e.getSource() == identify){
				identify();
			}
			
			if(e.getSource() == Next&&!(identify.isEnabled())) {
				identify.setEnabled(true);
				ans.setEnabled(true);
				ans.requestFocusInWindow();
			}
	}
	
	public void CorrectPercentage() {
		CrctPer.setText(String.format("%.1f", ql.CorctPer)+"%");
		if(ql.CorctPer>97) {
			c = Color.decode("#A13BDAF");
			grade = "SS";
		}
		else if(ql.CorctPer>95) {
			c = Color.decode("#A13BDAF");
			grade = "S";
		}
		else if(ql.CorctPer>90) {
			c = Color.decode("#A13BDAF");
			grade = "A";
		}
		else if(ql.CorctPer>80) {
			c = Color.decode("#22BCEA");
			grade = "B";
		}
		else if(ql.CorctPer>60) {
			c = Color.decode("#F7D559");
			grade = "C";
		}
		else if(ql.CorctPer>40) {
			c = Color.decode("#FE5870");
			grade = "D";
		}
		else if(ql.CorctPer>20) {
			c = Color.decode("#EC58FA");
			grade = "E";
		}
		else{
			c = Color.decode("#DD12FE");
			grade = "F";
		}
		CrctPer.setForeground(c);
	}
	/////////////////////////////////////////////////////////////
	
	///////////////////////퀴즈 중복방지랜덤 //////////////////////////
	public int[] randomGen(Mode m) {
		int _r;
		switch (m){
			case Hiragana:
			case Gatakana:
				_r = 73;
				break;
			case March11:
				_r = 20;
				break;
			default:
				_r = 1;
				break;
		}
		int r [] = new int[_r+1];
		
		for(int i = 0; i<r.length-1; i++) {
			r[i] = (int)(Math.random()*_r);
			for(int j =0; j<i; j++) {
				if(r[i]==r[j]) {
					i--;
					break;
				}
			}
		}
		r[_r]=_r;//끝 용도
		switch(m) {
		case March11:
			ql.forRandomquiz = r;
			break;
		default:
			ql.forRandomquiz = null;
			break;
		}
		return r;
	}
	/////////////////////////////////////////////////////////////
	
	/////////////////////////////////////////////////////////////
	public void NextChar() {
		int r = 0;
		
		if(!quizmode) {
			switch(m){
			case Hiragana:
			case Gatakana:
				r = (int)(Math.random()*73);
				break;
			case March11:
				r = (int)(Math.random()*20);
				break;
			default:
				r = 0;
				break;
			}
			ql.ModeChecking(m,r);
		}
		else {
			switch(m){
			case Hiragana:
			case Gatakana:
			case March11:
				if(rand!=null) {
					if(sequence<rand.length) {
						r = rand[sequence++];
					}
					if(sequence==rand.length) {//TODO 퀴즈가 끝난다면?
						ql.setVisible(false);
						ql.jpCr.setText(String.format("정답률: %.1f",ql.CorctPer)+"%");
						ql.jpCr2.setText(String.format("정답률: %.1f",ql.CorctPer)+"%");
						ql.jpCr.setFont(new Font("나눔바른펜",Font.BOLD,50));
						ql.jpCr2.setFont(new Font("나눔바른펜",Font.BOLD,50));
						ql.jpCr.setForeground(c);ql.jpCr2.setForeground(c);
						
						ql.korPr.setText("점수: "+grade);
						ql.korPr.setForeground(c);
						ql.korPr.setVisible(true);
						
						Next.setText("체크박스를 해제해주세요!");
						Next.setEnabled(false);
						Next.setEnabled(false);
						
						identify.setEnabled(false);
						
						ans.setText("★수고하셨습니다★");
						ans.setEnabled(false);
					}
				}
				break;
			default:
				r = 0;
				break;
			}
			//퀴즈일땐 끝에 도달할시 엔딩, 그전까진 중복방지랜덤으로 글자출력
			if(r<rand.length-1) {
				ql.ModeChecking(m,r);
			}
		}
		repaint();
	}
	/////////////////////////////////////////////////////////////
	
	/////////////////////////////////////////////////////////////
	public void identify() {
		String submit ="";
		submit = ans.getText().trim();
		//trim == 앞뒤의 공백제거 
		ans.setText("");
		ql.TrueOrFalse(submit,m,sequence);
	
		
		//제출답과 일치한지 아닌지 판별
		
		//버튼 끄기 켜기 설정.
		Next.setEnabled(true);
		identify.setEnabled(false);
		ans.setEnabled(false);
	}
	/////////////////////////////////////////////////////////////
	
	/////////////////////////////////////////////////////////////
	public void resetQuiz() {
		if(rand!=null) {
			ql.ModeChecking(m, 0);
		}
		rand = null;
		sequence = 1;
		//진행도 초기화
		rand = randomGen(m);
	}
	
	public void exitQuiz() {//quizmode checkbox 끌 때 
		if(rand!=null&&quizmode) {
				ql.ModeChecking(m, 0);
			Next.setText("다 음 단 어");
			sequence = 1;
			grade ="";
			quizmode = false;
		}
	}
	
	/////////////////////////////////////////////////////////////
	public void mouseClicked(MouseEvent arg0) {}
	public void mouseEntered(MouseEvent arg0) {}
	public void mouseExited(MouseEvent arg0) {}
	public void mousePressed(MouseEvent e) {
		System.out.print("X좌표:: "+e.getX()); System.out.print("Y좌표 :" +e.getY());
		System.out.println(m);
	}
	public void mouseReleased(MouseEvent arg0) {}
	/////////////////////////////////////////////////////////////
}

class btnKeyBind extends AbstractAction {

	Quiz_Board qb;
	
	btnKeyBind(Quiz_Board qb){
		this.qb = qb;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		qb.mark(qb.quizmode, e);
	}
	
	
}