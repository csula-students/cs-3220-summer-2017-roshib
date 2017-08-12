package jaxrs;

import java.util.Date;


public class Order {
	public final int id;
	public final FoodEntry food;
	public final String customerName;
	public enum Status {IN_QUEUE, IN_PROGRESS, COMPLETED};
	public Status statusQuality;
	public final Date created;

	public Order(int id,FoodEntry food,String customerName,Status statusQuality,Date created) {
		this.id = id;
		this.food = food;
		this.customerName=customerName;
		this.statusQuality=statusQuality;
		this.created=created;
	}
    public Order() {
		this.id = 0;
		this.food =null;
		this.customerName="";
		this.statusQuality=null;
		this.created=null;
	}
	public int getId() {
		return id;
	}
	public FoodEntry getFood() {
		return food;
	}

	public String getCustomerName() {
		return customerName;
	}
	
	public Status getStatus() {
		return statusQuality;
	}
	public void setStatus(Status statusQuality) {
	
		    this.statusQuality=statusQuality; 
		  
		
	}
	
	public Date getDate() {
		return created;
	}
	
}