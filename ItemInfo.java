package yg;


//get set을 사용하는 이유 private로 잠겨있는 객체에 접근하기 위해서 사용하고 get으로 값을 리턴
//set의 this로 private에 접근하기 위함
public class ItemInfo {
	private String code; //상품번호
	private String name; //상품이름
	private int price; //상품가격
	private String info; //상품정보
	private String origin; // 상품원산지
	private String image; //이미지
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
}
