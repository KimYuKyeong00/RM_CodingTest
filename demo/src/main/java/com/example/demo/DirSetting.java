package com.example.demo;


public class DirSetting {
	
	private static final String FOLDER_DIR = "D:/storage/";
	private static final String PRIVATE_FOLDER_DIR = "D:/storage/private/";
	private static final String BUSINESS_FOLDER_DIR = "D:/storage/business/";
	
	public static String getFolderDir() {
		return FOLDER_DIR;
	}
	
	public static String getPrivateFolderDir() {
		return PRIVATE_FOLDER_DIR;
	}
	
	public static String getBusinessFolderDir() {
		return BUSINESS_FOLDER_DIR;
	}

}
