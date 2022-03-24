package com.donus.restapiforum

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching

@SpringBootApplication
@EnableCaching
class RestApiForumApplication

fun main(args: Array<String>) {
    runApplication<RestApiForumApplication>(*args)
}
