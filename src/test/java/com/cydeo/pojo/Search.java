package com.cydeo.pojo;

import java.util.List;

public class Search {

    private List<Spartan> content;

    public List<Spartan> getContent() {
        return content;
    }

    public void setContent(List<Spartan> content) {
        this.content = content;
    }

    public int getTotalElement() {
        return totalElement;
    }

    public void setTotalElement(int totalElement) {
        this.totalElement = totalElement;
    }

    private int totalElement;
}
