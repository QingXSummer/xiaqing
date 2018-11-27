package com.java.socket.nio;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 这是一个服务类
 *
 * @author QingX
 */
public class NioServer {

    public static Logger logger = Logger.getLogger(NioServer.class);

    private Selector selector;
    private ByteBuffer readBuffer = ByteBuffer.allocate(5);
    private ByteBuffer writeBuffer = ByteBuffer.allocate(5);
//    private static Scanner scanner = new Scanner(System.in);
    private Charset charset = Charset.forName("UTF8");

    private static final String passMsg = "name-pass";

    private NioServer(int port) {
        this.initServer(port);
    }


    /**
     *
     */
    private Map <SocketChannel, String> clientSoc = new HashMap <>(16);



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
    private void listen() {
        while (true) {
            try {
                selector.select();
                Iterator <SelectionKey> iterator = selector.selectedKeys().iterator();
                logger.debug("keys-size:" + selector.selectedKeys().size());
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    iterator.remove();
                    handKey(key);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }


    private void handKey(SelectionKey key) {
        try {
            if (key.isAcceptable()) {
                ServerSocketChannel serverChannel = (ServerSocketChannel) key.channel();
                SocketChannel socketChannel = serverChannel.accept();
                socketChannel.configureBlocking(false);
                int inter = SelectionKey.OP_READ;
                socketChannel.register(selector, inter);
                clientSoc.put(socketChannel, null);
            }
            if (key.isReadable()) {
                logger.debug("服务端接收到客户端信息");
                SocketChannel socketChannel = (SocketChannel) key.channel();
                String name = clientSoc.get(socketChannel);
                //如果名称为空则说明未取名称
                if(name  ==null){

                }
                readBuffer.clear();
                socketChannel.read(readBuffer);
                readBuffer.flip();
                byte[] bytes = new byte[readBuffer.limit()];
                readBuffer.get(bytes);
                String msg = new String(bytes);
                System.out.println("客户端：" + msg);
                socketChannel.register(selector, SelectionKey.OP_READ);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    /**
     * 通过channel获取buffer
     * @param channel
     * @return
     */
    private String readMsg(SocketChannel channel){
        try {
            ByteBuffer readBuff = ByteBuffer.allocate(5);
            channel.read(readBuff);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public void sendMsg(SocketChannel socketChannel) {
//        try {
//            writeBuffer.clear();
//            writeBuffer.put(msg.getBytes());
//            writeBuffer.flip();
//            SocketChannel socketChannel = clientSoc.get(SERVER);
//            socketChannel.write(writeBuffer);
//        } catch (ClosedChannelException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    public static void main(String[] args) {
            final NioServer server = new NioServer(8888);
            server.listen();
    }

}
