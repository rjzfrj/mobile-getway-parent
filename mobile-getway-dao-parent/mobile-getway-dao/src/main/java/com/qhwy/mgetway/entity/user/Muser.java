package com.qhwy.mgetway.entity.user;

public class Muser {
	 private Long uId;

	    private String loginName;

	    private String psssword;

	    private String mobileReference;

	    public Long getuId() {
	        return uId;
	    }

	    public void setuId(Long uId) {
	        this.uId = uId;
	    }

	    public String getLoginName() {
	        return loginName;
	    }

	    public void setLoginName(String loginName) {
	        this.loginName = loginName == null ? null : loginName.trim();
	    }

	    public String getPsssword() {
	        return psssword;
	    }

	    public void setPsssword(String psssword) {
	        this.psssword = psssword == null ? null : psssword.trim();
	    }

	    public String getMobileReference() {
	        return mobileReference;
	    }

	    public void setMobileReference(String mobileReference) {
	        this.mobileReference = mobileReference == null ? null : mobileReference.trim();
	    }

		@Override
		public String toString() {
			return "Muser [uId=" + uId + ", loginName=" + loginName + ", psssword=" + psssword + ", mobileReference="
					+ mobileReference + ", getuId()=" + getuId() + ", getLoginName()=" + getLoginName()
					+ ", getPsssword()=" + getPsssword() + ", getMobileReference()=" + getMobileReference()
					+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
					+ "]";
		}
	
    
}