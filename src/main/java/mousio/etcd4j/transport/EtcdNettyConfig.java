package mousio.etcd4j.transport;

import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Settings for the etcd Netty client
 */
public class EtcdNettyConfig {
  private static final Logger logger = LoggerFactory.getLogger(EtcdNettyConfig.class);

  private EventLoopGroup eventLoopGroup = new NioEventLoopGroup();

  private Class<? extends SocketChannel> socketChannelClass = NioSocketChannel.class;

  private int connectTimeout = 300;

  private int maxFrameSize = 1024 * 100;

  private String hostName;

  private String username = null;
  private String password = null;

  /**
   * Constructor
   */
  public EtcdNettyConfig() {
    String frameSize = System.getProperty("mousio.etcd4j.maxFrameSize");
    if (frameSize != null) {
      logger.warn("Setting mousio.etcd4j.maxFrameSize through system propery is deprecated. " +
          "Please use the EtcdNettyConfig class");
      maxFrameSize = Integer.parseInt(frameSize);
    }
  }

  /**
   * Get the connect timeout
   *
   * @return the connect timeout
   */
  public int getConnectTimeout() {
    return connectTimeout;
  }

  /**
   * Set the connect timeout
   *
   * @param connectTimeout to set
   * @return itself for chaining.
   */
  public EtcdNettyConfig setConnectTimeout(int connectTimeout) {
    this.connectTimeout = connectTimeout;
    return this;
  }

  /**
   * Set a custom event loop group. For use within existing netty architectures
   *
   * @param eventLoopGroup to set.
   * @return itself for chaining.
   */
  public EtcdNettyConfig setEventLoopGroup(EventLoopGroup eventLoopGroup) {
    this.eventLoopGroup = eventLoopGroup;
    return this;
  }

  /**
   * Get the current event loop group. If it was never set it will use one loop group
   * for al etcd clients
   *
   * @return Event loop group.
   */
  public EventLoopGroup getEventLoopGroup() {
    return eventLoopGroup;
  }

  /**
   * Get the max frame size
   *
   * @return max frame size
   */
  public int getMaxFrameSize() {
    return maxFrameSize;
  }

  /**
   * Set the max frame size
   *
   * @param maxFrameSize to set
   * @return itself for chaining.
   */
  public EtcdNettyConfig setMaxFrameSize(int maxFrameSize) {
    this.maxFrameSize = maxFrameSize;
    return this;
  }

  /**
   * Get Socket channel class
   *
   * @return Socket channel class. Default is NioSocketChannel.class
   */
  public Class<? extends SocketChannel> getSocketChannelClass() {
    return socketChannelClass;
  }

  /**
   * Set Socket channel class. Default is NioSocketChannel.class
   *
   * @param socketChannelClass to set
   * @return itself for chaining
   */
  public EtcdNettyConfig setSocketChannelClass(Class<? extends SocketChannel> socketChannelClass) {
    this.socketChannelClass = socketChannelClass;
    return this;
  }

  public boolean hasHostName() {
    return hostName != null && !hostName.trim().isEmpty();
  }

  /**
   * Get the local host name
   *
   * @return local host name
   */
  public String getHostName() {
    return hostName;
  }

  /**
   * Set the host name for the local machine.
   *
   * @param hostName name of local host
   * @return itself for chaining
   */
  public EtcdNettyConfig setHostName(String hostName) {
    this.hostName = hostName;
    return this;
  }

  public boolean hasCredentials() {
    return this.username != null && !this.username.trim().isEmpty()
        && this.password != null && !this.password.trim().isEmpty();
  }

  public EtcdNettyConfig setUsername(String username) {
    this.username = username;
    return this;
  }

  public String getUsername() {
    return this.username;
  }

  public EtcdNettyConfig setPassword(String password) {
    this.password = password;
    return this;
  }

  public String getPassword() {
    return this.password;
  }
}