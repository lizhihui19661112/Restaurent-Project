小型桌面管理系统的设计与实现
----以小型点菜系统为例
1 需求分析
1.1需求
实现一个小型桌面点菜系统，要求有菜品的增删改查功能，并实现根据桌号的点菜与结账功能。
1.2 分析
点菜系统主要是对菜品的操作，首先定义菜品类，通过ArrayList保存菜品记录，实现增删改查功能。然后定义点菜类，保存每桌号的点菜记录，每个桌号的点菜记录允许添加与删除，结账时根据桌号与点菜记录计算总价格，结账后清空该桌号的点菜记录。
2 功能设计
功能分两个菜单项，第一个菜单项是“菜品管理”，下面包括菜品的添加、删除、修改与查询功能。第二个菜单项是“点菜管理”包括点菜与结账功能。
3 数据类设计
3.1 菜品类
3.1.1 菜品类
菜品类包括菜品数据类、菜品处理类和菜品输入输出类；
菜品类包括菜品ID、菜品名称和菜品价格三个项目；
public class DishData {
	public int id;//
	public String name;
	public double price;
	public DishData(int id, String name,double price) {
		this.id = id;
		this.name = name;
		this.price = price;
	}	
}
3.1.2菜品处理类
菜品处理类（数据处理类）模拟数据库功能，菜品处理类包括菜品列表、菜品列表名称、菜品最大ID三个项目，还包括菜品搜索、添加、修改和删除等方法，这些成员变量和方法都设置成公有静态的，可以由外部访问，且可以通过类名访问。
public class DishDAO {
	//菜品列表，静态变量，整个系统只有一份
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
（1）查询
	public static List<DishData> search(String text)
//单条件查询, 根据菜名查询， @param text 查询的内容
（2）添加
public static void add(DishData dish)
将一个DishData记录添加到菜品列表中
（3）删除
public static boolean deleById(int sid)
//根据菜品ID删除
（4）修改
public static DishData findById(int sid)
//先通过菜品ID查找记录
public static boolean update(int ids, DishData dish)
//再根据菜品ID修改记录
3.1.3 菜品输入输出类
主要是增删改查的方法对应的输入输出方法，可以通过控制台实现，如果使用界面则需要不同的界面控件实现。
3.2 点菜类
点菜类包括点菜数据类、点菜处理类和点菜输入输出类；
3.2.1 点菜类
点菜数据类包括桌号、本桌点菜序号、菜品ID、菜品名称和菜品价格；红色字体是必要的项目，菜品名称和菜品价格是为了方便显示与计算加上的。
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
3.2.2 点菜处理类
点菜处理类包括点菜列表、点菜列表名称、点菜总金额三个项目，还包括点菜搜索、添加和删除等方法。这些成员变量和方法都设置成公有静态的，可以由外部访问，且可以通过类名访问。
public class DishSeleDAO {
//点菜列表，静态变量，整个系统只有一份
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
（1）	查询
public static List<DishSeleData> search(int tableNum)
//根据桌号查询点菜，同时统计总金额，放在类成员变量sum中
（2）	添加
public static int add(DishData obj,int tableId)
//根据桌号添加点菜记录
（3）	删除
public static boolean deleById(int tid, int iid)
//根据桌号和桌内点菜序号删除点菜记录
（4）	结账删除
public static boolean deleByTId(int tid)
//根据桌号删除所有点菜记录
3.1.3 菜品输入输出类
主要是增删改查的方法对应的输入输出方法，可以通过控制台实现，如果使用界面则需要不同的界面控件实现。
4 实现步骤与方法
（1）	定义数据类
（2）	定义处理类（DAO类），成员变量部分
（3）	定义IO类，在IO类中添加main方法，一个个同时添加DAO类处理方法和IO类相应方法，例如：在DAO类中添加add方法，然后在IO类也添加add方法，通过main方法运行并调试成功后，再添加其他方法。
