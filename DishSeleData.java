package RestaurantProj;

public class DishSeleData{
	public int tableId;
	public int innerId;//桌内序号
	public int dishId;
	public String name;
	public double price;
	public DishSeleData(int tableId,int innerId,int dishId,String name,double price) {
		// TODO Auto-generated constructor stub
		this.tableId=tableId;
		this.innerId=innerId;
		this.dishId=dishId;
		this.name=name;
		this.price=price;
	}
}
