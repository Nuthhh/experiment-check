package com.ysu.common.utils;

/*
 * From www.JavaExchange.com, Open Software licensing
 */

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

public class GUID extends Object {

	public String valueBeforeMD5 = "";

	public String valueAfterMD5 = "";

	private static Random myRand;

	private static SecureRandom mySecureRand;

	private static String s_id;

	/*
	 * 以下静态块要用几秒钟的时间初始化，每个JVM只运行一次 也可以用加载的时间来产生这个串，这样可以节省时间。
	 */

	static {
		mySecureRand = new SecureRandom();
		long secureInitializer = mySecureRand.nextLong();
		myRand = new Random(secureInitializer);
		try {
			s_id = InetAddress.getLocalHost().toString();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}

	}

	public GUID() {
		getRandomGUID(false);
	}

	/**
	 * 参数secure设置产生的随机数是否强加密， false时，产生的随机数用弱加密
	 */
	public GUID(boolean secure) {
		getRandomGUID(secure);
	}

	private void getRandomGUID(boolean secure) {
		MessageDigest md5 = null;
		StringBuffer sbValueBeforeMD5 = new StringBuffer();

		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			System.out.println("Error: " + e);
		}

		try {
			long time = System.currentTimeMillis();
			long rand = 0;

			if (secure) {
				rand = mySecureRand.nextLong();
			} else {
				rand = myRand.nextLong();
			}

			// This StringBuffer can be a long as you need; the MD5
			// hash will always return 128 bits. You can change
			// the seed to include anything you want here.
			// You could even stream a file through the MD5 making
			// the odds of guessing it at least as great as that
			// of guessing the contents of the file!
			sbValueBeforeMD5.append(s_id);
			sbValueBeforeMD5.append(":");
			sbValueBeforeMD5.append(Long.toString(time));
			sbValueBeforeMD5.append(":");
			sbValueBeforeMD5.append(Long.toString(rand));

			valueBeforeMD5 = sbValueBeforeMD5.toString();
			md5.update(valueBeforeMD5.getBytes());

			byte[] array = md5.digest();
			StringBuffer sb = new StringBuffer();
			for (int j = 0; j < array.length; ++j) {
				int b = array[j] & 0xFF;
				if (b < 0x10)
					sb.append('0');
				sb.append(Integer.toHexString(b));
			}

			valueAfterMD5 = sb.toString();

		} catch (Exception e) {
			System.out.println("Error:" + e);
		}
	}

	public String toString() {
		String raw = valueAfterMD5.toUpperCase();
		StringBuffer sb = new StringBuffer();
		sb.append(raw.substring(0, 8));
		// sb.append("-");
		sb.append(raw.substring(8, 12));
		// sb.append("-");
		sb.append(raw.substring(12, 16));
		// sb.append("-");
		sb.append(raw.substring(16, 20));
		// sb.append("-");
		sb.append(raw.substring(20));
		return sb.toString();
	}

	public static String getGUID() {
		GUID GUID = new GUID(true);
		return GUID.toString();
	}

	/*
	 * Demonstraton and self test of class
	 */
	public static void main(String args[]) {
		for (int i = 0; i < 1; i++) {
			GUID myGUID = new GUID();
			System.out.println("Seeding String=" + myGUID.valueBeforeMD5);
			System.out.println("rawGUID=" + myGUID.valueAfterMD5);
			System.out.println("RandomGUID=" + myGUID.toString());
		}
		System.err.println(getGUID());
	}
}