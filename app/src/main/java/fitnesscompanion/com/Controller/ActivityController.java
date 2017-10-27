package fitnesscompanion.com.Controller;

import android.app.ProgressDialog;
import android.content.Context;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import fitnesscompanion.com.Model.Activity;
import fitnesscompanion.com.R;

/**
 * Created by Kok Fung on 10/5/2017.
 */

public class ActivityController {
    private static final String TAG_RESULTS = "Activity";
    public static final String SERVER_ADDRESS = "https://taruc.000webhostapp.com/Fitness/";
    private ProgressDialog dialog;
    private Context context;
    private RequestQueue queue;
    private ArrayList<Activity> activities;

    public ActivityController(Context context) {
        this.context=context;
    }
    public interface VolleyCallVer{
        void onSuccess(int version);
    }
    public interface VolleyCallActivity{
        void onSuccess(ArrayList<Activity> activity);
    }

    public void getVersion(final VolleyCallVer volleyCallVer){
        queue = Volley.newRequestQueue(context);
        dialog = ProgressDialog.show(context,context.getString(R.string.sync),
                context.getString(R.string.loading_version),true,false);

        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(SERVER_ADDRESS+"getVersion.php", new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {

                try {

                    JSONObject jsonObject = (JSONObject) response.get(0);
                    dialog.dismiss();
                    volleyCallVer.onSuccess(jsonObject.getInt("ver"));
                }catch(Exception e) {
                    Toast.makeText(context, "Error:" + e.getMessage(), Toast.LENGTH_LONG).show();
                    dialog.dismiss();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Error" + error.getMessage(), Toast.LENGTH_LONG).show();
                dialog.dismiss();
            }
        });
        jsonObjectRequest.setTag(TAG_RESULTS);
        queue.add(jsonObjectRequest);

    }
    public void getActivity(final VolleyCallActivity callActivity) {
        activities = new ArrayList<Activity>();
        queue = Volley.newRequestQueue(context);
        dialog = ProgressDialog.show(context,context.getString(R.string.sync),
                context.getString(R.string.loading_activity),true,false);

        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(SERVER_ADDRESS + "getActivity.php", new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                try{

                    for(int x=0;x<response.length();x++) {
                        JSONObject jsonObject = (JSONObject) response.get(x);
                        activities.add(new Activity(jsonObject.getInt("no"),jsonObject.getString("name"),
                                jsonObject.getString("desc"),jsonObject.getInt("calories")
                                ,jsonObject.getInt("time"),jsonObject.getString("image"),
                                jsonObject.getInt("hr")));
                    }
                    dialog.dismiss();
                    callActivity.onSuccess(activities);


                }catch(Exception e) {
                    Toast.makeText(context, "Error:" + e.getMessage(), Toast.LENGTH_LONG).show();
                    dialog.dismiss();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Error" + error.getMessage(), Toast.LENGTH_LONG).show();
                dialog.dismiss();
            }
        });

        jsonObjectRequest.setShouldCache(false);
        jsonObjectRequest.setTag(TAG_RESULTS);
        queue.add(jsonObjectRequest);
    }

}
