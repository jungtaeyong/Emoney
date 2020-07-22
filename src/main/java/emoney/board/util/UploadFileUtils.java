package emoney.board.util;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;

public class UploadFileUtils {
	
	public static String makeThumbnail(String path, String fileName) throws Exception {
		BufferedImage sourceImg=ImageIO.read(new File(path,fileName));
		BufferedImage destImg=Scalr.resize(sourceImg,Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_TO_HEIGHT,100);
		
		String thumbnailName = path+"s_"+fileName;
		
		File newFile = new File(thumbnailName);
		String formatName=fileName.substring(fileName.lastIndexOf(".")+1);
		
		ImageIO.write(destImg, formatName.toUpperCase(),newFile);
		return thumbnailName.substring(path.length()).replace(File.separatorChar,'/');
	}
	
	
	
}
