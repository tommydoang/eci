package com.example.tomz.electroniccity.utils;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;

import com.example.tomz.electroniccity.adapter.AddressAdapter;
import com.example.tomz.electroniccity.adapter.HistoryOrderAdapter;
import com.example.tomz.electroniccity.adapter.PromoAdapter;
import com.example.tomz.electroniccity.adapter.tab1.Tab1Adapter;
import com.example.tomz.electroniccity.adapter.tab1.Tab1DealAdapter;
import com.example.tomz.electroniccity.adapter.tab2.Tab2Adapter;
import com.example.tomz.electroniccity.adapter.tab3.Tab3Adapter;
import com.example.tomz.electroniccity.adapter.tab4.Tab4Adapter;
import com.example.tomz.electroniccity.adapter.tab5.Tab5Adapter;
import com.example.tomz.electroniccity.adapter.tab6.Tab6Adapter;
import com.example.tomz.electroniccity.adapter.tab7.Tab7Adapter;
import com.example.tomz.electroniccity.adapter.tab8.Tab8Adapter;
import com.example.tomz.electroniccity.adapter.tab9.Tab9Adapter;
import com.example.tomz.electroniccity.data.model.api.membership.DataAddressResponse;
import com.example.tomz.electroniccity.data.model.api.membership.DataHistoryOrderResponse;
import com.example.tomz.electroniccity.data.model.api.products.tab1.DataDealTab1Response;
import com.example.tomz.electroniccity.data.model.api.products.tab1.DataProductTab1Response;
import com.example.tomz.electroniccity.data.model.api.products.tab2.DataProductTab2Response;
import com.example.tomz.electroniccity.data.model.api.products.tab3.DataProductTab3Response;
import com.example.tomz.electroniccity.data.model.api.products.tab4.DataProductTab4Response;
import com.example.tomz.electroniccity.data.model.api.products.tab5.DataProductTab5Response;
import com.example.tomz.electroniccity.data.model.api.products.tab6.DataProductTab6Response;
import com.example.tomz.electroniccity.data.model.api.products.tab7.DataProductTab7Response;
import com.example.tomz.electroniccity.data.model.api.products.tab8.DataProductTab8Response;
import com.example.tomz.electroniccity.data.model.api.products.tab9.DataProductTab9Response;
import com.example.tomz.electroniccity.data.model.api.promo.DataPromoResponse;

import java.util.List;

public final class BindingUtils {

    private BindingUtils() {
        // This class is not publicly instantiable
    }

    @BindingAdapter({"adapterbangsat"})
    public static void addBlogItems(RecyclerView recyclerView, List<DataProductTab1Response> modelIdeasList) {
        Tab1Adapter adapter = (Tab1Adapter) recyclerView.getAdapter();
        if (adapter != null) {
            adapter.clearItems();
            adapter.addItems(modelIdeasList);
        }
    }

    @BindingAdapter({"adapterdeal"})
    public static void addDealItems(RecyclerView recyclerView, List<DataDealTab1Response> modelDealList){
        Tab1DealAdapter adapter = (Tab1DealAdapter) recyclerView.getAdapter();
        if (adapter != null){
            adapter.clearItems();
            adapter.addItems(modelDealList);
        }
    }

    @BindingAdapter({"adapterprodtab2"})
    public static void addProdItemsTab2(RecyclerView recyclerView, List<DataProductTab2Response> modelProdList){
        Tab2Adapter adapter = (Tab2Adapter)recyclerView.getAdapter();
        if (adapter != null){
            adapter.clearItems();
            adapter.addItems(modelProdList);
        }
    }

    @BindingAdapter({"adapterprodtab3"})
    public static void addProdItemsTab3(RecyclerView recyclerView, List<DataProductTab3Response> modelProdList){
        Tab3Adapter adapter = (Tab3Adapter)recyclerView.getAdapter();
        if (adapter != null){
            adapter.clearItems();
            adapter.addItems(modelProdList);
        }
    }


    @BindingAdapter({"adapterprodtab4"})
    public static void addProdItemsTab4(RecyclerView recyclerView, List<DataProductTab4Response> modelProdList){
        Tab4Adapter adapter = (Tab4Adapter)recyclerView.getAdapter();
        if (adapter != null){
            adapter.clearItems();
            adapter.addItems(modelProdList);
        }
    }

    @BindingAdapter({"adapterprodtab5"})
    public static void addProdItemsTab5(RecyclerView recyclerView, List<DataProductTab5Response> modelProdList){
        Tab5Adapter adapter = (Tab5Adapter)recyclerView.getAdapter();
        if (adapter != null){
            adapter.clearItems();
            adapter.addItems(modelProdList);
        }
    }

    @BindingAdapter({"adapterprodtab6"})
    public static void addProdItemsTab6(RecyclerView recyclerView, List<DataProductTab6Response> modelProdList){
        Tab6Adapter adapter = (Tab6Adapter)recyclerView.getAdapter();
        if (adapter != null){
            adapter.clearItems();
            adapter.addItems(modelProdList);
        }
    }

    @BindingAdapter({"adapterprodtab7"})
    public static void addProdItemsTab7(RecyclerView recyclerView, List<DataProductTab7Response> modelProdList){
        Tab7Adapter adapter = (Tab7Adapter)recyclerView.getAdapter();
        if (adapter != null){
            adapter.clearItems();
            adapter.addItems(modelProdList);
        }
    }

    @BindingAdapter({"adapterprodtab8"})
    public static void addProdItemsTab8(RecyclerView recyclerView, List<DataProductTab8Response> modelProdList){
        Tab8Adapter adapter = (Tab8Adapter)recyclerView.getAdapter();
        if (adapter != null){
            adapter.clearItems();
            adapter.addItems(modelProdList);
        }
    }

    @BindingAdapter({"adapterprodtab9"})
    public static void addProdItemsTab9(RecyclerView recyclerView, List<DataProductTab9Response> modelProdList){
        Tab9Adapter adapter = (Tab9Adapter)recyclerView.getAdapter();
        if (adapter != null){
            adapter.clearItems();
            adapter.addItems(modelProdList);
        }
    }

    @BindingAdapter({"adapteraddress"})
    public static void addAddressItem(RecyclerView recyclerView, List<DataAddressResponse> modelProdList){
        AddressAdapter adapter = (AddressAdapter)recyclerView.getAdapter();
        if (adapter != null){
            adapter.clearItems();
            adapter.addItems(modelProdList);
        }
    }

    @BindingAdapter({"adapterpromo"})
    public static void addPromoItem(RecyclerView recyclerView, List<DataPromoResponse> modelProdList){
        PromoAdapter adapter = (PromoAdapter)recyclerView.getAdapter();
        if (adapter != null){
            adapter.clearItems();
            adapter.addItems(modelProdList);
        }
    }

    @BindingAdapter({"adapterhistory"})
    public static void addHistoryItem(RecyclerView recyclerView, List<DataHistoryOrderResponse> modelProdList){
        HistoryOrderAdapter adapter = (HistoryOrderAdapter)recyclerView.getAdapter();
        if (adapter != null){
            adapter.clearItems();
            adapter.addItems(modelProdList);
        }
    }


    //added here adapter

}
