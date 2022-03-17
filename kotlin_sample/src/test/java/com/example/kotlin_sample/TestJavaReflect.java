package com.example.kotlin_sample;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TestJavaReflect {


    @Test
    public void xxx(String[] args) {

        List<? super Programmer> programmerLowerList = new ArrayList<Worker>();
//        Programmer programmer = programmerLowerList.get(0);
        Worker worker = (Worker) programmerLowerList.get(0);
        JavaArchitectProgrammer jj = (JavaArchitectProgrammer) programmerLowerList.get(0);

        Programmer pp = new Programmer();
        JavaProgrammer javaProgrammer = new JavaProgrammer();
        JavaArchitectProgrammer architectProgrammer = new JavaArchitectProgrammer();
//        Worker worker = new Worker();

//        programmerLowerList.add(worker);
        programmerLowerList.add(pp);
        programmerLowerList.add(javaProgrammer);
        programmerLowerList.add(architectProgrammer);
    }


    class Person {
    }

    class Worker extends Person {

    }

    class Programmer extends Worker {

    }

    class JavaProgrammer extends Programmer {

    }

    class JavaArchitectProgrammer extends JavaProgrammer {

    }
}

