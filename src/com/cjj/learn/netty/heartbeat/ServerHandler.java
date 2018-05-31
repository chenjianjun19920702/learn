package com.cjj.learn.netty.heartbeat;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhangfengzhe on 2017/2/4.
 */
public class ServerHandler extends ChannelHandlerAdapter {

	// KEY: ip:port VALUE: HeartInfo
	private Map<String,HeartInfo> heartInfoMap = new HashMap<String, HeartInfo>();

	private static final List<String> authList = new ArrayList<String>();

	static {
		// 从其他地方加载出来的IP列表
		authList.add("10.10.7.67:8765");
	}

	// 服务器会接收到2种消息 一个是客户端初始化时发送过来的认证信息 第二个是心跳信息
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		if (msg instanceof String) {
			if (authList.contains(msg)) { // 验证通过
				ctx.writeAndFlush("OK");
			} else {
				ctx.writeAndFlush("不在认证列表中...");
			}
		} else if (msg instanceof HeartInfo) {
			System.out.println((HeartInfo)msg);
			ctx.writeAndFlush("心跳接收成功!");

			HeartInfo heartInfo = (HeartInfo)msg;
			heartInfoMap.put(heartInfo.getIp() + ":" + heartInfo.getPort(),heartInfo);
		}
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}
}
