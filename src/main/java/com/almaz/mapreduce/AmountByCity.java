package com.almaz.mapreduce;

import java.io.IOException;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class AmountByCity 
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
		job.setJarByClass(AmountByCity.class);
		job.setJobName("Amount by city");
		
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		job.setMapperClass(AmountByCityMapper.class);
		job.setCombinerClass(AmountByCityReducer.class);
		job.setReducerClass(AmountByCityReducer.class);
		
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(IntWritable.class);
		
		System.exit(job.waitForCompletion(true) ? 0 : 1);
		
	}
}
