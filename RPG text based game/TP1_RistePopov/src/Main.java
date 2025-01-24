/*
Author : Riste Popov
Date : February 18 2024

This program is a role playing game that takes an input a phrase that creates a Hero and makes the hero do things
like fight enemies, heal, train and rest. After it has gone through all the actions, or dies before completing them,
there is an output of the outcome: whether the hero survived, how many enemies he beat, level reached and health points
remaining.
*/
public class Main {
    public static void main(String[] args) {
        ArgsProcessor.process(args);
    }
}