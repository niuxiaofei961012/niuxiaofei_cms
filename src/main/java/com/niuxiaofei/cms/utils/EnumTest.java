package com.niuxiaofei.cms.utils;
/**
 * 标题样式
 * @ClassName: TileEnum 
 * @Description: TODO
 * @author: charles
 * @date: 2019年7月30日 上午9:01:55
 */
public enum EnumTest  {
	
	COLOR_RED("color:red","标红"),FONTWEIGHT("font-weight:800","加粗"),
	FONTSTYLE("font-style:italic","斜体"),STYLE_NONE("text-decoration:none;color:inherit","无样式");

	private String code;
	private String value;
	EnumTest(String code, String value) {
		this.code = code;
		this.value = value;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public static void main(String[] args) {
		EnumTest[] enums = EnumTest.values();
		for (EnumTest e : enums) {
			System.out.println(e.getCode() +":"+e.getValue());
		}
		System.out.println();
	}
	

}
