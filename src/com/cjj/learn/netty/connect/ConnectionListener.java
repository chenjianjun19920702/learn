package com.cjj.learn.netty.connect;

import java.util.concurrent.TimeUnit;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.EventLoop;

public class ConnectionListener implements ChannelFutureListener {
	
//	private ClientConnection imConnection = new ClientConnection();
	
	private ClientConnection clientConnection;
	
	public ConnectionListener(ClientConnection clientConnection) {
		this.clientConnection = clientConnection;
	}
	
	@Override
	public void operationComplete(ChannelFuture channelFuture) throws Exception {
		if (!channelFuture.isSuccess()) {
			final EventLoop loop = channelFuture.channel().eventLoop();
			loop.schedule(new Runnable() {
				@Override
				public void run() {
					System.err.println("服务端链接不上，开始重连操作...");
					clientConnection.connect(ClientApp.HOST, ClientApp.PORT);
				}
			}, 1L, TimeUnit.SECONDS);
		} else {
			System.err.println("服务端链接成功...");
		}
	}
}
