package org.mobicents.rtsp;

import org.jboss.netty.handler.codec.http.HttpRequest;

/**
 * Created by Neo on 2015/12/14.
 */
public interface RtspRequest extends HttpRequest {
    public String getHost();

    public int getPort();

    public String debug();
}
