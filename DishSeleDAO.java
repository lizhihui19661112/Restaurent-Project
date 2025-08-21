package RestaurantProj;

import java.util.ArrayList;
import java.util.List;

public class DishSeleDAO {
	public static final List<DishSeleData> dishSele = new ArrayList<>();
	//表头列名，用于JTable中展示的表头，有多少列就写多少个字段在这里
	public static final Object[] columnNames={"桌号","序号","ID","菜品","价格"};
	public static double sum=0;//点菜金额
	//模拟数据库中初始化的数据，根据实际情况修改
	static{
		dishSele.add(new DishSeleData(1,1,1,"锅包肉",42.0));
		dishSele.add(new DishSeleData(1,2,2,"家常凉菜",22.0));
		dishSele.add(new DishSeleData(1,3,3,"罗马生菜",25.0));
	}
	/**
	*.单条件查询,  @param col 要查询的列, @param text 查询的内容
	**/
	public static List<DishSeleData> search(int tableNum) {
	List<DishSeleData> result = new ArrayList<>();
	sum=0;
	for (DishSeleData d : dishSele) {
		//这里是模糊查询，可改成完全匹配
		if (d.tableId==tableNum){
			result.add(d);
			sum+=d.price;}
		}
		return result;
	}
	public static int add(DishData obj,int tableId){
		//obj应该是DishData对象
		DishSeleData sele = new DishSeleData(tableId,0,obj.id,obj.name,obj.price);
		/*sele.tableId = tableId;
		sele.dishId =  (int)obj[0];
		sele.name = obj[1].toString();
		sele.price=Double.parseDouble(obj[2].toString());*/
		int maxInId=0;
		for (int i=0;i<dishSele.size();i++){
			if (dishSele.get(i).tableId==sele.tableId && dishSele.get(i).dishId==sele.dishId) {
				return	-1;
			}
			if (dishSele.get(i).tableId==sele.tableId && dishSele.get(i).innerId>maxInId) {
				maxInId=dishSele.get(i).innerId;}
			}
		sele.innerId = maxInId+1;
		dishSele.add(sele);
		return maxInId+1;
		}
	
	public static boolean deleById(int tid, int iid){
		//根据桌号和点菜序号删除点菜记录
		//int iid=(int)inId;
		boolean bDele=false;
		for (int i=0;i<dishSele.size();i++){
			if (dishSele.get(i).tableId==tid && dishSele.get(i).innerId==iid) {
				dishSele.remove(i);
				bDele=true;}
			if (bDele) {
				dishSele.get(i).innerId=dishSele.get(i).innerId-1;	}
		}
		return bDele;
	}
	public static boolean deleByTId(int tid) {
		//根据桌号删除点菜记录
		boolean bDele=false;
		for (int i=dishSele.size()-1;i>=0;i--){
			if (dishSele.get(i).tableId==tid) {
				dishSele.remove(i);
				bDele=true;}
			}
		return bDele;
	}
	
}
