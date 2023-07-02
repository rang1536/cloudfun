package com.cloudfun.www.util;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.apache.commons.io.FilenameUtils;
import org.mozilla.universalchardet.UniversalDetector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.cloudfun.www.com.dao.FileDao;
import com.cloudfun.www.post.dao.PostDao;
import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.MetadataException;
import com.drew.metadata.exif.ExifIFD0Directory;
import com.drew.metadata.exif.ExifSubIFDDirectory;
import com.mortennobel.imagescaling.ResampleOp;

import com.mortennobel.imagescaling.AdvancedResizeOp;
import com.mortennobel.imagescaling.MultiStepRescaleOp;

import net.coobird.thumbnailator.Thumbnailator;
import net.coobird.thumbnailator.Thumbnails;

@Component
public class UtilFile {
	
	
	@Value("#{globals['upload.file.path']}") 
    private String filePath;
	
	
	@Autowired
	private FileDao fileDao;
	
	/*
	//占쎈��餓λ���占쎌�わ옙毓썸에��諭�
	public List<TbFile> multiUploadFile(List<MultipartFile> fileList){
		List<TbFile> uploadFileList = new ArrayList<TbFile>();
		//占쎌��占쎈�わ옙��
		String rootPath = "/home/hosting_users/avinext/tomcat/webapps/files/";

		//嚥≪��類�
		//String rootPath = "C:\\git\\abnext\\abnext\\src\\main\\webapp\\resources\\files\\";

		//占쎌�띰옙沅� 占쎄�甕곤옙
		//String rootPath = "F:\\sh86\\resources\\files\\";

		if (fileList.size() > 0){
            for (int i = 0; i < fileList.size(); i++) {
            	TbFile uploadFile = uploadFile(fileList.get(i), rootPath);
                uploadFileList.add(uploadFile);
            }
        }
		return uploadFileList;
	}
	*/

	//占쎈��占쎌�わ옙��占쎌�� 占쎈씜嚥≪��諭�
/*
	public TbFile singleUploadFile(MultipartFile file){
		//占쎌��占쎈�わ옙��
		//String rootPath = "/home/hosting_users/avinext/tomcat/webapps/files/";
		//String rootPath = "http:///sh86.kr/resources/files/"+classNum+"/";

		//占쎌��占쎈�わ옙��
		String rootPath = "/home/hosting_users/avinext/tomcat/webapps/files/";

		//嚥≪��類�
		//String rootPath = "C:\\git\\abnext\\abnext\\src\\main\\webapp\\resources\\files\\";

		return uploadFile(file, rootPath);

	}*/

	// 筌�占쏙옙��占쎈��占쎈�� 占쎈��占쎌�� > 占쎈��占쎌�� 占쎌��占쎈��占쎌��嚥∽옙 癰�占쏙옙�� (占쎈�占쏙옙.)
	/*public File convert(MultipartFile multipartFile) throws IOException {
	    File file= new File(multipartFile.getOriginalFilename());
	    file.createNewFile();
	    FileOutputStream fos = new FileOutputStream(file);
	    fos.write(multipartFile.getBytes());
	    fos.close();
	    return file;
	}
	*/
	
	//param

		
	/** uploadFile.put("memberId", param.get("memberId"));
	uploadFile.put("postId", param.get("postId"));
	uploadFile.put("fileDvcd", param.get("fileDvcd"));
	uploadFile.put("groupId", param.get("groupId"));
	uploadFile.put("useYn", param.get("useYn"));
	*/
	
	public HashMap<String, String> uploadFile(MultipartFile multipartFile, Map<String, String> param) {
		HashMap<String, String> uploadFile = new HashMap<String, String>();
		
		String rootPath = filePath;
		
		try {
			String originalName = multipartFile.getOriginalFilename();
			String fileExtension = originalName.substring(originalName.lastIndexOf("."),originalName.length());

			// 
			LocalDateTime now = LocalDateTime.now();
			//
			String formatedNow = now.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
			//String formatedNow = "";
			String uuid = UUID.randomUUID().toString();
			String uuid2 = UUID.randomUUID().toString();

	        String fileName = formatedNow + uuid + fileExtension; //DB
	        fileName = fileName.replace("-", "");
	        String savePath = rootPath + fileName;
	        File destFile = new File(savePath);
	        multipartFile.transferTo(destFile);
	        
	    	uploadFile.put("rootPath", rootPath);
			uploadFile.put("fileName", fileName);
			uploadFile.put("originalName", originalName);
			
			uploadFile.put("memberId", param.get("memberId"));
			uploadFile.put("postId", param.get("postId"));
			uploadFile.put("fileDvcd", param.get("fileDvcd"));
			uploadFile.put("groupId", param.get("groupId"));
			uploadFile.put("useYn", param.get("useYn"));
			
			
			 // 파일정보 업로드.
			fileDao.insertFile(uploadFile);
			
			
			// 이미지파일의 경우 썸네일 생성
            if(multipartFile.getContentType().startsWith("image") == true){
            	String thumbnailSaveName = formatedNow + uuid2 + fileExtension; //DB
            	thumbnailSaveName = thumbnailSaveName.replace("-", "");
            	String savePath2 = rootPath  +"s_"+thumbnailSaveName;
            	File thumbnailFile = new File(savePath2);
            	
            	File destFile2 = new File(savePath);
            	Thumbnailator.createThumbnail(destFile2, thumbnailFile,200,200);
            	
            	uploadFile.put("thumbnailNm", "s_"+thumbnailSaveName);
            	
            	fileDao.updateThumbnail(uploadFile);
            	
            }
            
            List<String> txtList = new ArrayList<String>();
            
            String extension = FilenameUtils.getExtension(multipartFile.getOriginalFilename());
            if(extension.equals("txt")) {
            	
            	String encoding = UniversalDetector.detectCharset(destFile);
            	if (encoding != null) {
            		BufferedReader reader = new BufferedReader(
            			    new InputStreamReader(new FileInputStream(savePath), encoding)
            			);
            	    String str ;
            	    String strResult ="";
            	    int i = 0 ;
            	    
                    while ((str = reader.readLine()) != null) {
                    	strResult += str ; 
                    	i++;
                    	if(i==10) {
                    		break;
                    	}
                    	strResult += "<br/>";
                    }
                    
             
                    System.out.println("strResult");
                    System.out.println(strResult);
                    
                    reader.close();
                    
                    uploadFile.put("txtPreview", strResult);
                	fileDao.updateTxtPreview(uploadFile);
                    
                    
        		} else {
        			System.out.println("No encoding detected.");
        		}
            }
            
			
			
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return uploadFile;
	}
		


	public HashMap<String, String> uploadFile(MultipartFile multipartFile, String rootPath) {
		HashMap<String, String> uploadFile = new HashMap<String, String>();

	


		/* 占쎌��沃�紐�占� 占쎈씜嚥≪��諭띰옙��. 占쎌��占쎌�억옙釉х�곤옙 獄�占� jpg癰����� 嚥≪��彛� 占쎈７占쎈맙
		byte[] imgBytes = null;
		try {
			imgBytes = multipartFile.getBytes();
			BufferedInputStream bufferedIS = new BufferedInputStream(new ByteArrayInputStream(imgBytes));

			 Thumbnails 占쎄�占쎌��占쎈�� 占쎌��沃�紐�占� 占쎄�占쎄맒占쎌�� 癰�占쏙옙釉�占쎈�� �얜��ｅ�占� 占쎄문繹�占�.
			 * BufferedImage bi = Thumbnails.of(file).scale(1).asBufferedImage();

			int orientation = correctOrientation(bufferedIS);

			BufferedInputStream bis = new BufferedInputStream(new ByteArrayInputStream(imgBytes)); //占쎄�嚥∽옙 占쎄문占쎄쉐占쎈�餓μ��鍮� bufferedImage揶�占� 占쎄섯占쎌�� 占쎈�占쎈�뀐옙��.
			BufferedImage bi = rotateImageForMobile(bis, orientation);

			//�브쑵��獄�怨�瑗� 占쎄�占쎄맒 占쎌��占쎄�占쎄맒占쎌��嚥∽옙 癰�����
			int w = bi.getWidth();
			int h = bi.getHeight();
			BufferedImage biNew = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
			int[] rgb = bi.getRGB(0, 0, w, h, null, 0, w);
			biNew.setRGB(0, 0, w, h, rgb, 0, w);

			makeJPG(biNew, new File(rootPath+multipartFile.getOriginalFilename().replace("-", "")));
			ByteArrayInputStream byteIS = new ByteArrayInputStream(imgBytes);

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ImageProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MetadataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/


		try {
			String originalName = multipartFile.getOriginalFilename();
			String fileExtension = originalName.substring(originalName.lastIndexOf("."),originalName.length());

			// 
			LocalDateTime now = LocalDateTime.now();
			//
			//String formatedNow = now.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS"));
			String formatedNow = "";
			String uuid = UUID.randomUUID().toString();

	        String fileName = formatedNow + uuid + fileExtension; //DB
	        fileName = fileName.replace("-", "");
	        String savePath = rootPath + fileName;
	        File destFile = new File(savePath);
	        multipartFile.transferTo(destFile);
	        
	    	uploadFile.put("rootPath", rootPath);
			uploadFile.put("fileName", fileName);
			uploadFile.put("originalName", originalName);
			
	      /*  uploadFile.setFileOriNm(originalName);
	        uploadFile.setFilePath(rootPath);
	        uploadFile.setFileNewNm(fileName);*/
			
			// ���쇱��濡��� �대�� ����.
			
			
			
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return uploadFile;
	}
	/*
	//占쎈��占쎌�� 占쎄�占쎌��
	public boolean deleteImage(String path){
		File destFile = new File(path);
		boolean result = destFile.delete();
		return result;
	}


	public static int correctOrientation(BufferedInputStream inputStream) throws ImageProcessingException, IOException, MetadataException {
	    Metadata metadata = ImageMetadataReader.readMetadata(inputStream);
	    int orientation = 1;
	    System.out.println("metadata : "+metadata);
	    if(metadata != null) {
	        // Get the current orientation of the image
            Directory directory = metadata.getFirstDirectoryOfType(ExifIFD0Directory.class);
            System.out.println("占쎈�占쎌��占쎈���깍옙 : "+directory);
            if(directory != null) {
            	orientation = directory.getInt(ExifIFD0Directory.TAG_ORIENTATION);
                System.out.println("orientation : "+orientation);
                // Create a buffered image from the input stream
            }
        }
	    return orientation;
	}

	public BufferedImage rotateImageForMobile(InputStream is,int orientation) throws IOException {
        BufferedImage bi = ImageIO. read(is);
         if(orientation == 6){ //占쎌��占쎌��燁삼옙
                return rotateImage(bi, 90);
        } else if (orientation == 1){ //占쎌��筌�����嚥∽옙 占쎈��占쏙옙占쎌�ｏ옙釉�
                return bi;
        } else if (orientation == 3){//占쎌�ㅷ��紐���占쎌��嚥∽옙 占쎈��占쏙옙占쎌�ｏ옙釉�
                return rotateImage(bi, 180);
        } else if (orientation == 8){//180占쎈�
                return rotateImage(bi, 270);
        } else{
                return bi;
        }
	}

	public BufferedImage rotateImage(BufferedImage orgImage,int radians) {
        BufferedImage newImage;
         if(radians==90 || radians==270){
               newImage = new BufferedImage(orgImage.getHeight(),orgImage.getWidth(),orgImage.getType());
        } else if (radians==180){
               newImage = new BufferedImage(orgImage.getWidth(),orgImage.getHeight(),orgImage.getType());
        } else{
                return orgImage;
        }
        Graphics2D graphics = (Graphics2D) newImage.getGraphics();
        graphics.rotate(Math. toRadians(radians), newImage.getWidth() / 2, newImage.getHeight() / 2);
        graphics.translate((newImage.getWidth() - orgImage.getWidth()) / 2, (newImage.getHeight() - orgImage.getHeight()) / 2);
        graphics.drawImage(orgImage, 0, 0, orgImage.getWidth(), orgImage.getHeight(), null );

         return newImage;
	}

	public void makeJPG(BufferedImage orgImage, File destFile) throws IOException {
		/*MultiStepRescaleOp rescale = new MultiStepRescaleOp(orgImage.getWidth(),orgImage.getWidth());
		rescale.setUnsharpenMask(AdvancedResizeOp.UnsharpenMask.Soft);

		BufferedImage resizedImage = rescale.filter(orgImage, null);

		ImageIO.write(orgImage, "jpg", destFile);
	}*/
}

