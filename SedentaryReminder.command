#!/bin/bash
read -p "工作时长：" working_time
echo "working..." && sleep $(($working_time * 60)) && pmset displaysleepnow
