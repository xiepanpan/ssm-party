package com.baobao.common.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
*
* <P>
* 	党支部表实体类
* </P>
* 
* @author 袁杨柳(17607177525)
*/
public class MemberInfoModel {
	/**
	 * 党支部Id
	 */
    private Integer memberId;

    /**
     * 姓名
     */
    private String memberName;

    /**
     * 性别
     */
    private String memberSex;

    /**
     * 民族
     */
    private String memberNational;

    /**
     * 籍贯
     */
    private String memberNative;

    /**
     * 入党时间
     */
 /*   @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")*/
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date memberRddate;

    /**
     * 转正时间
     */
    /*@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")*/
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date memberZzdate;

    /**
     * 工作单位
     */
    private String memberWorkunit;

    /**
     * 职务
     */
    private String memberJob;

    /**
     * 身份证号
     */
    private String memberIdcard;

    /**
     * 所在支部ID
     */
    private Integer memberInbranchid;

    /**
     * 备注
     */
    private String memberRemark;

    /**
     * 党员当前登记照地址
     */
    private String memberPhotourl;

    /**
     * 当前所属类型(积极分子、发展对象、预备党员)
     */
    private String memberType;
    
    /**
     * 电话
     */
    private String memberTel;
    /**
     * 密码
     */
    private String memberPass;
    /**
     * 生日
     */
  /*  @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")*/
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date memberBirth;

    /**
     * 支部名称
     */
    private String branchName;
    /**
     * 这里是支部的名称
     */
    private String branchId;
    /**
     * 软删除
     */
    private Integer memberIsDelete;
    /**
     * 党员邮箱
     */
    private String memberEmail;
    /**
     * 党员学历
     */
    private String memberEdu;
    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName == null ? null : memberName.trim();
    }

    public String getMemberSex() {
        return memberSex;
    }

    public void setMemberSex(String memberSex) {
        this.memberSex = memberSex == null ? null : memberSex.trim();
    }

    public String getMemberNational() {
        return memberNational;
    }

    public void setMemberNational(String memberNational) {
        this.memberNational = memberNational == null ? null : memberNational.trim();
    }

    public String getMemberNative() {
        return memberNative;
    }

    public void setMemberNative(String memberNative) {
        this.memberNative = memberNative == null ? null : memberNative.trim();
    }

    public Date getMemberRddate() {
        return memberRddate;
    }

    public void setMemberRddate(Date memberRddate) {
        this.memberRddate = memberRddate;
    }

    public Date getMemberZzdate() {
        return memberZzdate;
    }

    public void setMemberZzdate(Date memberZzdate) {
        this.memberZzdate = memberZzdate;
    }

    public String getMemberWorkunit() {
        return memberWorkunit;
    }

    public void setMemberWorkunit(String memberWorkunit) {
        this.memberWorkunit = memberWorkunit == null ? null : memberWorkunit.trim();
    }

    public String getMemberJob() {
        return memberJob;
    }

    public void setMemberJob(String memberJob) {
        this.memberJob = memberJob == null ? null : memberJob.trim();
    }

    public String getMemberIdcard() {
        return memberIdcard;
    }

    public void setMemberIdcard(String memberIdcard) {
        this.memberIdcard = memberIdcard == null ? null : memberIdcard.trim();
    }

    public Integer getMemberInbranchid() {
        return memberInbranchid;
    }

    public void setMemberInbranchid(Integer memberInbranchid) {
        this.memberInbranchid = memberInbranchid;
    }

    public String getMemberRemark() {
        return memberRemark;
    }

    public void setMemberRemark(String memberRemark) {
        this.memberRemark = memberRemark == null ? null : memberRemark.trim();
    }

    public String getMemberPhotourl() {
        return memberPhotourl;
    }

    public void setMemberPhotourl(String memberPhotourl) {
        this.memberPhotourl = memberPhotourl == null ? null : memberPhotourl.trim();
    }

    public String getMemberType() {
        return memberType;
    }

    public void setMemberType(String memberType) {
        this.memberType = memberType == null ? null : memberType.trim();
    }

	/**
	 * @return the memberTel
	 */
	public String getMemberTel() {
		return memberTel;
	}

	/**
	 * @param memberTel the memberTel to set
	 */
	public void setMemberTel(String memberTel) {
		this.memberTel = memberTel;
	}

	/**
	 * @return the memberPass
	 */
	public String getMemberPass() {
		return memberPass;
	}

	/**
	 * @param memberPass the memberPass to set
	 */
	public void setMemberPass(String memberPass) {
		this.memberPass = memberPass;
	}

	public Date getMemberBirth() {
		return memberBirth;
	}

	public void setMemberBirth(Date memberBirth) {
		this.memberBirth = memberBirth;
	}

	/**
	 * @return the branchName
	 */
	public String getBranchName() {
		return branchName;
	}

	/**
	 * @param branchName the branchName to set
	 */
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public Integer getMemberIsDelete() {
		return memberIsDelete;
	}

	public void setMemberIsDelete(Integer memberIsDelete) {
		this.memberIsDelete = memberIsDelete;
	}

	/**
	 * @return the branchId
	 */
	public String getBranchId() {
		return branchId;
	}

	/**
	 * @param branchId the branchId to set
	 */
	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	/**
	 * @return the memberEmail
	 */
	public String getMemberEmail() {
		return memberEmail;
	}

	/**
	 * @param memberEmail the memberEmail to set
	 */
	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}

	/**
	 * @return the memberEdu
	 */
	public String getMemberEdu() {
		return memberEdu;
	}

	/**
	 * @param memberEdu the memberEdu to set
	 */
	public void setMemberEdu(String memberEdu) {
		this.memberEdu = memberEdu;
	}
    
}