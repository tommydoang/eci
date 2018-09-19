package com.example.tomz.electroniccity.data;

import com.example.tomz.electroniccity.data.local.db.DbHelper;
import com.example.tomz.electroniccity.data.local.pref.PreferencesHelper;
import com.example.tomz.electroniccity.data.remote.ApiHelper;

public interface DataManager extends DbHelper, PreferencesHelper, ApiHelper {

    void authInfo(
            String deviceId,
            String authKey
    );

    void userInfo(
            String userID,
            String userName, String userKTP,
            String userEmail, String userFullName, String userBirthday,
            String userPhone, String userHandphone,
            String userAddress1,
            String userAddress2,
            String userAddress3,
            String userAddress4,
            String userDistrictID, String userZipCode,
            String userRuleID, String userTypeID,
            String userRate, String userMemberID,
            String userActivateDate, String userChannel
    );

//    void allProducts(
//            String id_category_head,
//            String id_prod, String sku,
//            String name_prod, String tags,
//            String model_prod, String size,
//            String id_brand, String id_cat,
//            String id_cat_new, String product_description,
//            String long_description, String real_price,
//            String spc_price, String video_link,
//            String isPublish, String userCreated,
//            String dateCreated, String userModified,
//            String dateModified, String publisher,
//            String publisher_time, String publisher_user, String type_prod,
//            String delivery_type, String city_free_shipping,
//            String city_free_shipping_all, String viewed,
//            String turun_harga, String naik_harga,
//            String img_best, String img_thumb,
//            String merk_name_brand, String attr_id_prod,
//            String stock_store_code, String number_stock,
//            String stock_item_bought
//    );

}
