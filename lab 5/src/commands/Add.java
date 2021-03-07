package commands;

import general.GeneralCollection;

import java.util.Collection;

public class Add implements CommandDo {
    Collection collection;

    public Add(Collection collection){
        this.collection=collection;
    }

    @Override
    public void execute(String name, GeneralCollection generalCollection) {
      collection.add(1);
    }
}
