package com.almaz.mapreduce;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class AmountByEmployerMapper 
	extends Mapper<LongWritable, Text, Text, IntWritable>
{
	public void map(LongWritable key, Text value, Context context)
		throws IOException, InterruptedException
	{
		String line = value.toString();
		String[] fields = line.split("\t");
		
		if(fields.length > 3)
		{
			String keyVal = fields[7].concat(", ").concat(fields[6]);
			context.write(new Text(keyVal), new IntWritable(1));
//			context.write(new Text(fields[7].replaceAll(",.*","")), new IntWritable(1));
		}
	}
}
