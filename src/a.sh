#!/bin/bash

rm -r ./*.class
rm -r */*.class
rm -r */*/*.class

javac Main.java
java Main
