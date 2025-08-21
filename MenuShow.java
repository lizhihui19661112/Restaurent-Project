package RestaurantProj;

import java.util.Scanner;

public class MenuShow {
	public Object[] menuArray;
	public int itemNum;
	public MenuShow(int itemNum,Object[] menuArray) {
		// TODO Auto-generated constructor stub
		this.itemNum=itemNum;
		this.menuArray=menuArray;
	}
	public int display() {
		int choice;
		for(int i=0;i<itemNum;i++) {
			System.out.println(menuArray[i]);
		}
		System.out.println("0.退出");	
		Scanner scanner = new Scanner(System.in); 
		while(true) {
			System.out.println(String.format("请输入0-%d:",itemNum));
			choice=scanner.nextInt();
			if (choice<=itemNum && choice >=0)
				break;
		}
		return choice;
	}
	
}
