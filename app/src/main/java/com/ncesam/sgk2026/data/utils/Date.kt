package com.ncesam.sgk2026.data.utils

import java.time.ZoneOffset
import java.time.format.DateTimeFormatter


val PocketBaseFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSX").withZone(
	ZoneOffset.UTC)