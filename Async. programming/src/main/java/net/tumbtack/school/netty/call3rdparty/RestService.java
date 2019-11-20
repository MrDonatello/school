package net.tumbtack.school.netty.call3rdparty;


import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpResponseDecoder;
import io.netty.util.CharsetUtil;

import java.net.InetSocketAddress;

import static io.netty.handler.codec.http.HttpHeaders.Names.CONTENT_LENGTH;
import static io.netty.handler.codec.http.HttpHeaders.Names.CONTENT_TYPE;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

public class RestService {



    // // p.addLast(new HttpRequestEncoder());????
    ///api/v7/convert?q=USD_PHP&compact=ultra&apiKey=0b5529ecdd9423c5689b
    //free.currconv.com
    private final static String REQUEST = "GET /USD_PHP HTTP/1.1\r\n" +
            "Host: lo\r\n" +
            "Accept: */*\r\n" +
            "Connection: close\r\n\r\n";



    public void process(ChannelHandlerContext ctx) throws Exception {
        Channel inboundChannel = ctx.channel();

        Bootstrap b = new Bootstrap();
        b.group(inboundChannel.eventLoop())
                .channel(NioSocketChannel.class)
                .remoteAddress(new InetSocketAddress("free.currconv.com", 80))
                .handler(new ChannelInitializer<Channel>() {
                    @Override
                    public void initChannel(Channel ch) {
                        ChannelPipeline p = ch.pipeline();
                        p.addLast(new HttpResponseDecoder());
                        p.addLast(new HttpObjectAggregator(100 * 1024));
                        p.addLast(new ClientHandler(ctx));
                    }
                });
        b.connect();
    }


    private static class ClientHandler extends SimpleChannelInboundHandler<FullHttpResponse> {

        private ChannelHandlerContext serverctx;

        public ClientHandler(ChannelHandlerContext serverctx) {
            this.serverctx = serverctx;
        }

        @Override
        public void channelActive(ChannelHandlerContext ctx) {
            System.out.println(REQUEST);
            ctx.writeAndFlush(Unpooled.copiedBuffer(REQUEST, CharsetUtil.UTF_8));
        }

        @Override
        public void channelRead0(ChannelHandlerContext ctx, FullHttpResponse in) {
            String result = in.content().toString(CharsetUtil.UTF_8);

            FullHttpResponse response = new DefaultFullHttpResponse(
                    HTTP_1_1,
                    OK,
                    Unpooled.copiedBuffer(result, CharsetUtil.UTF_8));
            response.headers().set(CONTENT_TYPE, "application/json; charset=UTF-8");
            response.headers().set(CONTENT_LENGTH, response.content().readableBytes());

            serverctx.writeAndFlush(response)
                    .addListener(ChannelFutureListener.CLOSE);

        }

        @Override
        public void exceptionCaught(
                ChannelHandlerContext ctx, Throwable cause) {
            cause.printStackTrace();
            ctx.close();
        }
    }




}
