/**
 * @author 신춘호(sch0718@naver.com)
 * @date 2019. 6. 22.
 * @file SnakeCaseNamingStrategy.java
 */
package me.devstar.common.jpa;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.CaseFormat;

/**
 * 
 */
public class SnakeCaseNamingStrategy extends PhysicalNamingStrategyStandardImpl {
	private static final Logger log = LoggerFactory.getLogger(SnakeCaseNamingStrategy.class);
	
	private static final long serialVersionUID = 5133613343741971210L;

	@Override
	public Identifier toPhysicalTableName(Identifier name, JdbcEnvironment context) {
		CaseFormat cf = getCaseFormat(name.getText());
		Identifier idf = null;
		
		if(cf != null) {
			idf = new Identifier(getCaseFormat(name.getText()).to(CaseFormat.UPPER_UNDERSCORE, name.getText()), name.isQuoted());
		}
		else {
			idf = new Identifier(name.getText().toUpperCase(), name.isQuoted());
		}
		
		log.debug("table name - input : [{}], output : [{}]", name.getText(), idf.getText());
		
		return idf;
	}

	@Override
	public Identifier toPhysicalColumnName(Identifier name, JdbcEnvironment context) {
		CaseFormat cf = getCaseFormat(name.getText());
		Identifier idf = null;
		
		if(cf != null) {
			idf = new Identifier(getCaseFormat(name.getText()).to(CaseFormat.UPPER_UNDERSCORE, name.getText()), name.isQuoted());
		}
		else {
			idf = new Identifier(name.getText().toUpperCase(), name.isQuoted());
		}
		
		log.debug("column name - input : [{}], output : [{}]", name.getText(), idf.getText());
		
		return idf;
	}

	private CaseFormat getCaseFormat(String name) {
		if(name.contains("_")) {
			if(name.toUpperCase().equals(name))
				return CaseFormat.UPPER_UNDERSCORE;

			if(name.toLowerCase().equals(name))
				return CaseFormat.LOWER_UNDERSCORE;
			
			return CaseFormat.UPPER_UNDERSCORE;
		} 
		else if(name.contains("-")) {
			if(name.toLowerCase().equals(name))
				return CaseFormat.LOWER_HYPHEN;
		} 
		else {
			if(Character.isLowerCase(name.charAt(0))) {
				if(name.matches("([a-z]+[A-Z]+\\w+)+"))
					return CaseFormat.LOWER_CAMEL;
			}
			else if(name.matches("([A-Z])+")) {
				return null;
			}
			else if(name.matches("([A-Z]+[a-z]+\\w+)+")) {
					return CaseFormat.UPPER_CAMEL;
			}
		}

		return CaseFormat.UPPER_CAMEL;
//		throw new IllegalArgumentException("Couldn't find the case format of the given string. - [" + name + "]");
	}
}
