package weather_analyser;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class MeteoMap extends Mapper<LongWritable, Text, Text, IntWritable> {
    @Override
    public void map(LongWritable key, Text value, Context context)
            throws IOException, InterruptedException {
        String line = value.toString();
        
        // Split the line into fields
        String[] fields = line.split(", ");
        if (fields.length < 6) {
            System.err.println("Skipping line with missing fields: " + line);
            return;
        }
        
                // Extract the date, temperature, wind speed, pressure, and sky cover
        String date = fields[0].split(": ")[1];
        String tempStr = fields[2].split(": ")[1];
        String windSpeedStr = fields[3].split(": ")[1];
        String pressureStr = fields[4].split(": ")[1];
        String skyCoverStr = fields[5].split(": ")[1];
        
        // Extract the year, month, and day from the date
        String year = date.substring(0, 4);
        String month = date.substring(4, 6);
        String day = date.substring(6, 8);
        
        // Emit the year, month, and day as the key and all parameters as values
        int temperature = Integer.parseInt(tempStr)/100;
        int windSpeed = Integer.parseInt(windSpeedStr)/10;
        int pressure = Integer.parseInt(pressureStr);
        int skyCover = Integer.parseInt(skyCoverStr);
        
//        context.write(new Text(year + "-" + month + "-" + day), new IntWritable(temperature));
//        context.write(new Text(year + "-" + month + "-" + day), new IntWritable(windSpeed));
//        context.write(new Text(year + "-" + month + "-" + day), new IntWritable(pressure));
//        context.write(new Text(year + "-" + month + "-" + day), new IntWritable(skyCover));
        context.write(new Text("Temperature|" + year + "-" + month + "-" + day), new IntWritable(temperature));
        context.write(new Text("WindSpeed|" + year + "-" + month + "-" + day), new IntWritable(windSpeed));
        context.write(new Text("Pressure|" + year + "-" + month + "-" + day), new IntWritable(pressure));
        context.write(new Text("SkyCover|" + year + "-" + month + "-" + day), new IntWritable(skyCover));

    }
}
