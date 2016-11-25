package com.madhouse.dsp.client;

import com.domain.MadhouseParam;
import com.google.protobuf.GeneratedMessage;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by zhujiajun
 * 16/3/10 18:00
 */
public class YoukuClient extends BaseClient {

    public YoukuClient(String name) {
        super(name);
    }

    @Override
    public GeneratedMessage genBidRequest(MadhouseParam madParam) {
        return null;
    }

    @Override
    public GeneratedMessage parseFrom(InputStream in) throws IOException {
        return null;
    }
}
