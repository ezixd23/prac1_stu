package p01_MutexTable;

import java.util.concurrent.Semaphore;

import p00_CommonA.Table;

public class MutexTable extends Table{

	
	/* Declare and initialize your semaphore here */
	private Semaphore mutex = new Semaphore(1);
	
	protected void gainExclusiveAccess () {
		/* COMPLETE */
		this.mutex.acquireUninterruptibly();
	}
	
	protected void releaseExclusiveAccess() {
		/* COMPLETE */
		this.mutex.release();
	}
	
	
	public void putJack(int id) {
		/* COMPLETE */
		while (true) {
			this.gainExclusiveAccess();
			if ((this.ffs == 0 && id == 0) || (this.ffs == 3 && id == 1) || this.ffs == 1 || this.ffs == 2)
				break;
			this.releaseExclusiveAccess();
		}	
	}
	
	public void putQueen(int id) {
		/* COMPLETE */
		while (true) {
			this.gainExclusiveAccess();
			if ((this.ffs == 0 && id == 0) || (this.ffs == 3 && id == 1) || this.ffs == 1 || this.ffs == 2)
				break;
			this.releaseExclusiveAccess();
		}	
	}
	
	public void putKing(int id) {
		/* COMPLETE */
		while (true) {
			this.gainExclusiveAccess();
			if ((this.ffs == 0 && id == 0) || (this.ffs == 3 && id == 1) || this.ffs == 1 || this.ffs == 2)
				break;
			this.releaseExclusiveAccess();
		}	
	}

	
	public void startCheck(int id) {
		/* COMPLETE */
		while (this.ffs < 4) Thread.yield();
	}

	
	public void endCheck(int id) {
		/* COMPLETE */
		this.ffs = 0;
	}

	
	public void cardPut() {
		/* COMPLETE */
		this.releaseExclusiveAccess();
	}

}
