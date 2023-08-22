package com.raitha.admin.user.export;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import jakarta.servlet.http.HttpServletResponse;

public class AbstractExporter {
	

	public void setResponseHeader(HttpServletResponse response,String contentType,
			String extension) throws IOException {
		DateFormat dateFormatter=new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
		String timestamp=dateFormatter.format(new Date());
		String fileName="users_"+ timestamp+ extension;
		response.setContentType(contentType);
		
		String headerKey="content-Disposition";
		String headerValue="attachment; fileName=" +fileName;
		response.setHeader(headerKey,headerValue);
}
}
