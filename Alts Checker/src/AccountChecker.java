import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AccountChecker {

	public static void main(String[] args) throws IOException {
		System.out
				.println("Enter the account list location (press enter to use accountlist.in).");

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		String str = in.readLine();

		// Default to accountlist.in if no input was received.
		if (str.equals(""))
			str = "accountlist.in";

		@SuppressWarnings("resource")
		BufferedReader reader = new BufferedReader(new FileReader(str));

		String line = null;

		int linenum = 1;
		int processed = 0;

		int good = 0;

		long begin = System.currentTimeMillis();

		// Read to end.
		while ((line = reader.readLine()) != null) {
			String[] unp = line.split(":");

			if (unp.length != 2) {
				System.out
						.println("[WARNING] Malformed username/password combination @ line "
								+ linenum
								+ "!  Data: "
								+ line
								+ " (Skipping...)");

				linenum++;

				continue;
			}

			String res = "";

			try {
				res = Utils.doLogin(unp[0], unp[1]);
			} catch (Exception e) {
				res = "A mysterious error occurred...";
			}

			if (res.contains("OK"))
				good++;

			String toprint = unp[0] + ": " + res;

			System.out.println(toprint);

			processed++;
			linenum++;
		}

		System.out.println("Done. " + processed + " of " + linenum
				+ " username/password combinations processed in "
				+ (System.currentTimeMillis() - begin) + "ms.");
		System.out.println(good + " out of " + linenum + " good accounts.");
		System.out.println("Press any enter to exit...");

		in.read();
	}
}
