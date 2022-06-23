package com.ping.common.utils;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {
	
//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String app_id = "2016091200494034";
	
	// 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCAhWALVa7HybgfIbonYxz19OgRzELPA11wyCfdZoFN7j9d06VhBtDr1L0xqLeAKItFDXqHq6uyWlLGOhDKyu+GDvFJvUEM4hJbKPQkCKQao5MbQ7/ztLTt7YH67vFJKNMB1uaPVcQ9WoKka/0921dCE5ls4E9ODVDFxrO/dmAI0Ti4z+PtogeqV3P+7ECqB50lYuGcw0wbD9WEgMk1pmF1FPu7YHd/fNPYrPdvqEZ1JMGeZw3PmwuxfHqcVJfm7MlJiAUIe20094DiS0sRkYxIclgSxuy2NVvhkQIlT3Kp2IqLCGmJbzPFDR5LPJx7sLPJut+jVr9ka4HbUZMTzk4RAgMBAAECggEAVbgfkg/I7koVevYk0eZiR43cRxqkqKwG45UsOZgHwDGao/rov+kq6VBQC9IK6figwyK1Sc3vS5rAwKOz1X46+X/34QXQYEZcld6fz3TP91CIalPqsCIj1tllqzc5SWFoMz7hMlKy+3Tl08KO+nXUIicuWpbVbiqlZ/QaqSXL8bScAYNovy1YoOBmVwl08isU9rFLRZXzhWB1JLrgUbJP1Q/d7Q0DvjXqnsHBTqd+pYmNIw0pRU7Rim9dnlSvcvNP6y5+DCfKPjTj5T2HJPXX/hbvufiJ4XbO7kvhO4XeTFu6YHraKv4MPirpWS61rEc+lGCzqwkW3PNOYCQGsbQC3QKBgQDZ9HpgRRSGo8tHBX8NOdy2yeomFiTg//wa2gGUTQ3nUNpyKiDICGQJdqcqy7ZkMdLkiC0hXD6KjNjaSJio5LcUBI33bhfI97uftEX6iG9jm0iFq9kUTbIuowDQIHd3aw+uvnz62H51cV8uzrFNd0/nN7zDoD/seQPxucoeo+KFPwKBgQCW9HSojsbveY3ucLjK9S2C2Spe6lU0KyFZQ1H4OXdlSib4aqpfEFaLR4fki3uYZ5I/Z5vHzwXzLqwSPZ4c5EohiFZpQX9500rNP7MSwcLuF746/xtRNp2WgV2FQ1Ji7hdu1bb/B3ocG67gGbx+X8EJUVJkZtvNGomxk1/J6STIrwKBgF3jIYAs4c2p6xv58yP4Y+pdij7D1TkTLD9I39tJYg7d1mvNV/ezHZyx/wrBXB1HFSiavI4dVVeLmrJVCWXfuDmoSJtoUlig1rLSDj8A3LLUpmXmhAlp+rJ6RxRgl/XaKZRFvEK2VaszniPkp53OIp5gdTDz6/Vqn9dWCAow4JgzAoGALaqSSBO5VuTc7Q8ouC1nsTflO4WEdR2/HvPgrkkkN34xP+F03QFiTQw8rgUMRNTAR3IejFmyoRizcWk2leLyEibejUUFPWOQM3tbvwbi44hHQi2+o9jz334PPqtZFxiT+jGpBInMhQhS5PSkM07gZOM3G6yKPggTM6czyS9As30CgYA159dHAIH6pmL+p2f9EIVKHQIyYWvo9H7CQZOG99hG4QG0xC/bX55QuTbhm8RBe1KMRrtB/AD1KwhIGkhGJABxy3scCcS6His4Zqdr3C5j57StsrfJhkcu1NdaGN7Zspb4bcsJYpYycJbJgmTMwohajor9JWuJ9iIVpYFs+rIt7Q==";
	
	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAx/iasc39GZt4mnMLPkPlyaeceMuqbkQkNvUFAmarR3704ma0nieQKvMspgA9TS0FDaHgX6N2erEe+FPnuouHcBUL5kCV+p+1YB871cNygrKIEfa9LvoJz4S4ARsOwqSA7gU7XxRSHbhmtuSmt8jAXfw7OOBgxZwW04+kyZK6iDaOsgb4MQBKKsn7BA5nT3lYgkvOBAtw7Wqx05Xemo8S4aQ5suhCtX7fACqQWzT03kQxb7l5JEnbpZ63L6qAewV1bfl/6z7QqoHrfsq0kDmva4q+Yujb1v5Uk/6L+QKrdvWl6r+wT51J4UecxtUeGmbm7zQNx+RncHgh6BQrpgEj1wIDAQAB";

	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://pb34m9.natappfree.cc/phoneshop/payCallBack";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://pb34m9.natappfree.cc/phoneshop/paysuccess";

	// 签名方式
	public static String sign_type = "RSA2";
	
	// 字符编码格式
	public static String charset = "utf-8";
	
	// 支付宝网关
	public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
	
	// 支付宝网关
	public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /** 
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

