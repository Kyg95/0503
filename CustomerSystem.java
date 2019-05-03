package yg;

import java.awt.Button;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class CustomerSystem extends Panel implements ActionListener {
	TotalSystem ts;
	Label[] labels;
	TextField[] textField;
	Button btns[];
	Panel[] panels;
	String[] label_title = { "I D", "이 름", "주 소", "가입일" };
	String[] btns_title = { "삽 입", "삭 제", "변 경", "조 회", "지우기", "달 력" };

	void makePanel() {
		panels = new Panel[5];
		for (int i = 0; i < panels.length; i++) {
			panels[i] = new Panel();// 패널 생성
		}
	}

	void makeButton() {
		btns = new Button[6];// 배열 생성
		for (int i = 0; i < btns.length; i++) {
			btns[i] = new Button(btns_title[i]);// 버튼 생성
			btns[i].addActionListener(this);
		}
	}

	void makeTextField() {
		textField = new TextField[4];// 배열 생성
		for (int i = 0; i < textField.length; i++) {
			textField[i] = new TextField(15);
		}
		textField[3].setEnabled(false);// 가입일 텍스트필드 비활성화
	}

	void makeLabel() {
		labels = new Label[4];
		for (int i = 0; i < labels.length; i++) {
			labels[i] = new Label(label_title[i]);// 레이블 생성
		}
	}

	public CustomerSystem(TotalSystem ts) {
//		super();
		this.ts = ts;
		this.setLayout(new GridLayout(5, 1));
		makeLabel();
		makeButton();
		makeTextField();
		makePanel();
		panels[0].add(labels[0]);
		panels[0].add(textField[0]);
		panels[1].add(labels[1]);
		panels[1].add(textField[1]);
		panels[2].add(labels[2]);
		panels[2].add(textField[2]);
		panels[3].add(labels[3]);
		panels[3].add(textField[3]);
		panels[3].add(btns[5]);
		panels[4].add(btns[0]);
		panels[4].add(btns[1]);
		panels[4].add(btns[2]);
		panels[4].add(btns[3]);
		panels[4].add(btns[4]);
		for (int i = 0; i < panels.length; i++) {
			this.add(panels[i]);
			panels[i].setBackground(Color.ORANGE);
		}
//		this.setSize(500, 400);
//		this.setVisible(true);
//		this.addWindowListener(new LoginSystemExit());
	}

	public static void main(String[] args) {
//		new CustomerSystem("고객관리 시스템 ver1.0");
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		Object obj = arg0.getSource();
		if (obj == btns[0]) {// 삽입버튼
			String id = textField[0].getText();// 입력한 id불러옴
			if (id == null) {
				JOptionPane.showMessageDialog(ts, "고객ID가 존재하지 않아서 작업을 진행할 수 없습니다.");
			} else {
				CRUDprocess crud = new CRUDprocess();
				Customer_info ci = crud.selectCustomer(id);
				if (ci == null) {
					String name = textField[1].getText();// 이름
					String addr = textField[2].getText();// 주소
					String reg = textField[3].getText();// 가입일
					Customer_info cust = new Customer_info();
					cust.setCust_id(id);
					cust.setCust_name(name);
					cust.setCust_reg_date(reg);
					cust.setCust_addr(addr);
					int r = crud.insertCustomer(cust);
					if (r > 0) {
						JOptionPane.showMessageDialog(ts, "고객정보가 등록되었습니다.");
					} else {
						JOptionPane.showMessageDialog(ts, "고객정보 등록 중 문제가 발생했습니다.");
					}
				} else {
					JOptionPane.showMessageDialog(ts, "이미 동일한 ID가 존재합니다.");
				}
			}

		} else if (obj == btns[1]) {// 삭제버튼

		} else if (obj == btns[2]) {// 변경버튼

		} else if (obj == btns[3]) {// 조회 버튼

		} else if (obj == btns[4]) {// 지우기 버튼

		} else if (obj == btns[5]) {// 달력버튼을 누른경우
			new CalendarByAwt(this);
		}

	}
}
