package timer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class CollectionModifyExceptionTest {

	public static void main(String[] args) {
		
		List users = new CopyOnWriteArrayList<User>();     //new ArrayList();
		
		
		
		
		users.add(new User("美国经济",01));
		users.add(new User("控制涨幅",011));
		users.add(new User("放心贷款",0x5));
		Iterator<User> itrUser = users.iterator();
 		
		while(itrUser.hasNext()){
			User user = itrUser.next();
			if ("放心贷款".equals(user.getName())) {
				users.remove(user);
			}else {
				System.out.println(user.getName());
				
			}
			
			
			
		}
		
	}

}
