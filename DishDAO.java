package RestaurantProj;

import java.util.ArrayList;
import java.util.List;

public class DishDAO {
	//菜品列表
	public static final List<DishData> dishData = new ArrayList<>();
	//表头列名，用于JTable中展示的表头，有多少列就写多少个字段在这里
	public static final Object[] columnNames={"ID","菜品","价格"};
	//用于模拟数据库中自增的id，给每一条数据唯一标识，每增加一条数据，maxId+1，删除数据不用管
	public static int maxId= 10;
	//模拟数据库中初始化的数据，根据实际情况修改
	static{
		dishData.add(new DishData(1,"锅包肉",42.0));
		dishData.add(new DishData(2,"家常凉菜",22.0));
		dishData.add(new DishData(3,"罗马生菜",25.0));
		dishData.add(new DishData(4,"酱牛肉",51.0));
		dishData.add(new DishData(5,"清蒸鱼",56.0));
		dishData.add(new DishData(6,"铁板牛柳",48.0));
		dishData.add(new DishData(7,"鱼香肉丝",38.0));
		dishData.add(new DishData(8,"火爆大头菜",22.0));
		dishData.add(new DishData(9,"干煸豆角",32.0));
		dishData.add(new DishData(10,"西红柿炒鸡蛋",28.0));
	}
	/**
	*.单条件查询, 根据菜名查询， @param text 查询的内容
	*@return*/
	public static List<DishData> search(String text) {
	List<DishData> result = new ArrayList<>();
	for (DishData d : dishData) {
		//这里是模糊查询，可改成完全匹配
		if (d.name.contains(text)){
			result.add(d);}
		}
		return result;
	}
	public static void add(DishData dish){		
		DishData newDish = new DishData(++maxId,dish.name,dish.price);
		dishData.add(newDish);
	}
	public static DishData findById(int sid){		
		for (DishData d:dishData) {
			if (d.id==sid) {
				return d;
			}
		}
		return null;
	}
	public static boolean update(int ids, DishData dish){
		
		DishData newDish = new DishData(ids,dish.name,dish.price);
		boolean bUp=false;
		for (int i=0;i<dishData.size();i++){
			if (dishData.get(i).id==ids) {
				dishData.set(i, newDish);
				bUp=true;
				break;
		}
		}
		return bUp;
	}
	public static boolean deleById(int sid){
		boolean bDele=false;
		for (int i=0;i<dishData.size();i++){
			if (dishData.get(i).id==sid) {
				dishData.remove(i);
				bDele=true;
				break;}
		}
		return	bDele;
	}
}
