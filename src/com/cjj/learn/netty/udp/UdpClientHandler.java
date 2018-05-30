package com.cjj.learn.netty.udp;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;
import io.netty.util.CharsetUtil;

public class UdpClientHandler extends SimpleChannelInboundHandler<DatagramPacket> {

	@Override
	protected void messageReceived(ChannelHandlerContext ctx, DatagramPacket packet) throws Exception {
		String body =  packet.content().toString(CharsetUtil.UTF_8);
        System.out.println("Search, body:" + body);
        // 这里接收到服务端发送的内容
		
	}

}
