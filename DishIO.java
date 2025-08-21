package RestaurantProj;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class DishIO {
	Scanner scanner = new Scanner(System.in);
	int itemNum=5;
	Object menuArr[]=new Object[] {"1.显示所有菜品;","2.添加菜品;","3.删除菜品;","4.修改菜品;","5.查询菜品;"};
	MenuShow menu = new MenuShow(itemNum,menuArr);
	public DishIO() {
		// TODO Auto-generated constructor stub

	}
	public void showMenu() {
		boolean bCyl=true;
		while(bCyl) {
			int choice=menu.display();
			switch (choice){
			case 1:
				showAll();
				break;
			case 2:
				add();
				break;
			case 3:
				delete();
				break;
			case 4:
				modify();
				break;
			case 5:
				search();
				break;
			default:
				bCyl=false;
				System.out.println("退出成功！");
				break;
			}
		}
	}
	public void showAll() {
		showDish(DishDAO.dishData);
	}
	private void add() {
		DishData dish=new DishData(0,"",0);
		while(true) {
			dish=inputDish(dish);
			if (dish.price==0.0) break;
			DishDAO.add(dish);
			System.out.println("输入成功！");
		}
	}
	private void delete() {
		boolean bCyl=true;
		while(bCyl) {
			int id = inputID();
			if (id>0 && id<=DishDAO.maxId) {
				if (DishDAO.deleById(id))
					System.out.println("删除成功！");
				else
					System.out.println("未找到记录，删除失败！");
			}else {
				bCyl=false;		}		
		}
		
	}
	private void modify() {
		boolean bCyl=true;
		while(bCyl) {
			int id = inputID();
			if (id>0 && id<=DishDAO.maxId) {
				DishData dish=DishDAO.findById(id);
				if (dish!=null) {
					System.out.println("找到菜品："+dish.id+","+dish.name+","+dish.price);
					dish=inputDish(dish);
					if (DishDAO.update(id, dish))
						System.out.println("修改成功！");
					}
				
			}else {
				bCyl=false;	}		
		}
		
	}
	public void search() {
		boolean bCyl=true;
		List<DishData> list = new ArrayList<>();
		while(bCyl) {
		System.out.println("请输入菜品名称（为空则退出）：");
		String name = scanner.nextLine();
		if (!name.isEmpty()) {
			list = DishDAO.search(name);
			if (list.isEmpty()) {
				System.out.println("未找到记录！");	
			}else {
				showDish(list);	}	
		}else
			bCyl=false;	
		}
	}
	private void showDish(List<DishData> dishList) {
		for(int i=0; i<dishList.size();i++) {
			System.out.println(dishList.get(i).id+","+dishList.get(i).name+","+dishList.get(i).price);
		}
	}
	public int inputID() {
		System.out.println(String.format("请输入菜品ID（最大值为%d,超过范围退出）：", DishDAO.maxId));
		int id=scanner.nextInt();
		scanner.nextLine();  // 新增这行：清理缓冲区
		return id;
	}
	private DishData inputDish(DishData dish) {
		System.out.println("请输入菜品名称：");
		dish.name = scanner.nextLine();
		System.out.println("请输入菜品价格（为0退出）：");
		try {
			dish.price = scanner.nextDouble();
			scanner.nextLine();  // 新增这行：清理缓冲区
			System.out.println(dish.name+","+dish.price);
		}catch (InputMismatchException e) {
			System.out.println(dish.name+","+dish.price);
			dish.price=0;
			return dish;
	    }
		return dish;
	}
		
}
