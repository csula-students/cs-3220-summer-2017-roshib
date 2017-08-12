package jaxrs;
public class FoodEntry {
	public final int id;
	public final String name;
	public final String price;
	public final String comment;
	public final String image;

	public FoodEntry() {
		this.id = 0;
		this.name = "";
		this.price="";
		this.comment = "";
		this.image="";
	}
	public FoodEntry(int id, String name,String price,String comment, String image) {
		this.id = id;
		this.name = name;
		this.price=price;
		this.comment = comment;
		this.image=image;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getComment() {
		return comment;
	}
	public String getPrice() {
		return price;
	}
	public String getImage() {
		return image;
	}
	
}

