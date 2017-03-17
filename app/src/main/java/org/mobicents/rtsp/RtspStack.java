package org.mobicents.rtsp;

/**
 * Created by Neo on 2015/12/14.
 */
public interface RtspStack {

    public int getPort();

    public String getAddress();

    public void start();

    public void stop();

    public void setRtspListener(RtspListener listener);

    public void sendRquest(RtspRequest rtspRequest);

}
