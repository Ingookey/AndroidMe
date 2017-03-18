
### Java-反射

**Java反射的功能：**
1. 运行时确定数据类型，解析类结构、获取属性；
2. 可访问属性、调用方法、创建新对象； 

**创建Class对象有三种方式？**
1. Class.forName("")
2. st.getClass()
3. 获取Class对象，可得到对象的真实信息如：对象的构造函数，属性，方法，注解，接口，父类，注解（必须是具有源码，否则注解不能查询到）等;


	Constructor<?> getconstructors（）//返回所有public构造器
	Method[] getMethods()
	Field getField(String name)
	Annotation[] getAnnotations()


Java.demo:

	public class Ademo {
	
		public static void main(String[] args) throws Exception {
			
			System.out.println(Ademo.class); 	//类.class获得Class对象
			Ademo demoInit = new Ademo();
			System.out.println(st.getClass());	 //实例名.getClass()获得Class对象
			System.out.println(Class.forName("demo.Ademo"));	//通过Class.forName(全路径)获得Class对象
	
			System.out.println(st);
			// 使用不同的方式创建对象
			System.out.println(Ademo.class.newInstance());
			System.out.println(demoInit.getClass().newInstance());
			System.out.println(Class.forName("demo.Ademo").newInstance());
		}
	}

Java.Result:

	class demo.Ademo
	class demo.Ademo
	class demo.Ademo
	.................................
	demo.Ademo@1cfe4b2
	demo.Ademo@1f2d08e
	demo.Ademo@b5c224
	demo.Ademo@1e25b76


**new关键字和newInstance()方法的区别：**

- new: 强类型。相对高效，能调用任何public构造；
	使用关键字new创建一个类的时候，这个类可以没有被加载;
- newInstance: 弱类型。低效率，只能调用无参构造；
	使用Class对象的newInstance()方法时，必须保证：1、这个类已经加载；2、这个类已经连接了；

Java.demo:

	import java.lang.reflect.Field;
	import java.lang.reflect.Method;
	import java.util.ArrayList;
	import java.util.List;
	
	public class Ademo {
		public static void main(String[] args) {
			getProperty(AUser.class);
		}
	
		public static void getProperty(Class<?> pClass) {
			String cName = pClass.getName();
			// 从类的名字中解析出类名
			String userName = cName.substring(cName.lastIndexOf(".") + 1, cName.length());
			System.out.println("实体名称为：" + userName);
			
			// 获取对象的所有属性
			Field[] fields = pClass.getDeclaredFields();
			for (Field field : fields) {
				System.out.println("属性名称：" + field.getName());
			}
			
			// 获取对象的所有方法
			Method[] methods = pClass.getDeclaredMethods();
			for (Method method : methods) {
				System.out.println("方法名称：" + method.getName());
			}
			
			// 反射创建对象，调用对象方法
			AUser userforname = (AUser) Class.forName(cName).newInstance();										
			AUser userClass = (AUser) pClass.newInstance();		

			userClass.setUserName("通过class名称创建对象");
			userforname.setUserName("Class类型创建对象");
			
			System.out.println(userClass.getUserName());
			System.out.println(userforname.getUserName());
		}
	}