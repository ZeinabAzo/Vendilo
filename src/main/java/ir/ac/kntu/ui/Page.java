package ir.ac.kntu.ui;

import java.util.ArrayList;

public class Page {

    private Page previousPage;

    public Page(Page previousPage){
        this.previousPage=previousPage;
    }

    public Page getPreviousPage() {
        return previousPage;
    }
}
