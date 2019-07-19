package omikuji;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 入力された誕生日の形式チェック
 * 異なる場合はテーブルにおみくじの結果を追加する
 * おみくじの結果をResult.jspにフォワードする
 * Servlet implementation class InputDisplay
 */
@WebServlet("/InputDisplayServlet")
public class InputDisplayServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		LocalDate date = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		//sessionを使う準備
		HttpSession session = request.getSession();

		try {

			//入力された誕生日
			String birthday = (String) request.getParameter("birthday");

			//入力チェック
			boolean chkFlg = false;
			Matcher m = null;
			Date today = null;
			Date birth = null;

			//正規表現のチェック
			//現在の日付を取得
			date = LocalDate.now();

			// 正規表現のパターンを作成
			Pattern p = Pattern.compile("^[0-9]{4}-[0-9]{2}-[0-9]{2}$");
			m = p.matcher(birthday);
			chkFlg = m.find();

			//正規表現のパターンが正しくない場合
			if (!chkFlg) {
				session.setAttribute("errorMessage", "正しい形式ではありません。もう一度入力してください");
				RequestDispatcher rd = request.getRequestDispatcher("/InputBirthdayServlet");
				rd.forward(request, response);
				session.invalidate();

			} else {
				//入力が成功した時にエラーメッセージを表示させないようにする(sessionの破棄)
				session.invalidate();

				//Date型に変換
				today = Date.valueOf(date);
				birth = Date.valueOf(birthday);

				//おみくじを引くかどうかの判定及び行数から乱数を生成
				int omikuji_id = LineCheckDao.Gyo(birth, today);

				//おみくじ結果の取得
				Unsei omikuji = ResultDao.CheckOmikujiId(omikuji_id);
				request.setAttribute("omikuji", omikuji);

				//おみくじ結果をDBに登録
				ResistTableDao.ResistTable(omikuji_id, birth, today, connection, preparedStatement);

				//JSPにフォワード
				RequestDispatcher rd = request.getRequestDispatcher("/JSP/Result.jsp");
				rd.forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(connection);
		}
	}
}
