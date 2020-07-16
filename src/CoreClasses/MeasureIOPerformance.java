/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CoreClasses;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.Scanner;

/**
 *
 * @author R3DN3T
 */
public final class MeasureIOPerformance {
        static final int SIZE_GB = Integer.getInteger("sizeGB", 8);
        static final int BLOCK_SIZE = 64 * 1024;
        static final int blocks = (int) (((long) SIZE_GB << 30) / BLOCK_SIZE);
        static final byte[] acceptBuffer = new byte[1024];

        public void main() throws IOException {
            System.out.println("Starting");
            File file = new File("h:\\moo");
            FileChannel fc = new FileOutputStream(file).getChannel();
            ByteBuffer directBuffer = ByteBuffer.allocateDirect(BLOCK_SIZE);
            byte[] b;
            double block = System.nanoTime() + 20000000000.0;
            while (System.nanoTime()<block) {
                        directBuffer.clear();
                        while (directBuffer.remaining() > 0) {
                            fc.write(directBuffer);
                        }
            }
            System.out.println("Writing done");
            double fsize = file.length()/1024/1024;
            System.out.println("Writing Speed: " + fsize/20 + " mb/s");
            
            
            
            FileChannel inputStream = null;
            inputStream = new FileInputStream(file).getChannel();
            
            double filesize = inputStream.size()/1024/1024;
            double freadpos = inputStream.position()/1024/1024;
            b = new byte [1024];   
            //System.nanoTime()<blockread||
            int proc;
            //while (inputStream.available()!=0) {
            int read;
            double blockread = System.nanoTime();
            double readdata = 0;
            int blocksdyn = (int) (((long) SIZE_GB << 30) / inputStream.size());
            double stopread = System.nanoTime() + 20000000000.0;
            while (System.nanoTime()<stopread) {
            /*for (int i = 0; i < blocksdyn; i++) {
                        directBuffer.clear();
                        while (directBuffer.remaining() > 0) {
                            inputStream.read(directBuffer);
                            
                        }
                    }*/
            inputStream.read(directBuffer);
            if(inputStream.position() <= fsize){
                
            }
            readdata+=inputStream.size();
            }
            double blockreaddone = System.nanoTime();
            
            
            double blockreadspeed = System.nanoTime();
            long l = (new Double(blockreadspeed-blockread)).longValue();
            long seconds = l/1000000000;    
            System.out.println(blockreaddone-blockread);
            System.out.println(fsize);
            System.out.println((inputStream.size()/1024/1024)/20);
            System.out.println("Reading done");
            //measure(new ChannelRw());
                //measure(new StreamRw());
          
          /*byte[] b;
         File file = new File("z:\\moo");
         file.createNewFile();
        FileInputStream fis = null;              
        FileOutputStream fos = null; 
//          fis = new FileInputStream (file);           
            fos = new FileOutputStream (file);             
            b = new byte [1024];              
            int i;              
            StringBuilder sb = new StringBuilder();
            long start = System.nanoTime();
            double block = start + 15000000000.0;
            while (System.nanoTime()<block){                               
                fos.write(b);               
            }
            fos.close();
            System.out.println("Writing done");
            
           FileInputStream inputStream = null;
            inputStream = new FileInputStream(file);
            double blockread = System.nanoTime();
            double filesize = inputStream.getChannel().size();
            //System.nanoTime()<blockread||
            while (System.nanoTime()<blockread+15000000000.0) {
                inputStream.read(b);
                // System.out.println(line);
            }
            double fsize = file.length()/1024/1024;
            double freadpos = inputStream.getChannel().position()/1024/1024;
            double blockreadspeed = System.nanoTime();
            long l = (new Double(blockreadspeed-blockread)).longValue();
            long seconds = l/1000000000;
            System.out.println(freadpos/seconds);
            /*long readspeedstart = System.nanoTime();
            fis = new FileInputStream(file);

			System.out.println("Total file size to read (in bytes) : "
					+ fis.available());

			double blockread = System.nanoTime() + 15000000000.0;
			while (System.nanoTime()<blockread) {
                            fis.read();
			}
                        System.out.println(fis.getChannel().position());
                        System.out.println(fis.getChannel().size());
                        fis.close();*/

            
        }
        
        public long read(File f) throws IOException {
                ByteBuffer buffer = ByteBuffer.allocateDirect(BLOCK_SIZE);
                FileChannel fc = new FileInputStream(f).getChannel();
                long checksum = 0;
                long stopper = System.nanoTime();
                try {
                   // while (stopper<stopper+15000000000.0) {
                        buffer.clear();
                        while (buffer.hasRemaining()) {
                            fc.read(buffer);
                        }
                        buffer.flip();
                        while (buffer.hasRemaining()) {
                            buffer.get(acceptBuffer, 0, Math.min(acceptBuffer.length, buffer.remaining()));
                            checksum += acceptBuffer[acceptBuffer[0]];
                        }
                   // }
                } finally {
                    fc.close();
                }
                return checksum;
        }

        private static void measure(RW rw) throws IOException {
            File file = new File("h:\\moo");
            file.deleteOnExit();
            System.out.println("Writing " + SIZE_GB + " GB " + " with " + rw);
            long start = System.nanoTime();
            rw.write(file);
            long mid = System.nanoTime();
            System.out.println("Reading " + SIZE_GB + " GB " + " with " + rw);
            long checksum = rw.read(file);
            long end = System.nanoTime();
            long size = file.length();
            //System.out.printf("Write speed %.1f GB/s, read Speed %.1f GB/s%n",
           //         (double) size/(mid-start), (double) size/(end-mid));
            System.out.println(checksum);
            double writespeed = (double)end-start / 1000000000.0;
            System.out.println((double) size/(mid-start)+"----"+ (double) size/(end-mid));
            System.out.println(writespeed);
            file.delete();
        }

        interface RW {
            void write(File f) throws IOException;
            long read(File f) throws IOException;
        }

        static class ChannelRw implements RW {
            final ByteBuffer directBuffer = ByteBuffer.allocateDirect(BLOCK_SIZE);

            @Override public String toString() {
                return "Channel";
            }

            @Override public void write(File f) throws IOException {
                FileChannel fc = new FileOutputStream(f).getChannel();
                try {
                    for (int i = 0; i < blocks; i++) {
                        directBuffer.clear();
                        while (directBuffer.remaining() > 0) {
                            fc.write(directBuffer);
                        }
                    }
                } finally {
                    fc.close();
                }
            }
            @Override public long read(File f) throws IOException {
                ByteBuffer buffer = ByteBuffer.allocateDirect(BLOCK_SIZE);
                FileChannel fc = new FileInputStream(f).getChannel();
                long checksum = 0;
                try {
                    for (int i = 0; i < blocks; i++) {
                        buffer.clear();
                        while (buffer.hasRemaining()) {
                            fc.read(buffer);
                        }
                        buffer.flip();
                        while (buffer.hasRemaining()) {
                            buffer.get(acceptBuffer, 0, Math.min(acceptBuffer.length, buffer.remaining()));
                            checksum += acceptBuffer[acceptBuffer[0]];
                        }
                    }
                } finally {
                    fc.close();
                }
                return checksum;
            }
        }

        static class StreamRw implements RW {
            final byte[] buffer = new byte[BLOCK_SIZE];

            @Override public String toString() {
                return "Stream";
            }

            @Override public void write(File f) throws IOException {
                FileOutputStream out = new FileOutputStream(f);
                try {
                    for (int i = 0; i < blocks; i++) {
                        out.write(buffer);
                    }
                } finally {
                    out.close();
                }
            }
            @Override public long read(File f) throws IOException {
                FileInputStream in = new FileInputStream(f);
                long checksum = 0;
                try {
                    for (int i = 0; i < blocks; i++) {
                        for (int remaining = acceptBuffer.length, read;
                             (read = in.read(buffer)) != -1 && (remaining -= read) > 0; )
                        {
                            in.read(acceptBuffer, acceptBuffer.length - remaining, remaining);
                        }
                        checksum += acceptBuffer[acceptBuffer[0]];
                    }
                } finally {
                    in.close();
                }
                return checksum;
            }
        }


        public static void purgeCache() throws IOException, InterruptedException {
            if (System.getProperty("os.name").startsWith("Mac")) {
                new ProcessBuilder("sudo", "purge")
    //                    .inheritIO()
                        .start().waitFor();
            } else {
                new ProcessBuilder("sudo", "su", "-c", "echo 3 > /proc/sys/vm/drop_caches")
    //                    .inheritIO()
                        .start().waitFor();
            }
        }
    }
