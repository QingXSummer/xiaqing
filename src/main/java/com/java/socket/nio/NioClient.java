package com.java.socket.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;

public class NioClient {
	
	Selector selector; 
	
	ByteBuffer readBuffer = ByteBuffer.allocate(2048);
	ByteBuffer writeBuffer = ByteBuffer.allocate(2048);
	
	Scanner scanner = new Scanner(System.in);
	
	public NioClient(String ip, int port) {
		this.initClient(ip, port);
	}
	
	private void initClient(String ip,int port) {
		try {
			this.selector = Selector.open();
			SocketChannel socketChannel = SocketChannel.open();
			socketChannel.configureBlocking(false);
			socketChannel.register(selector, SelectionKey.OP_CONNECT, null);
			socketChannel.connect(new InetSocketAddress(ip, port));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void connect() throws IOException {
		while(true) {
			selector.select();
			Iterator<SelectionKey> keys = selector.selectedKeys().iterator();
			while (keys.hasNext()) {
				SelectionKey key = (SelectionKey) keys.next();
				keys.remove();
				if(key.isConnectable()) {
					SocketChannel socketChannel = (SocketChannel) key.channel();
					
                    // 如果正在连接，则完成连接  
                    if(socketChannel.isConnectionPending()){                        
                    	socketChannel.finishConnect();                            
                    }  
					
					socketChannel.configureBlocking(false);
					socketChannel.register(selector, SelectionKey.OP_READ);
					writeBuffer.clear();
					writeBuffer.put("hello".getBytes());
					writeBuffer.flip();
					socketChannel.write(writeBuffer);
					
					System.out.println("开始发送握手信息");
				}else if(key.isReadable()) {
					readBuffer.clear();
					writeBuffer.clear();
					SocketChannel socketChannel = (SocketChannel) key.channel();
					socketChannel.read(readBuffer);
					readBuffer.flip();
					byte[] bytes = new byte[readBuffer.limit()];
					readBuffer.get(bytes);
					System.out.println("服务端："+new String(bytes));
					System.out.println("请输入回应");
					String result = scanner.next();
					writeBuffer.put(result.getBytes());
					writeBuffer.flip();
					socketChannel.write(writeBuffer);
				}
			}
		}
	}
	
	public static void main(String[] args) {
		try {
			NioClient client = new NioClient("127.0.0.1", 8888);
			client.connect();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
