package yg;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class MyLogin implements ActionListener {

	LoginSystem ls; TotalSystem ts;
	public MyLogin(LoginSystem l, TotalSystem ts) {
		ls = l; this.ts = ts;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		//로그인 버튼을 누른경우
		//다시 입력 버튼을 누른경우
		Object obj = arg0.getSource();
		if(obj == ls.login) {//로그인 버튼을 누른경우
			 //DB에 접속한 후 manager_info 테이블에서 계정과 암호를 찾는다
			//찾은 계정과 암호를 비교한다.
			String id = ls.id_txt.getText();//입력한 id를 id에 찾아서 저장
			String pwd = ls.pwd_txt.getText();//입력한 pwd를 pwd에 찾아서 저장
			UserIdPwd idpwd = new UserIdPwd();
			idpwd.setId(id); idpwd.setPwd(pwd);
			CRUDprocess crud = new CRUDprocess();
			Manager1Info info = crud.selectIdAndPwd(idpwd);
			
			if(info == null) {//로그인 실패
				String title = "정보관리 시스템 ver1.0 우호호홍";
//				ts.setTitle(title);
//				ls.setTitle(title);
				JOptionPane.showMessageDialog(ts, "ID와 PWD를 확인하세요");
			}else {
				String title = "정보관리 시스템 ver1.0 환엽합니다. "+id+"~";
//				ls.setTitle(title);
				ts.setTitle(title);
				JOptionPane.showMessageDialog(ts, "로그인 되었습니다.");
				//메뉴를 활성화 시켜야한다.
				ts.menu_logout.setEnabled(true);//메뉴바 활성화
				ts.menu_fruits.setEnabled(true);//메뉴바 활성화
				ts.menu_ordering.setEnabled(true);
				ts.menu_Item.setEnabled(true);
				ts.menu_home.setEnabled(true);
				ts.menu_employee.setEnabled(true);
				ts.menu_customer.setEnabled(true);
				ts.menu_barchar.setEnabled(true);
				ts.menu_imag.setEnabled(true);
				ts.card.show(ts.totalPanel, "background");
				//title를 윈도우의 제목으로 출력
			}
			
		}else if(obj == ls.cancel) { // 다시입력 버튼을 누른경우
			//계정 텍스트필드와 암호 텍스트필드를 지운다.
			ls.id_txt.setText(" ");//계정 텍스트필드를 지움
			ls.id_txt.setText(""); //가끔 버그로 두번씩 써야 완전히 지워짐
			ls.pwd_txt.setText(" ");//암호 텍스트 필드를 지움
			ls.pwd_txt.setText("");//이것도 마찬가지임
		}
	}
}
