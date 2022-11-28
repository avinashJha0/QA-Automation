package com.phpTravels.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadConfig {

	Properties pr;

	public ReadConfig() {
		File src = new File("./Configuration/config.properties");

		FileInputStream fs;
		try {
			fs = new FileInputStream(src);
			pr = new Properties();

			pr.load(fs);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public String getApplicationURL() {
		String url = pr.getProperty("baseURL");
		return url;
	}

	public String getUsername() {
		String username = pr.getProperty("username");
		return username;
	}

	public String getPassword() {
		String password = pr.getProperty("password");
		return password;
	}

	public String getChromepath() {
		String chromepath = pr.getProperty("chromepath");
		return chromepath;
	}
	
	public String getFirefoxpath() {
		String firefoxpath = pr.getProperty("firefoxpath");
		return firefoxpath;
	}
	
	public String getEdgepath() {
		String edgepath = pr.getProperty("edgepath");
		return edgepath;
	}
	
	

}
