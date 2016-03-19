package sy.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class Connections {
	public static Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/qualitytest?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull";
			String user = "root";
			String pass = "123456";
			conn = DriverManager.getConnection(url, user, pass);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	static String importStr = "";
	static String path = System.getProperty("user.dir") + "\\src\\main\\java";

	public static void main(String[] args) throws Exception {
		String pagekage = "com/midai/miya/standard";
		String tableName = "zyzj_standard";
		create(pagekage, tableName);
		createDao(pagekage, tableName, true);
		createService(pagekage, tableName, true);
		createXml(pagekage, tableName, true);
		createServiceImpl(pagekage, tableName, true);
	}

	public static ResultSet selectInformation(String tableName)
			throws SQLException {
		Connection conn = getConnection();
		String sql = "SELECT column_name as columnName, COLUMN_KEY as columnKey ,column_comment as columnComment FROM Information_schema. COLUMNS WHERE table_Name = '"
				+ tableName + "' ";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		return rs;
	}

	/**
	 * 创建文件 王梦圆 2015年6月23日
	 */
	public static void createFile(String pagekagePathStr, String tableName,
			String suffix, String importStr, boolean flag) throws Exception {
		String pagekageStr = pagekagePathStr.replace("/", ".");// 首行引入包名
		Connection conn = getConnection();
		String sql = "select * from " + tableName;
		PreparedStatement stmt;
		stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery(sql);
		ResultSetMetaData data = rs.getMetaData();
		String javaName = getFileName(tableName);// 表名
		if (!suffix.equals("service.impl")) {
			pagekagePathStr = pagekagePathStr + "/" + suffix;// 创建接口的路径
		}
		String name = "";
		if (suffix.equals("service.impl")) {
			String suffixStr = suffix.replace(".", "");
			String before = suffixStr.substring(0, 7);
			String end = suffixStr.substring(7);
			pagekagePathStr = pagekagePathStr + "/" + before + "/" + end;// 创建接口的路径
			end = end.replaceFirst(end.substring(0, 1), end.substring(0, 1)
					.toUpperCase());
			name = before + end;
			name = name.replaceFirst(name.substring(0, 1), name.substring(0, 1)
					.toUpperCase());
			name = javaName + name;
		} else {
			String suff = suffix.replaceFirst(suffix.substring(0, 1), suffix
					.substring(0, 1).toUpperCase());// service或dao首字母大写，拼类名
			name = javaName + suff;// 类名
		}
		String packageStr = "";
		if (!suffix.equals("mapper")) {
			packageStr = "package " + pagekageStr + "." + suffix + ";\r\n\n";// 包名
		}
		File fileDir = new File(path + "/" + pagekagePathStr);// 创建文件夹
		if (!fileDir.exists()) {
			fileDir.mkdirs();
		}
		File file = new File("");
		if (suffix.equals("mapper")) {
			file = new File(path + "/" + pagekagePathStr + "/" + javaName
					+ "Dao.xml");
		} else {
			file = new File(path + "/" + pagekagePathStr + "/" + name + ".java");// 创建文件
		}
		if (!file.exists()) {
			file.createNewFile();
		}
		String fileContent = "";
		if (suffix.equals("service")) {
			fileContent = createSerivceContent(javaName);
		} else if (suffix.equals("dao")) {
			fileContent = createDaoContent(javaName);
		} else if (suffix.equals("mapper")) {
			fileContent = createXmlContent(pagekageStr, data, tableName, flag);
		} else if (suffix.equals("service.impl")) {
			fileContent = createImplContent(javaName);
		}
		byte bytes[] = new byte[512];
		bytes = (packageStr + importStr + fileContent).toString().getBytes();
		int b = (packageStr + importStr + fileContent).length();
		try {
			FileOutputStream fos = new FileOutputStream(file);
			fos.write(bytes, 0, b);
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 创建实现类 王梦圆 2015年6月24日
	 */
	public static void createServiceImpl(String pagekagePathStr,
			String tableName, boolean flag) throws Exception {
		String javaName = getFileName(tableName);
		String pagekageStr = pagekagePathStr.replace("/", ".");// 首行引入包名
		importStr = "";
		importStr += "import java.util.List;\r\nimport org.springframework.beans.factory.annotation.Autowired;\r\n"
				+ "import org.springframework.stereotype.Service;\r\n"
				+ "import "
				+ pagekageStr
				+ ".dao."
				+ javaName
				+ "Dao;\r\nimport "
				+ pagekageStr
				+ ".service."
				+ javaName
				+ "Service;\r\n"
				+ "import "
				+ pagekageStr
				+ ".model."
				+ javaName + ";\r\nimport com.midai.miya.utils.PageUtil;\r\n\n";
		createFile(pagekagePathStr, tableName, "service.impl", importStr, flag);
	}

	/**
	 * 实现类内容 王梦圆 2015年6月24日
	 */
	public static String createImplContent(String javaName) {
		StringBuffer str = new StringBuffer("");
		String javaNameStr = javaName.replaceFirst(javaName.substring(0, 1),
				javaName.substring(0, 1).toLowerCase());
		str.append("@Service\r\n");
		str.append("public class " + javaName + "ServiceImpl implements "
				+ javaName + "Service {\r\n\n" + "     @Autowired\r\n"
				+ "     private " + javaName + "Dao " + javaNameStr
				+ "Dao;\r\n\n" + "     @Override\r\n" + "     public List<"
				+ javaName + "> findByConditions(" + javaName + " "
				+ javaNameStr + "," + "PageUtil page) {\r\n" + "        List<"
				+ javaName + "> lists=" + javaNameStr + "Dao.findByConditions("
				+ javaNameStr + ",page);\r\n" + "        return lists;\r\n"
				+ "     }\r\n\n" + "     @Override\r\n"
				+ "     public long findByConditionsCount(" + javaName + " "
				+ javaNameStr + ") {\r\n" + "        long count=" + javaNameStr
				+ "Dao.findByConditionsCount(" + javaNameStr + ");\r\n"
				+ "        return count;\r\n" + "     }\r\n\n"
				+ "     @Override\r\n" + "     public void save(" + javaName
				+ " " + javaNameStr + ") {\r\n" + "        " + javaNameStr
				+ "Dao.save(" + javaNameStr + ");\r\n" + "     }\r\n\n"
				+ "     @Override\r\n" + "     public void update(" + javaName
				+ " " + javaNameStr + ") {\r\n" + "        " + javaNameStr
				+ "Dao.update(" + javaNameStr + ");\r\n" + "     }\r\n\n"
				+ "     @Override\r\n" + "     public void delete(" + javaName
				+ " " + javaNameStr + ") {\r\n" + "        " + javaNameStr
				+ "Dao.delete(" + javaNameStr + ");\r\n" + "     }\r\n"
				+ "}\r\n\n");
		return str.toString();
	}

	/**
	 * 创建xml 王梦圆 2015年6月24日
	 */
	public static void createXml(String pagekagePathStr, String tableName,
			boolean flag) throws Exception {
		createFile(pagekagePathStr, tableName, "mapper", "", flag);
	}

	/**
	 * xml内容 王梦圆 2015年6月24日
	 */
	public static String createXmlContent(String pagekagePathStr,
			ResultSetMetaData data, String tableName, boolean flag)
			throws SQLException, IOException {
		String javaName = getFileName(tableName);
		String javaNameStr = javaName.replaceFirst(javaName.substring(0, 1),
				javaName.substring(0, 1).toLowerCase());
		StringBuffer str = new StringBuffer("");
		String time = "";
		String id = "";
		String idStr = "";
		str.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n");
		str.append("<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\" >\r\n");
		str.append("<mapper namespace=\"" + pagekagePathStr + ".dao."
				+ javaName + "Dao\">\r\n");
		str.append("   <resultMap id=\"BaseResultMap\" type=\""
				+ pagekagePathStr + ".model." + javaName + "\">\r\n");
		for (int i = 1; i <= data.getColumnCount(); i++) {
			// 获得指定列的列名
			String columnName = data.getColumnName(i);
			String columnNameStr = replaceUnderlineAndfirstToUpper(columnName,
					"_", "");
			if (columnNameStr.contains("CreateTime")) {
				time = columnName;
			}
			// 对应数据类型的类
			String columnClassName = getColumnClassName(data
					.getColumnClassName(i));
			if (columnClassName.equals("String")) {
				columnClassName = "VARCHAR";
			} else if (columnClassName.equals("Integer")) {
				columnClassName = "INTEGER";
			} else if (columnClassName.equals("Date")) {
				columnClassName = "TIMESTAMP";
			}
			if (columnName.contains("id")) {
				id = columnName;
				idStr = columnNameStr;
				str.append("         <id column=\"" + columnName
						+ "\" property=\"" + columnNameStr + "\" jdbcType=\""
						+ columnClassName + "\" />\r\n");
			} else {
				str.append("         <result column=\"" + columnName
						+ "\" property=\"" + columnNameStr + "\" jdbcType=\""
						+ columnClassName + "\" />\r\n");
			}
		}
		str.append("   </resultMap>\r\n");
		str.append("   <sql id=\"findWhere\">\r\n");
		str.append("   <where>\r\n");
		for (int i = 1; i <= data.getColumnCount(); i++) {
			// 获得指定列的列名
			String columnName = data.getColumnName(i);
			// System.out.println(columnName);
			String columnNameStr = replaceUnderlineAndfirstToUpper(columnName,
					"_", "");
			str.append("      <if test=\"" + javaNameStr + "." + columnNameStr
					+ "!=null and " + javaNameStr + "." + columnNameStr
					+ "!='' \">\r\n");
			str.append("        and " + columnName + "=#{" + javaNameStr + "."
					+ columnNameStr + "}\r\n" + "      </if>\r\n");
		}
		str.append("   </where>\r\n");
		str.append("   </sql>\r\n");
		str.append("   <select id=\"findByConditions\" resultMap=\"BaseResultMap\">\r\n");
		str.append("       select * from " + tableName + "\r\n");
		str.append("       <include refid=\"findWhere\"></include>\r\n");
		str.append("       order by \r\n");
		str.append("       <choose>\r\n");
		str.append("       <when test=\"page.sort !=null and page.sort!=\'\' \">\r\n");
		str.append("          ${page.sort}  ${page.order}\r\n");
		str.append("       </when >\r\n");
		if (flag && !time.equals("")) {
			str.append("       <otherwise>\r\n");
			str.append("	    " + time + " DESC\r\n");
			str.append("	   </otherwise>\r\n");
		}
		str.append("       </choose >\r\n");
		str.append("       limit #{page.page},#{page.rows}\r\n");
		str.append("   </select>\r\n\n");
		str.append("   <select id=\"findByConditionsCount\" resultType=\"java.lang.Long\">\r\n");
		str.append("      select count(1) from " + tableName + "\r\n");
		str.append("      <include refid=\"findWhere\"></include>\r\n");
		str.append("   </select>\r\n\n");
		str.append("   <insert id=\"save\">\r\n");
		str.append("      insert into " + tableName + " values(\r\n");
		for (int i = 1; i <= data.getColumnCount(); i++) {
			// 获得指定列的列名
			String columnName = data.getColumnName(i);
			String columnNameStr = replaceUnderlineAndfirstToUpper(columnName,
					"_", "");
			if (i == data.getColumnCount()) {
				str.append("         #{" + javaNameStr + "." + columnNameStr
						+ "}\r\n");
			} else {
				str.append("         #{" + javaNameStr + "." + columnNameStr
						+ "},\r\n");
			}
		}
		str.append("      )\r\n");
		str.append("   </insert>\r\n\n");
		str.append("   <update id=\"update\">\r\n");
		str.append("      update " + tableName + " set\r\n");
		String idsql = null;
		String idname = "";
		for (int i = 1; i <= data.getColumnCount(); i++) {
			// 获得指定列的列名
			String columnName = data.getColumnName(i);
			// System.out.println(columnName);
			String columnNameStr = replaceUnderlineAndfirstToUpper(columnName,
					"_", "");
			ResultSet rs = selectInformation(tableName);
			while (rs.next()) {
				String coulmnName = rs.getString("columnName");
				// System.out.println(coulmnName);
				String columnKey = rs.getString("columnKey");
				if (columnKey.equals("PRI")) {
					idname = coulmnName;
				}
			}
			if (columnName.equals(idname)) {
				idsql = "      <if test=\"" + javaNameStr + "." + columnNameStr
						+ "!=null and " + javaNameStr + "." + columnNameStr
						+ "!='' \">\r\n" + "        " + columnName + "=#{"
						+ javaNameStr + "." + columnNameStr + "}\r\n"
						+ "      </if>\r\n";
				idsql += "      where " + idname + "=#{" + javaNameStr + "."
						+ columnNameStr + "}\r\n";
			} else {
				str.append("      <if test=\"" + javaNameStr + "."
						+ columnNameStr + "!=null and " + javaNameStr + "."
						+ columnNameStr + "!='' \">\r\n");
				str.append("        " + columnName + "=#{" + javaNameStr + "."
						+ columnNameStr + "},\r\n" + "      </if>\r\n");
			}
		}
		str.append(idsql);
		str.append("   </update>\r\n\n");
		str.append("   <delete id=\"delete\">\r\n");
		String idcolumnNameStr = replaceUnderlineAndfirstToUpper(idname, "_",
				"");
		str.append("      DELETE FROM " + tableName + " WHERE " + idname
				+ "=#{" + javaNameStr + "." + idcolumnNameStr + "}\r\n");
		str.append("   </delete>\r\n\n");
		str.append("</mapper>");
		return str.toString();
	}

	/**
	 * 创建service 王梦圆 2015年6月23日
	 */
	public static void createService(String pagekagePathStr, String tableName,
			boolean flag) throws Exception {
		String javaName = getFileName(tableName);// 表名
		String pagekageStr = pagekagePathStr.replace("/", ".");
		importStr = "";
		importStr += "import "
				+ pagekageStr
				+ ".model."
				+ javaName
				+ ";\r\nimport java.util.List;\r\nimport com.midai.miya.utils.PageUtil;\r\n\n";
		createFile(pagekagePathStr, tableName, "service", importStr, flag);
	}

	/**
	 * service里面自动生成的内容 王梦圆 2015年6月23日
	 */
	public static String createSerivceContent(String javaName) {
		StringBuffer str = new StringBuffer("");
		String javaNameStr = javaName.replaceFirst(javaName.substring(0, 1),
				javaName.substring(0, 1).toLowerCase());
		str.append("public interface " + javaName + "Service {\r\n\n");
		str.append("     List<" + javaName + "> findByConditions(" + javaName
				+ " " + javaNameStr + ",PageUtil page);\r\n\n");
		str.append("     long findByConditionsCount" + "(" + javaName + " "
				+ javaNameStr + ");\r\n\n");
		str.append("     void save(" + javaName + " " + javaNameStr
				+ ");\r\n\n");
		str.append("     void update(" + javaName + " " + javaNameStr
				+ ");\r\n\n");
		str.append("     void delete(" + javaName + " " + javaNameStr
				+ ");\r\n\n");
		str.append("}");
		return str.toString();
	}

	/**
	 * 创建dao 王梦圆 2015年6月23日
	 */
	public static void createDao(String pagekagePathStr, String tableName,
			boolean flag) throws Exception {
		String javaName = getFileName(tableName);
		String pagekageStr = pagekagePathStr.replace("/", ".");
		importStr = "";
		importStr += "import "
				+ pagekageStr
				+ ".model."
				+ javaName
				+ ";\r\nimport java.util.List;\r\nimport org.apache.ibatis.annotations.Param;\r\nimport com.midai.miya.utils.PageUtil;\n\n";
		createFile(pagekagePathStr, tableName, "dao", importStr, flag);
	}

	/**
	 * dao的内容 王梦圆 2015年6月23日
	 */
	public static String createDaoContent(String javaName) {
		StringBuffer str = new StringBuffer("");
		String javaNameStr = javaName.replaceFirst(javaName.substring(0, 1),
				javaName.substring(0, 1).toLowerCase());
		str.append("public interface " + javaName + "Dao {\r\n\n");
		str.append("     List<" + javaName + "> findByConditions"
				+ "(@Param(\"" + javaNameStr + "\")" + javaName + " "
				+ javaNameStr + ",@Param(\"page\")PageUtil page);\r\n\n");
		str.append("     long findByConditionsCount" + "(@Param(\""
				+ javaNameStr + "\")" + javaName + " " + javaNameStr
				+ ");\r\n\n");
		str.append("     void save(@Param(\"" + javaNameStr + "\")" + javaName
				+ " " + javaNameStr + ");\r\n\n");
		str.append("     void update(@Param(\"" + javaNameStr + "\")"
				+ javaName + " " + javaNameStr + ");\r\n\n");
		str.append("     void delete(@Param(\"" + javaNameStr + "\")"
				+ javaName + " " + javaNameStr + ");\r\n\n");
		str.append("}");
		return str.toString();
	}

	/**
	 * 创建实体类 王梦圆 2015年6月23日
	 */
	public static void create(String pagekagePathStr, String tableName)
			throws SQLException, IOException {
		String pagekageStr = pagekagePathStr.replaceAll("/", ".");
		Connection conn = getConnection();
		String sql = "select * from " + tableName;
		PreparedStatement stmt;
		stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery(sql);
		ResultSetMetaData data = rs.getMetaData();
		StringBuffer str = new StringBuffer("");
		String pagekegeStr = "package " + pagekageStr + ".model;\r\n\n";
		String javaName = getFileName(tableName);
		String fileContent = createModel(str, data, tableName);
		pagekagePathStr = pagekagePathStr + "/model";
		File fileDir = new File(path + "/" + pagekagePathStr);
		if (!fileDir.exists()) {
			fileDir.mkdirs();
		}
		File file = new File(path + "/" + pagekagePathStr + "/" + javaName
				+ ".java");
		if (!file.exists()) {
			file.createNewFile();
		}
		byte bytes[] = new byte[2000];
		bytes = (pagekegeStr + importStr + fileContent).toString().getBytes(
				"utf-8");
		try {
			RandomAccessFile fos = new RandomAccessFile(file, "rw");
			fos.write(bytes);
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 传入myw_开头的表名，去掉下划线以前的，下划线之后首字母大写 王梦圆 2015年6月23日
	 */
	public static String getFileName(String tableName) {
		String javaName = tableName.replace("myw_", "");
		javaName = javaName.replace("zyzj_", "");
		javaName = javaName.replaceFirst(javaName.substring(0, 1), javaName
				.substring(0, 1).toUpperCase());
		javaName = replaceUnderlineAndfirstToUpper(javaName, "_", "");
		return javaName;
	}

	/**
	 * 创建实体类内容 王梦圆 2015年6月23日
	 */
	public static String createModel(StringBuffer str, ResultSetMetaData data,
			String tableName) throws SQLException {
		String javaName = getFileName(tableName);
		str.append("import java.io.Serializable;\r\n");
		str.append("public class " + javaName
				+ " implements Serializable {\n\n");
		str.append("    private static final long serialVersionUID = 1L;\r\n");
		for (int i = 1; i <= data.getColumnCount(); i++) {
			// 获得指定列的列名
			String columnName = data.getColumnName(i);
			columnName = replaceUnderlineAndfirstToUpper(columnName, "_", "");
			// 对应数据类型的类
			String columnClassName = getColumnClassName(data
					.getColumnClassName(i));
			ResultSet rs = selectInformation(tableName);
			while (rs.next()) {
				String coulmnContent = rs.getString("columnComment");
				String columName = rs.getString("columnName");
				if (columName.equals(data.getColumnName(i))) {
					str.append("    /**\r\n" + "     * " + coulmnContent
							+ "\r\n" + "     */\r\n");
					break;
				}
			}
			str.append("    private " + columnClassName + " " + columnName
					+ ";\n");

		}
		for (int i = 1; i <= data.getColumnCount(); i++) {
			// 获得指定列的列名
			String columnName = data.getColumnName(i);
			columnName = replaceUnderlineAndfirstToUpper(columnName, "_", "");
			// 对应数据类型的类
			String columnClassName = getColumnClassName(data
					.getColumnClassName(i));
			// 获取某列对应的表名
			str.append("    public "
					+ columnClassName
					+ " "
					+ "get"
					+ columnName.replaceFirst(columnName.substring(0, 1),
							columnName.substring(0, 1).toUpperCase())
					+ "(){\n"
					+ "        return "
					+ columnName
					+ ";\n    }\n"
					+ "    public void set"
					+ columnName.replaceFirst(columnName.substring(0, 1),
							columnName.substring(0, 1).toUpperCase()) + "("
					+ columnClassName + " " + columnName + "){\n"
					+ "        this." + columnName + "=" + columnName
					+ ";\n    }\n");
		}
		str.append("}");
		System.out.println(str);
		return str.toString();
	}

	/**
	 * 判断属性类型是否是Date,如果是引入相关的包 王梦圆 2015年6月23日
	 */
	public static String getColumnClassName(String columnClassName) {
		int begin = columnClassName.lastIndexOf(".") + 1;
		int end = columnClassName.length();
		columnClassName = columnClassName.substring(begin, end);
		if (columnClassName.equals("Timestamp")) {
			columnClassName = "Date";
			importStr = "import java.util.Date;\r\n";
		}
		return columnClassName;
	}

	/**
	 * 把属性值中的下划线去掉，并且让下划线后面的字母大写 王梦圆 2015年6月23日
	 */
	public static String replaceUnderlineAndfirstToUpper(String srcStr,
			String org, String ob) {
		String newString = "";
		int first = 0;
		while (srcStr.indexOf(org) != -1) {
			first = srcStr.indexOf(org);
			if (first != srcStr.length()) {
				newString = newString + srcStr.substring(0, first) + ob;
				srcStr = srcStr
						.substring(first + org.length(), srcStr.length());
				srcStr = srcStr.replaceFirst(srcStr.substring(0, 1), srcStr
						.substring(0, 1).toUpperCase());
			}
		}
		newString = newString + srcStr;
		return newString;
	}
}