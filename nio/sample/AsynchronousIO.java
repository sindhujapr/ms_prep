package nio.sample;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.DatagramChannel;
import java.nio.channels.Pipe;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/*
 * http://jfarcand.wordpress.com/2006/07/19/httpweblogs-java-netblog20060719tricks-and-tips-nio-part-iv-meet-selectors/
 * 
 * http://tutorials.jenkov.com/java-nio/nio-vs-io.html
 * 
 * http://tutorials.jenkov.com/java-nio/overview.html
 * 
 * http://tutorials.jenkov.com/java-nio/selectors.html
 * 
 * http://tutorials.jenkov.com/java-nio/socket-channel.html
 * 
 * If you need to manage thousands of open connections simultaneously, 
 * which each only send a little data, for instance a chat server, 
 * implementing the server in NIO is probably an advantage. 
 * Similarly, if you need to keep a lot of open connections to other computers, 
 * e.g. in a P2P network, using a single thread to manage all of your 
 * outbound connections might be an advantage. 
 * 
 * If you have fewer connections with very high bandwidth, 
 * sending a lot of data at a time, 
 * perhaps a classic IO server implementation might be the best fit.
 */
public class AsynchronousIO {
    public static void main(String[] args) throws Exception {
        new AsynchronousIO().asyncIO();
    }

    public void createServerSocketChannel() throws Exception {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(9999));

        while (true) {
            SocketChannel socketChannel = serverSocketChannel.accept();
            // do something with socketChannel...
        }
    }

    /*
     * http://tutorials.jenkov.com/java-nio/datagram-channel.html
     */
    public void createDatagramChannel() throws Exception {
        DatagramChannel channel = DatagramChannel.open();
        channel.socket().bind(new InetSocketAddress(9999));

        ByteBuffer buf = ByteBuffer.allocate(48);
        buf.clear();

        channel.receive(buf);

        String newData = "New String to write to file..." + System.currentTimeMillis();

        ByteBuffer buf1 = ByteBuffer.allocate(48);
        buf1.clear();
        buf1.put(newData.getBytes());
        buf1.flip();

        int bytesSent = channel.send(buf1, new InetSocketAddress("jenkov.com", 80));
    }

    public void createPipe() throws Exception {
        Pipe pipe = Pipe.open();
        Pipe.SinkChannel sinkChannel = pipe.sink();
        
        String newData = "New String to write to file..." + System.currentTimeMillis();

        ByteBuffer buf = ByteBuffer.allocate(48);
        buf.clear();
        buf.put(newData.getBytes());

        buf.flip();

        while(buf.hasRemaining())
            sinkChannel.write(buf);

        Pipe.SourceChannel sourceChannel = pipe.source();
        ByteBuffer buf1 = ByteBuffer.allocate(48);

        int bytesRead = sourceChannel.read(buf);
    }
    
    public void asyncIO() throws IOException, ClosedChannelException {
        SocketChannel socketChannel = SocketChannel.open();
        // !!!
        socketChannel.configureBlocking(false);
        socketChannel.connect(new InetSocketAddress("http://www.jenkov.com", 80));

        Selector selector = Selector.open();
        socketChannel.register(selector, SelectionKey.OP_READ | SelectionKey.OP_ACCEPT
                | SelectionKey.OP_WRITE);

        while (true) {
            // this is a blocking operation
            int readyChannels = selector.select();
            // We can sleep awhile or just spin around
            if (readyChannels == 0)
                continue;

            Set<SelectionKey> selectedKeys = selector.selectedKeys();
            // Should we remove these keys from the selector in an atomic operation?
            Iterator<SelectionKey> keyIterator = selectedKeys.iterator();

            while (keyIterator.hasNext()) {
                SelectionKey key = keyIterator.next();
                SocketChannel chl = (SocketChannel) key.channel();

                System.out.println("available");
                if (key.isAcceptable()) {
                    // a connection was accepted by a ServerSocketChannel.

                } else if (key.isConnectable()) {
                    // should we call finishConnect()?

                } else if (key.isReadable()) {
                    // a channel is ready for reading
                    ByteBuffer buf = ByteBuffer.allocate(48);
                    int bytesRead = chl.read(buf);
                } else if (key.isWritable()) {
                    // a channel is ready for writing
                    String newData = "New String to write to file..." + System.currentTimeMillis();

                    ByteBuffer buf = ByteBuffer.allocate(48);
                    buf.clear();
                    buf.put(newData.getBytes());
                    buf.flip();

                    while (buf.hasRemaining())
                        chl.write(buf);
                }

                keyIterator.remove();
            }
        }

        // socketChannel.close();
    }
}