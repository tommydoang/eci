package com.example.tomz.electroniccity.page.bn_tab_home.account.invite;

import com.example.tomz.electroniccity.data.DataManager;
import com.example.tomz.electroniccity.utils.base.BaseViewModel;
import com.example.tomz.electroniccity.utils.rx.SchedulerProvider;

public class FragInviteViewModel extends BaseViewModel<InviteNavigator> {

    public FragInviteViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void linkRequest(String nomorHP){
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .doInviteMemberApiCall(new InviteMemberRequest.req(nomorHP))
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(jsonObject -> {
                    if (jsonObject.getString("status").equals("SUCCESS")){
                        getNavigator().onSuccessLinkRequest(jsonObject.getString("status"), jsonObject.getString("result"));
                    } else {
                        getNavigator().onFailedLinkRequest(jsonObject.getString("status"));
                    }
                    setIsLoading(false);
                }, throwable -> {
                    setIsLoading(false);
                    getNavigator().handleError(throwable);
                })
        );
    }

    public void onSendActivateLink(){
        getNavigator().sendLinkReq();
    }

}
