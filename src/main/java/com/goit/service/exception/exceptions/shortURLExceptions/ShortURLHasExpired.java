package com.goit.service.exception.exceptions.shortURLExceptions;

import java.time.LocalDate;

public class ShortURLHasExpired extends Exception{
    private static final String SHORT_URL_EXPIRED_BY_CREATION_TIME = "Short URL has expired." +
            "Time of validity of short links is 30 days." +
            "Creation time: %s";

    public ShortURLHasExpired(LocalDate creationTime) {
    }
}
