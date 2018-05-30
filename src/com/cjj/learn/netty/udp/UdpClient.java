package com.cjj.learn.netty.udp;

import java.net.*;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;
import io.netty.util.CharsetUtil;

public class UdpClient {

	private int scanPort;

	public UdpClient(int scanPort) {
		this.scanPort = scanPort;
	}

	public void sendPackage() {
		EventLoopGroup group = new NioEventLoopGroup();
		try {
			Bootstrap b = new Bootstrap();
			b.group(group)
			.channel(NioDatagramChannel.class)
			.option(ChannelOption.SO_BROADCAST, true)
			.handler(new UdpClientHandler());

			Channel ch = b.bind(0).sync().channel();
			
			/*ch.writeAndFlush(new DatagramPacket(
                    Unpooled.copiedBuffer("Searh:", CharsetUtil.UTF_8),
                    new InetSocketAddress("255.255.255.255", scanPort))).sync();*/

			ch.writeAndFlush(new DatagramPacket("Searh:".getBytes(), "Searh:".length(), 
					new InetSocketAddress("255.255.255.255", scanPort))).sync();
			
			// QuoteOfTheMomentClientHandler will close the DatagramChannel when a
			// response is received.  If the channel is not closed within 5 seconds,
			// print an error message and quit.
			if (!ch.closeFuture().await(5000)) {
				System.err.println("Search request timed out.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			group.shutdownGracefully();
		}
	}

	public static void main(String args[]){
		int port = 9956;
        new UdpClient(port).sendPackage();
	}
}
