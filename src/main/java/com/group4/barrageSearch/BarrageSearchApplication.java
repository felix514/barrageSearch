package com.group4.barrageSearch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.group4.barrageSearch.dao")
@EnableElasticsearchRepositories(basePackages = "com.group4.barrageSearch.dao")
@EntityScan(basePackages = "com.group4.barrageSearch.entity")
@Import(RedisConfig.class)
public class BarrageSearchApplication {

	public static void main(String[] args) {
		SpringApplication.run(BarrageSearchApplication.class, args);
	}
}
