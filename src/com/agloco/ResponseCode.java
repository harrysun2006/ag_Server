package com.agloco;

public interface ResponseCode {

	
	public final static int BAD_REQUEST           = 400;
	public final static int UNAUTHORIZED          = 401;
	public final static int OTHER_PLACE_LOGIN     = 701;
	public final static int FORCE_UPDATE 		  = 801;
	
	public final static int SUCCESS               = 100000;
	
	public final static int DUPLICATE_RECORD	  = 200000;
	public final static int SERVICE_NAME_ERROR    = 200001;
	public final static int PARAMETER_ERROR       = 200002;
	public final static int COMMON_EXCEPTION      = 200003;
	public final static int MAPPING_EXCEPTION     = 200004;
	
	public final static int USER_INEXSTENCE_ERROR = 300000;
	public final static int PASSWORD_WRONG_ERROR  = 300001;
	public final static int USER_SUSPEND_ERROR    = 300002;
	public final static int USER_LOCKED_ERROR     = 300003;


	
	
	
}
