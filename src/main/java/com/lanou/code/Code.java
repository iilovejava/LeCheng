package com.lanou.code;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import cn.dsna.util.images.ValidateCode;
/*
 * 人类与计算机的图灵测试
 */
public class Code extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// fun1(response);
		// 创建vaildate对象
		ValidateCode validateCode = new ValidateCode(100, 25, 4, 9);
		// 获取随机出来的验证码
		String code = validateCode.getCode();
		System.out.println(code);
		HttpSession session = request.getSession();
		session.setAttribute("mycode",code);
		// 将验证码写回网页
		validateCode.write(response.getOutputStream());
		
	}

	private void fun1(HttpServletResponse response) throws IOException {
		// 定义图片的宽高
		int width = 100;
		int height = 25;
		
		// 创建画布
		// 参数3 选择图片的属性
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		// 从画布中获取画笔
		Graphics g = image.getGraphics();
		// 设置背景颜色
		g.setColor(Color.white);
		// 填充背景颜色(留出边框)
		g.fillRect(1, 1, width-2, height-2);
		// 设置边框颜色
		
		g.setColor(Color.RED);
		g.drawRect(0, 0, width-1, height-1);
		// 设置字体颜色 类型大小
		g.setColor(Color.blue);
		g.setFont(new Font("宋体",Font.BOLD,20));
		// 随机4个数字 画到画布上
		Random random = new Random();
		// 0-9的整数 [0,9)
		// int num = random.nextInt(10);
		// 声明x坐标
		int positionX = 20;
		for (int i = 0; i < 4; i++) {
			// 把随机数 画到画布上
			// x,y距离画布的坐标
			g.drawString(random.nextInt(10)+"", positionX, 20);
			positionX = positionX +20;
		}
		// 画干扰线
		for (int i = 0; i < 8; i++) {
			g.drawLine(random.nextInt(width), random.nextInt(height),
					random.nextInt(width), random.nextInt(height));
		}
		// 响应回页面中
		ImageIO.write(image, "jpg", response.getOutputStream());
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}