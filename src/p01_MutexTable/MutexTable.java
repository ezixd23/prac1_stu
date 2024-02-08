package p01_MutexTable;

import java.util.concurrent.Semaphore;

import p00_CommonA.Table;

public class MutexTable extends Table{

	
	/* Declare and initialize your semaphore here */
	private Semaphore mutex = new Semaphore(1);
	
	protected void gainExclusiveAccess () {
		/* COMPLETE */
		try {
			mutex.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected void releaseExclusiveAccess() {
		/* COMPLETE */
		mutex.release();
	}
	
	
	public void putJack(int id) {
		/* COMPLETE */
		while(true) {
			this.gainExclusiveAccess();
			if((id==0 && this.ffs==0)|| (id==1 && this.ffs==3)  || (this.ffs>0 && this.ffs<3)) {
				break;
			}else this.releaseExclusiveAccess();
		}
	}
	
	public void putQueen(int id) {
		/* COMPLETE */
		while(true) {
			this.gainExclusiveAccess();
			if((id==0 && this.ffs==0)|| (id==1 && this.ffs==3)  || (this.ffs>0 && this.ffs<3)) {
				break;
			}else this.releaseExclusiveAccess();
		}
	}
	
	public void putKing(int id) {
		/* COMPLETE */
		while(true) {
			this.gainExclusiveAccess();
			if((id==0 && this.ffs==0)||(id==1 && this.ffs==3) || (this.ffs>0 && this.ffs<3)) {
				break;
			}else this.releaseExclusiveAccess();
		}
	}

	
	public void startCheck(int id) {
		/* COMPLETE */
		while (this.ffs < 4) {}
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
