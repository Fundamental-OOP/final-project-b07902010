#!/bin/bash

cd ./src
rm -r ./*.class
rm -r */*.class
rm -r */*/*.class

javac Main.java
java Main