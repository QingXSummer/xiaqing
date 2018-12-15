package com.java.socket.nio;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

public class NioClient {


    Logger logger = Logger.getLogger(NioClient.class);

    Selector selector;

    /**
     * 是否通过服务端验证
     */
    volatile boolean pass = false;

    Scanner scanner = new Scanner(System.in);

    private SocketChannel clientChanel;

    Charset charset = Charset.forName("utf8");

    private static final String OK = "OK";

    private static final String ERROR = "ERROR";

    private String name = null;


    public NioClient(String ip, int port) {
        this.initClient(ip, port);
    }

    private void initClient(String ip, int port) {
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

    public void connect() {
        while (true) {
            try {
                selector.select();
                Iterator <SelectionKey> keys = selector.selectedKeys().iterator();
                while (keys.hasNext()) {
                    SelectionKey key = keys.next();
                    keys.remove();
                    if (key.isConnectable()) {
                        SocketChannel socketChannel = (SocketChannel) key.channel();
                        // 如果正在连接，则完成连接
                        if (socketChannel.isConnectionPending()) {
                            socketChannel.finishConnect();
                        }
                        int inter = SelectionKey.OP_READ;
                        socketChannel.register(selector, inter);
                        clientChanel = socketChannel;
                        System.out.println("成功连接服务器");
                    }
                    if (key.isReadable()) {
                        handReadKey(key);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @param readKey 读的key
     */
    void handReadKey(SelectionKey readKey) {
        SocketChannel channel = null;
        try {
            channel = (SocketChannel) readKey.channel();
            String msg = readMsg(channel);
            if (OK.equals(msg)) {
                System.out.println("您现在可以与其它人聊天了！");
            } else if (ERROR.equals(msg)) {
                System.out.println("与服务器连接异常");
            } else {
                System.out.println(msg);
            }
        } catch (IOException e) {
            e.printStackTrace();
            if (channel != null) {
                try {
                    channel.close();
                } catch (IOException e1) {
                    logger.debug("客户端退出");
                    e1.printStackTrace();
                }
            }
        }
    }

    private String readMsg(SocketChannel socketChannel) throws IOException {
        ByteBuf byteBuf = new ByteBuf();
        ByteBuffer buffer = ByteBuffer.allocate(5);
        while (socketChannel.read(buffer) > 0) {
            buffer.flip();
            int len = buffer.remaining();
            byteBuf.write(buffer.array(), 0, len);
            buffer.clear();
        }
        return byteBuf.toString();
    }

    public void sendMsg(String msg) {
        if (clientChanel != null) {
            try {
                clientChanel.write(ByteBuffer.wrap(msg.getBytes()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean checkName(String name) {
        return false;
    }


    public static void main(String[] args) throws IOException {

        final NioClient client = new NioClient("127.0.0.1", 8888);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    Scanner scanner = client.scanner;
                    String msg = scanner.nextLine();
                    client.sendMsg(msg);
                }
            }
        });
        thread.start();
        client.connect();
    }
}