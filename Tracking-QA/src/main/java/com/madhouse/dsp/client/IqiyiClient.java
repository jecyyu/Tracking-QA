package com.madhouse.dsp.client;

import com.domain.MadhouseParam;
import com.typesafe.config.Config;
import protocol.iqiyi.IQiyiBidRequest.*;
import protocol.iqiyi.IQiyiBidResponse.BidResponse;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiang.sun on 2016/1/20.
 */
public class IqiyiClient extends BaseClient {

    public IqiyiClient(String name) {
        super(name);
    }

    public BidRequest genBidRequest(MadhouseParam madParam) {
        Content.Builder cntBuilder = Content.newBuilder();
        cntBuilder.setTitle(getString("request.site.content.title"));
        List<String> keywords = getStringList("request.site.content.keyword");
        cntBuilder.addAllKeyword(keywords);
        cntBuilder.setUrl(getString("request.site.content.url"));

        Site.Builder siteBuilder = Site.newBuilder();
        siteBuilder.setId(getInt("request.site.id", 1));
        siteBuilder.setContent(cntBuilder);

        Device.Builder deviceBuilder = Device.newBuilder();
        deviceBuilder.setUa(getString("request.device.ua"));
        deviceBuilder.setIp(getString("request.device.ip"));
        deviceBuilder.setPlatformId(getInt("request.device.platform_id", 1));

        List<? extends Config> impConfs = config.getConfigList("request.impression");
        List<Impression> impressions = new ArrayList<>();
        for (Config c : impConfs) {
            Video.Builder videoBuilder = Video.newBuilder();
            videoBuilder.setAdZoneId(c.getLong("video.ad_zone_id"));
            videoBuilder.setAdType(c.getInt("video.ad_type"));
            videoBuilder.setProtocol(c.getInt("video.protocol"));
            videoBuilder.setLinearity(c.getInt("video.linearity"));
            videoBuilder.setW(c.getInt("video.width"));
            videoBuilder.setH(c.getInt("video.height"));
            videoBuilder.setMinduration(c.getInt("video.minduration"));
            videoBuilder.setMaxduration(c.getInt("video.maxduration"));
            videoBuilder.setStartdelay(c.getInt("video.startdelay"));

            Impression.Builder impBuilder = Impression.newBuilder();
            impBuilder.setId(c.getString("id"));
            impBuilder.setCampaignId(c.getInt("campaign_id"));
            impBuilder.setBidfloor(c.getInt("bidfloor"));
            impBuilder.setVideo(videoBuilder);
            impressions.add(impBuilder.build());
        }

        return BidRequest.newBuilder()
            .setId(getString("request.id"))
            .setUser(User.newBuilder().setId(getString("request.user.id")))
            .setSite(siteBuilder)
            .setDevice(deviceBuilder)
            .addAllImp(impressions)
            .setIsTest(getBoolean("request.is_test", true))
            .build();
    }

    public BidResponse parseFrom(InputStream in) throws IOException {
        return BidResponse.parseFrom(in);
    }
}
