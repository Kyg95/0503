package yg;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class CRUDprocess {
//이 클래스의 목적 : MyBatis의 매퍼를 호출한다.
	// 무슨일을 하나? 1. mybatis환경설정을 읽는다
	// 2.위의 1의 작업으로 매퍼를 호출할 객체(sqlSession)를 생성한다.
	// 특이사항 : sqlSession은 sqlSessionFactory가 생성한다.
	// sqlSessionFactory는 sqlSessionFactoryBuiler가 생성한다.
	/////// sqlSession을 만드는 메서드/////////////////
	private SqlSession getSession() {// 다른 객체에서 쓰면안되니깐 private로 만듬
		String path = "yg/mybatis_config.xml";// 파일 경로주소 추가해줘야함
		InputStream/* 어떤 파일을 읽느냐에 따라서 달라짐 */ is = null;// 파일의 내용을 읽을 객체
		try {
			is = Resources.getResourceAsStream(path);
		} catch (Exception e) {
			System.out.println("환경설정 오류 발생");
		}
		SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder(); // 빌더를 만들어야 팩토리를 쓸수있음
		SqlSessionFactory factory = builder.build(is); // 팩토리만들고 빌더생성자 부름
		SqlSession session = factory.openSession(); // 팩토리 생성자에서
		return session;
	}

	public Outputs selectOutputs() {
		SqlSession s = getSession();
		Outputs outputs;
		try {
			outputs = s.selectOne("loginmapper.selectOutputs");
			return outputs;
		} finally {
			s.close();
		}
	}

	public Customer_info selectCustomer(String id) {
		SqlSession s = getSession();
		Customer_info cust;
		try {
			cust = s.selectOne("loginmapper.selectCustomer", id);
			return cust;
		} finally {
			s.close();
		}
	}

	public int insertCustomer(Customer_info customer) {
		SqlSession s = getSession();
		int result = 0;
		try {
			result = s.insert("loginmapper.insertCustomer", customer);
			if (result > 0)
				s.commit();
			else
				s.rollback();
			return result;
		} finally {
			s.close();
		}
	}

	// db작성순서 mapper작성 crud소스 작성
	public int updateEmpl(Empl_info emp) {
		SqlSession s = getSession();
		int result = 0;// 작업의 성공유무를 위한 변수
		try {
			result = s.update("loginmapper.updateEmpl", emp);
			if (result > 0)
				s.commit();
			else
				s.rollback();
			return result;
		} finally {
			s.close();
		}
	}

	public int deleteEmpl(String emp_id) {
		SqlSession s = getSession();
		int result = 0;// 작업의 성공유무를 위한 변수
		try {
			result = s.delete("loginmapper.deleteEmpl", emp_id);
			if (result > 0)
				s.commit();
			else
				s.rollback();
			return result;
		} finally {
			s.close();
		}
	}

	public int insertEmpl(Empl_info empl) {
		SqlSession s = getSession();
		int result = 0;
		try {
			result = s.insert("loginmapper.insertEmpl", empl);
			if (result > 0)
				s.commit();
			else
				s.rollback();
			return result;
		} finally {
			s.close();
		}
	}

	public Empl_info selectEmpl(String id) {
		SqlSession s = getSession();
		Empl_info emp;
		try {
			emp = s.selectOne("loginmapper.selectEmpl", id); // 데이터를 업데이트
			return emp;
		} finally {
			s.close();// 완료될경우 닫는다
		}

	}

	public int updateItemInfo(ItemInfo info) {
		SqlSession s = getSession();
		int result = 0;// 작업의 성공유무를 위한 변수
		try {
			result = s.update("loginmapper.updateItemInfo", info); // 데이터를 업데이트
			if (result > 0)
				s.commit(); // 양수일경우 저장하고 커밋
			else
				s.rollback();// 아닐경우 롤백
			return result;
		} finally {
			s.close();// 완료될경우 닫는다
		}

	}

	public int deleteItemCode(String code) {
		SqlSession s = getSession();
		int result = 0;// 작업의 성공유무를 위한 변수
		try {
			result = s.delete("loginmapper.deleteItemCode", code); // 데이터를 삭제
			if (result > 0)
				s.commit(); // 양수일경우 저장하고 커밋
			else
				s.rollback();// 아닐경우 롤백
			return result;
		} finally {
			s.close();// 완료될경우 닫는다
		}
	}

	public int insertItemInfo(ItemInfo item) { // 다른곳에서 사용해야 하기떄문에 public사용
		SqlSession s = getSession();
		int result = 0;// 작업의 성공유무를 위한 변수
		try {
			result = s.insert("loginmapper.insertItemInfo", item); // 데이터를 넘겨주기위해 item도 선언
			if (result > 0)
				s.commit(); // 양수일경우 저장하고 커밋
			else
				s.rollback();// 아닐경우 롤백
			return result;
		} finally {
			s.close();// 완료될경우 닫는다
		}
	}
	// 화면에서 입력한 id와 패스워드를 사용해서 쿼리를 실행하는 메서드///

	public ItemInfo selectItemCode(String code) {
		SqlSession s = getSession();
		ItemInfo ii = null;
		try {
			ii = s.selectOne("loginmapper.selectItemCode", code); // 메퍼이름,
			return ii;
		} finally {
			s.close(); // 완료될경우 닫는다
		}
	}

	public Manager1Info selectIdAndPwd(UserIdPwd uip) { // 다른곳에서 사용해야하기떄문에 public 를 사용
		SqlSession s = getSession();
		try {
			Manager1Info info = s.selectOne("loginmapper.selectIdPwd", uip); // 매퍼의 쿼리 이름을 씀
			// selectone는 검색결과가 1건일 때만 사용하는 메서드
			// selectList는 검색결과가 여러건에 사용하는 메서드
			return info;
		} finally { // 예외가 발생하던 하지않던 무조건 실행함
			s.close();// 완료될경우 닫는다

		} // DB의 manager_info 테이블의 id와 암호를 찾아서 리턴
	}
}
//파일 네트워크,db연동,스레드 
//반드시 "예외처리"를 해야한다. try {파일처리 프로그렘, 네트워크프로그램 ,스레드} catch(exception A 모두가 상속하는것을 씀,다형성){"안내메세지출력"}
//자바에서 발생하는 모든것은 객체임