package tech.ynfy.common.util;

import java.util.concurrent.atomic.AtomicInteger;

public class SerialNumberUtil {


    private static final AtomicInteger SERIAL = new AtomicInteger(Integer.MAX_VALUE);


    /**
     * @return
     */
    public static Long next() {
        
        long randomFirst =  ( (long) (Math.random() * 0x7)) << 60; // 15f

        long second = System.currentTimeMillis();
        long secondShift = (second & 0xfffff) << 40; // 5f 5*4

        long nodeRandom = (long) (Math.random() * 0xffff);
        long nodeShift = nodeRandom << 24; // 4f 4*4

        long serial = SERIAL.incrementAndGet();
        long serialShift = serial & 0xffffffL; // 6f
        
        long number = randomFirst | secondShift | nodeShift | serialShift;
        
        return number;
    }


    
}
