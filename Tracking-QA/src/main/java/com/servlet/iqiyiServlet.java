package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.madhouse.dsp.client.IqiyiClient;
import com.domain.MadhouseParam;

/**
 * Servlet implementation class trackingServlet
 */
/**
 * @author liuyu
 *
 */
public class iqiyiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String IQIYI = "iqiyi";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public iqiyiServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name = IQIYI;
		String path = getServletContext().getRealPath("/");
		String configname = request.getParameter("configname");
		System.out.println(path);
		String message = new IqiyiClient(name,configname).post();
		String imp_url = message.split("<Impression>")[1].split("</Impression>")[0];
		imp_url = imp_url.replace(" ", "").replace("\\n<![CDATA[", "").replace("]]>\\n", "");
		String click_url = message.split("ClickThrough")[1];
		click_url = click_url.replace(" ", "").replace("type=\\\"0\\\">\\n<![CDATA[","").replace("]]>\\n</", "");
		String icon_url = message.split("<StaticResource>")[1].split("</StaticResource>")[0];
		icon_url = icon_url.replace(" ", "").replace("\\n<![CDATA[","").replace("]]>\\n", "");
		String crid = message.split("crid=")[1];
		crid = crid.replace(" ", "").split("]]>")[0];
		response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        String jsonStr = "{\"impression\":\""+imp_url+"\",\"click\":\""+click_url+"\",\"icon_url\":\""+icon_url+"\",\"crid\":\""+crid+"\"}}";
        jsonStr = jsonStr + ">>>>L<<<<" + message;
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
