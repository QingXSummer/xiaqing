package com.java.socket.nio;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

/**
 * 这是一个服务类
 * @author QingX
 */
public class NioServer {

    private Logger logger = Logger.getLogger(NioServer.class);

    private Selector selector;
    private ByteBuffer readBuffer = ByteBuffer.allocate(2048);
    private ByteBuffer writeBuffer = ByteBuffer.allocate(2048);
    private static Scanner scanner = new Scanner(System.in);

    private NioServer(int port) {
        this.initServer(port);
    }


    private Map <String, SelectionKey> keys = new HashMap <>(16);


    private static String msg = null;

    public static final String SERVER = "SERVER";

    /**
     * 初始化服务器端监听
     *
     * @author QingX
     * Date 2018年10月6日 上午9:44:32
     */
    private void initServer(int port) {
        try {
            ServerSocketChannel serverChannel = ServerSocketChannel.open();
            serverChannel.configureBlocking(false);
            serverChannel.bind(new InetSocketAddress(port));
            this.selector = Selector.open();
            serverChannel.register(selector, SelectionKey.OP_ACCEPT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @author QingX
     * Date 2018年10月6日 上午9:44:49
     */
    private void listen() throws IOException {
        while (true) {
            selector.select();
            Iterator <SelectionKey> iterator = selector.selectedKeys().iterator();
            logger.debug("keys-size:" + selector.selectedKeys().size());
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                iterator.remove();
                if (key.isAcceptable()) {
                    ServerSocketChannel serverChannel = (ServerSocketChannel) key.channel();
                    SocketChannel socketChannel = serverChannel.accept();
                    socketChannel.configureBlocking(false);
                    int inter = SelectionKey.OP_READ | SelectionKey.OP_WRITE;
                    socketChannel.register(selector, inter);
                }
                if (key.isReadable()) {
                    logger.debug("服务端接收到客户端信息");
                    SocketChannel socketChannel = (SocketChannel) key.channel();
                    readBuffer.clear();
                    socketChannel.read(readBuffer);
                    readBuffer.flip();
                    byte[] bytes = new byte[readBuffer.limit()];
                    readBuffer.get(bytes);
                    String msg = new String(bytes);
                    System.out.println("客户端：" + msg);
                }
                if (key.isWritable()) {
                    SocketChannel socketChannel = (SocketChannel) key.channel();
                    writeBuffer.clear();
                    String result = scanner.nextLine();
                    if (msg != null && !"".equals(msg)) {
                        writeBuffer.put(result.getBytes());
                        writeBuffer.flip();
                        socketChannel.write(writeBuffer);
                        logger.debug("服务端发送完成信息");
                    }

                }
            }
        }
    }


    public void sendMsg(String msg) {

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
