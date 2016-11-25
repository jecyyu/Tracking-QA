package com.madhouse.dsp.client;

import static com.madhouse.dsp.client.Main.*;
import com.domain.MadhouseParam;
import com.alibaba.fastjson.JSON;
import com.google.protobuf.GeneratedMessage;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiang.sun on 2016/1/20.
 */
abstract class BaseClient {
    public Config config;
    private final String name;
    public ArrayList<String> al;
    public String baseUrl;
    public int connectTimeout;
    public int readTimeout;

    public BaseClient(String name) {
        this.name = name;
//        config = ConfigFactory.load().getConfig(name);
//        String filePath = "/services/data/dspclient/conf/" + name + ".conf";
//        String filePath = "/Users/liuyu/Documents/workspace/dspclient_4/src/main/resources/" + name + ".conf";
        ///Users/liuyu/Documents/workspace/dspclient_4/src/main/resources/iqiyi.conf
        //config = ConfigFactory.parseFile(new File(filePath)).getConfig(name);
    }

    abstract GeneratedMessage genBidRequest(MadhouseParam madparam);

    abstract GeneratedMessage parseFrom(InputStream in) throws IOException;

    public String post(MadhouseParam madParam) {
    	String result = "";
        try {        	
        	al=new ArrayList<String>();
            //System.out.println("senddata: " + config.getString("url"));        	
            long start = System.currentTimeMillis();  
            URL url;
            String str_url = "";
            str_url = madParam.getUrl();
            //baseUrl="114.80.90.115:9001";
            if(str_url != null && str_url !=""){  
            	
            	String Link= "http://" + str_url;
            	al.add("senddata_params: " + Link);
            	url = new URL(Link);
            }
            else
            {
            	al.add("senddata_conf: " + str_url);
            	url = new URL(str_url);                     	
            }        	
        	al.add("initialize: ");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setConnectTimeout(connectTimeout);
            connection.setReadTimeout(readTimeout);
            byte [] b;
            switch (name) {
                case SOHU:
                case IQIYI:
                case TENCENT:
//                    connection.setRequestProperty("content-type", "application/x-protobuf");
//                    b = genBidRequest().toByteArray();
                    //break;
                case LETV:
                case YOUKU:
                    connection.setRequestProperty("content-type", "application/json");
                    b = JSON.toJSONBytes(config.getAnyRef("request"));
                    break;
                case MADHOUSE:
                    connection.setRequestProperty("content-type", "application/x-protobuf");
                    b = genBidRequest(madParam).toByteArray();
                    break;
                default:
                    System.out.println("Something is wrong");
                    return "Something is wrong";
            }
            BufferedOutputStream out = new BufferedOutputStream(connection.getOutputStream());
            out.write(b);
            out.flush();
            int code = connection.getResponseCode();
            //System.out.println("ResponseCode is : " + code);
            if (code == 200) {
                if (name.equals(SOHU) || name.equals(IQIYI) || name.equals(TENCENT) || name.equals(MADHOUSE)) {
                    InputStream in = connection.getInputStream();
                    long end = System.currentTimeMillis();
                    //System.out.println("cost time is :" + (end - start) + " ms");
                    String str = parseFrom(in).toString();                
                    //System.out.println(str); 
                    al.set(1,"success cost time is :" + (end - start) + " ms " + " ResponseCode is : " + code + " " + str);
                    result = str;
                    
                } else if (name.equals(LETV) || name.equals(YOUKU)) {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    long end = System.currentTimeMillis();
                    String lines;
                    String details = null;
                    while ((lines = reader.readLine()) != null) {
                        lines = new String(lines.getBytes(), "utf-8");
                        details+=lines;
                        //System.out.println(lines);                        
                    }
                    //System.out.println("cost time is :" + (end - start) + " ms");
                    al.set(1,"success cost time is :" + (end - start) + " ms " + " ResponseCode is : " + code + " " + details); 
                    reader.close();
                }
            } else {
                System.out.println("Something is wrong");
            	//int le = connection.getErrorStream().available();
                //BufferedInputStream in = new BufferedInputStream(connection.getErrorStream());
                //byte[] Error= new byte[le];
                //in.read(Error);
                //al.set(1, "ErrorStream: " + new String(Error));
            }
            int contentLength = connection.getContentLength();
            InputStream is = connection.getInputStream();
            is.close();

            connection.disconnect();

        } catch (IOException e) {
        	al.set(1,"IOException: " + e.getMessage().toString() );
            e.printStackTrace();
        }
        return result;
    }


    public String getString(String path) {
        return config.hasPath(path) ? config.getString(path) : "";
    }

    public List<String> getStringList(String path) {
        return config.hasPath(path) ? config.getStringList(path) : new ArrayList<String>();
    }

    public int getInt(String path, int def) {
        int res = def;
        if (config.hasPath(path)) {
            try {
                res = config.getInt(path);
            } catch (Exception e) {

            }
        }
        return res;
    }

    public int getInt(String path) {
        return  getInt(path, 0);
    }

    public List<Integer> getIntList(String path) {
        return config.hasPath(path) ? config.getIntList(path) : new ArrayList<Integer>();
    }

    public long getLong(String path, long def) {
        long res = def;
        if (config.hasPath(path)) {
            try {
                res = config.getLong(path);
            } catch (Exception e) {

            }
        }
        return res;
    }

    public long getLong(String path) {
        return getLong(path, 0L);
    }

    public List<Long> getLongList(String path) {
        return config.hasPath(path) ? config.getLongList(path) : new ArrayList<Long>();
    }

    public boolean getBoolean(String path, boolean def) {
        return config.hasPath(path) ? config.getBoolean(path) : def;
    }
}
