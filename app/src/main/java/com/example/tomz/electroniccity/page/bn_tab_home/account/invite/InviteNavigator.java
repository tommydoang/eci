package com.example.tomz.electroniccity.page.bn_tab_home.account.invite;

public interface InviteNavigator {

    void sendLinkReq();
    void onSuccessLinkRequest(String status, String message);
    void handleError(Throwable throwable);
    void onFailedLinkRequest(String status);

}
