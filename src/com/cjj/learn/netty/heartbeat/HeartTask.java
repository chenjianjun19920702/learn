package com.cjj.learn.netty.heartbeat;

import io.netty.channel.ChannelHandlerContext;
import org.hyperic.sigar.CpuPerc;
import org.hyperic.sigar.Mem;
import org.hyperic.sigar.Sigar;

import java.util.Date;
 
/**
 * Created by zhangfengzhe on 2017/2/4.
 */
public class HeartTask implements Runnable {
 
    // 持有引用,方便读写操作
    private ChannelHandlerContext ctx;
 
    private HeartInfo heartInfo = new HeartInfo();
 
    public HeartTask(ChannelHandlerContext ctx, String ip, int port) {
 
        this.ctx = ctx;
 
        heartInfo.setIp(ip);
        heartInfo.setPort(port);
    }
 
    @Override
    public void run() {
        try {
            // 利用sigar获取 内存/CPU方面的信息 ; 利用CTX给服务器端发送消息
            Sigar sigar = new Sigar();
 
            // 内存使用信息memory
            Mem mem = sigar.getMem();
            heartInfo.getMemInfo().put("total",String.valueOf(mem.getTotal()));
            heartInfo.getMemInfo().put("used",String.valueOf(mem.getUsed()));
            heartInfo.getMemInfo().put("free",String.valueOf(mem.getFree()));
 
            // CPU使用信息
            CpuPerc cpuPerc = sigar.getCpuPerc();
            heartInfo.getCpuInfo().put("user",String.valueOf(cpuPerc.getUser()));
            heartInfo.getCpuInfo().put("sys",String.valueOf(cpuPerc.getSys()));
            heartInfo.getCpuInfo().put("wait",String.valueOf(cpuPerc.getWait()));
            heartInfo.getCpuInfo().put("idle",String.valueOf(cpuPerc.getIdle()));
 
            heartInfo.setLastTime(new Date());
        	
            this.ctx.writeAndFlush(heartInfo);
 
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
    	/*Sigar sigar = new Sigar();
    	try {
			Mem mem = sigar.getMem();
			CpuPerc cpuPerc = sigar.getCpuPerc();
			System.out.println(mem);
			System.out.println(cpuPerc);
		} catch (Exception e) {
			e.printStackTrace();
		}*/
    }
}
