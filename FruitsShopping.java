package yg;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Label;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

class FruitsShoppingEnd extends WindowAdapter {

	@Override
	public void windowClosing(WindowEvent e) {
		System.exit(0);
	}
	
}
class FruitsShoppingExit implements WindowListener {

	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		System.exit(0);
		
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	//오버라이드
}
class FruitImage extends Panel{
	Image img;
	FruitImage(String path){
		img=Toolkit.getDefaultToolkit().getImage(path);
	}
	@Override
	public void paint(Graphics arg0) {
//		arg0.drawImage(img, 0, 0, 
//				img.getWidth(this),img.getHeight(this),this);
		arg0.drawImage(img, 0, 0, 
				this.getWidth(),this.getHeight(),this);
	}
	
}
class MyButtonListener implements ActionListener{
	FruitsShopping fs; TotalSystem ts;
	MyButtonListener(FruitsShopping fs, TotalSystem ts){
		this.fs = fs; this.ts = ts;
	}
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj == fs.first) {
			fs.card.first(fs.fruits);
		}else if(obj == fs.prev) {
			fs.card.previous(fs.fruits);
		}else if(obj == fs.next) {
			fs.card.next(fs.fruits);
		}else if(obj == fs.last) {
			fs.card.last(fs.fruits);
		}else if(obj == fs.logout); //로그아웃을 누른경우
		    ts.card.show(ts.totalPanel,"login");
	}
}
public class FruitsShopping extends Panel { 
//	implements ActionListener{
//	@Override
//	public void actionPerformed(ActionEvent arg0) {
//		Object obj = arg0.getSource();
//		if(obj == first) {//첫번째 버튼인 경우
//			card.first(fruits);//fruits에 있는 패널 중 첫번째로 이동
//		}else if(obj == prev) {//두번째 버튼인 경우
//			card.previous(fruits);//fruits에 있는 패널중 이전으로 이동
//		}else if(obj == next) {//세번째 버튼인 경우
//			card.next(fruits);//fruits에 있는 패널 중 다음으로 이동
//		}else if(obj == last) {//네번째 버튼인 경우
//			card.last(fruits);//fruits에 있는 패널 중 마지막으로 이동
//		}
//	}
	Button first,prev,next,last,logout;
	Panel buttons;//버튼을 담을 패널
	Panel fruits;//과일 소개를 담을 패널(카드레이아웃을 사용)
	Panel[] items_intro, item, desc;
	//items_intro:과일 소개용 패널,(1행 2열의 GridLayout을 사용)
	//item:과일 이미지용 패널, items_intro의 왼쪽에 배치된다.
	//desc:과일 소개글용 패널, items_intro의 오른쪽에 배치된다.
	CardLayout card;//fruits가 사용할 배치관리자
	TotalSystem ts;
	void init() {
		card = new CardLayout();//배치관리자 생성
		fruits = new Panel(card);//패널을 생성하면서 배치관리자 설정
		items_intro = new Panel[6]; item=new Panel[6];
		desc = new Panel[6];
		//items_intro,item,desc를 모두 크기 6의 배열 생성
		for(int i=0; i<items_intro.length; i++) {//6회 반복
			items_intro[i] = new Panel();//패널 생성
			items_intro[i].setLayout(new GridLayout(1,2));//1행2열
			desc[i]=new Panel(new GridLayout(3,1));//3행 1열
			switch(i) {
			case 0:
				item[i] = new FruitImage("src\\yg\\grape.jpg");//이미지를 가지고 있는 패널 생성
				break;
			case 1:
				item[i]=new FruitImage("src\\yg\\img.jpg"); 
				break;
			case 2:
				item[i]=new FruitImage("src\\yg\\kiwi.jpg"); 
				break;
			case 3:
				item[i]=new FruitImage("src\\yg\\lemon.jpg"); 
				break;
			case 4:
				item[i]=new FruitImage("src\\yg\\orange.jpg"); 
				break;
			case 5:
				item[i]=new FruitImage("src\\yg\\strawberry.jpg"); 
				break;
			}
			items_intro[i].add(item[i]);//과일 이미지를 붙임
			items_intro[i].add(desc[i]);//과일 설명을 붙임
		}//end of for
		desc[0].add(new Label("이름 : 포도"));//첫번째 과일 이름
		desc[0].add(new Label("폴리페놀을 다량 함유하고 있어"));//과일설명1
		desc[0].add(new Label("황산화 작용을 합니다."));//과일설명2
		desc[1].add(new Label("이름 : 귤"));//두번째 과일 이름
		desc[1].add(new Label("시네피린을 함유하고 있어"));
		desc[1].add(new Label("감기예방에 좋습니다."));
		desc[2].add(new Label("이름 : 키위"));
		desc[2].add(new Label("비타민 C가 풍부합니다."));
		desc[2].add(new Label("다이어트나 미용에 좋습니다."));
		desc[3].add(new Label("이름 : 레몬"));
		desc[3].add(new Label("레몬에 포함된 구연산은 피로회복에 좋습니다."));
		desc[3].add(new Label("비타민 C도 풍부합니다."));
		desc[4].add(new Label("이름 : 오렌지"));
		desc[4].add(new Label("비타민 C가 풍부합니다."));
		desc[4].add(new Label("생과일 쥬스로 마시면 좋습니다."));
		desc[5].add(new Label("이름 : 딸기"));
		desc[5].add(new Label("비타민 C나 폴라보노이드를 다량 "));
		desc[5].add(new Label("함유하고 있습니다."));
		
		fruits.add(items_intro[0],"grape");
		fruits.add(items_intro[1],"tangerine");
		fruits.add(items_intro[2],"kiwi");
		fruits.add(items_intro[3],"lemon");
		fruits.add(items_intro[4],"orange");
		fruits.add(items_intro[5],"strawberry");
		first=new Button("처음으로");prev=new Button("이전으로");
		next=new Button("다음으로");last=new Button("맨뒤로");
		logout = new Button("로그아웃");
		logout.addActionListener(new MyButtonListener(this,ts));
		first.addActionListener(new MyButtonListener(this,ts));
		prev.addActionListener(new MyButtonListener(this,ts));
		next.addActionListener(new MyButtonListener(this,ts));
		last.addActionListener(new MyButtonListener(this,ts));
		buttons = new Panel();//버튼을 담을 패널 생성
		buttons.add(first);buttons.add(prev);
		buttons.add(next); buttons.add(last);
		buttons.add(logout);
	}
	
	public FruitsShopping(TotalSystem ts) {
//		super(str);
		this.ts = ts;
		init();
		this.setLayout(new BorderLayout()); //패널로 생성할경우에는 일케생성해야함
		this.add("Center",fruits);
		this.add("South",buttons);
//		this.setSize(800, 500);
//		this.setLocation(200, 200);
//		this.setVisible(true);
//		this.addWindowListener(new FruitsShoppingExit());
//		this.addWindowListener(new FruitsShoppingEnd());
	}
	public static void main(String[] args) {
//		new FruitsShopping("과일 소개 ver1.0");
	}
}








