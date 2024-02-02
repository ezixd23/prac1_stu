package p04_DDD_CandS;

import java.util.concurrent.atomic.*;

import p00_CommonB.*;

public class CSSync implements InterfaceSync {
	
	/* Declare and initialize your single instance of AtomicBoolean */
	private AtomicBoolean canCheck = new AtomicBoolean(true);
	
	/* Declare and initialize all the simple-typed variables required */
	private final int DING = 0;
	private final int DANG = 1;
	private final int DONG = 2;
	private int lastPrinted = DONG;
	private int expectedId = 0;
	private int dingCount = 0;
	
	private final int NUMINSTANCES; 
	
	public CSSync (int numinstances) {
		this.NUMINSTANCES = numinstances;
	}
	
	public void letMeDing(int id) {
		/* COMPLETE */
		while (true) {
			while (!canCheck.compareAndSet(true, false)) {
				this.backOff();
			}
			if(!(id != expectedId || !((this.lastPrinted == DONG || this.lastPrinted == DING) && this.dingCount < 3))) {
				break;
			} else {
				canCheck.set(true);
				this.backOff();
			}
		}
	}

	public void dingDone(int id) {
		/* COMPLETE */
		this.lastPrinted = DING;
		this.expectedId = (expectedId + 1) % NUMINSTANCES;
		this.dingCount++;
		canCheck.set(true);
	}


	public void letMeDang(int id) {
		/* COMPLETE */
		while (true) {
			while (!canCheck.compareAndSet(true, false)) {
				this.backOff();
			}
			if(!(id != expectedId || !((this.lastPrinted == DING || this.lastPrinted == DANG) && this.dingCount > 0))) {
				break;
			} else {
				canCheck.set(true);
				this.backOff();
			}
		}
	}


	public void dangDone() {
		/* COMPLETE */
		this.lastPrinted = DANG;
		this.expectedId = (expectedId + 1) % NUMINSTANCES;
		this.dingCount--;
		canCheck.set(true);
	}

	
	public void letMeDong(int id) {
		/* COMPLETE */
		while (true) {
			while (!canCheck.compareAndSet(true, false)) {
				this.backOff();
			}
			if(!(id != expectedId || !(this.lastPrinted == DANG && this.dingCount == 0 || this.lastPrinted == DONG))) {
				break;
			} else {
				canCheck.set(true);
				this.backOff();
			}
		}
	}

	
	public void dongDone() {
		/* COMPLETE */
		this.lastPrinted = DONG;
		this.expectedId = (expectedId + 1) % NUMINSTANCES;
		canCheck.set(true);
	}

	// use this method instead of Thread.yield()
	public void backOff () {
		try {Thread.sleep(0,1);} catch (InterruptedException ie) {}
	}
	
}
