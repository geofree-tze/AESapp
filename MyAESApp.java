// https://youtu.be/a3ZdcDvUwdY

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
//AES - Advanced Encryption Standard
public class MyAESApp {
	private static SecretKeySpec secretkey;
	private static byte[] key;
	
	//setkey
	public static void setKey(String myKey) {
		try {			
			key = myKey.getBytes("UTF-8");
			/* Checksum: error detection method */
			/* hash function: it is a function to produce checksum */
			/* hash value (is a numeric value of fixed length that uniquely identifies data. ) */
			/* Message digest: it is a fixed sized numeric representation of the contents of the message computed by hash function */
			/* In java, MessageDigest class provides functionality of a message digest using algorithms such as SHA=1 or SHA-256 */
			/* SHA stands for Secure Hashing Algorithm */
			MessageDigest sha = MessageDigest.getInstance("SHA-256");
//			MessageDigest sha = MessageDigest.getInstance("SHA-1");
			key = sha.digest(key);
			key = Arrays.copyOf(key, 16); //orig, new length
			secretkey = new SecretKeySpec(key, "AES");
		}
		catch (NoSuchAlgorithmException e) { }
		catch (UnsupportedEncodingException e) { }
	}
	
	//encryption
	public static String encrypt(String strToEnc, String sec) {
		try {
			setKey(sec);
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, secretkey);
			return Base64.getEncoder().encodeToString(cipher.doFinal(strToEnc.getBytes("UTF-8")));
		}
		catch (Exception e) {
			
		}
		return null;
	}
	
	//decryption
	public static String decrypt(String strToDec, String sec) {
		try {
			setKey(sec);
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, secretkey);
			return new String(cipher.doFinal(Base64.getDecoder().decode(strToDec)));
		}
		catch (Exception e) {
			
		}
		return null;
	}

}
