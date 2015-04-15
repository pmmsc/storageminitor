package org.pmmsc.storagemonitor.task;

public class InstallTask extends Task {

	public InstallTask(String apk) {
		mCommand = "adb install -r " + apk;
	}
}
