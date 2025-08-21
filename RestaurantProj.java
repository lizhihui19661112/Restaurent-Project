package RestaurantProj;

public class RestaurantProj {
	public static void main(String[] args) {
		int itemNum=2;
		Object menuArr[]=new Object[] {"1.菜品管理;","2.点菜操作;"};
		
		MenuShow menu = new MenuShow(itemNum,menuArr);
		boolean bCyl=true;
		DishIO dishIO=new DishIO();
		DishSeleIO dishSeleIO = new DishSeleIO(dishIO);
		while(bCyl) {
			int choice=menu.display();
			switch (choice){
			case 1:
				dishIO.showMenu();
				break;
			case 2:
				dishSeleIO.showMenu();
				break;
			default:
				bCyl=false;
				System.out.println("退出成功！");
				break;
			}
		}
		
	}
}
