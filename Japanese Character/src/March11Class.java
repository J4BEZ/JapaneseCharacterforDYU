import java.util.ArrayList;

class M11 {
	String _Sent, _Mean = null;
	
	M11(String Sent, String mean){
		_Sent = Sent; _Mean = mean;
	}
}

public class March11Class {
	ArrayList<M11> M11 = new ArrayList<M11>();
	
	String []Sentence = {"かさ","ほん","いす","くつ","ねこ",		"いぬ","かばん","さかな","いちご","くつした",
						 "りんご","とけい","でんわ","めがね","くるま",	"ぼうし","ひこうき","せっけん","ふうせん","ちょうちょ","끝"};
	String []Mean = {"우산","책","의자","구두","고양이",	"강아지","가방","물고기","딸기","양말",
					 "사과","시계","전화","안경","차",	"모자","비행기","비누","풍선","나비","끝"};
	
	
	March11Class(){
		for(int i=0; i<Sentence.length; i++) {
			M11.add(new M11(Sentence[i],Mean[i]));
		}
	}
}
