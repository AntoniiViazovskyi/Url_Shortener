package com.goit.service.exception.exceptions.generalExceptions;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UnauthorizedAccessExceptionTest {
    private UnauthorizedAccessException accessException;

    @Test
    public void cleanTest(){
        //When
        accessException = new UnauthorizedAccessException();

        //Then
        assertEquals("You don't have permission to this data", accessException.getMessage());
    }

    @Test
    public void withParameter(){
        //When
        List<Long> ids = new ArrayList<>();
        ids.add(13L);
        ids.add(2L);

        accessException = new UnauthorizedAccessException(ids);

        //Then
        String expectedExceptionMessage = """
            You are not creator of this link. Users can edit only their own links.
            The id's of your links: %s
            """;
        assertEquals(String.format(expectedExceptionMessage, ids), accessException.getMessage());
    }
}
