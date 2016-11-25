package com.tracking;

import com.conn.ConnDatabase;

public class testTracking {
	
	public void testTrack(String campId)
	{
		String procName = "proc_testCount('"+campId+"')";
		ConnDatabase conn = new ConnDatabase();
		conn.getConnectionByProc(procName);
	}

}
