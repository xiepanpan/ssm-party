package com.baobao.common.model;

import java.util.ArrayList;
import java.util.List;

/**
 * <P>我的学习摸块整体</P>
 */
public class StudyPageModel {
		/**
		 * 轮播列表
		 */
	    private List<BannerModel> bannerList = new ArrayList<BannerModel>(); 
	    /**
	     * 理论学习
	     */
	    private TheoryStudy theoryStudy = new TheoryStudy(); 
	    /**
	     * 时政学习
	     */
	    private CurrentPoliticsStudy currentPoliticsStudy = new CurrentPoliticsStudy(); 

	    public List<BannerModel> getBannerList() {
			return bannerList;
		}

		public void setBannerList(List<BannerModel> bannerList) {
			this.bannerList = bannerList;
		}

		public TheoryStudy getTheoryStudy() {
			return theoryStudy;
		}

		public void setTheoryStudy(TheoryStudy theoryStudy) {
			this.theoryStudy = theoryStudy;
		}

		public CurrentPoliticsStudy getCurrentPoliticsStudy() {
			return currentPoliticsStudy;
		}

		public void setCurrentPoliticsStudy(CurrentPoliticsStudy currentPoliticsStudy) {
			this.currentPoliticsStudy = currentPoliticsStudy;
		}

}
