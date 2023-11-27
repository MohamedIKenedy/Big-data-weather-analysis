package weather_analyser;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class Main {

    public static void main(String[] args) throws Exception {
        if (args.length != 2) {
            System.err.println("Usage: WeatherAnalyzerDriver <input path> <output path>");
            System.exit(-1);
        }

        // Create a Hadoop configuration
        Configuration conf = new Configuration();
        
        // Create a MapReduce job
        @SuppressWarnings("deprecation")
		Job job = new Job(conf, "Weather Analyzer");
        job.setJarByClass(Main.class);

        // Set the Mapper and Reducer classes
        job.setMapperClass(MeteoMap.class);
        job.setReducerClass(MeteoReduce.class);

        // Set the output key and value classes
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        // Set the input and output paths
        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        // Run the job and wait for it to complete
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}
