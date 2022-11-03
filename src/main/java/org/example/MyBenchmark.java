package org.example;

import org.openjdk.jmh.annotations.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
@Fork(value = 1)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
public class MyBenchmark {

    private final List<Person> personList = List.of(
            new Person("Antonio", 20, Gender.MALE),
            new Person("Alina Smith", 33, Gender.FEMALE),
            new Person("Helen White", 57, Gender.FEMALE),
            new Person("Alex Boz", 14, Gender.MALE),
            new Person("Jamie Goa", 99, Gender.MALE),
            new Person("Anna Cook", 7, Gender.FEMALE),
            new Person("Zelda Brown", 120, Gender.FEMALE)
    );

    @Benchmark
    public List<Person> testStream() {
        return personList.stream().filter(p -> p.gender() == Gender.FEMALE).toList();
    }

    @Benchmark
    public List<Person> testTraditional() {
        List<Person> females = new ArrayList<>();
        for (Person person : personList) {

            if (person.gender() == Gender.FEMALE) {
                females.add(person);
            }
        }
        return females;
    }
}
