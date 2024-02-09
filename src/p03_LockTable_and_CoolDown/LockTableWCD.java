package p03_LockTable_and_CoolDown;

import java.util.concurrent.locks.ReentrantLock;

import p00_CommonA.*;

public class LockTableWCD extends Table implements CoolDownSupport{
	
	private ReentrantLock lock = new ReentrantLock();
	
	/* Declare and initialize here the required simple-typed variables */
	
	private boolean queen = false;
	private boolean king = false;
	
	protected void gainExclusiveAccess() {
		/* COMPLETE */
		lock.lock();
	}


	protected void releaseExclusiveAccess() {
		/* COMPLETE */
		lock.unlock();
	}


	public void putJack(int id) {
		/* COMPLETE */
		while(true) {
			this.gainExclusiveAccess();
			if (ffs < 3) {
				break;
			} else {
				king = false;
				this.releaseExclusiveAccess();
			}
		}
	}

	
	public void putQueen(int id) {
		/* COMPLETE */
		
		while(true) {
			this.gainExclusiveAccess();
			queen = true;
			if (ffs < 3 || (queen && ffs == 3&& id%2==0)) {
				break;
			} else {
				queen = false;
				king = false;
				this.releaseExclusiveAccess();
			}
		}
	}

	
	public void putKing(int id) {
		/* COMPLETE */
		while(true) {
			this.gainExclusiveAccess();
			if ( ffs < 3) {
				if(ffs == 2) king = true;
				break;
			}
			else this.releaseExclusiveAccess();
			
		}
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
	
	
	public void coolDownDone() {
		/* COMPLETE */
		this.ffs = 0;
		lock.unlock();
	}


	@Override
	public void coolDownIsReady() {
		// TODO Auto-generated method stub
		while (true) {
			this.lock.lock();
			if ( this.king && this.ffs == 2)
				break;
			else
				this.lock.unlock();
		}
	}

}
