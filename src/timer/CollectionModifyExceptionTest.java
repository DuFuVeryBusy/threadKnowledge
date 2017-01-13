package timer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class CollectionModifyExceptionTest {

	public static void main(String[] args) {
		
		List users = new CopyOnWriteArrayList<User>();     //new ArrayList();
		
		
		
		
		users.add(new User("��������",01));
		users.add(new User("�����Ƿ�",011));
		users.add(new User("���Ĵ���",0x5));
		Iterator<User> itrUser = users.iterator();
 		
		while(itrUser.hasNext()){
			User user = itrUser.next();
			if ("���Ĵ���".equals(user.getName())) {
				users.remove(user);
			}else {
				System.out.println(user.getName());
				
			}
			
			
			
		}
		
	}

}
