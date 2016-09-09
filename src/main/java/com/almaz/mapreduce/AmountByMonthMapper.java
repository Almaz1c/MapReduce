package com.almaz.mapreduce;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class AmountByMonthMapper 
	extends Mapper<LongWritable, Text, Text, IntWritable>
{
	public void map(LongWritable key, Text value, Context context)
		throws IOException, InterruptedException
	{
		String line = value.toString();
		String[] fields = line.split("\t");
		
		String date = fields[1].replaceFirst("^[0-9]{2}\\.","");
		date = date.replaceFirst("^[0-9]{1,2} июля ","07.");
		date = date.replaceFirst("^[0-9]{1,2} августа ","08.");
		date = date.replaceFirst("\\.$","");
		date = date.replaceFirst("([0-9]{2})\\.([0-9]{4})","$2.$1");
		if(fields.length > 3)
		{
			context.write(new Text(date), new IntWritable(1));
//			context.write(new Text(fields[3].replaceAll(",.*","")), new IntWritable(1));
		}
	}
}
