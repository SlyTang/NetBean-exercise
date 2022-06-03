/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.bean;

/**
 *
 * @author JKc
 */
public class EquipmentBean {
    private String name;
    private String type;
    private String states;
    private String detail;
    private String id;
    private int rantDateLimit;
    private boolean deleted;

    public int getRantDateLimit() {
        return rantDateLimit;
    }

    public void setRantDateLimit(int rantDateLimit) {
        this.rantDateLimit = rantDateLimit;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStates() {
        return states;
    }

    public void setStates(String states) {
        this.states = states;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    
    public EquipmentBean(String name, String type){
        this.name = name; 
        this.type = type;
    }
    public EquipmentBean(String id,String name, String type,String states,String detail){
        this.id = id ;
        this.name = name;
        this.type = type;
        this.states = states;
        this.detail = detail;
    }
    public EquipmentBean(String id,String name, String type,String states,String detail,int rantDateLimit, boolean deleted){
        this.id = id;
        this.name = name;
        this.type = type;
        this.states = states;
        this.detail = detail;
        this.rantDateLimit = rantDateLimit;
        this.deleted = deleted;
    }
}
