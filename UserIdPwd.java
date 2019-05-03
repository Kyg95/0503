package yg;

public class UserIdPwd {
	private String id; // private로 숨김 
	private String pwd; // 마찬가지
	// 은닉된 변수에 데이터를 넣어주는 메서드를 만든다.
	// 이런 메서드를 세터(setter)메서드라고 한다.

	public void setId(String id) {
		this.id = id;//속성을 의미함 this로 써서 private로 숨긴곳에 접근이 가능함
	}

	public void setPwd(String pwd) { //void메서드 이름이 set으로 시작해서 set터라함
		this.pwd = pwd;
	}// 소스 get set들가서 세터만 설정함

	public String getId() { //get으로 시작해서 게터  void가 아님 게터는 리턴타입임
		return id;
	}

	public String getPwd() {
		return pwd;
	}
}
// mymapp.xml에서 아이디랑 패스워드 가지고 로그인함