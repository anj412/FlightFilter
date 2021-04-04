package com.grednine.testing;

import com.gridnine.testing.domain.Segment;
import com.gridnine.testing.service.SegmentService;
import com.gridnine.testing.service.exceptions.SegmentConstrainException;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class SegmentServiceTest {

    @Test
    public void testFalseCreateSegment(){
        SegmentConstrainException thrown = assertThrows(SegmentConstrainException.class, ()->
                SegmentService.createSegment(LocalDateTime.now(), LocalDateTime.now().minusMinutes(5)));
        assertNotNull(thrown);

        SegmentConstrainException thrown1 = assertThrows(SegmentConstrainException.class, ()->
                SegmentService.createSegment(LocalDateTime.now(), null));
        assertNotNull(thrown1);
    }

    @Test
    public void testOkCreateSegment(){
        assertNotNull(SegmentService.createSegment(LocalDateTime.now(), LocalDateTime.now().plusHours(5)));
    }

}
