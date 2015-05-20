package com.shuiwen.campusys.bean;

public class Kemu {
	private int id;
	private int xiaoquid;
	private String km_mingzi;
	private int xueqizhi;
	private int keshi;
	private float danjia;
	
	public Kemu(){
		
	}
	public Kemu(String km_mingzi,int xiaoquid){
		this.xiaoquid = xiaoquid;
		this.km_mingzi = km_mingzi;
	}
	public int getXueqizhi() {
		return xueqizhi;
	}
	public void setXueqizhi(int xueqizhi) {
		this.xueqizhi = xueqizhi;
	}
	public int getKeshi() {
		return keshi;
	}
	public void setKeshi(int keshi) {
		this.keshi = keshi;
	}
	public float getDanjia() {
		return danjia;
	}
	public void setDanjia(float danjia) {
		this.danjia = danjia;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getXiaoquid() {
		return xiaoquid;
	}
	public void setXiaoquid(int xiaoquid) {
		this.xiaoquid = xiaoquid;
	}
	public String getKm_mingzi() {
		return km_mingzi;
	}
	public void setKm_mingzi(String km_mingzi) {
		this.km_mingzi = km_mingzi;
	}
	
	
}
