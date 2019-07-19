package omikuji;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

/**
	 * SQLで乱数を取得
	 */
public class LineCheckDao {
	public static int Gyo(Date birth, Date today) throws SQLException, IOException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int omikuji_id = 0;

		try {
			//DBに接続
			connection = DBManager.getConnection();

			//行数を取得する文
			String para = "select COUNT(*) from omikuji";

			//SQLを準備する
			preparedStatement = connection.prepareStatement(para);
			ResultSet rset = preparedStatement.executeQuery();

			//0かomikuji_idを代入
			omikuji_id = Check.PatternCheck(birth, today, connection, preparedStatement);

			//0を上書きする
			if (omikuji_id == 0) {

				Random rand = new Random();
				rset.next();

				//行数の取得
				omikuji_id = rand.nextInt(rset.getInt("count"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(connection);
			DBManager.close(preparedStatement);
		}

		return omikuji_id;

	}

}
