package org.pmmsc.storagemonitor.task;

public class CleanTFTask extends Task {

	public CleanTFTask() {
		mCommand = "adb shell rm -rf /sdcard/* /sdcard/.*";
	}
}