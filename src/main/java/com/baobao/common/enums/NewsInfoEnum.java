package com.baobao.common.enums;

public enum NewsInfoEnum {
	
	
	NEWS_TYPE_ONE("1", "轮播"), 
	NEWS_TYPE_TWENTY_ONE("21", "理论-系列讲话"),
	NEWS_TYPE_TWENTY_TWO("22", "理论-规章制度"),
	NEWS_TYPE_THIRTY_ONE("23", "时政-国内时政"),
	NEWS_TYPE_THIRTY_TWO("24", "时政-省内时政");
	
	/**
	 * 轮播
	 */
	public final static int ONE=1;
	/**
	 * 理论 -系列讲话
	 */
	public final static int TWENTY_ONE=21;
	/**
	 * 理论 -规章制度
	 */
	public final static int TWENTY_TWO=22;
	/**
	 * 时政 -国内时政
	 */
	public final static int THIRTY_ONE=31;
	/**
	 * 时政 -省内时政
	 */
	public final static int THIRTY_TWO=32;
	
	
	private String id;

	private String name;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private NewsInfoEnum(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public static NewsInfoEnum getById(String id) {
		for (NewsInfoEnum tmpEnum : NewsInfoEnum.values()) {
			if (tmpEnum.getId().equalsIgnoreCase(id)) {
				return tmpEnum;
			}
		}
		return null;
	}

	public static NewsInfoEnum getByName(String name) {
		for (NewsInfoEnum tmpEnum : NewsInfoEnum.values()) {
			if (tmpEnum.getName().equalsIgnoreCase(name)) {
				return tmpEnum;
			}
		}
		return null;
	}
	
}
