package p02_ImpLockTable;

import p00_CommonA.Table;

public class ImplicitLockTable extends Table{
	
	/* Declare and initialize tour simple-typed variables here */
	private boolean permission = true;

	protected synchronized void gainExclusiveAccess() {
		/* COMPLETE */
		while (!this.permission || this.ffs >= 4) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		this.permission = false;
	}


	protected synchronized void releaseExclusiveAccess() {
		/* COMPLETE */
		this.permission = true;
	}


	public void putJack(int id) {
		/* COMPLETE */
		this.gainExclusiveAccess();
	}

	
	public void putQueen(int id) {
		/* COMPLETE */
		this.gainExclusiveAccess();
	}

	
	public void putKing(int id) {
		/* COMPLETE */
		this.gainExclusiveAccess();
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
