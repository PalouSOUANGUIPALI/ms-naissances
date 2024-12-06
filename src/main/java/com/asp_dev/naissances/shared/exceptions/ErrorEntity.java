package com.asp_dev.naissances.shared.exceptions;

import java.time.LocalDateTime;

public record ErrorEntity(String code, String message, LocalDateTime timestamp, int stackTrace) {
}
