package yg;

import java.awt.Button;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class CalendarByAwt extends Frame implements ItemListener, WindowListener, ActionListener {
	private Choice chyear, chmonth;
	private Label yLabel, mLabel;
	GregorianCalendar gc;
	private int year, month;
	private Label[] dayLabel = new Label[7];
	private String[] day = { "일", "월", "화", "수", "목", "금", "토" };
	private Button[] days = new Button[42];
	private Panel selectPanel = new Panel();
	private GridLayout grid = new GridLayout(7, 7, 5, 5);
	private Calendar ca = Calendar.getInstance();
	private Dimension dimen, dimen1;
	private int xpos, ypos;
	private EmployeeSystem es;
	private CustomerSystem cs;

	public CalendarByAwt(CustomerSystem cs) {
		this.cs = cs;
		setTitle("달력");
		setSize(450, 400);
		dimen = Toolkit.getDefaultToolkit().getScreenSize();
		dimen1 = this.getSize();
		xpos = (int) (dimen.getWidth() / 2 - dimen1.getWidth() / 2);
		ypos = (int) (dimen.getHeight() / 2 - dimen1.getHeight() / 2);
		setLocation(xpos, ypos);
		setResizable(false);
		setVisible(true);
		chyear = new Choice();
		chmonth = new Choice();
		yLabel = new Label("년");
		mLabel = new Label("월");
		addWindowListener(this);
		init();
	}

	public CalendarByAwt(EmployeeSystem es) {
		this.es = es;
		setTitle("달력");
		setSize(450, 400);
		dimen = Toolkit.getDefaultToolkit().getScreenSize();
		dimen1 = this.getSize();
		xpos = (int) (dimen.getWidth() / 2 - dimen1.getWidth() / 2);
		ypos = (int) (dimen.getHeight() / 2 - dimen1.getHeight() / 2);
		setLocation(xpos, ypos);
		setResizable(false);
		setVisible(true);
		chyear = new Choice();
		chmonth = new Choice();
		yLabel = new Label("년");
		mLabel = new Label("월");
		addWindowListener(this);
		init();
	}

	@Override

	public void actionPerformed(ActionEvent arg0) {
		String year = chyear.getSelectedItem();
		String month = chmonth.getSelectedItem();
		Button btn = (Button) arg0.getSource();
		String day = btn.getLabel();
		System.out.println(year + "," + month + "," + day);

		if (cs != null) {
			cs.textField[3].setText(year + "/" + month + "/" + day);
		}
		if (es != null) {
			es.choices[1].select(year);
			es.choices[2].select(month);

			int index = es.choices[2].getSelectedIndex();
			if (index != 0) {
				switch (index) {
				case 4:
				case 6:
				case 9:
				case 11:
					for (int i = 1; i <= 30; i++) {
						es.choices[3].add(i + "");
					}
					break;
				case 2:
					for (int i = 1; i <= 28; i++) {
						es.choices[3].add(i + "");
					}
					break;
				default:
					for (int i = 1; i <= 31; i++) {
						es.choices[3].add(i + "");
					}
				}// end of switch
			} // end of if
			es.choices[3].select(day);
		}
	}

	public void init() {
		select();
		calendar();
	}

	public void select() {
		Panel panel = new Panel(grid);
		for (int i = 2020; i >= 2000; i--) {
			chyear.add(String.valueOf(i));
		}
		for (int i = 1; i <= 12; i++) {
			chmonth.add(String.valueOf(i));
		}
		for (int i = 0; i < day.length; i++) {
			dayLabel[i] = new Label(day[i], Label.CENTER);
			panel.add(dayLabel[i]);
			dayLabel[i].setBackground(Color.GRAY);
		}

		dayLabel[6].setForeground(Color.BLUE);
		dayLabel[0].setForeground(Color.RED);
		for (int i = 0; i < 42; i++) {
			days[i] = new Button("");
			if (i % 7 == 0)
				days[i].setForeground(Color.RED);
			else if (i % 7 == 6)
				days[i].setForeground(Color.BLUE);
			else
				days[i].setForeground(Color.BLACK);
			days[i].addActionListener(this);
			panel.add(days[i]);
		}

		selectPanel.add(chyear);
		selectPanel.add(yLabel);
		selectPanel.add(chmonth);
		selectPanel.add(mLabel);

		this.add(selectPanel, "North");
		this.add(panel, "Center");

		String m = (ca.get(Calendar.MONTH) + 1) + "";
		String y = ca.get(Calendar.YEAR) + "";
		chyear.select(y);
		chmonth.select(m);
		chyear.addItemListener(this);
		chmonth.addItemListener(this);
	}

	public void calendar() {
		year = Integer.parseInt(chyear.getSelectedItem());
		month = Integer.parseInt(chmonth.getSelectedItem());
		gc = new GregorianCalendar(year, month - 1, 1);
		int max = gc.getActualMaximum(gc.DAY_OF_MONTH);
		int week = gc.get(gc.DAY_OF_WEEK);
		String today = Integer.toString(ca.get(Calendar.DATE));
		for (int i = 0; i < days.length; i++) {
			days[i].setEnabled(true);
		}
		for (int i = 0; i < week - 1; i++) {
			days[i].setEnabled(false);
		}
		for (int i = week; i < max + week; i++) {
			days[i - 1].setLabel((String.valueOf(i - week + 1)));
			days[i - 1].setBackground(Color.WHITE);
			if (today.equals(days[i - 1].getLabel())) {
				days[i - 1].setBackground(Color.CYAN);
			}
		}
		for (int i = (max + week - 1); i < days.length; i++) {
			days[i].setEnabled(false);
		}
//	System.out.println("max+week:"+(max+week)+",week:"+week);
	}

	public void windowActivated(WindowEvent arg0) {
	}

	public void windowClosed(WindowEvent arg0) {
	}

	public void windowClosing(WindowEvent arg0) {
		arg0.getWindow().dispose();
	}

	public void windowDeactivated(WindowEvent arg0) {
	}

	public void windowDeiconified(WindowEvent arg0) {
	}

	public void windowIconified(WindowEvent arg0) {
	}

	public void windowOpened(WindowEvent arg0) {
	}

	public void itemStateChanged(ItemEvent arg0) {
		if (arg0.getStateChange() == ItemEvent.SELECTED) {
			for (int i = 0; i < 42; i++) {
				days[i].setLabel("");
				days[i].setBackground(Color.WHITE);
			}
			calendar();
		}
	}

	/**
	 * 
	 * @param args
	 * 
	 */

	public static void main(String[] args) {
//	new CalendarByAwt();
	}
}