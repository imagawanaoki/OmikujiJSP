package omikuji;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * DBに接続 omikuji_idを条件に運勢等の値を取り出す
 */
public class ResultDao {

	public static Unsei CheckOmikujiId(Integer omikuji_id) {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {

			//SQL文の準備
			String join = "SELECT um.unsei_name, omi.gakumon, omi.negaigoto, omi.akinai, omi.unsei_id FROM UnseiMaster um "
					+ "INNER JOIN Omikuji omi ON um.unsei_id = omi.unsei_id WHERE omi.omikuji_id = ?";

			//SQLをDBに渡す
			connection = DBManager.getConnection();
			preparedStatement = connection.prepareStatement(join);
					preparedStatement.setInt(1, omikuji_id);
			ResultSet rs = preparedStatement.executeQuery();
			rs.next();


			//テーブルの値を取り出す
		Unsei omikuji = null;
		String gakumon = rs.getString("gakumon");
		String akinai = rs.getString("akinai");
		String negaigoto = rs.getString("negaigoto");
		String unsei_name = rs.getString("unsei_name");

		int unsei_id = rs.getInt("unsei_id");

		switch (unsei_id) {
		case 1:
			omikuji = new Daikichi();
			break;

		case 2:
			omikuji = new Tyuukichi();
			break;

		case 3:
			omikuji = new Shokichi();
			break;

		case 4:
			omikuji = new Suekichi();
			break;

		case 5:
			omikuji = new Kichi();
			break;

		case 6:
			omikuji = new Kyou();
			break;


		default:
			break;
		}
		omikuji.setUnsei(unsei_name);
		omikuji.setNegaigoto(negaigoto);
		omikuji.setAkinai(akinai);
		omikuji.setGakumon(gakumon);

			return omikuji;

		} catch (Exception e) {
			e.printStackTrace();
			return null;

		} finally {
			DBManager.close(connection);
			DBManager.close(preparedStatement);
		}


	}

}