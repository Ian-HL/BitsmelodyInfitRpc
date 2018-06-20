call mvn install:install-file -Dfile=./lib/javabuilder.jar -DgroupId=com.infit -DartifactId=matlab -Dversion=1.1 -Dpackaging=jar
call mvn install:install-file -Dfile=./lib/SH_MW_HRVLib_V2.9.jar -DgroupId=com.infit -DartifactId=hrvlib -Dversion=2.9 -Dpackaging=jar
call mvn install:install-file -Dfile=./lib/hrv_20170607.jar -DgroupId=com.infit -DartifactId=hrv -Dversion=20170607 -Dpackaging=jar
