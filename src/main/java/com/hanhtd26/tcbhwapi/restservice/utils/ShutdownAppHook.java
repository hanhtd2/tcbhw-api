package com.hanhtd26.tcbhwapi.restservice.utils;

import com.hanhtd26.tcbhwapi.restservice.service.ApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * This class is used for prevent data loss and data persistence
 */
public class ShutdownAppHook extends Thread {

    private static final Logger logger = LoggerFactory.getLogger(ApiService.class);
    public void run() {
        //check this application has data to store
        if (null != ApiService.map && ApiService.map.size() > 0) {
            MapIO mapIO = new MapIO();
            try {
                mapIO.writeMapToFile(ApiService.map);
            } catch (IOException e) {
                logger.error(e.getMessage(), e);
            }
        }
    }
}
