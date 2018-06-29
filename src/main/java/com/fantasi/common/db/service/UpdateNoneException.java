package com.fantasi.common.db.service;

import java.sql.SQLException;

public class UpdateNoneException extends SQLException{

	/**
	 *
	 */
	private static final long serialVersionUID = 4919079571045171389L;

	public UpdateNoneException(String msg) {
		super(msg);
	}
	
	public String getErrorMessage() {
		return this.getMessage();
	}

}
