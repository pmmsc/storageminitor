package org.pmmsc.storagemonitor.task;

import java.util.Scanner;

public class WaitTask extends Task {

	public WaitTask(String status) {
		mStatus = status;
	}

	public void run() {
		super.run();
		Scanner in = new Scanner(System.in);
		in.nextLine();
	}
}
