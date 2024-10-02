package com.abdullahkahraman.demo.classStructure;

public class FinalClass {
    //can not modify
    private final String name = "";

    //can not be override
    public final void finalMethod() {
    }
}

//Cannot inherit from final class
//class FinalSub extends FinalClass { }

class FinalSubClass extends FinalClass {

}
