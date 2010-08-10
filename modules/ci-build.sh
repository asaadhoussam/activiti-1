set -e
for i in `cat modules.txt`; do cd $i; mvn clean install; cd ..; done
