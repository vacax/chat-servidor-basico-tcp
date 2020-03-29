package edu.pucmm.eict.csbt.util;

import java.io.Serializable;


public interface IObserver extends Serializable {

    public void update(Class clase,Object argumento,Enum anEnum);
}