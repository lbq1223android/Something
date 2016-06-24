package com.oasis.something.bean;

public class FuctionBean {
    public String funcionName ;
    public Class className ;
    public  FuctionBean(String funcionName,Class className){
        this.funcionName = funcionName ;
        this.className = className ;   
    }
    public String getFuncionName() {
        return funcionName;
    }

    public void setFuncionName(String funcionName) {
        this.funcionName = funcionName;
    }

    public Class getClassName() {
        return className;
    }

    public void setClassName(Class className) {
        this.className = className;
    }




}
