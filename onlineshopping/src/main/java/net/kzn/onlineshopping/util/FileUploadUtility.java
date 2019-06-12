package net.kzn.onlineshopping.util;
import java.io.File;
import org.slf4j.Logger;
import java.io.IOException;
import org.slf4j.LoggerFactory;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;

public class FileUploadUtility {
	private static final String ABS_PATH = "/home/sis021/Documents/Java Online Shopping Project/online-shpping/onlineshopping/src/main/webapp/assets/images/";
	private static String REAL_PATH = "";
	private static final Logger logger = LoggerFactory.getLogger(FileUploadUtility.class);
	public static boolean uploadFile(HttpServletRequest request, MultipartFile file, String code) {
		//Get the real path
		REAL_PATH = request.getSession().getServletContext().getRealPath("/assets/images/");
		logger.info(REAL_PATH);
		//Please create the directory to ensure all the directories exist
		if(!new File(ABS_PATH).exists()) {
			new File(ABS_PATH).mkdirs();    //Creating the directories
		}
		if(!new File(REAL_PATH).exists()) {
			new File(REAL_PATH).mkdirs();    //Creating the directories
		}
		try {
			//File Upload to Server
			File serverFile = new File(REAL_PATH + code + ".jpg");
			File folderFile = new File(ABS_PATH + code + ".jpg");
			file.transferTo(serverFile);    //Image upload to server
			file.transferTo(folderFile);    //Image upload to project directory
		} catch(IOException ex) {
			ex.printStackTrace();
		}
		return true;
	}
}