package com.baobao.util;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class BaseObject
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 2820441983839220195L;
/*    */   private String loginId;
/*    */   
/*    */   public BaseObject() {}
/*    */   
/*    */   public boolean equals(Object other)
/*    */   {
/* 27 */     return EqualsBuilder.reflectionEquals(this, other);
/*    */   }
/*    */   
/*    */   public int hashCode()
/*    */   {
/* 32 */     return HashCodeBuilder.reflectionHashCode(this);
/*    */   }
/*    */   
/*    */   public String toString()
/*    */   {
/* 37 */     return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
/*    */   }
/*    */ 
/*    */ 
/*    */   public String getLoginId()
/*    */   {
/* 48 */     return this.loginId;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public void setLoginId(String loginId)
/*    */   {
/* 55 */     this.loginId = loginId;
/*    */   }
/*    */ }