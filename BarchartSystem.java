package yg;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class GraphPanel extends Panel {
	final String PATH = "src\\yg\\lib.jpg";
	Image img;

	GraphPanel() {
		img = Toolkit.getDefaultToolkit().getImage(PATH);
	}

	private int total_jan, total_feb, total_mar, total_apr, total_may, total_jun, total_jul, total_aug, total_sep,
			total_oct, total_nov, total_dec;

	@Override
	public void paint(Graphics g) {
		g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
	//	g.clearRect(0, 0, this.getWidth(), this.getHeight());// 그려질 영역을 지움
		// 0.0에서 (패널의 가로길이 패널의 세로길이)
		g.drawLine(30, 250, 750, 250);
		for (int cnt = 1; cnt < 12; cnt++) {
			g.drawString(cnt * 10 + "", 25, 255 - 20 * cnt);
			g.drawLine(50, 250 - 20 * cnt, 750, 250 - 20 * cnt);
		} // 10점부터 100점까지 점수와 수평선을 그린다.
		g.drawLine(50, 20, 50, 250);
		g.drawString("1월", 100, 270);
		g.drawString("2월", 150, 270);
		g.drawString("3월", 200, 270);
		g.drawString("4월", 250, 270);
		g.drawString("5월", 300, 270);
		g.drawString("6월", 350, 270);
		g.drawString("7월", 400, 270);
		g.drawString("8월", 450, 270);
		g.drawString("9월", 500, 270);
		g.drawString("10월", 550, 270);
		g.drawString("11월", 600, 270);
		g.drawString("12월", 650, 270);

		g.setColor(Color.RED);
		if (total_jan > 0) {// 값이 있는 경웅에만 그래프를 그림
			g.fillRect(100, 250 - total_jan * 2, 10, total_jan * 2);
		}
		if (total_feb > 0) {// 값이 있는 경웅에만 그래프를 그림
			g.fillRect(150, 250 - total_feb * 2, 10, total_feb * 2);
		}
		if (total_mar > 0) {// 값이 있는 경웅에만 그래프를 그림
			g.fillRect(200, 250 - total_mar * 2, 10, total_mar * 2);
		}
		if (total_apr > 0) {// 값이 있는 경웅에만 그래프를 그림
			g.fillRect(250, 250 - total_apr * 2, 10, total_apr * 2);
		}
		if (total_may > 0) {// 값이 있는 경웅에만 그래프를 그림
			g.fillRect(300, 250 - total_may * 2, 10, total_may * 2);
		}
		if (total_jun > 0) {// 값이 있는 경웅에만 그래프를 그림
			g.fillRect(350, 250 - total_jun * 2, 10, total_jun * 2);
		}
		if (total_jul > 0) {// 값이 있는 경웅에만 그래프를 그림
			g.fillRect(400, 250 - total_jul * 2, 10, total_jul * 2);
		}
		if (total_aug > 0) {// 값이 있는 경웅에만 그래프를 그림
			g.fillRect(450, 250 - total_aug * 2, 10, total_aug * 2);
		}
		if (total_sep > 0) {// 값이 있는 경웅에만 그래프를 그림
			g.fillRect(500, 250 - total_sep * 2, 10, total_sep * 2);
		}
		if (total_oct > 0) {// 값이 있는 경웅에만 그래프를 그림
			g.fillRect(550, 250 - total_oct * 2, 10, total_oct * 2);
		}
		if (total_nov > 0) {// 값이 있는 경웅에만 그래프를 그림
			g.fillRect(600, 250 - total_nov * 2, 10, total_nov * 2);
		}
		if (total_dec > 0) {// 값이 있는 경웅에만 그래프를 그림
			g.fillRect(650, 250 - total_dec * 2, 10, total_dec * 2);
		}

	}

	public void setTotal_jan(int total_jan) {
		this.total_jan = total_jan;
	}

	public void setTotal_feb(int total_feb) {
		this.total_feb = total_feb;
	}

	public void setTotal_mar(int total_mar) {
		this.total_mar = total_mar;
	}

	public void setTotal_apr(int total_apr) {
		this.total_apr = total_apr;
	}

	public void setTotal_may(int total_may) {
		this.total_may = total_may;
	}

	public void setTotal_jun(int total_jun) {
		this.total_jun = total_jun;
	}

	public void setTotal_jul(int total_jul) {
		this.total_jul = total_jul;
	}

	public void setTotal_aug(int total_aug) {
		this.total_aug = total_aug;
	}

	public void setTotal_sep(int total_sep) {
		this.total_sep = total_sep;
	}

	public void setTotal_oct(int total_oct) {
		this.total_oct = total_oct;
	}

	public void setTotal_nov(int total_nov) {
		this.total_nov = total_nov;
	}

	public void setTotal_dec(int total_dec) {
		this.total_dec = total_dec;
	}

}

public class BarchartSystem extends Panel implements ActionListener {

	TotalSystem ts;
	Button btn;
	Label[] labels;
	TextField[] textfields;
	Panel[] panels;
	GraphPanel graphpanel;
	String[] labels_title = { "1월의 매출", "2월의매출", "3월의 매출", "4월의 매출", "5월의 매출", "6월의 매출", "7월의 매출", "8월의 매출", "9월의 매출",
			"10월의 매출", "11월의 매출", "12월의 매출" };

	void makeTextfield() {
		textfields = new TextField[3];
		for (int i = 0; i < textfields.length; i++) {
			textfields[i] = new TextField(5);
		}
	}

	void makeLabel() {
		labels = new Label[12];
		for (int i = 0; i < labels.length; i++) {
			labels[i] = new Label(labels_title[i]);
		}
	}

	public BarchartSystem(TotalSystem ts) {
//		super(str);
		makeLabel();
		makeTextfield();
		this.setLayout(new BorderLayout());
		btn = new Button("그리기");
		btn.addActionListener(this);
		panels = new Panel[2];
		panels[0] = new Panel();
		panels[1] = new Panel();
		for (int i = 0; i < labels.length; i++) {
//			panels[1].add(labels[i]);
//			panels[1].add(textfields[i]);
		}
		panels[1].add(btn);
		panels[1].setBackground(new Color(35, 157, 200));
		graphpanel = new GraphPanel();
		this.add("Center", graphpanel);
		this.add("South", panels[1]);
//		this.setSize(500, 400);
//		this.addWindowListener(new LoginSystemExit());
//		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		int jan = 0;
		int feb = 0;
		int mar = 0;
		int apr;
		int may = 0;
		int jun = 0;
		int jul = 0;
		int aug = 0;
		int sep = 0;
		int oct = 0;
		int nov = 0;
		int dec = 0;
		CRUDprocess crud = new CRUDprocess();
		Outputs outputs = crud.selectOutputs();// DB에서 매출정보 검색
		jan = outputs.getJan();
		feb = outputs.getFeb();
		mar = outputs.getMar();
		apr = outputs.getApr();
		may = outputs.getMay();
		jun = outputs.getJun();
		jul = outputs.getJul();
		aug = outputs.getAug();
		sep = outputs.getSep();
		oct = outputs.getOct();
		nov = outputs.getNov();
		dec = outputs.getDec();

//		if (!textfields[0].getText().equals("")) { //DB에서 연동으로 불러오는거라 필요없음
//			jan = Integer.parseInt(textfields[0].getText());
//		}
//		if (!textfields[1].getText().equals("")) {
//			feb = Integer.parseInt(textfields[1].getText());
//		}
//		int mar = 0;
//		if (!textfields[2].getText().equals("")) {
//			mar = Integer.parseInt(textfields[2].getText());
//		}
		graphpanel.setTotal_jan(jan);
		graphpanel.setTotal_feb(feb);
		graphpanel.setTotal_mar(mar);
		graphpanel.setTotal_apr(apr);
		graphpanel.setTotal_may(may);
		graphpanel.setTotal_jun(jun);
		graphpanel.setTotal_jul(jul);
		graphpanel.setTotal_aug(aug);
		graphpanel.setTotal_sep(sep);
		graphpanel.setTotal_oct(oct);
		graphpanel.setTotal_nov(nov);
		graphpanel.setTotal_dec(dec);
		graphpanel.repaint();// 다시그린다.
	}

	public static void main(String[] args) {
//		new BarchartSystem("그래프");
	}

}
