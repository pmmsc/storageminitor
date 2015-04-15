package org.pmmsc.storagemonitor;

import java.util.ArrayList;
import java.util.List;

import org.pmmsc.storagemonitor.task.*;

public class MainEntrance {
	public static final String CURRENT_DIR = System.getProperty("user.dir"); 

	public static void main(String[] args) {
		//安装前清理T卡
		System.out.println("Initializing the cleaning method, please wait...");
		Task cleanTF = new CleanTFTask();
		cleanTF.run();
		Task dumpTF = new DumpTask();
		dumpTF.run();
		List<String> beforeInstall = new ArrayList<String>();
		beforeInstall.addAll(dumpTF.getOutputList());

		//正在安装apk
		String apk = CURRENT_DIR + "/" + args[0];
		System.out.println("Installing application:" + apk);
		Task installTask = new InstallTask(apk);
		installTask.run();
		Task waitOpen = new WaitTask("Please open the application and use, press any key to continue...");
		waitOpen.run();

		//使用后dump
		dumpTF.run();
		List<String> afterInstall = new ArrayList<String>();
		afterInstall.addAll(dumpTF.getOutputList());
		Task waitUninstall = new WaitTask("Please uninstall the application, press any key to continue...");
		waitUninstall.run();

		//卸载后dump
		dumpTF.run();
		List<String> afterUninstall = new ArrayList<String>();
		afterUninstall.addAll(dumpTF.getOutputList());

		List<String> installIncreased = new ArrayList<String>();
		List<String> unstallDecreased = new ArrayList<String>();
		List<String> residual = new ArrayList<String>();
		for (String ai : afterInstall) {
			if (!beforeInstall.contains(ai)) {
				installIncreased.add(ai);
			}
			if (!afterUninstall.contains(ai)) {
				unstallDecreased.add(ai);
			}
		}
		for (String au : afterUninstall) {
			if (!beforeInstall.contains(au)) {
				residual.add(au);
			}
		}
		
		System.out.println("==========The list of after install==========");
		for (String ii : installIncreased) {
			System.out.println(ii);
		}
		System.out.println();

		System.out.println("==========The list of after uninstall==========");
		for (String ud : unstallDecreased) {
			System.out.println(ud);
		}
		System.out.println();

		System.out.println("==========The list of residual==========");
		for (String r : residual) {
			System.out.println(r);
		}
		System.out.println();
	}
}
