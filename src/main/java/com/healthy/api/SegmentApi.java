package com.healthy.api;

import lombok.Setter;
import lombok.Getter;

public class SegmentApi {
    @Getter
    @Setter
    public static class Request{
        private Long player_id;
    }

    @Getter
    @Setter
    public static class Response{
        private String title;
        private boolean status;
    }
}
