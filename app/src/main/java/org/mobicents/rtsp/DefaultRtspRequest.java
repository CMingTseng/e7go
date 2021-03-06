package org.mobicents.rtsp;

import org.jboss.netty.handler.codec.http.DefaultHttpMessage;
import org.jboss.netty.handler.codec.http.HttpMethod;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Set;

/**
 * Created by Neo on 2015/12/14.
 */
public class DefaultRtspRequest extends DefaultHttpMessage implements RtspRequest {
    private final RtspMethod method;
    private final String uri;
    private final String host;
    private final int port;

    private final static String COLON = ":";
    private final static String SP = " ";
    private final static String CRLF = "\r\n";

    public DefaultRtspRequest(RtspVersion rtspVersion, RtspMethod method, String uri) throws URISyntaxException {
        super(rtspVersion);
        if (method == null) {
            throw new NullPointerException("method");
        }
        if (uri == null) {
            throw new NullPointerException("uri");
        }

        URI objUri = new URI(uri);
        String scheme = objUri.getScheme() == null ? "rtsp" : objUri.getScheme();
        host = objUri.getHost() == null ? "localhost" : objUri.getHost();
//        port = objUri.getPort() == -1 ? 5050 : objUri.getPort();
        port = objUri.getPort() == -1 ? 554 : objUri.getPort();

        if (!scheme.equalsIgnoreCase("rtsp")) {
            throw new UnsupportedOperationException("Only rtsp is supported");
        }

        this.method = method;
        this.uri = uri;
    }

    public HttpMethod getMethod() {
        return method;
    }

    @Override
    public void setMethod(HttpMethod method) {

    }

    public String getUri() {
        return uri;
    }

    @Override
    public void setUri(String uri) {

    }

    @Override
    public String toString() {
        return getMethod().toString() + ' ' + getUri() + ' '
                + getProtocolVersion().getText();
    }

    public String debug() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(this.toString());
        buffer.append(CRLF);
        Set<String> headers = this.getHeaderNames();

        for (String header : headers) {
            List<String> values = this.getHeaders(header);
            for (String value : values) {

                buffer.append(header);
                buffer.append(COLON);
                buffer.append(SP);
                buffer.append(value);
                buffer.append(CRLF);
            }
        }

        return buffer.toString();
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

}
