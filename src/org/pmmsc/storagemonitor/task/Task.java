package org.pmmsc.storagemonitor.task;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Task {

	protected String mStatus = null;
	protected String mCommand = null;

	protected List<String> mOutputList = new ArrayList<String>();

	public List<String> getOutputList() {
		return mOutputList;
	}

	public void run() {
		if (mStatus != null) {
			System.out.println(mStatus);
		}
		if (mCommand != null) {
			Process process = null;
	        try {
	            process = Runtime.getRuntime().exec(mCommand);
	            BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));
	            String line = null;
	            String path = "";
	            while ((line = input.readLine()) != null) {
		            if (line.contains("/")) {
		            	path = line.replace(":", "");
		            } else {
		            	line = path + "/" + line;
		            	if (!"".equals(line)) {
		            		mOutputList.add(line);
		            	}
		            }
	            }
	            input.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		}
	}

	public void dumpOutput() {
		for (String output : mOutputList) {
			System.out.println(output);
		}
	}
}