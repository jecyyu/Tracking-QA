package com.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class uploadServlet
 */
public class uploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public uploadServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 从request当中获取流信息

		InputStream fileSource = request.getInputStream();
		String tempFileName = "tmp_config";
		// tempFile指向临时文件
		File tempFile = new File(tempFileName);
		// if (tempFile.exists()) {
		// outputStram文件输出流指向这个临时文件
		FileOutputStream outputStream = new FileOutputStream(tempFile);
		byte b[] = new byte[2 * 1024];
		int n;
		while ((n = fileSource.read(b)) != -1) {
			outputStream.write(b, 0, n);
		}
		// 关闭输出流、输入流
		outputStream.close();
		fileSource.close();

		// 获取上传文件的名称
		RandomAccessFile randomFile = new RandomAccessFile(tempFile, "r");
		randomFile.readLine();
		String str = randomFile.readLine();
		System.out.println(str);

		int endIndex = str.lastIndexOf("\"");
		String beginStr = str.substring(0, endIndex);
		int beginIndex = beginStr.lastIndexOf("\"") + 1;
		String filename = beginStr.substring(beginIndex, beginStr.length());
		if (filename.equals("")) {
			return;
		} else {
			System.out.println("filename:" + filename);

			// 重新定位文件指针到文件头
			randomFile.seek(0);
			long startPosition = 0;
			int i = 1;
			// 获取文件内容 开始位置
			while ((n = randomFile.readByte()) != -1 && i <= 4) {
				if (n == '\n') {
					startPosition = randomFile.getFilePointer();
					i++;
				}
			}
			startPosition = randomFile.getFilePointer() - 1;
			// 获取文件内容 结束位置
			randomFile.seek(randomFile.length());
			long endPosition = randomFile.getFilePointer();
			int j = 1;
			while (endPosition >= 0 && j <= 2) {
				endPosition--;
				randomFile.seek(endPosition);
				if (randomFile.readByte() == '\n') {
					j++;
				}
			}
			endPosition = endPosition - 1;

			// 设置保存上传文件的路径
			String realPath = getServletContext().getRealPath("/") + "config_temp";
			File fileupload = new File(realPath);
			if (!fileupload.exists()) {
				fileupload.mkdir();
			}
			File saveFile = new File(realPath, filename);
			RandomAccessFile randomAccessFile = new RandomAccessFile(saveFile, "rw");

			// 从临时文件当中读取文件内容（根据起止位置获取）
			randomFile.seek(startPosition);
			while (startPosition < endPosition) {
				randomAccessFile.write(randomFile.readByte());
				startPosition = randomFile.getFilePointer();
			}
			// 关闭输入输出流、删除临时文件
			randomAccessFile.close();
			randomFile.close();
			tempFile.delete();
			System.out.println(saveFile.toString());
			PrintWriter writer = response.getWriter();
			writer.print(" { ");
			writer.print(" \"success\" : \"true\" ,\"msg\" : \"上传成功！ \", \"filePath\" : \"" + filename + " \"");
			writer.print(" } ");
			writer.close();
		}
		// }
	}

}
