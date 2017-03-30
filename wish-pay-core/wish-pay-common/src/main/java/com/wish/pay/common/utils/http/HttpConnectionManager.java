package com.wish.pay.common.utils.http;

import org.apache.http.Consts;
import org.apache.http.config.ConnectionConfig;
import org.apache.http.config.MessageConstraints;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

import javax.net.ssl.SSLContext;
import java.nio.charset.CodingErrorAction;

/**
 * 基于 httpclient 4.5版本的 httpClient 连接池工具类
 * Created by fqh on 2016/1/3
 */
public class HttpConnectionManager {

	private static PoolingHttpClientConnectionManager poolingConnManager=null;


	private static final CloseableHttpClient httpClient;
	private static final Integer DefaultMaxPerRoute=50;//每个路由默认链接数
	private static final Integer MaxTotal=500;//线程池最大链接数量

	static {

		Registry<ConnectionSocketFactory> socketFactoryRegistry = null;
		try {
			socketFactoryRegistry = RegistryBuilder
                    .<ConnectionSocketFactory> create()
                    .register("http", PlainConnectionSocketFactory.INSTANCE)
                    .register("https", SSLs.getInstance().getSSLCONNSF(SSLs.SSLProtocolVersion.SSLv3)).build();
		} catch (Exception e) {
			e.printStackTrace();
		}
		poolingConnManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
		poolingConnManager.setDefaultMaxPerRoute(DefaultMaxPerRoute);
		poolingConnManager.setMaxTotal(MaxTotal);

		//消息约束
		MessageConstraints messageConstraints = MessageConstraints.custom()
				.setMaxHeaderCount(200)
				.setMaxLineLength(2000)
				.build();
		//链接配置设置
		ConnectionConfig connectionConfig = ConnectionConfig.custom()
				.setMalformedInputAction(CodingErrorAction.IGNORE)
				.setUnmappableInputAction(CodingErrorAction.IGNORE)
				.setCharset(Consts.UTF_8)
				.setMessageConstraints(messageConstraints)
				.build();
		poolingConnManager.setDefaultConnectionConfig(connectionConfig);

		httpClient = HttpClients.custom().setConnectionManager(poolingConnManager).build();
		// Trust own CA and all self-signed certs
		/*SSLContext sslcontext = SSLContexts.custom()
				.loadKeyMaterial(keyStore, Configure.getCertPassword().toCharArray())
				.build();
		// Allow TLSv1 protocol only
		SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
				sslcontext,
				new String[]{"TLSv1"},
				null,
				SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);

		httpClient = HttpClients.custom()
				.setSSLSocketFactory(sslsf)
				.build();*/
	}

	public static CloseableHttpClient getHttpClient(){
		return httpClient;
	}




}
