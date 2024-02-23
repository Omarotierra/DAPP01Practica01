package org.uv.dapp01practica01;

import java.sql.Connection;
import java.util.List;

public abstract class SelectionDB<T> {
    protected T pojo;
    
    protected SelectionDB(T pojo){
        this.pojo = pojo;
    }
    public abstract List<T> select(Connection con);
 
}
