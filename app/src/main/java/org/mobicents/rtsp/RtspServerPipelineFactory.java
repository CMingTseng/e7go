package org.mobicents.rtsp;

import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;

import static org.jboss.netty.channel.Channels.pipeline;

/**
 * Created by Neo on 2015/12/14.
 */
public class RtspServerPipelineFactory implements ChannelPipelineFactory {

    private final RtspServerStackImpl rtspServerStackImpl;

    protected RtspServerPipelineFactory(RtspServerStackImpl rtspServerStackImpl) {
        this.rtspServerStackImpl = rtspServerStackImpl;
    }

    public ChannelPipeline getPipeline() throws Exception {
        // Create a default pipeline implementation.
        ChannelPipeline pipeline = pipeline();
        pipeline.addLast("decoder", new RtspRequestDecoder());
        pipeline.addLast("encoder", new RtspResponseEncoder());
        pipeline.addLast("handler", new RtspRequestHandler(this.rtspServerStackImpl));
        return pipeline;
    }
}
