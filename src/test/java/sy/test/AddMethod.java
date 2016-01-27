package sy.test;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;

public class AddMethod {
	private static String path = System.getProperty("user.dir") + "\\src\\main\\java";

	public static void main(String[] args) {
		String packageName = "com/midai/miya/abc";
		String entityName = "Module";
		String returnType = "List<Module>";
		String methodName = "addModule";
		addMethod(packageName, entityName, returnType, methodName,xmlType.insert, "String aa",
				"String bb");
	}

	/**
	 * @param packageName 包名 .替换�? 去掉model 后传进来 
	 * @param entityName 实体类名�?
	 * @param returnType 返回值类�?
	 * @param  methodName 方法名称
	 * @param parameters 方法参数 不定�?可以�?到多个字符串
	 * @author 王梦�?
	 * 2015�?�?6�?
	 */
	private static void addMethod(String packageName, String entityName,
			String returnType, String methodName,xmlType xmlType, String... parameters) {
		addMethodToService(packageName, entityName, returnType, methodName,
				parameters);
		addMethodToDao(packageName, entityName, returnType, methodName, parameters);
		addMethodToXml(packageName, entityName, returnType, methodName, xmlType, parameters);
		addMethodToServiceImpl(packageName, entityName, returnType, methodName, parameters);
	}
	
	private static void addMethodToServiceImpl(String packageName,
			String entityName, String returnType, String methodName,
			String... parameters){
		String serviceImplPath=path+"/"+packageName+"/service/impl";
		String serviceImplName=entityName+"ServiceImpl.java";
		serviceImplPath += "/"+serviceImplName;
		String serviceImplContent=readFileByLines(serviceImplPath);
		serviceImplContent=serviceImplContent.substring(0, serviceImplContent.lastIndexOf("}"));
		StringBuffer serviceImplMethodeBuffer = new StringBuffer();
		serviceImplMethodeBuffer.append("\n");
		serviceImplMethodeBuffer.append("     @Override");
		serviceImplMethodeBuffer.append("\n");
		serviceImplMethodeBuffer.append("     ");
		serviceImplMethodeBuffer.append("public "+returnType+" "+methodName+"(");
		String parameterStr = "";
		String daoParameterStr="";
		for (String parameter : parameters) {
			parameterStr += parameter + ",";
			String parameterByte[]=parameter.split(" ");
			daoParameterStr+=parameterByte[parameterByte.length-1]+",";
		}
		serviceImplMethodeBuffer.append(parameterStr.substring(0,
				parameterStr.length() - 1));
		serviceImplMethodeBuffer.append("){\n");
		String entityNameStr=entityName.replaceFirst(entityName.substring(0, 1), entityName.substring(0, 1).toLowerCase());
		if(returnType.equals("void")){
			serviceImplMethodeBuffer.append("        "+entityNameStr+"Dao."+methodName+"(");
			serviceImplMethodeBuffer.append(daoParameterStr.substring(0,
					daoParameterStr.length() - 1));
			serviceImplMethodeBuffer.append(");\n");
		}else{
			serviceImplMethodeBuffer.append("        "+returnType+" "+entityName+" = "+entityNameStr+"Dao."+methodName+"(");
			serviceImplMethodeBuffer.append(daoParameterStr.substring(0,
					daoParameterStr.length() - 1));
			serviceImplMethodeBuffer.append(");\n");
			serviceImplMethodeBuffer.append("        return "+entityName+";\n");
		}
		serviceImplMethodeBuffer.append("     }\n");
		serviceImplMethodeBuffer.append("}");
		serviceImplContent=serviceImplContent+serviceImplMethodeBuffer;
		String result=updateFile(serviceImplPath, serviceImplContent);
		System.out.println(result);
	}
	
	/**
	 * 创建xml
	 * 王梦�?
	 * 2015�?�?6�?
	 */
	private static void addMethodToXml(String packageName, String entityName,
			String returnType, String methodName,xmlType xmlType, String... parameters){
		String xmlPath=path+"/"+packageName+"/mapper";
		String xmlName=entityName+"Dao.xml";
		xmlPath += "/" + xmlName;
		String xmlContent=readFileByLines(xmlPath);
		xmlContent=xmlContent.substring(0, xmlContent.lastIndexOf("<"));
		StringBuffer xmlMethodeBuffer = new StringBuffer();
		xmlMethodeBuffer.append("\n");
		xmlMethodeBuffer.append("   ");
		xmlMethodeBuffer.append("<"+xmlType+" id=\""+methodName+"\">");
		xmlMethodeBuffer.append("\n\n");
		xmlMethodeBuffer.append("   </"+xmlType+">");
		xmlMethodeBuffer.append("\n");
		xmlMethodeBuffer.append("</mapper>");
		xmlContent=xmlContent+xmlMethodeBuffer;
		String result=updateFile(xmlPath, xmlContent);
		System.out.println(result);
	}
	
	/**
	 * 创建dao
	 * 王梦�?
	 * 2015�?�?6�?
	 */
	private static void addMethodToDao(String packageName,
			String entityName, String returnType, String methodName,
			String... parameters){
		String daoPath=path+"/"+packageName+"/dao";
		String daoName=entityName+"Dao.java";
		daoPath += "/" + daoName;
		String daoContent=readFileByLines(daoPath);
		daoContent=daoContent.substring(0, daoContent.lastIndexOf("}"));
		StringBuffer daoMethodeBuffer = new StringBuffer();
		daoMethodeBuffer.append("\n   ");
		daoMethodeBuffer.append("	 ");
		daoMethodeBuffer.append(returnType);
		daoMethodeBuffer.append(" ");
		daoMethodeBuffer.append(methodName);
		daoMethodeBuffer.append("(");
		String parameterStr = "";
		for (String parameter : parameters) {
			parameterStr += parameter + ",";
		}
		daoMethodeBuffer.append(parameterStr.substring(0,
				parameterStr.length() - 1));
		daoMethodeBuffer.append(");");
		daoMethodeBuffer.append("\r\n");
		daoMethodeBuffer.append("}");
		daoContent+=daoMethodeBuffer;
		String result=updateFile(daoPath, daoContent);
		System.out.println(result);
	}
	
	/**
	 * 创建service
	 * 王梦�?
	 * 2015�?�?6�?
	 */
	private static void addMethodToService(String packageName,
			String entityName, String returnType, String methodName,
			String... parameters) {
		String servicePath = path + "/" + packageName + "/service";
		String serviceName = entityName + "Service.java";
		servicePath += "/" + serviceName;
		String serviceContent = readFileByLines(servicePath);
		serviceContent = serviceContent.substring(0,
				serviceContent.lastIndexOf("}"));
		StringBuffer serviceMethodeBuffer = new StringBuffer();
		serviceMethodeBuffer.append("\n   ");
		serviceMethodeBuffer.append("	");
		serviceMethodeBuffer.append(returnType);
		serviceMethodeBuffer.append(" ");
		serviceMethodeBuffer.append(methodName);
		serviceMethodeBuffer.append("(");
		String parameterStr = "";
		for (String parameter : parameters) {
			parameterStr += parameter + ",";
		}
		serviceMethodeBuffer.append(parameterStr.substring(0,
				parameterStr.length() - 1));
		serviceMethodeBuffer.append(");");
		serviceMethodeBuffer.append("\r\n");
		serviceMethodeBuffer.append("}");
		serviceContent = serviceContent + serviceMethodeBuffer;
		//System.out.println(serviceContent);
		String reuslt=updateFile(servicePath,serviceContent);
		System.out.println(reuslt);
	}

	/**
	 * 更新文件内容
	 * 王梦�?
	 * 2015�?�?6�?
	 */
	private static String updateFile(String path, String content) {
		String result=path+"更新成功";
		try {
			File file = new File(path);
			if (!file.exists()) {
				result="找不到文件："+path;
				return result;
			}
			byte bytes[] = new byte[2000];
			bytes = content.toString().getBytes("utf-8");
			RandomAccessFile fos = new RandomAccessFile(file, "rw");
			fos.write(bytes);
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
			result=path+"创建失败";
		}
		return result;
	}

	/**
	 * 以行为单位读取文件，常用于读面向行的格式化文�?
	 */
	private static String readFileByLines(String fileName) {
		StringBuffer str = new StringBuffer();
		File file = new File(fileName);
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			// �?��读入�?��，直到读入null为文件结�?
			while ((tempString = reader.readLine()) != null) {
				// 显示行号
				str.append(tempString +"\r\n");
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
		return str.toString();
	}
}

enum xmlType{
	select,
	insert,
	update
}
