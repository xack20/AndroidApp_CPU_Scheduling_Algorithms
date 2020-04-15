package com.example.al;

import java.util.Comparator;

class priority implements Comparator<proccess> {
    @Override
    public int compare(proccess a, proccess b) {
        if(a.pv == b.pv)
        {
            if (a.at < b.at) return (int)(a.at);
        }
        else return (int)(a.pv-b.pv);
        return 0;
    }
}
