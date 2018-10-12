package com.java.socket.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;

public class NioServer {
	private Selector selector;
	private ByteBuffer readBuffer  = ByteBuffer.allocate(2048);
	private ByteBuffer writeBuffer  = ByteBuffer.allocate(2048);
	private Scanner scanner = new Scanner(System.in);
	private NioServer(int port) {
		this.initServer(port);
	}
	
	/**
	 * 初始化服务器端监听
	 *@author QingX
	 *Date 2018年10月6日 上午9:44:32
	 *
	 */
	private void initServer(int port) {
		try {
			ServerSocketChannel serverChannel = ServerSocketChannel.open();
			serverChannel.configureBlocking(false);
			serverChannel.bind(new InetSocketAddress(port));
			this.selector = Selector.open();
			serverChannel.register(selector,	SelectionKey.OP_ACCEPT);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 *@author QingX
	 *Date 2018年10月6日 上午9:44:49
	 * @throws IOException 
	 */
	public void listen() throws IOException {
		while(true) {
			selector.select();
			Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
			while(iterator.hasNext()) {
				SelectionKey key = iterator.next();
				iterator.remove();
				if(key.isAcceptable()) {
					ServerSocketChannel serverChannel = (ServerSocketChannel)key.channel();
					SocketChannel socketChannel = serverChannel.accept();
					socketChannel.configureBlocking(false);
					socketChannel.register(selector, SelectionKey.OP_READ);
				}else if(key.isReadable()) {
					SocketChannel socketChannel =(SocketChannel)key.channel();
					readBuffer.clear();
					writeBuffer.clear();
					socketChannel.read(readBuffer);
					readBuffer.flip();
					byte[] bytes = new byte[readBuffer.limit()];
					readBuffer.get(bytes);
					String msg = new String(bytes);
					System.out.println("客户端："+msg.trim());
					System.out.println("请输入回答：");
					String answer = scanner.next();
					writeBuffer.put(answer.getBytes());
					writeBuffer.flip();
					socketChannel.write(writeBuffer);
				}
			}
		}
	}
	
	public static void main(String[] args) {
		try {
			NioServer server = new NioServer(8888);
			server.listen();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
