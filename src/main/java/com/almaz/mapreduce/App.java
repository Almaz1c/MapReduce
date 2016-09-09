package com.almaz.mapreduce;

import java.io.IOException;
import java.io.OutputStream;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hdfs.MiniDFSCluster;

/**
 * Hello world!
 *
 */
public class App 
{

	public static void main( String[] args ) throws IOException
    {
		MiniDFSCluster cluster;
		FileSystem fs;
        System.out.println( "Hello World!" );
		Configuration conf = new Configuration();
		conf.setStrings("dfs.datanode.data.dir.perm", "775");
//		cluster = new MiniDFSCluster.Builder(conf).build();
		cluster = new MiniDFSCluster(conf, 1, true, null);
		fs = cluster.getFileSystem();
		OutputStream out = fs.create(new Path("dir/file"));
		out.write("content".getBytes("UTF-8"));
		out.close();
		System.out.println("Almazzzz");

    	Path file = new Path("/dir/file");
		System.out.println("Almazaaaa");
    	FileStatus stat = fs.getFileStatus(file);
		System.out.println("Almazuuuuu");
    	System.out.println("$$$$$ " + stat.getPath().toUri().getPath());
		System.out.println("Almazbbbbb");
		cluster.shutdown();
		System.out.println("Almazeeeee");
    }
}
