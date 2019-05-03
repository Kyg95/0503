package yg;

import java.awt.Button;
import java.awt.Checkbox;
import java.awt.Choice;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class OrderingSystem1 extends Panel implements WindowListener, ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
		// 버튼을 눌렀을 때 자동으로 호출되는 메서드(콜백 메서드)
		if (e.getSource() == btns[0]) {// 계산하기를 누른경우
			// 어떤 음료를 선택했는지 찾는다.
			total_price = 0;
			if (drinks[0].getState()) {// 우유를 선택한 경우
				String m = numbers[0].getSelectedItem();
				int price = MILK_PRICE * Integer.parseInt(m);
				total_price = total_price + price;
			}
			if (drinks[1].getState()) {// 아메리카노를 선택한 경우
				String a = numbers[1].getSelectedItem();
				int price = AMERICANO * Integer.parseInt(a);
				total_price = total_price + price;
			}
			if (drinks[2].getState()) {// 라테를 선택한 경우
				String l = numbers[2].getSelectedItem();
				int price = LATTE * Integer.parseInt(l);
				total_price = total_price + price;
			}
			total.setText("총합:" + total_price);
		} else if (e.getSource() == btns[1]) {// 다시하기를 누른경우
			// total_price를 0으로 설정
			total_price = 0;
			// 음료수 체크박스를 해제
			drinks[0].setState(false);// 우유 체크박스가 해제된다.
			drinks[1].setState(false);// 아메리카노 체크박스 해제
			drinks[2].setState(false);// 라테 체크박스 해제
			// 음료수 갯수 초이스를 처음상태로 설정
			numbers[0].select(0);// 0->첫번째 항목,
			// 초이스의 데이터를 첫번째 항목으로 설정
			numbers[1].select(0);
			numbers[2].select(0);
		}
	}

	@Override
	public void windowActivated(WindowEvent arg0) {
	}

	@Override
	public void windowClosed(WindowEvent arg0) {
	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		System.exit(0);
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
	}

	@Override
	public void windowIconified(WindowEvent arg0) {
	}

	@Override
	public void windowOpened(WindowEvent arg0) {
	}

	Panel[] panels;
	Checkbox[] drinks;
	Choice[] numbers;
	Button[] btns;
	Label total;
	String[] drinks_name = { "우유", "아메리카노", "라테", "데자와", "콜라" };
//음료수 기준 가격 설정
	final int MILK_PRICE = 1000;// 우유의 기준값 설정
	final int AMERICANO = 1500;// 아메리카노의 기준값 설정
	final int LATTE = 2000;// 라테의 기준값 설정
	final int DEJAVA = 2500;
	final int CO = 1600;
	int total_price = 0;// 총 음료수 가격을 위한 변수

	void doIt() {// 모든 컴포넌트 생성작업을 여기서 한다.
		panels = new Panel[7];
		drinks = new Checkbox[5];
		numbers = new Choice[5];
		btns = new Button[2];
		total = new Label("                        ");
		for (int i = 0; i < panels.length; i++) {// 패널5개 생성
			panels[i] = new Panel();
			panels[i].setLayout(new FlowLayout());// 배치관리자설정
			switch (i) {
			case 0:
				panels[i].setBackground(new Color(245,205,255));
				break;
			case 1:
				panels[i].setBackground(new Color(0,255,255));
				break;
			case 2:
				panels[i].setBackground(new Color(255, 204, 255));
				break;
			case 3:
				panels[i].setBackground(new Color(051,255,255));
				break;
			case 4:
				panels[i].setBackground(new Color(102, 255, 255));
				break;
			case 5:
				panels[i].setBackground(new Color(153, 255, 255));
				break;
			case 6:
				panels[i].setBackground(new Color(204, 255, 255));
				break;
			}
		}
		for (int i = 0; i < drinks.length; i++) {// 체크박스3개 생성
			drinks[i] = new Checkbox(drinks_name[i]);
		}
		for (int i = 0; i < numbers.length; i++) {// 초이스 3개 생성
			numbers[i] = new Choice();
			for (int k = 1; k <= 10; k++) {// 초이스에 갯수를 채움(1~10)
				numbers[i].add(k + "");
			} // end of for
		} // end of for
		btns[0] = new Button("계산하기");
		btns[0].addActionListener(this);
		btns[1] = new Button("다시하기");
		btns[1].addActionListener(this);
		for (int i = 0; i < 5; i++) {// 첫번째 패널부터 세번째 패널까지 체크박스와 초이스 붙임
			panels[i].add(drinks[i]);// 체크박스 붙임
			panels[i].add(numbers[i]);// 초이스 붙임
		}
		panels[5].add(total);// 네번째 패널에 레이블을 붙임
		panels[6].add(btns[0]);
		panels[6].add(btns[1]);
		// 다섯번째 패널에 버튼 2개를 붙임
	}

//public OrderingSystem(String str) {
	public OrderingSystem1(TotalSystem ts) {
//	super(str);
		this.setLayout(new GridLayout(7, 1));
		doIt();
		for (int i = 0; i < 7; i++) {
			this.add(panels[i]);// 윈도우에 패널5개를 붙임
		}
//	this.setSize(400, 350);
//	this.setBackground(Color.GREEN);
//	this.addWindowListener(this);
//	this.setVisible(true);
	}

	public static void main(String[] args) {
//	new OrderingSystem1("음료수 주문 시스템 ver1.0");
	}
}