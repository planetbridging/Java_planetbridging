/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CoreClasses;

/**
 *
 * @author R3DN3T
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author shannon
 */
public class DriveSpeedTest implements Runnable{
    static final int SIZE_GB = Integer.getInteger("sizeGB", 1);
    static final int BLOCK_SIZE = 64 * 1024;
    private double WriteSpeed;
    
    public DriveSpeedTest(){
        this.WriteSpeed = 0;
    }
    
    public void StartDiskTest(){
        try {
            Start();
        } catch (IOException ex) {
            System.out.println(ex);
        }
        System.out.println("DONE!");
    }
    
    private void Start() throws IOException {
        ByteBuffer buffer = ByteBuffer.allocateDirect(BLOCK_SIZE);
        File tmp = File.createTempFile("delete", "me");
        tmp.deleteOnExit();
        int blocks = (int) (((long) SIZE_GB << 30) / BLOCK_SIZE);
        long start = System.nanoTime();
        try (FileChannel fc = new FileOutputStream(tmp).getChannel()) {
            for (int i = 0; i < blocks; i++) {
                buffer.clear();
                while (buffer.remaining() > 0)
                    fc.write(buffer);
            }
        }
        long mid = System.nanoTime();
        try (FileChannel fc = new FileInputStream(tmp).getChannel()) {
            for (int i = 0; i < blocks; i++) {
                buffer.clear();
                while (buffer.remaining() > 0)
                    fc.read(buffer);
            }
        }
        long end = System.nanoTime();

        long size = tmp.length();
        System.out.printf("Write speed %.1f GB/s, read Speed %.1f GB/s%n",
                (double) size/(mid-start), (double) size/(end-mid));
        System.out.println((double) size/(mid-start)*1000 + "WRITE!!!");
        this.WriteSpeed = (double) size/(mid-start)*1000;

    }

    @Override
    public void run() {
        StartDiskTest();
    }
    public double GetWriteSpeed(){return this.WriteSpeed;}
}

