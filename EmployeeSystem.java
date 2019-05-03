package yg;

import java.awt.Button;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JOptionPane;


public class EmployeeSystem extends Panel implements ActionListener, ItemListener {

	EmployeeSystem es;
	TotalSystem ts;
	Label[] labels;
	Font font;
	TextField[] textFields;
	Panel[] panels;
	Checkbox[] gender;
	Choice[] choices;
	Button[] btns;
	CheckboxGroup group;// 라디오버튼을 위한 그룹
	String[] labels_title = { "사 번", "이 름", "부 서", "성 별", "주 소", "입사일" };
	String[] btns_title = { "삽 입", "삭 제", "변 경", "조 회", "지우기", "달 력" };
	String[] dept_names = { "영업부", "홍보부", "개발부", "기획부" };

	void makePanel() {
		panels = new Panel[7];
		for (int i = 0; i < panels.length; i++) {
			panels[i] = new Panel();
			panels[i].setFont(font);
		}
	}

	void makeChoice() { // 초이스를 생성하는 메서드
		choices = new Choice[4];
		choices[0] = new Choice();
		for (int i = 0; i < dept_names.length; i++) {
			choices[0].add(dept_names[i]);
		}
		choices[1] = new Choice();
		choices[1].add("입사년");
		for (int i = 2019; i >= 1980; i--) {
			choices[1].add(i + "");
		}
		choices[2] = new Choice();
		choices[2].add("입사월");
		for (int i = 1; i <= 12; i++) {
			choices[2].add(i + "");
		}
		choices[3] = new Choice();
		choices[3].add("입사일");
		for (int i = 1; i <= 31; i++) {
			choices[3].add(i + "");
		}
	}

	void makeRadio() { // 성별을 위한 라디오 버튼 생성 메서드
		group = new CheckboxGroup();
		gender = new Checkbox[2];
		gender[0] = new Checkbox("남자", group, true);
		gender[0].setFont(font);
		gender[1] = new Checkbox("여자", group, false);
		gender[1].setFont(font);
	}

	void makeTextField() { // 텍필
		textFields = new TextField[3];
		for (int i = 0; i < textFields.length; i++) {
			textFields[i] = new TextField(15);
			textFields[i].setFont(font);
		}
	}

	void makeButton() { // 버튼생성
		btns = new Button[6];
		for (int i = 0; i < btns.length; i++) {
			btns[i] = new Button(btns_title[i]);
			btns[i].setFont(font);
			btns[i].addActionListener(this);
		}
	}

	void makeLabel() { // 라벨
		labels = new Label[6];
		for (int i = 0; i < labels.length; i++) {
			labels[i] = new Label(labels_title[i]);
			labels[i].setFont(font);
		}
	}

	public EmployeeSystem(TotalSystem ts) {
//		super(str);
		this.ts = ts;
		font = new Font("궁서", Font.BOLD, 15);
		this.setLayout(new GridLayout(7, 1));
		makeLabel();
		makeButton();
		makeTextField();
		makeChoice();
		makePanel();
		makeRadio();
		panels[0].add(labels[0]);
		panels[0].add(textFields[0]);
		panels[1].add(labels[1]);
		panels[1].add(textFields[1]);
		panels[2].add(labels[2]);
		panels[2].add(choices[0]);
		panels[3].add(labels[3]);
		panels[3].add(gender[0]);
		panels[3].add(gender[1]);
		panels[4].add(labels[4]);
		panels[4].add(textFields[2]);
		panels[5].add(labels[5]);
		panels[5].add(choices[1]);
		panels[5].add(choices[2]);
		panels[5].add(choices[3]);
		panels[5].add(btns[5]);// 달력버튼
		panels[6].add(btns[0]);
		panels[6].add(btns[1]);
		panels[6].add(btns[2]);
		panels[6].add(btns[3]);
		panels[6].add(btns[4]);
		for (int i = 0; i < panels.length; i++) {
			this.add(panels[i]);// 7개 패널 윈도우 붙임
		}
//		this.addWindowListener(new LoginSystemExit());
		this.setBackground(new Color(35, 157, 200));
//		this.setSize(400, 500);
//		this.setVisible(true);
	}

	public static void main(String[] args) {
//		new EmployeeSystem("사원관리 시스템");

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		Object ob = arg0.getSource();
		if (ob == btns[0]) {// 삽입버튼을 누른 경우
			// 동일한 사번이 DB에 존재하는지 검사 , 사번이 없는지 검사
			String id = textFields[0].getText(); // 사번을 읽어옴
			if (id.equals("")) { // 사번을 입력하지 않은경우
				JOptionPane.showMessageDialog(ts, "사번이 공란입니다.");
			} else {
				CRUDprocess crud = new CRUDprocess();
				Empl_info emp = crud.selectEmpl(id);
				if (emp != null) { // 동일한 사번이 이미 존재하는 경우
					JOptionPane.showMessageDialog(ts, "동일한 사번이 이미 존재합니다.");
				} else { // 동일한 사번이 존재하지 않는 경우
					String name = textFields[1].getText(); // 이름
					String dept = choices[0].getSelectedItem();// 부서명
					String gen = "";
					if (gender[0].getState() == true) {
						gen = "남자";
					} else if (gender[1].getState() == true) {
						gen = "여자";
					}
					String addr = textFields[2].getText();
					String year = choices[1].getSelectedItem();
					String month = choices[2].getSelectedItem();
					String date = choices[3].getSelectedItem();
					String hire = year + "/" + month + "/" + date;
					Empl_info empl = new Empl_info();
					empl.setEmp_id(id);
					empl.setEmp_name(name);
					empl.setEmp_dept(dept);
					empl.setEmp_gender(gen);
					empl.setEmp_addr(addr);
					empl.setEmp_hire(hire);
					int r = crud.insertEmpl(empl);
					if (r > 0) {
						JOptionPane.showMessageDialog(ts, "사원정보가 등록되었습니다.");
					} else {
						JOptionPane.showMessageDialog(ts, "사원등록중 오류가 발생했습니다.");
					}
				}
				// 위의 두가지를 통과하면 삽입
			}

		} else if (ob == btns[1]) {// 삭제버튼
			String id = textFields[0].getText();// 사번을 불러옴
			if (id.equals("")) {// 사번을 입력하지 않은 경우
				JOptionPane.showMessageDialog(ts, "사번이 입력되지 않아서 작업을 진행할 수 없습니다.");
			} else {
				int result = JOptionPane.showConfirmDialog(ts, "정말로 삭제하시겠습니까?", "작업 확인", JOptionPane.YES_NO_OPTION);
				if (result == JOptionPane.YES_OPTION) {
					// 예를 선택한 경우
					CRUDprocess crud = new CRUDprocess();
					int r = crud.deleteEmpl(id);// DB에서 삭제수행
					if (r > 0) {
						JOptionPane.showMessageDialog(ts, "해당 정보가 삭제되었습니다.");
					} else {
						JOptionPane.showMessageDialog(ts, "삭제작업 중 문제가 발생했습니다.");
					}
				} else if (result == JOptionPane.NO_OPTION) {
					// 아니오를 선택한 경우
				}
			}
		} else if (ob == btns[2]) {// 변경버튼
			String id = textFields[0].getText();// 사번을 불러옴
			if (id.equals("")) {
				JOptionPane.showMessageDialog(ts, "사번이 입력되지 않아서 작업을 진행할 수 없습니다.");
			} else {
				int result = JOptionPane.showConfirmDialog(ts, "정말로 변경하시겠습니까?", "작업 확인", JOptionPane.YES_NO_OPTION);
				if (result == JOptionPane.YES_OPTION) {
					CRUDprocess crud = new CRUDprocess();
					String name = textFields[1].getText();// 이름
					String addr = textFields[2].getText();// 주소
					String dept = choices[0].getSelectedItem();// 부서명
					String gen = "";
					if (gender[0].getState() == true) {
						gen = "남자";
					} else if (gender[1].getState() == true) {
						gen = "여자";
					}
					String year = choices[1].getSelectedItem();// 년
					String mon = choices[2].getSelectedItem();// 월
					String date = choices[3].getSelectedItem();// 일
					String hire = year + "/" + mon + "/" + date;
					Empl_info empl = new Empl_info();
					empl.setEmp_id(id);
					empl.setEmp_name(name);
					empl.setEmp_addr(addr);
					empl.setEmp_gender(gen);
					empl.setEmp_dept(dept);
					empl.setEmp_hire(hire);
					int r = crud.updateEmpl(empl);// DB에서 update
					if (r > 0) {
						JOptionPane.showMessageDialog(ts, "정보가 변경되었습니다.");
					} else {
						JOptionPane.showMessageDialog(ts, "변경 작업 중 문제가 발생했습니다.");
					}
				}
			}
		} else if (ob == btns[3]) {// 조회버튼
			String id = textFields[0].getText();
			if (id.equals("")) {
				JOptionPane.showMessageDialog(ts, "사번을 입력하세요");
			} else {
				CRUDprocess crud = new CRUDprocess();
				Empl_info empl = crud.selectEmpl(id); // 사번으로 조회
				if (empl == null) { // 조회결과가 없는경우
					JOptionPane.showMessageDialog(ts, "입력한 사번은 존재하지 않습니다.");
				} else { // 조회결과가 있는경우
					textFields[1].setText(empl.getEmp_name());// 이름
					textFields[2].setText(empl.getEmp_addr());// 주소
					if (empl.getEmp_gender().equals("남자")) {
						gender[0].setState(true);
					} else {
						gender[1].setState(true);
					}
					choices[0].select(empl.getEmp_dept()); // 부서명
					// 입사일은 3개로 분리해야 한다.
					String hire_date = empl.getEmp_hire();
					String[] hire = hire_date.split("/"); // /를 기준으로 자름
					// hire[0] 입사년 hire[1] 입사월 hire[2] 입사일
					choices[1].select(hire[0]);
					choices[2].select(hire[1]);
					choices[3].select(hire[2]);
				}

			}

		} else if (ob == btns[4]) {// 지우기버튼
			for (int i = 0; i < textFields.length; i++) {
				textFields[i].setText(" ");
				textFields[i].setText("");
			} // 사번 이름,주소를 지움
			gender[0].setState(true);
			choices[0].select(0);
			choices[1].select(0);
			choices[2].select(0);
			choices[3].select(0);
		} else if (ob == btns[5]) {// 달력
			new CalendarByAwt(this);
		}
	}

	@Override
	public void itemStateChanged(ItemEvent arg0) {
		Object obj = arg0.getSource();
		if (obj == choices[2])// 입사월을 선택한 경우
			choices[3].removeAll();
		choices[3].add("입사일");
		int month = choices[2].getSelectedIndex();
		// 선택한 위치 즉, 첫번째 항목이면 0 , 두번째 항목이면 1
		switch(month) {
		case 4:
		case 6:
		case 9:
		case 11:
			for(int i=1; i<=30; i++) {
				choices[3].add(i+"");
			}//1부터 30을 채운다.
			break;
		case 2:
			for(int i=1; i<=28; i++) {
				choices[3].add(i+"");
			}
			break;
		default : 
			for(int i=1; i<=31; i++) {
				choices[3].add(i+"");
			}
		}
	}
	
}
