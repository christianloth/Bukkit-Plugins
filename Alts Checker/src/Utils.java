import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.spec.InvalidKeySpecException;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import javax.net.ssl.HttpsURLConnection;

/**
 * @author silv3rbullet
 */

public class Utils {

	/**
	 * @return 0 if successful, -1, for unknown error, 1 for bad login, 2 for
	 *         couldn't connect
	 */
	public static String doLogin(String user, String pass) {
		String[] parts = null;

		parts = login(user, pass);

		if (parts == null)
			return "A mysterious error occurred...";

		if (parts.length == 1) {
			return parts[0];
		}

		// Success!

		return "OK";
	}

	@SuppressWarnings("unused")
	private static Cipher getCipher(int mode, String password)
			throws InvalidKeySpecException, NoSuchAlgorithmException,
			NoSuchPaddingException, InvalidAlgorithmParameterException,
			InvalidKeyException {
		Random random = new Random(43287234L);
		byte[] salt = new byte[8];
		random.nextBytes(salt);
		PBEParameterSpec pbeParamSpec = new PBEParameterSpec(salt, 5);

		SecretKey pbeKey = SecretKeyFactory.getInstance("PBEWithMD5AndDES")
				.generateSecret(new PBEKeySpec(password.toCharArray()));
		Cipher cipher = Cipher.getInstance("PBEWithMD5AndDES");
		cipher.init(mode, pbeKey, pbeParamSpec);
		return cipher;
	}

	private static String[] login(String user, String pass) {
		try {
			String parameters = "user=" + URLEncoder.encode(user, "UTF-8")
					+ "&password=" + URLEncoder.encode(pass, "UTF-8")
					+ "&version=" + 13;

			String res = Utils.executePost("https://login.minecraft.net/",
					parameters);

			if (res == null) {
				return new String[] { "Couldn't connect to minecraft.net" };
			}

			if (!res.contains(":")) {
				if (res.trim().equals("Bad login")) {
					return new String[] { "Login failed" };
				} else if (res.trim().equals("Old version")) {
					return new String[] { "Outdated launcher" };
				} else
					return new String[] { res };
			}

			String[] values = res.split(":");

			return values;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return null;
	}

	public static String executePost(String targetURL, String urlParameters) {
		HttpsURLConnection connection = null;
		try {
			URL url = new URL(targetURL);
			connection = (HttpsURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");

			connection.setRequestProperty("Content-Length",
					Integer.toString(urlParameters.getBytes().length));
			connection.setRequestProperty("Content-Language", "en-US");

			connection.setUseCaches(false);
			connection.setDoInput(true);
			connection.setDoOutput(true);

			connection.connect();
			Certificate[] certs = connection.getServerCertificates();

			byte[] bytes = new byte[294];
			DataInputStream dis = new DataInputStream(
					Utils.class.getResourceAsStream("minecraft.key"));
			dis.readFully(bytes);
			dis.close();

			Certificate c = certs[0];
			PublicKey pk = c.getPublicKey();
			byte[] data = pk.getEncoded();

			for (int i = 0; i < data.length; i++) {
				if (data[i] == bytes[i])
					continue;
				throw new RuntimeException("Public key mismatch");
			}

			DataOutputStream wr = new DataOutputStream(
					connection.getOutputStream());
			wr.writeBytes(urlParameters);
			wr.flush();
			wr.close();

			InputStream is = connection.getInputStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(is));

			StringBuffer response = new StringBuffer();
			String line;
			while ((line = rd.readLine()) != null) {
				response.append(line);
				response.append('\r');
			}
			rd.close();

			String str1 = response.toString();
			return str1;
		} catch (Exception e) {
			// e.printStackTrace();
			return null;
		} finally {
			if (connection != null)
				connection.disconnect();
		}
	}
}
