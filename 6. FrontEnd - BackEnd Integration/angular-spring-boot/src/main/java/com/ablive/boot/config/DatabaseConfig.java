package com.ablive.boot.config;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableJpaRepositories("com.ablive.boot.repository")
@EnableTransactionManagement
public class DatabaseConfig {

}