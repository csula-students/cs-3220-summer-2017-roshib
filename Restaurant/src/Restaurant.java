import java.util.ArrayList;

public class Restaurant {
	 int id;
	 String name;
	 String url;
	 ArrayList<Integer> designRatings;
	 ArrayList<Integer> tasteRatings;

	public Restaurant(int id, String name,String url,ArrayList<Integer> designRatings, ArrayList<Integer> tasteRatings) {
		this.id = id;
		this.name = name;
		this.url=url;
		this.designRatings=designRatings;
		this.tasteRatings=tasteRatings;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public ArrayList<Integer> getDesignRatings() {
		return designRatings;
	}
	public String geturl() {
		return url;
	}
	public ArrayList<Integer> getTasteRatingse() {
		return tasteRatings;
	}
}