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
public class EquipmentTypeBean {
    String name;
    String id;
    String path;
    public EquipmentTypeBean(String id,String name){
        this.name = name;
        this.id = id;
    }
    public EquipmentTypeBean(String id,String name, String path){
        this.name = name;
        this.id = id;
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
        
}
