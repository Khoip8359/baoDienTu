package assignment.utils;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.beanutils.converters.DateTimeConverter;

public class XBean {
	public static Object readBean(Object bean, HttpServletRequest req){
		DateTimeConverter converter = new DateConverter(new Date());
		converter.setPatterns(new String[] {"dd-MM-yyyy", "MM/dd/yyyy", "yyyy-MM-dd"});
		ConvertUtils.register(converter, Date.class);
		try {
			BeanUtils.populate(bean, req.getParameterMap());
			return bean;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
