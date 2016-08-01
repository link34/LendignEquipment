package jp.ucs.dao;
/**
 * DAOBase
 * 菴懈�先律 : 2016/03/30
 * @author nandarminsoe
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import jp.ucs.DBException.DBException;

public class DAOBase {
	protected static Connection conn = null;
	protected final String driverName = "org.postgresql.Driver";
	protected final String dbUrl = "jdbc:postgresql:lendingequipment";
	protected final String dbId = "postgres";
	protected final String pwd = "admin";
	protected void open() throws DBException {
		try {
			Class.forName(driverName);
			this.conn = DriverManager.getConnection(dbUrl, dbId, pwd);
		} catch(SQLException se) {
			throw new DBException
			("繝�繝ｼ繧ｿ繝吶�ｼ繧ｹ縺ｨ謗･邯壹〒縺阪∪縺帙ｓ縺ｧ縺励◆縲ゅす繧ｹ繝�繝�邂｡逅�閠�縺ｫ縺雁撫縺�蜷医ｏ縺帙￥縺�縺輔＞縲�");
		} catch(ClassNotFoundException ce) {
			throw new DBException
			("繝�繝ｼ繧ｿ繝吶�ｼ繧ｹ縺ｨ謗･邯壹〒縺阪∪縺帙ｓ縺ｧ縺励◆縲ゅす繧ｹ繝�繝�邂｡逅�閠�縺ｫ縺雁撫縺�蜷医ｏ縺帙￥縺�縺輔＞縲�");
		}
	}
	protected void close(Connection conn) throws DBException {
		if(conn != null) {
			try {
				conn.close();
			} catch(SQLException se) {
				throw new DBException("繝ｻ謗･邯壼��譁ｭ荳ｭ縺ｫ繧ｨ繝ｩ繝ｼ縺檎匱逕溘＠縺ｾ縺励◆縲�");
			}
		}
	}
}
