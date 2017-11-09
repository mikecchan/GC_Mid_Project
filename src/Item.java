
public class Item {
	String name;
	String desc;
	int qty;
	double price;
	int id;
	
	public Item(int id, String name, String desc, int qty, double price) {
		this.name = name;
		this.desc = desc;
		this.qty = qty;
		this.price = price;
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	@Override
	public String toString() {
		return (id + name + desc + qty + price);
	}
}
