package com.zcs.app.advance.entity;

public class MainOptionItem {
	private long id;
	private String name;
	private String type;
	private String tag;
	private String desc;
	private Class<?> targetActivity;

	public MainOptionItem() {
	}

	public MainOptionItem(long id) {
		this.id = id;
	}

	public MainOptionItem(String tag) {
		this.tag = tag;
	}

	public MainOptionItem(long id, String name, String desc) {
		this.id = id;
		this.name = name;
		this.desc = desc;
	}

	public MainOptionItem(long id, String name, String type, String desc) {
		this.id = id;
		this.name = name;
		this.type = type;
		this.desc = desc;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public Class<?> getTargetActivity() {
		return targetActivity;
	}

	public void setTargetActivity(Class<?> targetActivity) {
		this.targetActivity = targetActivity;
	}

}
