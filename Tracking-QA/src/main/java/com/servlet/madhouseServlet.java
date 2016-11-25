package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.domain.MadhouseParam;
import com.madhouse.dsp.client.IqiyiClient;
import com.madhouse.dsp.client.LetvClient;
import com.madhouse.dsp.client.MadhouseClient;
import com.madhouse.dsp.client.SohuClient;
import com.madhouse.dsp.client.TencentClient;
import com.madhouse.dsp.client.YoukuClient;
import com.tracking.testTracking;

/**
 * Servlet implementation class trackingServlet
 */
/**
 * @author liuyu
 *
 */
public class madhouseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String LETV = "letv";
    public static final String SOHU = "sohu";
    public static final String IQIYI = "iqiyi";
    public static final String YOUKU = "youku";
    public static final String TENCENT  =  "tencent";
    public static final String MADHOUSE = "madhouse";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public madhouseServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		MadhouseParam madParam = new MadhouseParam();
		//String platform = request.getParameter("platform");
		
		madParam.setUrl(request.getParameter("url"));
		madParam.setAdspaceid(request.getParameter("adspaceid"));
		madParam.setAdtype(request.getParameter("adtype"));
		madParam.setBid(request.getParameter("bid"));
		madParam.setPkgname(request.getParameter("pkgname"));
		madParam.setAppname(request.getParameter("appname"));
		madParam.setConn(request.getParameter("conn"));
		madParam.setCarrier(request.getParameter("carrier"));
		madParam.setOs(request.getParameter("os"));
		madParam.setOsv(request.getParameter("osv"));
		madParam.setWma(request.getParameter("wma"));
		madParam.setIdfa(request.getParameter("idfa"));
		madParam.setOid(request.getParameter("oid"));
		madParam.setUid(request.getParameter("uid"));
		madParam.setImei(request.getParameter("imei"));
		madParam.setAid(request.getParameter("aid"));
		madParam.setAaid(request.getParameter("aaid"));
		madParam.setDevice(request.getParameter("device"));
		madParam.setUa(request.getParameter("ua"));
		madParam.setIp(request.getParameter("ip"));
		madParam.setWidth(request.getParameter("width"));
		madParam.setHeight(request.getParameter("height"));
		madParam.setPid(request.getParameter("pid"));
		madParam.setPcat(request.getParameter("pcat"));
		madParam.setMedia(request.getParameter("media"));
		madParam.setDensity(request.getParameter("density"));
		madParam.setLon(request.getParameter("lon"));
		madParam.setLat(request.getParameter("lat"));
		madParam.setCell(request.getParameter("cell"));
		madParam.setMcell(request.getParameter("mcell"));
		madParam.setApitype(request.getParameter("apitype"));
		madParam.setTs(request.getParameter("ts"));
		madParam.setDebug(request.getParameter("debug"));

        String message = new MadhouseClient("madhouse").post(madParam);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        String jsonStr = message;
        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.write(jsonStr);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
