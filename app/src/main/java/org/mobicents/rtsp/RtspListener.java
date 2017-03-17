package org.mobicents.rtsp;

import org.jboss.netty.channel.Channel;

/**
 * Created by Neo on 2015/12/14.
 */
public interface RtspListener {
    public void onRtspRequest(RtspRequest request, Channel chanel);

    public void onRtspResponse(RtspResponse response);
}
