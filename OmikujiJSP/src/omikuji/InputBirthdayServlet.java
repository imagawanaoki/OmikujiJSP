package omikuji;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *誕生日入力画面の表示
 * @author n_imagawa
 * Servlet implementation class inputBirthday
 */
@WebServlet("/InputBirthdayServlet")
public class InputBirthdayServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html; charset=UTF-8");

		//PrintWriterの準備
		PrintWriter out = response.getWriter();
		//sessionの準備
		HttpSession session = request.getSession();

		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<meta http-equiv=Content-Type content=text/html;  charset=UTF-8>");
		out.println("<link href=/OmikujiJSP/css/style.css rel=stylesheet />");
		out.println("<title>誕生日入力画面</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<header>");
		out.println("</header>");
		out.println("<center>");

		//正しい形式で入力された場合orされなかった場合
		if (session.getAttribute("errorMessage") != null) {
			out.println(session.getAttribute("errorMessage"));

		}else {
			out.println("<p class=center>誕生日を入力してください<br></p>");

		}

		out.println(
				"<form class = center action = /OmikujiJSP/InputDisplayServlet method = get><input type = text name = birthday>"
						+ "	<input type = submit value = おみくじを引く></form>");
		out.println("</center>");
		out.println("</body>");
		out.println("</html>");

	}
	}

