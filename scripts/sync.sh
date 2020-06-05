#!/bin/bash

repo_path=/opt/yocto-rpi/meta-rpi
build_path=/opt/yocto-rpi/build

local=conf/local.conf
layer=conf/bblayers.conf

latest_local=`ls -u $repo_path/$local $build_path/$local | head -1`
latest_layer=`ls -u $repo_path/$layer $build_path/$layer | head -1`

echo "latest $latest_local, layer $latest_layer"

local_sync='n'
layer_sync='n'

diff `ls -ru $repo_path/$local $build_path/$local` > /dev/null

if [ $? == 1 ]; then
   echo "local.conf differences:"
   diff `ls -ru $repo_path/$local $build_path/$local`
   read -p 'sync local.conf? [Y/n] ' local_sync
   local_sync=`echo $local_sync | awk '{print tolower($0)}'`
   if [ x$local_sync == "x" ]; then
       local_sync='y'
   fi
fi

diff `ls -ru $repo_path/$layer $build_path/$layer` > /dev/null

if [ $? == 1 ]; then
   echo "bblayers.conf differences:"
   diff `ls -ru $repo_path/$layer $build_path/$layer`
   read -p 'sync bblayers.conf? [Y/n] ' layer_sync
   layer_sync=`echo $layer_sync | awk '{print tolower($0)}'`
   if [ x$layer_sync == "x" ]; then
       layer_sync='y'
   fi
fi

#echo "Res $local_sync $layer_sync"

if [ $local_sync == 'y' ]; then
    echo "Copy `ls -u $repo_path/$local $build_path/$local`"
    cp `ls -u $repo_path/$local $build_path/$local`    
fi

if [ $layer_sync == 'y' ]; then
    echo "Copy `ls -u $repo_path/$layer $build_path/$layer`"
    cp `ls -u $repo_path/$layer $build_path/$layer`    
fi



