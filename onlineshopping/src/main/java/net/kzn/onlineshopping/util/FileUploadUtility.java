package net.kzn.onlineshopping.util;
import java.net.URL;
import java.io.File;
import org.slf4j.Logger;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.slf4j.LoggerFactory;
import java.io.FileOutputStream;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;

public class FileUploadUtility {
	private static final String ABS_PATH = "/home/sis021/Documents/Java Online Shopping Project/online-shpping/onlineshopping/src/main/webapp/assets/images/";
	private static String REAL_PATH = null;
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
	public static void uploadNoImage(HttpServletRequest request, String code) {
		// get the real server path
		REAL_PATH = request.getSession().getServletContext().getRealPath("/assets/images/");
		String imageURL = "http://placehold.it/640X480?text=No Image";
		String destinationServerFile = REAL_PATH + code + ".jpg";
		String destinationProjectFile = REAL_PATH + code + ".jpg";
		try {
			URL url = new URL(imageURL);				
			try (	
					InputStream is = url.openStream();
					OutputStream osREAL_PATH = new FileOutputStream(destinationServerFile);
					OutputStream osABS_PATH = new FileOutputStream(destinationProjectFile);
				){
			
				byte[] b = new byte[2048];
				int length;
				while((length = is.read(b))!= -1) {
					osREAL_PATH.write(b, 0, length);
					osABS_PATH.write(b, 0, length);
				}
			}			
		}
		catch(IOException ex) {
			ex.printStackTrace();
		}
	}
}