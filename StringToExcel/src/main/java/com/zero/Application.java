package com.zero;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

import com.zero.utils.ConvertToExcel;

/**
 * main方法
 */
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class Application {

	public static void main(String[] args) {
		try {
			ConvertToExcel.exportExcel();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
