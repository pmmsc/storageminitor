package org.pmmsc.storagemonitor.task;


public class DumpTask extends Task {

	public DumpTask() {
		mCommand = "adb shell ls /sdcard/* -aR";
	}

	public void run() {
		mOutputList.clear();
		super.run();
	}
}
