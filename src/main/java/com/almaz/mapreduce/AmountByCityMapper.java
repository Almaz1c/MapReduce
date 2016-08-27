package com.almaz.mapreduce;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class AmountByCityMapper 
	extends Mapper<LongWritable, Text, Text, IntWritable>
{
	public void map(LongWritable key, Text value, Context context)
		throws IOException, InterruptedException
	{
		String line = value.toString();
		String[] fields = line.split("\t");
		
		if(fields.length > 3)
		{
//			context.write(new Text(fields[3]), new IntWritable(1));
			context.write(new Text(fields[3].replaceAll(",.*","")), new IntWritable(1));
		}
	}
}
