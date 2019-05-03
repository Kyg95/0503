package yg;

import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;

//public class LoginSystem extends Frame {
public class LoginSystem extends Panel {
	Font font; // 폰트
	Button login, cancel;
	Label id_info, pwd_info;
	TextField id_txt, pwd_txt;
	Panel pan_id, pan_pwd, pan_button;// 아이디 입력 암호 입력
	Panel north; TotalSystem ts;

	public LoginSystem(TotalSystem ts) {
//		super(str);
		this.ts = ts;
		font = new Font("휴면굴림체", Font.BOLD, 20); //사용할 폰트 굵기 크기
		id_info = new Label("ID를 입력하세요."); id_info.setFont(font); //라벨내용과 폰트
		id_txt=new TextField(20); id_txt.setFont(font); //텍필 크기와 폰트
		pwd_info = new Label("암호를 입력하세요"); pwd_info.setFont(font); //라벨내용과 폰트
		pwd_txt = new TextField(20); pwd_txt.setFont(font);//텍필 크기와 폰트
		pwd_txt.setEchoChar('*');//비번은 *로 보이게 설정
		pan_id = new Panel(); //ID입력을 위한 레이블과 텍스트필드를 담을 패널
		pan_id.add(id_info); pan_id.add(id_txt); //패널에 id라벨과 텍필을 추가
		pan_pwd = new Panel();//암호입력을 위한 레이블과 텍스틉필드를 담을패널
		pan_pwd.add(pwd_info); pan_pwd.add(pwd_txt);//비번도 동일
		login = new Button("로그인"); login.setFont(font);// 로그인 버튼과 폰트
		login.addActionListener(new MyLogin(this,ts));// 버튼눌렀을경우 액션리스너
		cancel = new Button("다시입력"); cancel.setFont(font);//다시입력 버튼과 폰트
		cancel.addActionListener(new MyLogin(this,ts));//눌렀을경우 액션리스너
		pan_button = new Panel(); //버튼을 담을패널
		pan_button.add(login); pan_button.add(cancel);//패널에 버튼2개를 담음
		north = new Panel(); north.setLayout(new GridLayout(2,1));
		north.add(pan_id); north.add(pan_pwd);
//		this.setSize(500,400);
		this.add("North",north); this.add("South",pan_button);
		this.setBackground(new Color(157,200,255));
//		this.addWindowListener(new LoginSystemExit());
//		this.setVisible(true);
		
	}
	public static void main(String[] args) {
//		new LoginSystem("로그인 ver1.0");
	}
}
//오라클 c에서 서버까지들어간다음에 jdbc까지 들어감 
//새로운 패키지만들어서 복붙함 다음에 패키지에 필드패치하고 새로운 라이브러리 추가
//MYBatis를 이용한 oracle 접속 
//1. xml 환경설정파일 <= db접속 계정 / 암호  /db의 ip주소 / db이름  4가지 정보를 환경설정파일에 넣음
//2. xml 매퍼파일(?) <--- 쿼리
//3. calss java객체 