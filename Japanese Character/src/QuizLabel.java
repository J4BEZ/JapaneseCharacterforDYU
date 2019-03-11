import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.*;

import javax.swing.*;

public class QuizLabel extends JPanel{	
	private Font j1c = new Font("나눔고딕",Font.BOLD,180);
	private Font k1p = new Font("나눔바른펜",Font.BOLD,50);
	private Font k2p = new Font("나눔바른펜",Font.BOLD,30);
	//폰트설정
	public JLabel jpCr, korPr, Mean;
	Hiragana hrg = new Hiragana();
	Gatakana gtk = new Gatakana();
	
	double CorctPer = 100;
	String WhichAnswer ="";
	private int C = 0, W = 0;

	public QuizLabel(int r,Quiz_Board q) {
		//Hiragana or Gatakana mode
		setBounds(10,10,300,300);
		setLayout(null);
		//ModeChecking(q.m,r);
		
		jpCr = new JLabel();
		jpCr.setForeground(Color.decode("#A13BDAF"));
		jpCr.setFont(j1c);		jpCr.setBounds(10,10,300,200);
		jpCr.setFont(j1c); this.add(jpCr);
		
		korPr = new JLabel();
		korPr.setForeground(new Color(40,40,40));
		korPr.setFont(k1p);
		korPr.setLayout(new BorderLayout());
		korPr.setHorizontalAlignment(SwingConstants.RIGHT);
		korPr.setBounds(100,200,170,70);
		korPr.setFont(k1p); this.add(korPr);
		
		
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
		switch(m) {
		case Hiragana:
			this.setVisible(true);jpCr.setVisible(true);korPr.setVisible(true);
			if(r==72) {
				jpCr.setFont(new Font("",Font.BOLD,180));
				//(부) 때문에 어쩔수 없이
			}else
				jpCr.setFont(j1c);
			
			jpCr.setText(hrg.H.get(r)._Char);
			korPr.setText(hrg.H.get(r)._Kor);
			
			//System.out.println(jpCr.getText()+korPr.getText());
			// 이 과정에서 jpCr과 korPr의 텍스트 값이 얻어지고 정상적으로 출력되는걸 보면 존재는 하는데 보이지 않는것 같네요..
			break;
			
		case Gatakana:
			this.setVisible(true);jpCr.setVisible(true);korPr.setVisible(true);
			if(r==11||r==14||r==17||r==51||r==54||r==57||r==70||r==71) {
				jpCr.setFont(new Font("바탕",Font.BOLD,180));
				
			}else
				jpCr.setFont(j1c);
			
			//シツッ  ンソ 구분을 조금 더 명확하게
			jpCr.setText(gtk.G.get(r)._Char);
			korPr.setText(gtk.G.get(r)._Kor);
			break;
		
		default:
			jpCr.setText("오류발생");
			korPr.setText("등록단어없음");
			this.setVisible(false);
			break;
		}
		repaint();
	}
	
	public void TrueOrFalse(String answer) {//정 오답 체크
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
