package com.midai.miya.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;

public class PropertieUtil {
	static Properties uncheckConfig = new Properties();
	static Properties unPassReasonMapConfig = new Properties();
	static Properties userunPassReasonMapConfig=new Properties();
	public static List<String> unCheckUrlList=new ArrayList<String>();
	public static Map<String,String> unPassReasonMap=new TreeMap<String,String>();
	public static Map<String,String> userunPassReasonMap=new TreeMap<String, String>();
	static{
		try {
			uncheckConfig.load(PropertieUtil.class.getResourceAsStream("../../../../unCheckUrl.properties"));
			Collection<Object> actions = uncheckConfig.values();
			for (Iterator<Object> localIterator = actions.iterator(); localIterator
					.hasNext();) {
				Object o = localIterator.next();
				if (o != null){
					unCheckUrlList.add(o.toString());
				}
					
			}
			unPassReasonMapConfig.load(PropertieUtil.class.getResourceAsStream("../../../../unPassReason.properties"));
			Collection<Object> unPassReasonMapConfigs = unPassReasonMapConfig.keySet();
			for (Iterator<Object> localIterator = unPassReasonMapConfigs.iterator(); localIterator
					.hasNext();) {
				Object o = localIterator.next();
				if (o != null){
					unPassReasonMap.put(o.toString(), unPassReasonMapConfig.getProperty(o.toString()));
					
				}
					
			}
			userunPassReasonMapConfig.load(PropertieUtil.class.getResourceAsStream("../../../../userunPassReason.properties"));
			Collection<Object> userunPassReasonMapConfigs=userunPassReasonMapConfig.keySet();
			for(Iterator<Object> localIterator = userunPassReasonMapConfigs.iterator();localIterator.hasNext();){
				Object o=localIterator.next();
				if(o!=null){
					userunPassReasonMap.put(o.toString(), userunPassReasonMapConfig.getProperty(o.toString()));
				}
			}
		
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
