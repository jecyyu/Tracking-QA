package com.madhouse.dsp.client;

import com.domain.MadhouseParam;
import com.google.protobuf.GeneratedMessage;
import com.typesafe.config.Config;
import protocol.tencent.Tencent;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

/**
 * Created by zhujiajun
 * 16/3/30 19:40
 */
public class TencentClient extends BaseClient {

    public TencentClient(String name,String pathname) {
    	
        super(name,pathname);
    }
    public String strDecode;
    @Override
    GeneratedMessage genBidRequest() { 
    	return null;
    	
    }
    
    @Override
    GeneratedMessage genBidRequest(MadhouseParam madParam) {
        Tencent.Request.Builder requestBuilder = Tencent.Request.newBuilder();
        Tencent.Request.App.Builder appBuilder = Tencent.Request.App.newBuilder();
        Tencent.Request.User.Builder userBuilder = Tencent.Request.User.newBuilder();
        Tencent.Request.Site.Builder siteBuilder = Tencent.Request.Site.newBuilder();
        Tencent.Request.Device.Builder deviceBuilder = Tencent.Request.Device.newBuilder();
        Tencent.Request.Device.Geo.Builder deviceGeoBuilder = Tencent.Request.Device.Geo.newBuilder();

        requestBuilder.setId(getString("request.id"));
        requestBuilder.setAt(getInt("request.at",2));

        if (config.hasPath("request.site")) {
            siteBuilder.setName(getString("request.site.name"));
            siteBuilder.setPage(getString("request.site.page"));
            siteBuilder.setRef(getString("request.site.ref"));
            siteBuilder.setChannel(getString("request.site.channel"));
            requestBuilder.setSite(siteBuilder);
        }

        deviceBuilder.setIp(getString("request.device.ip"));
        deviceBuilder.setUa(getString("request.device.ua"));
        deviceBuilder.setIdfa(getString("request.device.idfa"));
        deviceBuilder.setIdfaEnc(getInt("request.device.idfa_enc",0));
        deviceBuilder.setOpenudid(getString("request.device.openudid"));
        deviceBuilder.setCarrier(getInt("request.device.carrier",0));
        deviceBuilder.setMake(getString("request.device.make"));
        deviceBuilder.setModel(getString("request.device.model"));
        deviceBuilder.setOs(getString("request.device.os"));
        deviceBuilder.setOsv(getString("request.device.Osv"));
        deviceBuilder.setJs(getInt("request.device.Js",0));
        deviceBuilder.setConnectiontype(getInt("request.device.connectiontype",0));
        deviceBuilder.setDevicetype(getInt("request.device.devicetype",1));
        deviceBuilder.setMac(getString("request.device.mac"));
        deviceBuilder.setImei(getString("request.device.imei"));
        deviceBuilder.setAndroidid(getString("request.device.androidid"));

        deviceGeoBuilder.setLatitude(Float.valueOf(config.getAnyRef("request.device.geo.latitude").toString()));
        deviceGeoBuilder.setLongitude(Float.valueOf(config.getAnyRef("request.device.geo.longitude").toString()));
        deviceBuilder.setGeo(deviceGeoBuilder);
        requestBuilder.setDevice(deviceBuilder);

        userBuilder.setId(getString("request.user.id"));
        userBuilder.setBuyerid(getString("request.user.buyerid"));
        userBuilder.setGender(getString("request.user.gender"));
        userBuilder.setAge(getInt("request.user.age"));
        requestBuilder.setUser(userBuilder);

        List<Tencent.Request.Impression> impressionList = new ArrayList<>();
        List<? extends Config> configList = config.getConfigList("request.impression");
        for (Config config : configList) {
            Tencent.Request.Impression.Builder impBuilder = Tencent.Request.Impression.newBuilder();

            if (config.hasPath("banner")) {
                Tencent.Request.Impression.Banner.Builder bannerVideoBuilder = Tencent.Request.Impression.Banner.newBuilder();
                bannerVideoBuilder.setWidth(config.getInt("banner.width"));
                bannerVideoBuilder.setHeight(config.getInt("banner.height"));
                bannerVideoBuilder.addAllMimes(config.getStringList("banner.mimes"));
                bannerVideoBuilder.setExtraStyle(config.getString("banner.extra_style"));
                bannerVideoBuilder.setVisibility(config.getInt("banner.visibility"));
                impBuilder.setBanner(bannerVideoBuilder);
            }

            if (config.hasPath("video")) {
                Tencent.Request.Impression.Video.Builder impVideoBuilder = Tencent.Request.Impression.Video.newBuilder();
                impVideoBuilder.addAllMimes(config.getStringList("video.mimes"));
                impVideoBuilder.setLinearity(config.getInt("video.linearity"));
                impVideoBuilder.setMinduration(config.getInt("video.minduration"));
                impVideoBuilder.setMaxduration(config.getInt("video.maxduration"));
                impVideoBuilder.setProtocol(config.getInt("video.protocol"));
                impVideoBuilder.setWidth(config.getInt("video.width"));
                impVideoBuilder.setHeight(config.getInt("video.height"));
                impBuilder.setVideo(impVideoBuilder);
            }

            impBuilder.setId(config.getString("id"));
            impBuilder.setTagid(config.getString("tagid"));
            impBuilder.setBidfloor(Float.valueOf(config.getAnyRef("bidfloor").toString()));
            impBuilder.setClientid(config.getString("clientid"));
            impBuilder.setTradecode(config.getString("tradecode"));
            impBuilder.setSnsLists(config.getString("sns_lists"));
            impBuilder.setDealid(config.getString("dealid"));
            impressionList.add(impBuilder.build());
        }
        requestBuilder.addAllImpression(impressionList);

        if (config.hasPath("request.app")) {
            appBuilder.setId(getString("request.app.id"));
            appBuilder.setName(getString("request.app.name"));
            appBuilder.setDomain(getString("request.app.domain"));
            appBuilder.addAllCat(getStringList("request.app.cat"));
            appBuilder.addAllSectioncat(getStringList("request.app.sectioncat"));
            requestBuilder.setApp(appBuilder);
        }
        return requestBuilder.build();
    }

    @Override
    GeneratedMessage parseFrom(InputStream in) throws IOException {
    	 Tencent.Response response = Tencent.Response.parseFrom(in);
    	 try
    	 {
    		 //if(response.getSeatbid(0)in.)
    		 int intgetBidCount=response.getSeatbid(0).getBidCount();
    		 
    		 if(intgetBidCount>=0)
    		 {
    			String ext = response.getSeatbid(0).getBid(0).getExt();

    		 	byte[] decode = Base64.getDecoder().decode(ext);
    		 	strDecode= "jmeter: \"" + new String(decode) + "\"";
    		 	//System.out.println(strDecode); 
    		 }
    	 }
    	 catch (Exception e)
    	 {
    		 
    	 }
         return response;
    	
         //return Tencent.Response.parseFrom(in);
    }


}
