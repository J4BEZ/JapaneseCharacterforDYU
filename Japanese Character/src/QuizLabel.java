import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.*;

import javax.swing.*;

public class QuizLabel extends JPanel{	
	private Font j1c = new Font("나눔고딕",Font.BOLD,180);
	private Font j2c = new Font("나눔고딕",Font.BOLD,80);
	private Font k1p = new Font("나눔바른펜",Font.BOLD,50);
	private Font k2p = new Font("나눔바른펜",Font.BOLD,30);
	//폰트설정
	public JLabel jpCr, korPr;
	public JLabel jpCr2,Mean;
	
	Hiragana hrg = new Hiragana();
	Gatakana gtk = new Gatakana();
	March11Class M11C = new March11Class();
	
	double CorctPer = 100;
	String WhichAnswer ="";
	private int C = 0, W = 0;
	int forRandomquiz[] = null;

	public QuizLabel(int r,Quiz_Board q) {
		//Hiragana or Gatakana mode
		setBounds(10,10,300,300);
		setLayout(null);
		//ModeChecking(q.m,r);
		
		jpCr = new JLabel();
		jpCr.setForeground(Color.decode("#A13BDAF"));
		jpCr.setFont(j1c);	jpCr.setBounds(10,10,300,200);
		this.add(jpCr);
		
		korPr = new JLabel();
		korPr.setForeground(new Color(40,40,40));
		korPr.setFont(k1p);
		korPr.setLayout(new BorderLayout());
		korPr.setHorizontalAlignment(SwingConstants.RIGHT);
		korPr.setBounds(100,200,170,70);
		korPr.setFont(k1p); this.add(korPr);
		
		jpCr2 = new JLabel("<html>テツト</html>");
		jpCr2.setForeground(Color.decode("#A13BDAF"));
		jpCr2.setBounds(10,10,300,200); jpCr2.setHorizontalAlignment(SwingConstants.LEFT);jpCr2.setVerticalAlignment(SwingConstants.CENTER);
		jpCr2.setFont(j2c); this.add(jpCr2);
		
		Mean = new JLabel("<html>테스트</html>");
		Mean.setForeground(new Color(40,40,40));
		Mean.setBounds(100,200,170,70);Mean.setHorizontalAlignment(SwingConstants.RIGHT);Mean.setVerticalAlignment(SwingConstants.BOTTOM);
		Mean.setFont(k2p); this.add(Mean);
		
		
		jpCr.setVisible(false);
		korPr.setVisible(false);
		jpCr2.setVisible(false);
		Mean.setVisible(false);
		//Numbers Mode
		
	}
	public int[] rarry(int r) {
		int i =0;
		int rarry[] = new int[r];
		for(int _r:rarry) {
			_r = i++;
		}
		return rarry;
	}
		
	
	public void ModeChecking(Mode m,int r) {
		//모드에 알맞은 레이블을 추가시켜주는 메소드 입니다.
		jpCr.setForeground(Color.decode("#A13BDAF"));
		jpCr2.setForeground(Color.decode("#A13BDAF"));
		hide(m);
		switch(m) {
		case Hiragana:
			if(r==72) {
				jpCr.setFont(new Font("",Font.BOLD,180));
				//(부) 때문에 어쩔수 없이
			}else
				jpCr.setFont(j1c);
			
			jpCr.setText(hrg.H.get(r)._Char);
			korPr.setText(hrg.H.get(r)._Kor);
			break;
			
		case Gatakana:
			if(r==11||r==14||r==17||r==51||r==54||r==57||r==70||r==71) {
				jpCr.setFont(new Font("바탕",Font.BOLD,180));
				//シツッ  ンソ 구분을 조금 더 명확하게
			}else
				jpCr.setFont(j1c);
			
			
			jpCr.setText(gtk.G.get(r)._Char);
			korPr.setText(gtk.G.get(r)._Kor);
			break;
			
		case March11:
			jpCr2.setText("<html>"+M11C.M11.get(r)._Sent+"</html>");
			Mean.setText("<html>"+M11C.M11.get(r)._Mean+"</html>");
			break;
		
		default:
			jpCr.setText("오류발생");
			korPr.setText("등록단어없음");
			
			this.setVisible(false);
			break;
		}
		repaint();
	}
	
	public void hide(Mode m) {
		switch(m){
		case Hiragana:
		case Gatakana:
			this.setVisible(true); jpCr.setVisible(true); korPr.setVisible(true);
			jpCr2.setVisible(false); Mean.setVisible(false);
			//TODO 모드에 따라 라벨 보이게/ 안보이게
			break;
		case March11:
			this.setVisible(true); jpCr.setVisible(false); korPr.setVisible(false);
			jpCr2.setVisible(true); Mean.setVisible(true);
			break;
		default:
			break;
		}
		
	}
	
	public void TrueOrFalse(String answer,Mode m, int sequence) {//정 오답 체크
		switch(m) {
		case Hiragana:
		case Gatakana:
			if(answer.equals(korPr.getText())) {
				jpCr.setForeground(Color.decode("#326FC0"));
				jpCr.setText("O");
				C++;
			}
			else{
				WhichAnswer += jpCr.getText()+" : "+korPr.getText()+"\n";
				W++;
				jpCr.setForeground(Color.decode("#F67280"));
				jpCr.setText("X");
			}
			break;
		case March11://TODO sequence가 아닌 random[sequence-1]가 들어가야함 수정바람
			if(forRandomquiz!=null) {
				if(answer.equals(M11C.M11.get(forRandomquiz[sequence-1])._Mean)) {
					jpCr2.setForeground(Color.decode("#326FC0"));
					jpCr2.setText("O");
					C++;
				}
				else{
					WhichAnswer += M11C.M11.get(forRandomquiz[sequence-1])._Sent+" : "+M11C.M11.get(forRandomquiz[sequence-1])._Mean+"\n";
					W++;
					jpCr2.setForeground(Color.decode("#F67280"));
					jpCr2.setText("X");
				}
			}
			break;
			
		}
		CorctPer = (double)((double)C/(double)(C+W))*100.0;
		repaint();
		
	}
	
	public void resetQuiz() {
		jpCr.setFont(j1c);jpCr.setForeground(Color.decode("#A13BDAF"));
		korPr.setFont(k1p);korPr.setForeground(new Color(40,40,40));
		WhichAnswer = "";
		CorctPer = 100;
		W = 0; C=0;
	}
}
