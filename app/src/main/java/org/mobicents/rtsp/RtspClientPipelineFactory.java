package org.mobicents.rtsp;

import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;

import static org.jboss.netty.channel.Channels.pipeline;

/**
 * Created by Neo on 2015/12/14.
 */
public class RtspClientPipelineFactory implements ChannelPipelineFactory {

    private final RtspClientStackImpl rtspClientStackImpl;

    public RtspClientPipelineFactory(RtspClientStackImpl rtspClientStackImpl) {
        this.rtspClientStackImpl = rtspClientStackImpl;
    }

    public ChannelPipeline getPipeline() throws Exception {
        // Create a default pipeline implementation.
        ChannelPipeline pipeline = pipeline();
        pipeline.addLast("decoder", new RtspResponseDecoder());

        pipeline.addLast("encoder", new RtspRequestEncoder());
        pipeline.addLast("handler", new RtspResponseHandler(this.rtspClientStackImpl));
        return pipeline;
    }

}
