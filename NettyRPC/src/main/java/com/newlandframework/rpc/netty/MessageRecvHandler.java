/**
 * Copyright (C) 2016 Newland Group Holding Limited
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.newlandframework.rpc.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Map;
import java.util.concurrent.Callable;

import com.newlandframework.rpc.model.MessageRequest;
import com.newlandframework.rpc.model.MessageResponse;

/**
 * @author tangjie<https://github.com/tang-jie>
 * @filename:MessageRecvHandler.java
 * @description:MessageRecvHandler功能模块
 * @blogs http://www.cnblogs.com/jietang/
 * @since 2016/10/7
 */
public class MessageRecvHandler extends ChannelInboundHandlerAdapter {

    private final Map<String, Object> handlerMap;

    public MessageRecvHandler(Map<String, Object> handlerMap) {
        this.handlerMap = handlerMap;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        MessageRequest request = (MessageRequest) msg;
        MessageResponse response = new MessageResponse();
        RecvInitializeTaskFacade facade = new RecvInitializeTaskFacade(request, response, handlerMap);
        Callable<Boolean> recvTask = facade.getTask();
        MessageRecvExecutor.submit(recvTask, ctx, request, response);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}

