package RestaurantProj;

import java.util.List;
import java.util.Scanner;

public class DishSeleIO {
	Scanner scanner = new Scanner(System.in); 
	DishIO dishIO;
	
	int itemNum=5;
	Object menuArr[]=new Object[] {"1.显示所有菜品;","2.查询菜品;","3.点菜;","4.删除点菜;","5.结账;"};
	
	MenuShow menu = new MenuShow(itemNum,menuArr);
	public DishSeleIO(DishIO dishIO) {
		// TODO Auto-generated constructor stub
		this.dishIO = dishIO;
		
	}
	public void showMenu() {
		boolean bCyl=true;
		
		while(bCyl) {
			int choice=menu.display();
			switch (choice){
			case 1:
				dishIO.showAll();
				break;
			case 2:
				dishIO.search();
				break;
			case 3:
				add();
				break;
			case 4:
				delete();
				break;
			case 5:
				buy();
				break;
			default:
				bCyl=false;
				System.out.println("退出成功！");
				break;
			}
		}
	}
	private void add() {
		boolean bCyl=true;
		int tableId = inputTID("请输入桌号：");
		if (tableId<=0) {
			System.out.println("无效输入！");
			return;
		}	
		int dishId;
		while(bCyl) {
			List<DishSeleData> seleList=DishSeleDAO.search(tableId);
			if (seleList.isEmpty()) {
				System.out.println("还未点菜！");
			}else {
				showSeleDish(seleList);}
			dishId=dishIO.inputID();
			if (dishId>0 && dishId<=DishDAO.maxId) {
				DishData dish=DishDAO.findById(dishId);
				if (dish!=null) {
					DishSeleDAO.add(dish, tableId);
				}else {
					System.out.println("未找到输入的菜品ID！");
				}
			}else {
				bCyl=false;
			}
		}		
	}
	private void delete() {
		boolean bCyl=true;
		int tableId = inputTID("请输入桌号：");
		if (tableId<=0) {
			System.out.println("无效输入！");
			return;
		}	
		int iid;
		while(bCyl) {
			List<DishSeleData> seleList=DishSeleDAO.search(tableId);
			if (seleList.isEmpty()) {
				System.out.println("还未点菜！");
				bCyl=false;
				break;
			}else {
				showSeleDish(seleList);}
			iid=inputTID("请输入桌中点菜序号（输入0退出）：");
			if (iid>0) {
				if (DishSeleDAO.deleById(tableId, iid)) {
					System.out.println("删除成功！");
				}else {
					System.out.println("未找到记录！");
				}
			}else {
				bCyl=false;
			}
		}
		
	}
	private int inputTID(String prompt) {
		System.out.println(prompt);
		int id=scanner.nextInt();
		scanner.nextLine();  // 新增这行：清理缓冲区
		
		return id;
	}
	
	private void showSeleDish(List<DishSeleData> dishList) {
		for(int i=0; i<dishList.size();i++) {
			System.out.println(dishList.get(i).innerId+","+dishList.get(i).dishId+","+dishList.get(i).name+","+dishList.get(i).price);
		}
		System.out.println("总金额："+DishSeleDAO.sum);
	}
	private void buy() {
		int tableId = inputTID("请输入桌号：");
		List<DishSeleData> seleList=DishSeleDAO.search(tableId);
		if (seleList.isEmpty()) {
			System.out.println("还未点菜！");
			return;
		}else {
			showSeleDish(seleList);}
		int iid=inputTID("确定买单？（输入1买单，其他退出）：");
		if (iid==1) {
			if (DishSeleDAO.deleByTId(tableId)) {
				System.out.println("删除成功！");
			}else {
				System.out.println("未找到记录！");
			}
		}
	}
		
	
}
