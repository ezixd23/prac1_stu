package p03_LockTable_and_CoolDown;

import java.util.concurrent.locks.ReentrantLock;

import p00_CommonA.*;

public class LockTableWCD extends Table implements CoolDownSupport{
	
	private ReentrantLock lock = new ReentrantLock();
	
	/* Declare and initialize here the required simple-typed variables */
	private volatile int lastCard = 0;
	private volatile int lastId = -1;
	
	protected void gainExclusiveAccess() {
		/* COMPLETE */
		this.lock.lock();	
	}


	protected void releaseExclusiveAccess() {
		/* COMPLETE */
		this.lock.unlock();
	}


	public void putJack(int id) {
		/* COMPLETE */
		while (true) {
			this.gainExclusiveAccess();
			if (this.ffs < 4 && this.ffs != 3)
				break;
			else
				this.releaseExclusiveAccess();
		}
		this.lastCard = 0;
		this.lastId = id;
	}

	
	public void putQueen(int id) {
		/* COMPLETE */
		while (true) {
			this.gainExclusiveAccess();
			if (this.ffs < 3 || (this.ffs == 3 && id % 2 == 0))
				break;
			else
				this.releaseExclusiveAccess();
		}
		this.lastCard = 1;
		this.lastId = id;
	}

	
	public void putKing(int id) {
		/* COMPLETE */
		while (true) {
			this.gainExclusiveAccess();
			if (this.ffs < 4 && this.ffs != 3)
				break;
			else
				this.releaseExclusiveAccess();
		}
		this.lastCard = 2;
		this.lastId = id;
	}


	public void cardPut() {
		/* COMPLETE */
		this.releaseExclusiveAccess();
	}

	
	public void startCheck(int id) {
		/* COMPLETE */
		while (true) {
			this.lock.lock();
			if (this.ffs >= 4)
				break;
			else
				this.lock.unlock();
		}
	}

	
	public void endCheck(int id) {
		/* COMPLETE */
		this.ffs = 0;
		this.lock.unlock();
	}


	
	// --- IMPLEMENTATION of the CoolDownSupport interface

	public void coolDownIsReady() {
		while (true) {
			this.lock.lock();
			if (this.lastCard == 2 && this.lastId % 2 == 0 && this.ffs == 2)
				break;
			else
				this.lock.unlock();
		}
	}	
	
	public void coolDownDone() {
		/* COMPLETE */
		this.ffs = 0;
		this.lock.unlock();
	}

}
