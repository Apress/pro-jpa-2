package model.examples.singleton;

import javax.ejb.Lock;
import javax.ejb.LockType;				
import javax.ejb.Singleton;

@Singleton
public class HitCounter {
	int count;

	public void increment() { ++count; }
	
	@Lock(LockType.READ)
	public int getCount() { return count; }

    public void reset() { count = 0; }
}