package com.madhouse.dsp.client;

import com.domain.MadhouseParam;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigList;
import com.typesafe.config.ConfigValue;

import ads_serving.proto.IQiyiBidRequest.BidRequest;
import ads_serving.proto.IQiyiBidRequest.Content;
import ads_serving.proto.IQiyiBidRequest.Site;
import ads_serving.proto.IQiyiBidRequest.Device;
import ads_serving.proto.IQiyiBidRequest.Impression;
import ads_serving.proto.IQiyiBidRequest.Video;
import ads_serving.proto.IQiyiBidRequest.User;
import ads_serving.proto.IQiyiBidResponse.BidResponse;
import ads_serving.proto.IQiyiBidRequest.FloorPrice;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by xiang.sun on 2016/1/20.
 */
public class IqiyiClient extends BaseClient {

    public IqiyiClient(String name,String pathname) {
        super(name,pathname);
    }
    
    public BidRequest genBidRequest() {
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
		deviceBuilder.setModel("request.device.model");
		deviceBuilder.setOs("request.device.os");
		deviceBuilder.setOsVersion("request.device.osv");
		deviceBuilder.setAndroidId("request.device.android_id");

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
			
			ConfigList fpConfs = c.getList("floor_price");
			for (ConfigValue configValue : fpConfs) {
				int i =fpConfs.size() ;
				FloorPrice.Builder fp = FloorPrice.newBuilder() ;
				Object obj = configValue.unwrapped();
				HashMap hashfp = (HashMap)obj;
				long industry = Long.parseLong(hashfp.get("industry").toString());
				int price = (int)hashfp.get("price");
				fp.setIndustry(industry);
				fp.setPrice(price);
				impBuilder.addFloorPrice(fp);
				
			}
			impressions.add(impBuilder.build());
	
			
		}

		return BidRequest.newBuilder().setId(getString("request.id"))
				.setUser(User.newBuilder().setId(getString("request.user.id"))).setSite(siteBuilder)
				.setDevice(deviceBuilder).addAllImp(impressions).setIsTest(getBoolean("request.is_test", true)).build();
	}

    public BidRequest genBidRequest(MadhouseParam madParam) {
        return null;
    }

    public BidResponse parseFrom(InputStream in) throws IOException {
        return BidResponse.parseFrom(in);
    }
}
