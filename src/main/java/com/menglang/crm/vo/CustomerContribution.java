package com.menglang.crm.vo;

public class CustomerContribution {

	private String name;
	private String totalContribution;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTotalContribution() {
		return totalContribution;
	}
	public void setTotalContribution(String totalContribution) {
		this.totalContribution = totalContribution;
	}
	@Override
	public String toString() {
		return "CustomerContribution [name=" + name + ", totalContribution=" + totalContribution + "]";
	}
	
}
