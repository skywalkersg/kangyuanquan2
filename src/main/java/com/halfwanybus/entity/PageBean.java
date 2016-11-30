package com.halfwanybus.entity;

import java.util.List;

import com.halfwanybus.util.PageData;

public class PageBean extends Page {
	
	private List<PageData> pds;

	public List<PageData> getPds() {
		return pds;
	}

	public void setPds(List<PageData> pds) {
		this.pds = pds;
	}

	
}
