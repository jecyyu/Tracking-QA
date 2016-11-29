package com.madhouse.dsp.client;

/**
 * Created by sky-wind on 2016/1/15.
 */

import protocol.sohu.Sohu;

import java.io.IOException;
import java.io.InputStream;

import com.domain.MadhouseParam;
import com.google.protobuf.GeneratedMessage;

public class SohuClient extends BaseClient {

    public SohuClient(String name,String pathname) {
        super(name,pathname);
    }
    
    GeneratedMessage genBidRequest() { 
    	return null;
    	
    }


    public Sohu.Request genBidRequest(MadhouseParam madParam) {
        Sohu.Request.Builder requestBuilder =  Sohu.Request.newBuilder();
        Sohu.Request.User.Builder userbuilder =  Sohu.Request.User.newBuilder();
        Sohu.Request.Site.Builder sitebuilder =  Sohu.Request.Site.newBuilder();
        Sohu.Request.Device.Builder devicebuilder =  Sohu.Request.Device.newBuilder();
        Sohu.Request.Impression.Builder  impBuilder =  Sohu.Request.Impression.newBuilder();
        Sohu.Request.Impression.Video.Builder vediobuilder = Sohu.Request.Impression.Video.newBuilder();

        userbuilder.setSuid(getString("request.user.suid"));
        userbuilder.setVersion(getInt("request.user.version", 1));
        userbuilder.addAllSearchKeyWords(getStringList("request.user.searchKeyWords"));
        userbuilder.addAllCategory(getLongList("request.user.category"));

        sitebuilder.setName(getString("request.site.name"));
        String spage = getString("request.site.page");
        if (spage.trim().length() > 0) {
            sitebuilder.setPage(spage);
        }
        Long scate = getLong("request.site.category", 0L);
        if (scate > 0) {
            sitebuilder.setCategory(scate);
        }
        String sref = getString("request.site.ref");
        if (sref.trim().length() > 0) {
            sitebuilder.setRef(sref);
        }

        devicebuilder.setType(getString("request.device.type"));
        devicebuilder.setIp(getString("request.device.ip"));
        devicebuilder.setUa(getString("request.device.ua"));
        String carrier = getString("request.device.carrier");
        if (carrier.trim().length() > 0) {
            devicebuilder.setCarrier(carrier);
        }
        devicebuilder.setNetType(getString("request.device.nettype"));
        devicebuilder.setMobileType(getString("request.device.mobiletype"));

        vediobuilder.addAllMimes(getIntList("request.video.mimes"));
        vediobuilder.setDurationLimit(getInt("request.video.durationLimit", 0));
        vediobuilder.setProtocol(getInt("request.video.protocol", 1));
        vediobuilder.setWidth(getInt("request.video.width", 640));
        vediobuilder.setHeight(getInt("request.video.height", 360));
        vediobuilder.setPageurl(getString("video.pageurl"));
        vediobuilder.addAllCategory(getStringList("request.video.category"));
        String vtitle = getString("request.ideo.title");
        if (vtitle.trim().length() > 0) {
            vediobuilder.setTitle(vtitle);
        }
        String vexternal = getString("request.video.external");
        if (vexternal.trim().length() > 0) {
            vediobuilder.setExternal(vexternal);
        }

        impBuilder.setIdx(getInt("request.imp.idx", 0));
        impBuilder.setPid(getString("request.imp.pid"));
        impBuilder.setBidFloor(getInt("request.imp.bidfloor", 1200));
        impBuilder.setVideo(vediobuilder.build());
        impBuilder.setIsPreferredDeals(getBoolean("request.imp.isPreferredDeals", true));
        impBuilder.setCampaignId(getString("request.imp.campaignId"));
        impBuilder.setLineId(getString("request.imp.lineId"));
        impBuilder.addAllAcceptAdvertisingType(getStringList("request.imp.acceptAdvertisingType"));

        requestBuilder.setVersion(getInt("request.version", 1));
        requestBuilder.setBidid(getString("request.bidid"));
        requestBuilder.setIsTest(getInt("request.isTest", 1));
        requestBuilder.setSite(sitebuilder.build());
        requestBuilder.setDevice(devicebuilder.build());
        requestBuilder.setUser(userbuilder.build());
        requestBuilder.addImpression(impBuilder.build());
        requestBuilder.addAllExcludeAdCategory(getStringList("request.excludeAdCategory"));

        return requestBuilder.build();
    }

    public Sohu.Response parseFrom(InputStream in) throws IOException {
        return Sohu.Response.parseFrom(in);
    }
}