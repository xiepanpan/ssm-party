package com.baobao.common.model;

import java.util.ArrayList;
import java.util.List;

/**
 * <P>理论学习</P>
 */
public class TheoryStudy {
	/**
	 * 系列讲话
	 */
    private List<InformationModel> speech = new ArrayList<InformationModel>(); 
    /**
     * 规章制度  
     */
    private List<InformationModel> rulesAndRegulations = new ArrayList<InformationModel>();  
    
	public List<InformationModel> getSpeech() {
		return speech;
	}
	public void setSpeech(List<InformationModel> speech) {
		this.speech = speech;
	}
	public List<InformationModel> getRulesAndRegulations() {
		return rulesAndRegulations;
	}
	public void setRulesAndRegulations(List<InformationModel> rulesAndRegulations) {
		this.rulesAndRegulations = rulesAndRegulations;
	}
    
}
