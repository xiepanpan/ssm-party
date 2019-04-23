package com.baobao.common.model;

import java.util.ArrayList;
import java.util.List;

/**
 * <P>时政学习</P>
 */
public class CurrentPoliticsStudy {
	/**
	 * 国内时政
	 */
    private List<InformationModel> domesticNews = new ArrayList<InformationModel>();  
    /**
     * 省内时政
     */
    private List<InformationModel> provincialNews = new ArrayList<InformationModel>();  
    
	public List<InformationModel> getDomesticNews() {
		return domesticNews;
	}
	public void setDomesticNews(List<InformationModel> domesticNews) {
		this.domesticNews = domesticNews;
	}
	public List<InformationModel> getProvincialNews() {
		return provincialNews;
	}
	public void setProvincialNews(List<InformationModel> provincialNews) {
		this.provincialNews = provincialNews;
	}
    
}
