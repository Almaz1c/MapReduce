#!/bin/bash
sed 's/,/\t/g' amountByEmployer2.txt > amountByEmployerTab.txt
sort -rn -t $'\t' -k3 -o amountByEmployerSorted2.txt amountByEmployerTab.txt 
sort -t $'\t' -k2 -o amountByEmployerSorted1.txt amountByEmployerTab.txt 
