package com.app.utils;

public class Pair<T1, T2> implements Comparable<T1> {
    private T1 first;
    private T2 second;

    /**
     * full constructor
     * @param first
     * @param second
     */
    public Pair(T1 first, T2 second) {
        this.first = first;
        this.second = second;
    }

    /**
     * first constructor
     * @param first
     */
    public Pair(T1 first) {
        this.first = first;
    }
    
    public T1 getFirst() {
        return first;
    }

    public void setFirst(T1 first) {
        this.first = first;
    }

    public T2 getSecond() {
        return second;
    }

    public void setSecond(T2 second) {
        this.second = second;
    }

    public int compareTo(T1 anotherInstance) {
        if (anotherInstance instanceof Integer) {
            return ((Integer) first).compareTo((Integer) anotherInstance);
        } else if (anotherInstance instanceof String) {
            return ((String) first).compareTo((String) anotherInstance);
        } else {
            return first.toString().compareTo(anotherInstance.toString());
        }
    }
}
