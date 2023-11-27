package weather_analyser;

import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

//public class MeteoReduce extends Reducer<Text, IntWritable, Text, Text> {
//    @Override
//    public void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
//        int sumTemperature = 0;
//        int sumWindSpeed = 0;
//        int sumPressure = 0;
//        int sumSkyCover = 0;
//        int countTemperature = 0;
//        int countWindSpeed = 0;
//        int countPressure = 0;
//        int countSkyCover = 0;
//        int minTemperature = Integer.MAX_VALUE;
//        int minWindSpeed = Integer.MAX_VALUE;
//        int minPressure = Integer.MAX_VALUE;
//        int minSkyCover = Integer.MAX_VALUE;
//        int maxTemperature = Integer.MIN_VALUE;
//        int maxWindSpeed = Integer.MIN_VALUE;
//        int maxPressure = Integer.MIN_VALUE;
//        int maxSkyCover = Integer.MIN_VALUE;
//
//        String outputKey = key.toString().split("\\|")[1]; // Extract the date from the key
//        
//        for (IntWritable value : values) {
//            int val = value.get();
//            
//            // Extract the parameter type from the key
//            String parameterType = key.toString().split("\\|")[0];
//
//            if ("Temperature".equals(parameterType)) {
//                sumTemperature += val;
//                minTemperature = Math.min(minTemperature, val);
//                maxTemperature = Math.max(maxTemperature, val);
//                countTemperature++;
//            } else if ("WindSpeed".equals(parameterType)) {
//                sumWindSpeed += val;
//                minWindSpeed = Math.min(minWindSpeed, val);
//                maxWindSpeed = Math.max(maxWindSpeed, val);
//                countWindSpeed++;
//            } else if ("Pressure".equals(parameterType)) {
//                sumPressure += val;
//                minPressure = Math.min(minPressure, val);
//                maxPressure = Math.max(maxPressure, val);
//                countPressure++;
//            } else if ("SkyCover".equals(parameterType)) {
//                sumSkyCover += val;
//                minSkyCover = Math.min(minSkyCover, val);
//                maxSkyCover = Math.max(maxSkyCover, val);
//                countSkyCover++;
//            }
//        }
//
//        int avgTemperature = countTemperature > 0 ? sumTemperature / countTemperature : 0;
//        int avgWindSpeed = countWindSpeed > 0 ? sumWindSpeed / countWindSpeed : 0;
//        int avgPressure = countPressure > 0 ? sumPressure / countPressure : 0;
//        int avgSkyCover = countSkyCover > 0 ? sumSkyCover / countSkyCover : 0;
//
//        String outputValue = "Min Temperature: " + (countTemperature > 0 ? minTemperature : "No data") + ", Max Temperature: " + (countTemperature > 0 ? maxTemperature : "No data") +
//                ", Average Temperature: " + (countTemperature > 0 ? avgTemperature : "No data") + "\n" +
//                "Min Wind Speed: " + (countWindSpeed > 0 ? minWindSpeed : "No data") + ", Max Wind Speed: " + (countWindSpeed > 0 ? maxWindSpeed : "No data") +
//                ", Average Wind Speed: " + (countWindSpeed > 0 ? avgWindSpeed : "No data") + "\n" +
//                "Min Pressure: " + (countPressure > 0 ? minPressure : "No data") + ", Max Pressure: " + (countPressure > 0 ? maxPressure : "No data") +
//                ", Average Pressure: " + (countPressure > 0 ? avgPressure : "No data") + "\n" +
//                "Min Sky Cover: " + (countSkyCover > 0 ? minSkyCover : "No data") + ", Max Sky Cover: " + (countSkyCover > 0 ? maxSkyCover : "No data") +
//                ", Average Sky Cover: " + (countSkyCover > 0 ? avgSkyCover : "No data");
//
//        context.write(new Text(outputKey), new Text(outputValue));
//    }
//}

public class MeteoReduce extends Reducer<Text, IntWritable, Text, Text> {
    @Override
    public void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        int sum = 0;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        int count = 0;

        for (IntWritable value : values) {
            int val = value.get();
            sum += val;
            min = Math.min(min, val);
            max = Math.max(max, val);
            count++;
        }

        int avg = count > 0 ? sum / count : 0;

        String outputKey = key.toString().split("\\|")[1]; // Extract the date from the key
        String parameterType = key.toString().split("\\|")[0]; // Extract the parameter type from the key

        String outputValue = "Min " + parameterType + ": " + (count > 0 ? min : "No data") + ", Max " + parameterType + ": " + (count > 0 ? max : "No data") +
                ", Average " + parameterType + ": " + (count > 0 ? avg : "No data");

        context.write(new Text(outputKey), new Text(outputValue));
    }
}