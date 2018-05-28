Netty精粹之TCP粘包拆包问题

LineBasedFrameDecoder解码器
LineBasedFrameDecoder是回车换行解码器，如果用户发送的消息以回车换行符作为消息结束的标识，
则可以直接使用Netty的LineBasedFrameDecoder对消息进行解码，
只需要在初始化Netty服务端或者客户端时将LineBasedFrameDecoder正确的添加到ChannelPipeline中即可，
不需要自己重新实现一套换行解码器。

DelimiterBasedFrameDecoder解码器
DelimiterBasedFrameDecoder是分隔符解码器，用户可以指定消息结束的分隔符，
它可以自动完成以分隔符作为码流结束标识的消息的解码。
回车换行解码器实际上是一种特殊的DelimiterBasedFrameDecoder解码器。

FixedLengthFrameDecoder解码器
FixedLengthFrameDecoder是固定长度解码器，它能够按照指定的长度对消息进行自动解码，
开发者不需要考虑TCP的粘包/拆包等问题，非常实用。
对于定长消息，如果消息实际长度小于定长，则往往会进行补位操作，它在一定程度上导致了空间和资源的浪费。
但是它的优点也是非常明显的，编解码比较简单，因此在实际项目中仍然有一定的应用场景。

LengthFieldBasedFrameDecoder解码器
大多数的协议（私有或者公有），协议头中会携带长度字段，用于标识消息体或者整包消息的长度，
例如SMPP、HTTP协议等。由于基于长度解码需求的通用性，以及为了降低用户的协议开发难度，
Netty提供了LengthFieldBasedFrameDecoder，自动屏蔽TCP底层的拆包和粘包问题，
只需要传入正确的参数，即可轻松解决“读半包“问题。
