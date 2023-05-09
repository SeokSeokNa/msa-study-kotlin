package io.seok.userservice.util

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule

val mapperUtil = ObjectMapper().registerKotlinModule()