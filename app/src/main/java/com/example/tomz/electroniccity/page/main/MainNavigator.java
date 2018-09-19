package com.example.tomz.electroniccity.page.main;

import com.example.tomz.electroniccity.data.model._testdoang.DataModelIdeas;

import java.util.List;

/**
 * Created by tomz on 01/17/17.
 */

public interface MainNavigator {

    void onSuccessGetAuthKey(String status);

    void handleError(Throwable throwable);

    void updateBlog(List<DataModelIdeas> dataModelIdeasList);
}
