package net.tumbtack.school.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpResponseDecoder;
import io.netty.util.CharsetUtil;

import java.net.InetSocketAddress;


public class RestClient {

    private final static String REQUEST ="GET /api/v7/convert? HTTP/1.1\r\n" +
            "User-Agent: Opera/9.80 (X11; Linux x86_64) Presto/2.12.388 Version/12.16\r\n" +
            "Host: free.currconv.com\r\n" +
            "Accept: */*\r\n" +
            "Connection: close\r\n\r\n";


    public static void main(String[] args) throws Exception {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .remoteAddress(new InetSocketAddress("free.currconv.com", 80))
                    .handler(new Init());

            ChannelFuture f = b.connect().sync();
            f.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully().sync();
        }


    }


    private static class Init extends ChannelInitializer {

        @Override
        protected void initChannel(Channel ch) throws Exception {

            ch.pipeline().addLast(new HttpResponseDecoder());
            ch.pipeline().addLast(new HttpObjectAggregator(100 * 1024));
            ch.pipeline().addLast(new ClientHandler());
        }
    }



    private static class ClientHandler extends SimpleChannelInboundHandler<FullHttpResponse> {
        @Override
        public void channelActive(ChannelHandlerContext ctx) {
            System.out.println(REQUEST);
            ctx.writeAndFlush(Unpooled.copiedBuffer(REQUEST, CharsetUtil.UTF_8));
        }

        @Override
        public void channelRead0(ChannelHandlerContext ctx, FullHttpResponse in) {
            System.out.println(in.content().toString(CharsetUtil.UTF_8));
        }

        @Override
        public void exceptionCaught(
                ChannelHandlerContext ctx, Throwable cause) {
            cause.printStackTrace();
            ctx.close();
        }
    }

}
