package yg;

import java.awt.CardLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


class SystemBackgroundImage extends Panel {
	final String PATH = "src\\yg\\qdd.jpg";
	Image img;

	SystemBackgroundImage() {
		img = Toolkit.getDefaultToolkit().getImage(PATH);
	}

	@Override
	public void paint(Graphics arg0) {
		arg0.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
	}

}

public class TotalSystem extends Frame implements ActionListener {

	Font font, menuFont;
	CardLayout card;
	Panel totalPanel, fruitsSystem, backgroundImage, OrderingSystem1, ItemSystem, 
	EmployeeSystem, CustomerSystem, BarchartSystem, ImageSystem;
	MenuBar mb;
	Menu menu_logout, menu_fruits, menu_ordering, menu_Item, menu_home, 
	menu_employee, menu_customer, menu_barchar, menu_imag;
	MenuItem item_logout, item_fruits, item_ordering, item_Item, item_home,
	item_employee, item_customer, item_barchar, item_imag;
	LoginSystem loginSystem;

	public TotalSystem(String str) {
		super(str);
		font = new Font("굴림체", Font.BOLD, 20);
		menuFont = new Font("휴먼굴림체", Font.PLAIN, 15);
		// 메뉴 관련 작업 시작
		mb = new MenuBar();
		menu_home = new Menu("홈");
		menu_logout = new Menu("로그아웃");
		menu_fruits = new Menu("과일안내 시스템");
		menu_ordering = new Menu("음료주문 시스템");
		menu_Item = new Menu("물품관리시스템");
		menu_employee = new Menu("사원관리 시스템");
		menu_customer = new Menu("소비자 관리 시스템");
		menu_barchar = new Menu("그래프관리 시스템");
		menu_imag = new Menu("이미지 관리 시스템");
		menu_logout.setFont(menuFont);
		item_logout = new MenuItem("로그아웃");
		item_fruits = new MenuItem("과일 열기");
		item_ordering = new MenuItem("음료주문 하기");
		item_Item = new MenuItem("물품관리시스템");
		item_employee = new MenuItem("사원관리 시스템 열기");
		item_customer = new MenuItem("소비자관리 시스템 열기");
		item_barchar = new MenuItem("그래프관리 시스템 열기");
		item_home = new MenuItem("이 동");
		item_imag = new MenuItem("시스템 열기");
		item_home.addActionListener(this);
		item_logout.addActionListener(this);
		item_ordering.addActionListener(this);
		item_Item.addActionListener(this);
		item_employee.addActionListener(this);
		item_customer.addActionListener(this);
		item_barchar.addActionListener(this);
		item_imag.addActionListener(this);
		item_home.setFont(menuFont);
		item_logout.setFont(menuFont);
		item_fruits.setFont(menuFont);
		item_ordering.setFont(menuFont);
		item_Item.setFont(menuFont);
		item_employee.setFont(menuFont);
		item_customer.setFont(menuFont);
		item_barchar.setFont(menuFont);
		menu_imag.setFont(menuFont);
		item_fruits.addActionListener(this);
		menu_logout.setEnabled(false);// 비활성화,로그인 전이므로.
		menu_fruits.setEnabled(false);// 비활성화,로그인 전이므로.
		menu_ordering.setEnabled(false);
		menu_Item.setEnabled(false);
		menu_home.setEnabled(false);
		menu_employee.setEnabled(false);
		menu_customer.setEnabled(false);
		menu_barchar.setEnabled(false);
		menu_imag.setEnabled(false);
		menu_fruits.add(item_fruits);// 과일시스템열기를 메뉴에 붙임
		menu_ordering.add(item_ordering);
		menu_logout.add(item_logout);
		menu_Item.add(item_Item);
		menu_home.add(item_home);
		menu_employee.add(item_employee);
		menu_customer.add(item_customer);
		menu_barchar.add(item_barchar);
		menu_imag.add(item_imag);
		mb.add(menu_home);
		mb.add(menu_logout);
		mb.add(menu_fruits);// 메뉴바에 과일안내시스템 붙임
		mb.add(menu_ordering);
		mb.add(menu_Item);
		mb.add(menu_employee);
		mb.add(menu_customer);
		mb.add(menu_barchar);
		mb.add(menu_imag);
		this.setMenuBar(mb);// 메뉴바를 윈도우에 붙임
		// 메뉴 관련 작업 끝
		card = new CardLayout();
		totalPanel = new Panel();
		totalPanel.setLayout(card);
		loginSystem = new LoginSystem(this); // LoginSystem을 패널로 변경
		fruitsSystem = new FruitsShopping(this);// fruitsSystem패널생성
		OrderingSystem1 = new OrderingSystem1(this);
		ItemSystem = new ItemSystem(this);
		EmployeeSystem = new EmployeeSystem(this);
		backgroundImage = new SystemBackgroundImage();
		CustomerSystem = new CustomerSystem(this);
		BarchartSystem = new BarchartSystem(this);
		ImageSystem = new ImageSystem(this);
		totalPanel.add(loginSystem, "login");
		totalPanel.add(backgroundImage, "background");
		totalPanel.add(fruitsSystem, "fruits");
		totalPanel.add(OrderingSystem1, "ordering");
		totalPanel.add(ItemSystem, "Item");
		totalPanel.add(EmployeeSystem, "employee");
		totalPanel.add(CustomerSystem, "customer");
		totalPanel.add(BarchartSystem,"barchar");
		totalPanel.add(ImageSystem,"image");
//		card.show(totalPanel, "fruits");//카드레이아웃에서 처음 출력될 패널 설정 및 보여주기
		this.add("Center", totalPanel);
		this.setSize(1200, 700);
		this.setLocation(300, 200);
		this.addWindowListener(new LoginSystemExit());
		this.setVisible(true);
	}

	public static void main(String[] args) {
		new TotalSystem("정보관리 시스템 ver1.0");
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		Object obj = arg0.getSource();

		if (obj == item_fruits) {
			this.card.show(totalPanel, "fruits");
		} else if (obj == item_ordering) {
			this.card.show(totalPanel, "ordering");
		} else if (obj == item_Item) {
			this.card.show(totalPanel, "Item");
		} else if (obj == item_employee) {
			this.card.show(totalPanel, "employee");
		} else if (obj == item_home) {
			this.card.show(totalPanel, "background");
		} else if (obj == item_customer) {
			this.card.show(totalPanel, "customer");
		}else if(obj == item_barchar) {
			this.card.show(totalPanel, "barchar");
		}else if(obj == item_imag ) {
			this.card.show(totalPanel, "image");
		} else if (obj == item_logout) {// 로그아웃 메뉴아이템을 클릭한 경우
			// 정말로 종료하시겠습니까? 라는 다이얼로그를 출력
			// "예"를 선택한 경우에만 로그아웃 처리
			// 다이얼로그 처리를 했다치고
			this.card.show(totalPanel, "login");
			loginSystem.id_txt.setText(" ");// 로그인 화면에 계정입력창을 지운다.
			loginSystem.id_txt.setText("");// 로그인 화면에 계정입력창을 지운다.
			loginSystem.pwd_txt.setText(" ");// 로그인 화면에 계정입력창을 지운다.
			loginSystem.pwd_txt.setText("");// 로그인 화면에 계정입력창을 지운다.
			menu_logout.setEnabled(false);// 메뉴를 비활성화
			menu_fruits.setEnabled(false);
			menu_ordering.setEnabled(false);
			menu_Item.setEnabled(false);
			menu_home.setEnabled(false);
			menu_employee.setEnabled(false);
			menu_barchar.setEnabled(false);
			menu_imag.setEnabled(false);
			this.setTitle("정보관리 시스템 ver1.0");
		}
	}
}