import java.util.ArrayList;

class Gt{
	String _Char,_Kor = null;
	
	Gt(String Char, String Kor){
		_Char = Char; _Kor = Kor;
	}
}

public class Gatakana {
	ArrayList<Gt> G = new ArrayList<Gt>();
	
	String []Char = {"ア","イ","ウ","エ","オ",  "カ","キ","ク","ケ","コ",
			 		 "サ","シ","ス","セ","ソ",  "タ","チ","ツ","テ","ト",
			 		 "ナ","ニ","ヌ","ネ","ノ",  "ハ","ヒ","フ","ヘ","ホ",
			 		 "マ","ミ","ム","メ","モ",  "ヤ",     "ユ",     "ヨ",
			 		 "ラ","リ","ル","レ","ロ",  "ワ",		       "ヲ",
			 		 //
			 		 "ガ","ギ","グ","ゲ","ゴ",  "ザ","ジ","ズ","ゼ","ゾ",
			 		 "ダ","ヂ","ヅ","デ","ド",  "バ","ビ","ブ","ベ","ボ",
			 		 "パ","ピ","プ","ペ","ポ",  "ン","ッ","ヴ","끝"};

	String []Kor = {"아","이","우","에","오",  "카","키","쿠","케","코",
					"사","시","스","세","소",  "타","치","츠","테","토",
					"나","니","누","네","노",  "하","히","후","헤","호",
					"마","미","무","메","모",  "야",     "유",	 "요",
					"라","리","루","레","로",  "와",             "오",
					//
					"가","기","구","게","고",  "자","지","즈","제","조",
					"다","지","즈","데","도",  "바","비","부","베","보",
					"파","피","푸","페","포",  "응","읏","부","끝"};
	Gatakana(){
		for(int i =0; i <Char.length; i++) {
			G.add(new Gt(Char[i],Kor[i]));
		}
	}

}