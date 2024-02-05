package com.example.demo.ps;

import java.sql.Date;

public class PrivateFile {
	private int private_file_serial;
	private String pm_id;
	private String file_name;
	private String file_extension;
	private long file_size;
	private Date upload_date;
	
	public PrivateFile() {
	}

	public PrivateFile(int private_file_serial, String pm_id, String file_name, String file_extension, long file_size,
			Date upload_date) {
		super();
		this.private_file_serial = private_file_serial;
		this.pm_id = pm_id;
		this.file_name = file_name;
		this.file_extension = file_extension;
		this.file_size = file_size;
		this.upload_date = upload_date;
	}

	public int getPrivate_file_serial() {
		return private_file_serial;
	}

	public void setPrivate_file_serial(int private_file_serial) {
		this.private_file_serial = private_file_serial;
	}

	public String getPm_id() {
		return pm_id;
	}

	public void setPm_id(String pm_id) {
		this.pm_id = pm_id;
	}

	public String getFile_name() {
		return file_name;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	public String getFile_extension() {
		return file_extension;
	}

	public void setFile_extension(String file_extension) {
		this.file_extension = file_extension;
	}

	public long getFile_size() {
		return file_size;
	}

	public void setFile_size(long file_size) {
		this.file_size = file_size;
	}

	public Date getUpload_date() {
		return upload_date;
	}

	public void setUpload_date(Date upload_date) {
		this.upload_date = upload_date;
	}

	
}
