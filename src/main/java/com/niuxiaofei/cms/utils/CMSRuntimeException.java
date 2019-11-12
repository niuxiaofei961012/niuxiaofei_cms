package com.niuxiaofei.cms.utils;
/**
 * 
 * @ClassName: CMSRuntimeException 
 * @Description: 自定义异常
 * @author:nxf 
 * @date: 2019年7月30日 上午10:10:49
 */
public class CMSRuntimeException extends RuntimeException {

	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = 1L;
	
	public CMSRuntimeException() {
		super();
	}
	
	public CMSRuntimeException(String message) {
		super(message);
	}
}
