package com.madhouse.dsp.client;
import com.google.protobuf.GeneratedMessage;
import protocol.madhouse.Madhouse;
import java.io.IOException;
import java.io.InputStream;
import com.domain.MadhouseParam;



/**
 * @author liuyu
 *
 */
public class MadhouseClient extends BaseClient {
	public MadhouseClient(String name,String pathname) {

		super(name,pathname);
	}
	
	public String strDecode;
	
	GeneratedMessage genBidRequest() {
        Madhouse.Request.Builder requestBuilder = Madhouse.Request.newBuilder();

        requestBuilder.setBid(getString("request.bid"));
        requestBuilder.setAdspaceid(getString("request.adspaceid"));

//        if (config.hasPath("request.site")) {
//            siteBuilder.setName(getString("request.site.name"));
//            siteBuilder.setPage(getString("request.site.page"));
//            siteBuilder.setRef(getString("request.site.ref"));
//            siteBuilder.setChannel(getString("request.site.channel"));
//            requestBuilder.setSite(siteBuilder);
//        }
        requestBuilder.setAdtype(getString("request.adtype"));
        requestBuilder.setPkgname(getString("request.pkgname"));
        requestBuilder.setAppname(getString("request.appname"));
        requestBuilder.setConn(getString("request.conn"));
        requestBuilder.setCarrier(getString("request.carrier"));
        requestBuilder.setOs(getString("request.os"));
        requestBuilder.setOsv(getString("request.osv"));
        requestBuilder.setWma(getString("request.wma"));
        requestBuilder.setIdfa(getString("request.idfa"));
        requestBuilder.setOid(getString("request.oid"));
        requestBuilder.setUid(getString("request.uid"));
        requestBuilder.setImei(getString("request.imei"));
        requestBuilder.setAid(getString("request.aid"));
        requestBuilder.setAaid(getString("request.aaid"));
        requestBuilder.setUa(getString("request.ua"));
        requestBuilder.setIp(getString("request.ip"));
        requestBuilder.setWidth(getString("request.width"));
        requestBuilder.setHeight(getString("request.height"));
        requestBuilder.setPid(getString("request.pid"));
        requestBuilder.setPcat(getString("request.pcat"));
        requestBuilder.setMedia(getString("request.media"));
        requestBuilder.setDensity(getString("request.density"));
        requestBuilder.setLon(getString("request.lon"));
        requestBuilder.setLat(getString("request.lat"));
        requestBuilder.setCell(getString("request.cell"));
        requestBuilder.setMcell(getString("request.mcell"));
        requestBuilder.setApitype(getString("request.apitype"));
        requestBuilder.setTs(getString("request.ts"));
        requestBuilder.setDebug(getString("request.debug"));
        requestBuilder.setDevice(getString("request.device"));

        return requestBuilder.build();
    }
	
	
	@Override
    GeneratedMessage genBidRequest(MadhouseParam madParam) {
        Madhouse.Request.Builder requestBuilder = Madhouse.Request.newBuilder();
        
        requestBuilder.setBid(madParam.getBid());
        requestBuilder.setAdspaceid(madParam.getAdspaceid());
        requestBuilder.setAdtype(madParam.getAdtype());
        requestBuilder.setPkgname(madParam.getPkgname());
        requestBuilder.setAppname(madParam.getAppname());
        requestBuilder.setConn(madParam.getConn());
        requestBuilder.setCarrier(madParam.getCarrier());
        requestBuilder.setOs(madParam.getOs());
        requestBuilder.setOsv(madParam.getOsv());
        requestBuilder.setWma(madParam.getWma());
        requestBuilder.setIdfa(madParam.getIdfa());
        requestBuilder.setOid(madParam.getOid());
        requestBuilder.setUid(madParam.getUid());
        requestBuilder.setImei(madParam.getImei());
        requestBuilder.setAid(madParam.getAid());
        requestBuilder.setAaid(madParam.getAaid());
        requestBuilder.setUa(madParam.getUa());
        requestBuilder.setIp(madParam.getIp());
        requestBuilder.setWidth(madParam.getWidth());
        requestBuilder.setHeight(madParam.getHeight());
        requestBuilder.setPid(madParam.getPid());
        requestBuilder.setPcat(madParam.getPcat());
        requestBuilder.setMedia(madParam.getMedia());
        requestBuilder.setDensity(madParam.getDensity());
        requestBuilder.setLon(madParam.getLon());
        requestBuilder.setLat(madParam.getLat());
        requestBuilder.setCell(madParam.getCell());
        requestBuilder.setMcell(madParam.getMcell());
        requestBuilder.setApitype(madParam.getApitype());
        requestBuilder.setTs(madParam.getTs());
        requestBuilder.setDebug(madParam.getDebug());
        requestBuilder.setDevice(madParam.getDevice());

        return requestBuilder.build();
    }
	
	
	
	
	
	@Override
    GeneratedMessage parseFrom(InputStream in) throws IOException {
    	 Madhouse.Response response = Madhouse.Response.parseFrom(in);
         return response;
    }

}
