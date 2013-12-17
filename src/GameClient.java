import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class GameClient {

	public static void main(String[] args) throws IOException
	{
		Socket echoSocket = null;
		PrintWriter out = null;
		BufferedReader in = null;

		echoSocket = new Socket("localhost", 29999);
		out = new PrintWriter(echoSocket.getOutputStream(), true);
		in = new BufferedReader(new InputStreamReader(
			echoSocket.getInputStream()));

		BufferedReader stdIn = new BufferedReader(
                                   new InputStreamReader(System.in));
		String userInput = stdIn.readLine();
		while (userInput != null)
		{
			out.println(userInput);
			//read and print out what the server sent us
			System.out.println("Server: " + in.readLine());
			out.flush();
			//read the next input from the client
			userInput = stdIn.readLine();
		}

		out.close();
		in.close();
		stdIn.close();
		echoSocket.close();
    }
}
