package com.java.socket;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.channels.Selector;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class SocketTest {
	
	
	public static void main(String[] args) throws UnknownHostException, IOException {

		System.out.println();
//		ExecutorService executor = Executors.newFixedThreadPool(3);
//		executor.execute(new Server());
//		executor.execute(new Client());
		
//		System.out.println(System.getProperty("user.dir"));
//		System.out.println(File.separator);
		
		
		Selector.open();
		
	}
	
}

class Server implements Runnable{
	
	char[] msg =new char[1024];
	
	@Override
	public void run() {

			try(ServerSocket serverSocket = new ServerSocket(81)){
				while (true) {
					Socket client = serverSocket.accept();
					System.out.println("有客户端接入");
					InputStreamReader reader = new InputStreamReader(client.getInputStream());
					while(true) {
						int i = reader.read(msg);
						System.out.println("服务端输出："+i +"个字符");
						System.out.println(new String(msg,0,i));
						
					}
				}				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	}
	
	void parse() {
		
	}
	
}

class Client implements Runnable{

	@Override
	public void run() {
		try(Socket socket = new Socket("localhost", 81)){
			System.out.println("客户端已连接上服务器");
			OutputStreamWriter writer =new OutputStreamWriter(socket.getOutputStream());
			Scanner scanner = new Scanner(System.in);
			while (true) {
				String s =scanner.nextLine();				
				writer.write(s);
				writer.flush();
			}
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}