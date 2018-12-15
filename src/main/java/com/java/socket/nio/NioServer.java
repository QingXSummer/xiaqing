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
import java.util.Set;

/**
 * 这是一个服务类
 *
 * @author QingX
 */
public class NioServer {

    public static Logger logger = Logger.getLogger(NioServer.class);

    private Selector selector;

    private Charset charset = Charset.forName("UTF8");

    private NioServer(int port) {
        this.initServer(port);
    }

    private static final int END_FLAG = -1;

    private static final String OK = "OK";

    private static final String ERROR = "ERROR";


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
                if (!clientSoc.containsKey(socketChannel)) {
                    System.out.println("该客户端不存在");
                }
                String name = clientSoc.get(socketChannel);
                //如果名称为空则说明未取名称
                String clientMsg = readMsg(socketChannel);
                if (name == null) {
                    clientSoc.put(socketChannel, clientMsg);
                    socketChannel.write(ByteBuffer.wrap(OK.getBytes()));
                } else {
                    sendMsg(clientMsg, socketChannel);
                }
//                readBuffer.clear();
//                socketChannel.read(readBuffer);
//                readBuffer.flip();
//                byte[] bytes = new byte[readBuffer.limit()];
//                readBuffer.get(bytes);
//                String msg = new String(bytes);
//                System.out.println("客户端：" + msg);
//                socketChannel.register(selector, SelectionKey.OP_READ);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    /**
     * 通过channel获取buffer
     *
     * @param channel
     * @return
     */
    private String readMsg(SocketChannel channel) {
        try {
            ByteBuffer buffer = ByteBuffer.allocate(5);
            int count = END_FLAG;
            ByteBuf buf = new ByteBuf();
            while ((count = channel.read(buffer)) > 0) {
                buffer.flip();
                byte[] write = buffer.array();
                int length = buffer.remaining();
                buf.write(write, 0, length);
                buffer.clear();
            }
            return buf.toString();
        } catch (IOException e) {
            try {
                System.out.println("通道关闭");
                 channel.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
        return null;
    }


    public void sendMsg(String msg, SocketChannel selfChanel) {
        Set <SocketChannel> keys = clientSoc.keySet();
        String name = clientSoc.get(selfChanel);
        msg = name + " : " + msg;
        for (SocketChannel socketChannel : keys) {
            if (socketChannel == selfChanel) {
                continue;
            }
            try {
                ByteBuffer write = ByteBuffer.wrap(msg.getBytes());
                socketChannel.write(write);
            } catch (IOException e) {
                e.printStackTrace();
                logger.error("给" + name + "发送消息异常");
            }
        }
    }

    public static void main(String[] args) {
        final NioServer server = new NioServer(8888);
        server.listen();
    }

}
