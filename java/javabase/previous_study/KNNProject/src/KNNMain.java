import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

//鸢尾花类
class IrisObject {
    public int id;
    public double Sepal_Length;
    public double Sepal_Width;
    public double Petal_Length;
    public double Petal_width;
    public String Species = "";

    @Override
    public String toString() {
        return "id:" + this.id + ", Sepal_Length:" + this.Sepal_Length + ", Sepel_Width:" + this.Sepal_Width
                + ", Petal_Length:" + this.Petal_Length + ", Petal_Width:" + this.Petal_width
                + ", Species:" + this.Species;
    }

    public IrisObject() {
    }

    public IrisObject(IrisObject obj) {
        this.id = obj.id;
        this.Sepal_Length = obj.Sepal_Length;
        this.Sepal_Width = obj.Sepal_Width;
        this.Petal_Length = obj.Petal_Length;
        this.Petal_width = obj.Petal_width;
        this.Species = obj.Species;
    }
}

public class KNNMain {

    //读取iris.txt数据集
    public static void readCsvFileToData(List<IrisObject> models) throws IOException {
        try{
            String filePath = "/sers/YANGMIN/IdeaProjects/KNNProject/SourceFiles/irisDataSet.txt";
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String tempStr;
            String headLine = reader.readLine();
            System.out.println("文件头部：" + headLine);
////            while ((tempStr = reader.readLine()) != null) {
////                String[] strs = tempStr.split(",");
////                if (strs.length == 6) {
////                    IrisObject model = new IrisObject();
////                    for (int i = 0; i < 6; i++) {
////                        switch (i) {
////                            case 0:
////                                model.id = Integer.parseInt(strs[i]);
////                                break;
////                            case 1:
////                                model.Sepal_Length = Double.parseDouble(strs[i]);
////                                break;
////                            case 2:
////                                model.Sepal_Width = Double.parseDouble(strs[i]);
////                                break;
////                            case 3:
////                                model.Petal_Length = Double.parseDouble(strs[i]);
////                                break;
////                            case 4:
////                                model.Petal_width = Double.parseDouble(strs[i]);
////                                break;
////                            case 5:
////                                model.Species = strs[i];
////                                break;
////                        }
////                    }
////                    models.add(model);
//                } else {
//                    System.out.println("文件读取错误");
//                    System.exit(0);
////                    break;
//                }
//            }
//            reader.close();

        }
        catch (RuntimeException e)
        {
            throw e;
        }
        finally {
            System.out.println("我执行了finally");
        }
        System.out.println("我在catch后面");


    }

    //使用极差法来规则化各列数据信息
    public static void propertyRegularize(List<Double> values) {
        Double max = values.get(0);
        Double min = values.get(0);
        Double temp = 0.0;
        for (Double i : values) {
            if (max <= i) {
                max = i;
            }
            if (min >= i) {
                min = i;
            }
        }
        for (int j = 0; j < values.size(); j++) {
            temp = (values.get(j) - min) / (max - min);
            temp = ((int) (temp * 100)) / 100.0;
            values.set(j, temp);
        }
    }

    //计算每个测试数据到各个训练数据的距离
    public static Map<Integer, Double> caculateDistance(IrisObject testData, List<IrisObject> models) {
        HashMap<Integer, Double> map = new HashMap<Integer, Double>();
        double distance;
        for (IrisObject model : models) {
            distance = Math.pow((model.Sepal_Length - testData.Sepal_Length), 2);
            distance += Math.pow((model.Sepal_Width - testData.Sepal_Width), 2);
            distance += Math.pow((model.Petal_Length - testData.Petal_Length), 2);
            distance += Math.pow((model.Petal_width - testData.Petal_width), 2);
            distance = Math.sqrt(distance);
            map.put(model.id, distance);
        }
        return map;
    }

    public static void main(String[] args) throws IOException{
        int K = 15;
        ArrayList<IrisObject> models = new ArrayList<IrisObject>();
        ArrayList<IrisObject> cloneModel = new ArrayList<IrisObject>();
        ArrayList<IrisObject> testSet = new ArrayList<IrisObject>();
        IrisObject temp;
        ArrayList<Double> col_1 = new ArrayList<Double>();
        ArrayList<Double> col_2 = new ArrayList<Double>();
        ArrayList<Double> col_3 = new ArrayList<Double>();
        ArrayList<Double> col_4 = new ArrayList<Double>();
        readCsvFileToData(models);
        for (IrisObject obj : models) {
            cloneModel.add(new IrisObject(obj));
        }
        for (IrisObject o : models) {
            col_1.add(o.Sepal_Length);
            col_2.add(o.Sepal_Width);
            col_3.add(o.Petal_Length);
            col_4.add(o.Petal_width);
        }
        System.out.println("程序继续在运行");
        propertyRegularize(col_1);
        propertyRegularize(col_2);
        propertyRegularize(col_3);
        propertyRegularize(col_4);
        for (int i = 0; i < models.size(); i++) {
            temp = models.get(i);
            temp.Sepal_Length = col_1.get(i);
            temp.Sepal_Width = col_2.get(i);
            temp.Petal_Length = col_3.get(i);
            temp.Petal_width = col_4.get(i);
        }
        //将第13，79，109条记录加入到测试集中
        Collections.addAll(testSet, models.get(12), models.get(78), models.get(108));
        int[] idSet = new int[]{13, 79, 109};
        for (IrisObject i : models) {
            System.out.println(i.toString());
        }
        int[][] ids = new int[testSet.size()][K];
        int row = 0;
        int col = 0;
        for (IrisObject test : testSet) {
            Map<Integer, Double> map = caculateDistance(test, models);
            List<Map.Entry<Integer, Double>> list = new ArrayList<Map.Entry<Integer, Double>>(map.entrySet());
            Collections.sort(list, new Comparator<Map.Entry<Integer, Double>>() {
                //升序排序
                public int compare(Map.Entry<Integer, Double> o1,
                                   Map.Entry<Integer, Double> o2) {
                    return o1.getValue().compareTo(o2.getValue());
                }
            });
            System.out.println(list.toString());
            List<Map.Entry<Integer, Double>> subList = list.subList(1, K + 1);
            for (Map.Entry<Integer, Double> m : subList) {
                ids[row][col] = m.getKey();
                col++;
            }
            col = 0;
            row++;
        }

        String[][] species = new String[testSet.size()][K];

        //得到一个3行K列的二维数组。
        for (int i = 0; i < testSet.size(); i++) {
            for (int j = 0; j < K; j++) {
                System.out.print(ids[i][j] + " ");
                for (IrisObject thisObj : models) {
                    if (thisObj.id == ids[i][j]) {
                        species[i][j] = thisObj.Species;
                    }
                }
            }
            System.out.println("");
        }
        System.out.println("");
        Map<String, Integer> counterMap = new HashMap<String, Integer>();
        //输出每个测试集附近K个邻近点的类别
        for (int i = 0; i < testSet.size(); i++) {
            System.out.print(idSet[i] + ": ");
            for (int j = 0; j < K; j++) {
                System.out.print(species[i][j] + " ");
            }
            System.out.println("");
        }
        System.out.println("");
        for (int i = 0; i < testSet.size(); i++) {
            int maxValue = 0;
            String maxKey = "";
            for (int j = 0; j < K; j++) {
                if (!counterMap.containsKey(species[i][j])) {
                    counterMap.put(species[i][j], 1);
                } else {
                    int frequence = counterMap.get(species[i][j]);
                    counterMap.put(species[i][j], frequence + 1);
                }
            }
            for (Map.Entry<String, Integer> entry : counterMap.entrySet()) {
                if (entry.getValue() >= maxValue) {
                    maxValue = entry.getValue();
                    maxKey = entry.getKey();
                }
            }

            System.out.println(idSet[i] + ": 类别占比最多的是" + maxKey + ",类别占比为：" + maxValue + "/" + K + ",该测试数据原类别为：" + testSet.get(i).Species);
            counterMap.clear();
        }
    }
}
