package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ReadConfig {

	Properties properties;

	public ReadConfig() {
		File src = new File("./Configuration\\config.properties");

		FileInputStream fis;
		try {
			fis = new FileInputStream(src);
			properties = new Properties();
			properties.load(fis);
		} catch (FileNotFoundException e) {
			System.out.println("Exception is : " + e.getMessage());
		} catch (IOException e) {
			System.out.println("Exception is : " + e.getMessage());
		}

	}

	public String getApplicationURL() {
		String url = properties.getProperty("baseURL");
		return url;
	}

	public String getUsername() {
		String username = properties.getProperty("username");
		return username;
	}

	public String getPassword() {
		String password = properties.getProperty("password");
		return password;
	}

	public String getChromePath() {
		String chromepath = properties.getProperty("chromepath");
		return chromepath;
	}

	public String getFireFoxPath() {
		String firefoxpath = properties.getProperty("firefoxpath");
		return firefoxpath;
	}

	public String getIEPath() {
		String IEpath = properties.getProperty("IEpath");
		return IEpath;
	}
}
