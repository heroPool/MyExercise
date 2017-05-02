package com.example.weektext;

public interface I {
	String SERVER_URL = "http://139.196.185.33:8080/SuperQQ3Server/Server";
//	String SERVER_URL = "http://10.0.2.2:8080/SuperQQ3Server/Server";
	String PAGE_ID = "pageId";
	String PAGE_SIZE = "pageSize";

	interface User {
		String TABLE_NAME = "user";
		String ID = "id";
		String USER_NAME = "userName";
		String NICK = "nick";
		String AVATAR = "avatar";
		String HEADER = "header";
		String PASSWORD = "password";
		String LATITUDE = "latitude";
		String LONGITUDE = "longitude";
		String GROUPS = "groups";
		String UN_READ_MSG_COUNT = "unreadMsgCount";

	}

	interface Contact extends User {
		String TABLE_NAME = "contact";
		String NAME = "name";
		String MYUID = "myuid";
		String CUID = "cuid";
		String IS_GET_MY_LOCATION = "isGetMyLocation";
		String IS_SHOW_MY_LOCATION = "isShowMyLocation";
	}

	interface Avatar {
		String FILE_NAME = "fileName";
	}

	/** 上传图片的类型：user_avatar或group_icon */
	String AVATAR_TYPE = "avatarType";

	String KEY_REQUEST = "request";

	/**
	 * 客户端上传头像的请求
	 */
	String REQUEST_UPLOAD_AVATAR = "upload_avatar";
	/**
	 * 客户端发送的登陆请求
	 */
	String REQUEST_LOGIN = "login";

	String REQUEST_DOWNLOAD_AVATAR_URL =I.SERVER_URL
			+"?"+KEY_REQUEST+"=download_avatar"
			+"&"+ User.USER_NAME+"=";

	String REQUEST_DOWNLOAD_CONTACT_LIST = "download_contact_list";

}
