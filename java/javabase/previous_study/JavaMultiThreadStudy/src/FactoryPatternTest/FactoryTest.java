package FactoryPatternTest;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

interface INoodles {
    public void desc();
}

class LZNoodles implements INoodles {
    @Override
    public void desc() {
        System.out.println("兰州拉面真好吃");
    }
}

class HotNoodles implements INoodles {
    @Override
    public void desc() {
        System.out.println("热干面不好吃");
    }
}

class CQNoodles implements INoodles {
    @Override
    public void desc() {
        System.out.println("重庆小面真好吃");
    }
}

class NoodlesFactory {
    public static final int TYPE_LZ = 1;
    public static final int TYPE_HOT = 2;
    public static final int TYPE_CQ = 3;

    public static INoodles create(int type) {
        switch (type) {
            case TYPE_LZ: {
                return new LZNoodles();
            }
            case TYPE_HOT: {
                return new HotNoodles();
            }
            case TYPE_CQ:
            default: {
                return new CQNoodles();
            }


        }
    }
}

public class FactoryTest {
    public static void main(String[] args) {
        INoodles lz = NoodlesFactory.create(NoodlesFactory.TYPE_LZ);
        lz.desc();
        INoodles hot = NoodlesFactory.create(NoodlesFactory.TYPE_HOT);
        hot.desc();
        INoodles cq = NoodlesFactory.create(NoodlesFactory.TYPE_CQ);
        cq.desc();



    }
}
