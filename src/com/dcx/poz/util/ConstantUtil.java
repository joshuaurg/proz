package com.dcx.poz.util;

public class ConstantUtil {
	
	public static final String SERVER_IP = "http://localhost:8080/proz";
	
	/**
	 * 默认每页显示多少条记录
	 */
	public static final int DEFAULT_PAGE_SIZE = 5;
	
	/**
	 * 默认显示第几页记录
	 */
	public static final int DEFAULT_PAGE_NUM = 1;
	
	/**
	 * 七牛图片访问前缀
	 */
	public static final String QINIU_IMG_PREFIX = "http://o8ao1ngw0.bkt.clouddn.com/";

	/**
	 * 七牛音频访问前缀
	 */
	public static final String QINIU_AUDIO_PREFIX = "http://o82k6u3pp.bkt.clouddn.com/";
	
	/**
	 * 七牛视频访问前缀
	 */
	public static final String QINIU_VIDEO_PREFIX = "http://o82kaupli.bkt.clouddn.com/";

	/**
	 * 
	 * 图片前缀
	 */
	public static class MediaPrefix{
		public final static String ALBUM = "album";//相册
		public final static String POEM = "poem"; //诗词
		public final static String LORD_SONG = "lordsong"; //诗词
	}
	
	public static class MediaType{
		public final static String MP3 = ".mp3";
		public final static String MP4 = ".mp4";
		public final static String JPG = ".jpg";
	}
	
	/**
	 * 专辑类型
	 * @author dcx
	 *
	 */
	public static class AlbumType{
		public final static Integer PHOTO = 1;//相册
		public final static Integer LORDSONG = 2; //诗词
	}
	
}
