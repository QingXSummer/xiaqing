package com.java.socket.nio;


/**
 * 用于存储追加字节
 *
 * @author QingX
 * @date
 */
public class ByteBuf {


    private int position;

    private int len;

    private int factor;

    private byte[] buf;

    public ByteBuf() {
        this(16);
    }

    public ByteBuf(int length) {
        position = -1;
        len = length;
        factor = (int) (len * 0.75);
        this.buf = new byte[length];
    }

    /**
     * 向buf中写入需要添加的字节
     *
     * @param append
     */
    public void write(byte[] append, int start, int length) {
        if (length <= 0) {
            return;
        }
        while ((position + length + 1) > factor) {
            resize4two();
        }
        System.arraycopy(append, start, buf, position + 1, length);
        position = position + length;
    }

    /**
     * 将byte数组扩充两倍
     */
    private void resize4two() {
        int nLen = this.len * 2;
        byte[] newBuf = new byte[nLen];
        System.arraycopy(buf, 0, newBuf, 0, position + 1);
        this.len = nLen;
        buf = newBuf;
        factor = (int) (len * 0.75);
    }

    @Override
    public String toString() {
        if (position < 0) {
            return null;
        }
        return new String(buf, 0, position + 1);
    }

}
