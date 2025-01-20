package model;

public final class GalleryBean{
	private int id;
	private String img1;
	private String img2;
	private String img3;
	private String img4;
	
	
	public GalleryBean() {

	}
	
	//METODI
	
	public void setId(int idp) {
		id=idp;
	}
	public void setImg1(String i1) {
		img1=i1;
	}
	public void setImg2(String i2) {
		img2=i2;
	}
	public void setImg3(String i3) {
		img3=i3;
	}
	public void setImg4(String i4) {
		img4=i4;
	}
	public int getId() {
		return id;
	}
	public String getImg1() {
		return img1;
	}
	public String getImg2() {
		return img2;
	}
	public String getImg3() {
		return img3;
	}
	public String getImg4() {
		return img4;
	}

}
