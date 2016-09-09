package com.almaz.mapreduce;

import java.io.IOException;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class AmountByMonth 
{
	public static void main(String[] args) 
			throws IOException, ClassNotFoundException, InterruptedException
	{
		if(args.length < 2)
		{
			System.err.println("Usage: AmountByCity <inputPath> <outputPath>");
			System.exit(-1);
		}
		Job job = new Job();		
		job.setJarByClass(AmountByMonth.class);
		job.setJobName("Amount by month");
		
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		job.setMapperClass(AmountByMonthMapper.class);
		job.setCombinerClass(AmountByMonthReducer.class);
		job.setReducerClass(AmountByMonthReducer.class);
		
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(IntWritable.class);
		
		System.exit(job.waitForCompletion(true) ? 0 : 1);
		
	}
}
