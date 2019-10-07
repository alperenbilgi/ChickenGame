import java.util.LinkedList;

public class Physics {
	
	public static boolean CollisionAtoB(EntityA enta, LinkedList<EntityB> entb) {
		for(int i = 0; i < entb.size(); i++) {
			if(enta.getBounds().intersects(entb.get(i).getBounds())) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean CollisionBtoA(EntityB entb, LinkedList<EntityA> enta) {
		for(int i = 0; i < enta.size(); i++) {
			if(entb.getBounds().intersects(enta.get(i).getBounds())) {
				return true;
			}
		}		
		return false;
	}
	
	public static boolean CollisionAtoC(EntityA enta, LinkedList<EntityC> entc) {
		for(int i = 0; i < entc.size(); i++) {
			if(enta.getBounds().intersects(entc.get(i).getBounds())) {
				return true;
			}
		}
		return false;
	}
}