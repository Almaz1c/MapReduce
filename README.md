to run that MapReduce task:

> $ su hadoop

> $ cd <MapReduce project>/target/

> $ export HADOOP_CLASSPATH=$(pwd)/MapReduce-1.0.jar

> $ hadoop com.almaz.mapreduce.AmountByCity /user/hdfs/hh_vacancy/data/hh_ru_vacancy_8000000_8.txt /user/hdfs/hh_vacancy/results/amountByCity

Explanation:

If we set key value in 'map()' in that way:

> context.write(new Text(fields[3]), new IntWritable(1));

the output of: 

> $ grep "," amountByCity.txt

return as:

```
Алматы, Абая	14
Алматы, Алатау	35
...
Днепр (Днепропетровск), Вокзальная	1
Екатеринбург, Ботаническая	25
Екатеринбург, Геологическая	86
...
Казань, Авиастроительная	1
Казань, Аметьево	43
Казань, Горки	34
...
Киев, Академгородок	2
Киев, Берестейская	5
Киев, Бориспольская	2
Киев, Васильковская	4
...
Минск, Автозаводская	20
Минск, Академия наук	52
Минск, Борисовский тракт	10
Минск, Восток	19
Минск, Грушевка	15
...
Москва, Авиамоторная	73
Москва, Автозаводская	83
Москва, Академическая	33
...
Москва, Павелецкая, Добрынинская	1
Москва, Павелецкая, Павелецкая	1
Москва, Павелецкая, Павелецкая, Автозаводская	1
Москва, Павелецкая, Профсоюзная	1
Москва, Павелецкая, Серпуховская, Добрынинская	1
Москва, Павелецкая, Серпуховская, Павелецкая, Добрынинская	1
...
Москва, Фили	25
Москва, Фили, Багратионовская	1
Москва, Фили, Багратионовская, Парк Победы	1
Москва, Фрунзенская	11
Москва, Фрунзенская, Киевская	1
Москва, Фрунзенская, Спортивная	1
Москва, Царицыно	8
Москва, Цветной бульвар	31
Нижний Новгород, Автозаводская	6
Нижний Новгород, Буревестник	38
...
Новосибирск, Березовая роща	11
Новосибирск, Гагаринская	16
...
Санкт-Петербург, Автово	6
Санкт-Петербург, Адмиралтейская	31
Санкт-Петербург, Академическая	15
Санкт-Петербург, Балтийская	49
Санкт-Петербург, Бухарестская	47
Санкт-Петербург, Бухарестская, Электросила	5
Санкт-Петербург, Василеостровская	94
Санкт-Петербург, Василеостровская, Спортивная	2
Санкт-Петербург, Владимирская	4
...
```

as we need just city name we do that:

> context.write(new Text(fields[3].replaceAll(",.*","")), new IntWritable(1));


How to run that task:

> $ su hadoop

> $ cd <MapReduceProjRootDir>/target

> $ export HADOOP_CLASSPATH=$(pwd)/MapReduce-1.0.jar

> $ hadoop com.almaz.mapreduce.AmountByCity /user/hdfs/hh_vacancy/data/hh_ru_vaca* /user/hdfs/hh_vacancy/results/amountByCity7

> $ su hdfs

> $ hdfs dfs -ls -R /user/hdfs/hh_vacancy/results

> $ hdfs dfs -get /user/hdfs/hh_vacancy/results/amountByCity7/part-r-00000 <yourHomeDir>/hh_vacancy/results/amountByCity7.txt

Resulted file ("AmountByCity7.txt") of that task performed over 25.7 Gb of vacansies can be found here:

> https://www.dropbox.com/sh/ysuyj3c8u93vcfn/AADkBmPcNmTWeT3n0N7e8Yzha?dl=0



Some sorting and analising of results:

City amount:

> $ awk 'END { print NR; }' amountByCity7.txt

Total amount of vacancies:

> $ awk 'BEGIN {sum=0;} {sum = sum + $2;} END {print sum;}' amountByCity7.txt

Amount of vacancies in Moscow:

> $ grep "Москва" amountByCity7.txt

Sort cities by amount of vacancies:

> $ sort -rn -t $'\t' -k2 -o amountByCitySorted.txt amountByCity7.txt

> $ head -n 15 amountByCitySorted.txt





