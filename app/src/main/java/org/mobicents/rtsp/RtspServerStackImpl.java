package org.mobicents.rtsp;

import android.util.Log;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelFutureListener;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by Neo on 2015/12/14.
 */
public class RtspServerStackImpl implements RtspStack {
    private final String TAG = RtspServerStackImpl.class.getSimpleName();
    private final String address;
    private final int port;
    private final InetAddress inetAddress;
    private Channel channel = null;
    private ServerBootstrap bootstrap = null;

    private RtspListener listener = null;

    public RtspServerStackImpl(String address, int port)
            throws UnknownHostException {
        this.address = address;
        this.port = port;
        inetAddress = InetAddress.getByName(this.address);
    }

    public String getAddress() {
        return this.address;
    }

    public int getPort() {
        return this.port;
    }

    public void start() {

        InetSocketAddress bindAddress = new InetSocketAddress(this.inetAddress,
                this.port);

        bootstrap = new ServerBootstrap(
                new NioServerSocketChannelFactory(Executors.newCachedThreadPool(new RtspServerBossThreadFactory()), Executors.newCachedThreadPool(new RtspServerWorkerThreadFactory())));

        // Set up the event pipeline factory.
        bootstrap.setPipelineFactory(new RtspServerPipelineFactory(this));

        // Bind and start to accept incoming connections.
        channel = bootstrap.bind(bindAddress);
        Log.d(TAG, "Mobicents RTSP Server started and bound to " + bindAddress.toString());
    }

    public void stop() {
        ChannelFuture cf = channel.getCloseFuture();
        cf.addListener(new ServerChannelFutureListener());

        channel.close();
        cf.awaitUninterruptibly();
        bootstrap.getFactory().releaseExternalResources();

    }

    public void setRtspListener(RtspListener listener) {
        this.listener = listener;

    }

    protected void processRtspResponse(RtspResponse rtspResponse) {
        synchronized (this.listener) {
            listener.onRtspResponse(rtspResponse);
        }
    }

    protected void processRtspRequest(RtspRequest rtspRequest, Channel channel) {
        synchronized (this.listener) {
            listener.onRtspRequest(rtspRequest, channel);
        }
    }

    private class ServerChannelFutureListener implements ChannelFutureListener {

        public void operationComplete(ChannelFuture arg0) throws Exception {
            Log.d(TAG, "Mobicents RTSP Server Stop complete");
        }

    }

    public void sendRquest(RtspRequest rtspRequest) {
        throw new UnsupportedOperationException("Not Supported yet");
    }
}

class RtspServerBossThreadFactory implements ThreadFactory {

    public static final AtomicLong sequence = new AtomicLong(0);
    private ThreadGroup factoryThreadGroup = new ThreadGroup(
            "RtspServerBossThreadGroup[" + sequence.incrementAndGet() + "]");

    public Thread newThread(Runnable r) {
        Thread t = new Thread(this.factoryThreadGroup, r);
        t.setPriority(Thread.NORM_PRIORITY);
        return t;
    }
}

class RtspServerWorkerThreadFactory implements ThreadFactory {

    public static final AtomicLong sequence = new AtomicLong(0);
    private ThreadGroup factoryThreadGroup = new ThreadGroup(
            "RtspServerWorkerThreadGroup[" + sequence.incrementAndGet() + "]");

    public Thread newThread(Runnable r) {
        Thread t = new Thread(this.factoryThreadGroup, r);
        t.setPriority(Thread.NORM_PRIORITY);
        return t;
    }
}
