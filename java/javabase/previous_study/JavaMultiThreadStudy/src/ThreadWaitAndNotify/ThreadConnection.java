package ThreadWaitAndNotify;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.ArrayList;

class WriteData {
    public void writeMethod(PipedOutputStream out) {
        try {
            System.out.println("Write：");
            for (int i = 0; i < 20; i++) {
                String s = "" + (i + 1);
                out.write(s.getBytes());
                System.out.print(s);
            }
            System.out.println();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ReadData{
    public void readMethod(PipedInputStream in)
    {
        try {
            System.out.println("read:");
            byte[] b=new byte[20];
            int count=0;
            while((count=in.read(b))!=-1)
            {
                String readData=new String(b,0,count);
                System.out.print(readData);
            }
            System.out.println();
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class RThread extends Thread{
    private ReadData readData;
    private PipedInputStream in;
    public RThread(ReadData readData,PipedInputStream in)
    {
        this.readData=readData;
        this.in=in;
    }

    @Override
    public void run() {
        super.run();
        readData.readMethod(in);
    }
}

class WThread extends Thread{
    private WriteData writeData;
    private PipedOutputStream out;
    public WThread(WriteData writeData,PipedOutputStream out)
    {
        this.writeData=writeData;
        this.out=out;
    }

    @Override
    public void run() {
        super.run();
        writeData.writeMethod(out);
    }
}

public class ThreadConnection {

    public static void main(String[] args)
    {
        try {
            WriteData writeData=new WriteData();
            ReadData readData=new ReadData();
            PipedInputStream in=new PipedInputStream();
            PipedOutputStream out=new PipedOutputStream();
            in.connect(out);
            RThread r=new RThread(readData,in);
            r.start();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            WThread w=new WThread(writeData,out);
            w.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




}

