package me.devstar.web;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * Application Properties
 */
@Configuration
@Primary
@ConfigurationProperties(prefix = WebApplicationProperties.CONFIGURATION_PREFIX)
public class WebApplicationProperties implements InitializingBean {

	public static class Format {

		public static class Date {
			private String java;

			private String datepicker;

			/**
			 * @return the datepicker
			 */
			public String getDatepicker() {
				return datepicker;
			}

			/**
			 * @return the java
			 */
			public String getJava() {
				return java;
			}

			/**
			 * @param datepicker the datepicker to set
			 */
			public void setDatepicker(String datepicker) {
				this.datepicker = datepicker;
			}

			/**
			 * @param java the java to set
			 */
			public void setJava(String java) {
				this.java = java;
			}

		}

		public static class DateTime {
			private String java;

			/**
			 * @return the java
			 */
			public String getJava() {
				return java;
			}

			/**
			 * @param java the java to set
			 */
			public void setJava(String java) {
				this.java = java;
			}

		}

		private Date date;

		private DateTime dateTime;

		/**
		 * @return the date
		 */
		public Date getDate() {
			return date;
		}

		/**
		 * @return the dateTime
		 */
		public DateTime getDateTime() {
			return dateTime;
		}

		/**
		 * @param date the date to set
		 */
		public void setDate(Date date) {
			this.date = date;
		}

		/**
		 * @param dateTime the dateTime to set
		 */
		public void setDateTime(DateTime dateTime) {
			this.dateTime = dateTime;
		}
	}

	public static class Pagination {
		private int pageSize;
		private String sortProperty;
		private String sortDirection;

		/**
		 * @return the pageSize
		 */
		public int getPageSize() {
			return pageSize;
		}

		/**
		 * @return the sortDirection
		 */
		public String getSortDirection() {
			return sortDirection;
		}

		/**
		 * @return the sortProperty
		 */
		public String getSortProperty() {
			return sortProperty;
		}

		/**
		 * @param pageSize the pageSize to set
		 */
		public void setPageSize(int pageSize) {
			this.pageSize = pageSize;
		}

		/**
		 * @param sortDirection the sortDirection to set
		 */
		public void setSortDirection(String sortDirection) {
			this.sortDirection = sortDirection;
		}

		/**
		 * @param sortProperty the sortProperty to set
		 */
		public void setSortProperty(String sortProperty) {
			this.sortProperty = sortProperty;
		}

	}

	public static class Path {

		private String apiPrefix;

		/**
		 * @return the apiPrefix
		 */
		public String getApiPrefix() {
			return apiPrefix;
		}

		/**
		 * @param apiPrefix the apiPrefix to set
		 */
		public void setApiPrefix(String apiPrefix) {
			this.apiPrefix = apiPrefix;
		}

	}

	public static final String CONFIGURATION_PREFIX = "devstar";

	private static final Logger LOG = LoggerFactory.getLogger(WebApplicationProperties.class);

	Pagination pagination;

	Format format;

	Locale defaultLocale;

	Path path;

	@Override
	public void afterPropertiesSet() {
		if (LOG.isDebugEnabled()) {
			LOG.debug(getClass().getName() + " initialized.");

			LOG.debug("pagination pageSize: " + pagination.getPageSize());
			LOG.debug("pagination sort: " + pagination.getSortProperty());
			LOG.debug("pagination sort: " + pagination.getSortDirection());
			LOG.debug("format date java: " + format.getDate().getJava());
			LOG.debug("format date datepicker: " + format.getDate().getDatepicker());
			LOG.debug("format datetime java: " + format.getDateTime().getJava());
			LOG.debug("default locale: " + getDefaultLocale().getLanguage());
			// LOG.debug("path api prefix: " + getPath().getApiPrefix());
		}

	}

	/**
	 * @return the defaultLocale
	 */
	public Locale getDefaultLocale() {
		return defaultLocale;
	}

	/**
	 * @return the format
	 */
	public Format getFormat() {
		return format;
	}

	/**
	 * @return the pagination
	 */
	public Pagination getPagination() {
		return pagination;
	}

	/**
	 * @return the path
	 */
	public Path getPath() {
		return path;
	}

	/**
	 * @param defaultLocale the defaultLocale to set
	 */
	public void setDefaultLocale(Locale defaultLocale) {
		this.defaultLocale = defaultLocale;
	}

	/**
	 * @param format the format to set
	 */
	public void setFormat(Format format) {
		this.format = format;
	}

	/**
	 * @param pagination the pagination to set
	 */
	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

	/**
	 * @param path the path to set
	 */
	public void setPath(Path path) {
		this.path = path;
	}

}
