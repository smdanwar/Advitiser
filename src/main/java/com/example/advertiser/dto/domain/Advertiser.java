package com.example.advertiser.dto.domain;

import io.swagger.annotations.ApiModelProperty;

public class Advertiser {

	@ApiModelProperty(notes="The advertiser name",required=true,position=1)
	private String name;

	@ApiModelProperty(notes="The advertiser contact number",position=2)
	private String contactNumber;

	@ApiModelProperty(value="The advertiser cradit limit.",position=3)
	private Float craditLimit;

	public Advertiser() {
	}

	public Advertiser(String name, String contactNumber, Float craditLimit) {
		this.name = name;
		this.contactNumber = contactNumber;
		this.craditLimit = craditLimit;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public Float getCraditLimit() {
		return craditLimit;
	}

	public void setCraditLimit(Float craditLimit) {
		this.craditLimit = craditLimit;
	}

}
