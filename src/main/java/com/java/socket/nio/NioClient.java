package com.java.socket.nio;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;

public class NioClient {

	Logger logger = Logger.getLogger(NioClient.class);

	Selector selector; 
	
	ByteBuffer readBuffer = ByteBuffer.allocate(2048);
	ByteBuffer writeBuffer = ByteBuffer.allocate(2048);

	static  String msg=null;
	
	static Scanner scanner = new Scanner(System.in);
	
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
			logger.debug("keys-size:"+selector.selectedKeys().size());
			while (keys.hasNext()) {
				SelectionKey key = (SelectionKey) keys.next();
				keys.remove();
				if(key.isConnectable()) {
					SocketChannel socketChannel = (SocketChannel) key.channel();
					
                    // 如果正在连接，则完成连接  
                    if(socketChannel.isConnectionPending()){
                    	socketChannel.finishConnect();
                    }
                    int inter = SelectionKey.OP_READ | SelectionKey.OP_WRITE;
					socketChannel.register(selector, inter);
					
					System.out.println("成功连接服务器");
				}
				if(key.isReadable()) {
					readBuffer.clear();
					writeBuffer.clear();
					SocketChannel socketChannel = (SocketChannel) key.channel();
					socketChannel.read(readBuffer);
					readBuffer.flip();
					byte[] bytes = new byte[readBuffer.limit()];
					readBuffer.get(bytes);
					System.out.println("服务端："+new String(bytes));
				}
				if(key.isWritable()){
					SocketChannel socketChannel = (SocketChannel) key.channel();
//					String msg = scanner.nextLine();
					if(this.msg != null && this.msg != ""){
						writeBuffer.clear();
						writeBuffer.put(msg.getBytes());
						writeBuffer.flip();
						socketChannel.write(writeBuffer);
						logger.debug("客户端发送信息");
					}
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