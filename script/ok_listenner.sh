#! /usr/bin/env bash

listen_dir_path=/Users/guzhandong/workfile/github_workfile/es-com.guzhandong.helper
ok_dir_path=/Users/guzhandong/workfile/github_workfile/es-com.guzhandong.helper

file_mark=kg_

count=`ls $listen_dir_path  | grep $file_mark | wc -l`
while  [$count -eq "0" ]
do
    echo 'sleep 5 min'
    sleep 1*60*5
    count=`ls $dir_path | grep $file_mark| wc -l`

done

mv $dir_path/* $ok_dir_path/
exit 0