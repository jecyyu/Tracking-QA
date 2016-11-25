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
	public MadhouseClient(String name) {

		super(name);
	}
	
	public String strDecode;
	
	
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
