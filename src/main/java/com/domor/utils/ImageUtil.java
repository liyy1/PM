package com.domor.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.font.TextAttribute;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.text.AttributedCharacterIterator;
import java.text.AttributedString;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * Title: ImageUtil
 * Description: 生成图片缩略图,裁剪图片,从ueditor中截取图片路径
 */
public class ImageUtil {

	public static String DEFAULT_PREVFIX = "thumb_";
	private static Boolean DEFAULT_FORCE = false;

	/**
	 * Title: thumbnailImage
	 * Description: 根据图片路径生成缩略图
	 * @param imgFile 原图片路径
	 * @param w 缩略图宽
	 * @param h 缩略图高
	 * @param prevfix 生成缩略图的前缀
	 * @param force 是否强制按照宽高生成缩略图(如果为false，则生成最佳比例缩略图)
	 */
	public static String thumbnailImage(File imgFile, int w, int h, String prevfix, boolean force) {
		String p = null;
		if (imgFile.exists()) {
			try {
				// ImageIO 支持的图片类型 : [BMP, bmp, jpg, JPG, wbmp, jpeg, png, PNG,
				// JPEG, WBMP, GIF, gif]
				String types = Arrays.toString(ImageIO.getReaderFormatNames());
				String suffix = null;
				// 获取图片后缀
				if (imgFile.getName().indexOf(".") > -1) {
					suffix = imgFile.getName().substring(imgFile.getName().lastIndexOf(".") + 1);
				}// 类型和图片后缀全部小写，然后判断后缀是否合法
				if (suffix == null || types.toLowerCase().indexOf(suffix.toLowerCase()) < 0) {
					// log.error("Sorry, the image suffix is illegal. the standard image suffix is {}."
					// + types);
					throw new Exception("Sorry, the image suffix is illegal.");
				}
				// log.debug("target image's size, width:{}, height:{}.",w,h);
				Image img = ImageIO.read(imgFile);
				if (!force) {
					// 根据原图与要求的缩略图比例，找到最合适的缩略图比例
					int width = img.getWidth(null);
					int height = img.getHeight(null);
					if ((width * 1.0) / w < (height * 1.0) / h) {
						if (width > w) {
							h = Integer.parseInt(new java.text.DecimalFormat("0").format(height * w / (width * 1.0)));
							// log.debug("change image's height, width:{}, height:{}.",w,h);
						}
					} else {
						if (height > h) {
							w = Integer.parseInt(new java.text.DecimalFormat("0").format(width * h / (height * 1.0)));
							// log.debug("change image's width, width:{}, height:{}.",w,h);
						}
					}
				}
				BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
				Graphics g = bi.getGraphics();
				g.drawImage(img, 0, 0, w, h, Color.LIGHT_GRAY, null);
				g.dispose();
				p = imgFile.getPath();
				// 将图片保存在原目录并加上前缀
				File thumbFile = new File(p.substring(0, p.lastIndexOf(File.separator)) + File.separator + prevfix + imgFile.getName());
				ImageIO.write(bi, suffix, thumbFile);
				p = thumbFile.getPath();
			} catch (Exception e) {
				// log.error("generate thumbnail image failed.",e);
			}
		} else {
			// log.warn("the image is not exist.");
		}
		return p;
	}

	public static String thumbnailImage(String imagePath, int w, int h, String prevfix, boolean force) {
		File imgFile = new File(imagePath);
		return thumbnailImage(imgFile, w, h, prevfix, force);
	}

	public static String thumbnailImage(String imagePath, int w, int h, boolean force) {
		return thumbnailImage(imagePath, w, h, DEFAULT_PREVFIX, force);
	}

	public static String thumbnailImage(String imagePath, int w, int h) {
		return thumbnailImage(imagePath, w, h, DEFAULT_FORCE);
	}
	
	/**
	 * 创建水印
	 * @param souchFilePath ：源图片路径
	 * @param targetFilePath ：生成后的目标图片路径
	 * @param markContent :要加的文字
	 * @param markContentColor :文字颜色
	 * @param qualNum :质量数字
	 * @param fontType :字体类型
	 * @param fontSize :字体大小
	 * @return
	 */
	public static void createMark(String souchFilePath, String targetFilePath, String markContent, Color markContentColor, float qualNum, String fontType, int fontSize, int w, int h, Color color) {
		markContentColor = color;
		/* 构建要处理的源图片 */
		ImageIcon imageIcon = new ImageIcon(souchFilePath);
		/* 获取要处理的图片 */
		Image image = imageIcon.getImage();
		/* Image可以获得图片的属性信息 */
		int width = image.getWidth(null);
		int height = image.getHeight(null);
		/* 为画出与源图片的相同大小的图片（可以自己定义） */
		BufferedImage bImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		/* 构建2D画笔 */
		Graphics2D g = bImage.createGraphics();
		/* 设置2D画笔的画出的文字颜色 */
		g.setColor(markContentColor);
		/* 设置2D画笔的画出的文字背景色 */
		g.setBackground(Color.white);
		/* 画出图片 */
		g.drawImage(image, 0, 0, null);

		/* --------对要显示的文字进行处理-------------- */
		AttributedString ats = new AttributedString(markContent);
		Font font = new Font(fontType, Font.BOLD, fontSize);
		g.setFont(font);
		/* 消除java.awt.Font字体的锯齿 */
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		/* 消除java.awt.Font字体的锯齿 */
		/* font = g.getFont().deriveFont(30.0f); */
		ats.addAttribute(TextAttribute.FONT, font, 0, markContent.length());
		AttributedCharacterIterator iter = ats.getIterator();
		/* 添加水印的文字和设置水印文字出现的内容 ----位置 */
		g.drawString(iter, width - w, height - h);
		/* --------对要显示的文字进行处理-------------- www.it165.net */

		g.dispose(); /* 画笔结束 */
		try {
			/* 输出 文件 到指定的路径 */
			FileOutputStream out = new FileOutputStream(targetFilePath);
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
			JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(bImage);
			param.setQuality(qualNum, true);
			encoder.encode(bImage, param);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 从HTML源码中提取图片路径，最后以一个 String 类型的 List 返回，如果不包含任何图片，则返回一个 size=0　的List
	 * 需要注意的是，此方法只会提取以下格式的图片：.jpg|.bmp|.eps|.gif|.mif|.miff|.png|.tif|.tiff|.svg|.wmf|.jpe|.jpeg|.dib|.ico|.tga|.cut|.pic
	 * @param htmlCode HTML源码
	 * @return <img>标签 src 属性指向的图片地址的List集合
	 * @author liyy
	 */
	public static List<String> getImageSrc(String htmlCode) {
		List<String> imageSrcList = new ArrayList<String>();
		Pattern p = Pattern.compile("<img\\b[^>]*\\bsrc\\b\\s*=\\s*('|\")?([^'\"\n\r\f>]+(\\.jpg|\\.bmp|\\.eps|\\.gif|\\.mif|\\.miff|\\.png|\\.tif|\\.tiff|\\.svg|\\.wmf|\\.jpe|\\.jpeg|\\.dib|\\.ico|\\.tga|\\.cut|\\.pic)\\b)[^>]*>", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(htmlCode);
		String quote = null;
		String src = null;
		while (m.find()) {
			quote = m.group(1);
			src = (quote == null || quote.trim().length() == 0) ? m.group(2).split("\\s+")[0] : m.group(2);
			imageSrcList.add(src);
		}
		return imageSrcList;
	}

	/**
	 * 从HTML源码中提取图片路径，最后返回String，如果不包含任何图片，则返回一个 size=0　null
	 * 需要注意的是，此方法只会提取以下格式的图片：.jpg|.bmp|.eps|.gif|.mif|.miff|.png|.tif|.tiff|.svg|.wmf|.jpe|.jpeg|.dib|.ico|.tga|.cut|.pic
	 * @param htmlCode HTML源码
	 * @return <img>标签 src 属性指向的图片地址的数组
	 * @author liyy
	 */
	public static String getImages(String htmlCode) {
		String images = "";
		List<String> imageSrcList = getImageSrc(htmlCode);
		for (int i=0;i<imageSrcList.size();i++){
			if(i==0)
				images = imageSrcList.get(i);
			else
				images = images + "," + imageSrcList.get(i);
		}
		return images;
	}

}
