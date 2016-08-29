#!/bin/bash
sort -rn -t $'\t' -k2 -o amountByCitySorted.txt amountByCity7.txt
awk 'BEGIN {sum=0;} {sum = sum + $2;} END {print sum;}' amountByCity7.txt
