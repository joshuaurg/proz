package com.dcx.poz.util;

public class ConstantUtil {
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
	public static final String QINIU_IMG_PREFIX = "http://joshuago.qiniudn.com/";

	/**
	 * 七牛音频访问前缀
	 */
	public static final String QINIU_AUDIO_PREFIX = "http://o82k6u3pp.bkt.clouddn.com/";
	
	/**
	 * 
	 * 图片前缀
	 */
	public static class ImagePrefix{
		public final static String ALBUM = "album";//相册
		public final static String POEM = "poem"; //诗词
		public final static String LORD_SONG = "lordsong"; //诗词
	}
	
}
