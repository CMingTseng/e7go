package org.mobicents.rtsp;

import org.jboss.netty.handler.codec.http.HttpResponse;

/**
 * Created by Neo on 2015/12/14.
 */
public interface RtspResponse extends HttpResponse {

    public String debug();
}
