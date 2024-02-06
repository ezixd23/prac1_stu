package p02_ImpLockTable;

import p00_CommonA.Table;

public class ImplicitLockTable extends Table{
	
	/* Declare and initialize tour simple-typed variables here */
	private volatile boolean permission = true;
	private volatile int firstCard = -1;

	protected void gainExclusiveAccess() {
		/* COMPLETE */
		while (!this.permission) {
			Thread.yield();
		}
		this.permission = false;
	}


	protected void releaseExclusiveAccess() {
		/* COMPLETE */
		this.permission = true;
	}


	public void putJack(int id) {
		/* COMPLETE */
		while (true) {
			synchronized (this) {
				this.gainExclusiveAccess();
				if (ffs < 3 || (firstCard == 0 && ffs == 3)) {
					break;
				} else {
					this.releaseExclusiveAccess();
				}
			}
		}
		if(this.ffs == 0) this.firstCard = 0;
	}

	
	public void putQueen(int id) {
		/* COMPLETE */
		while (true) {
			synchronized (this) {
				this.gainExclusiveAccess();
				if (ffs < 3 || (firstCard == 1 && ffs == 3)) {
					break;
				} else {
					this.releaseExclusiveAccess();
				}
			}
		}
		if(this.ffs == 0) this.firstCard = 1;
	}

	
	public void putKing(int id) {
		/* COMPLETE */
		while (true) {
			synchronized (this) {
				this.gainExclusiveAccess();
				if (ffs < 3 || (firstCard == 2 && ffs == 3)) {
					break;
				} else {
					this.releaseExclusiveAccess();
				}
			}
		}
		if(this.ffs == 0) this.firstCard = 2;
	}


	public void cardPut() {
		/* COMPLETE */
		this.releaseExclusiveAccess();
	}

	
	public void startCheck(int id) {
		/* COMPLETE */
		while (this.ffs < 4) {}
	}

	
	public void endCheck(int id) {
		/* COMPLETE */
		this.ffs = 0;
	}

}
