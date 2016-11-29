package com.madhouse.dsp.client;

import com.domain.MadhouseParam;
import com.google.protobuf.GeneratedMessage;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by zhujiajun
 * 16/3/10 17:18
 */
public class LetvClient extends BaseClient {

    public LetvClient(String name,String pathname) {
        super(name,pathname);
    }
    
    @Override
    public GeneratedMessage genBidRequest() {
        return null;
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
